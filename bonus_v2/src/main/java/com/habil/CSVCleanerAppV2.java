package com.habil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.opencsv.CSVReader;

public class CSVCleanerAppV2
{
    public static void main(String[] args) throws IOException, FileNotFoundException
    {
        Pattern emailPattern = Pattern.compile("^[\\w-.]+@[\\w-.]+\\.[a-zA-Z]{2,}$");
        Pattern mobilPattern = Pattern.compile("^(?:\\d{3}|\\d{1})\\d{9}$");
        Pattern idPattern = Pattern.compile("^[\\d]+$");

        Workbook workbook = new SXSSFWorkbook();
        ((SXSSFWorkbook) workbook).setCompressTempFiles(true);
        Sheet maleSheet = workbook.createSheet("male_member_details");
        Sheet femaleSheet = workbook.createSheet("female_member_details");
        Sheet noGenderSheet = workbook.createSheet("gender_missing_member_details");
        Sheet errSheet = workbook.createSheet("err_member_details");

        BlockingQueue<Object[]> csvQueue = new LinkedBlockingQueue<>(500);
        final Object[] POISON_PILL = new Object[0];

        Thread csvProcessor = new Thread(() -> 
        {

            try (CSVReader reader = new CSVReader(new FileReader("member_details.csv")))
            {
                String[] line;
                boolean isFirst = true;
                
                while ((line = reader.readNext()) != null)
                {
                    if (isFirst)
                    {
                        isFirst = false;
                        continue;
                    }

                    
                    String id = line[0].replaceAll("\\s+", "");
                    String name = line[1].replaceAll("\\s+", "");
                    String mobileNumber = line[2].replaceAll("\\s+", "");
                    String emailAddress = line[3].replaceAll("\\s+", "");
                    String gender = line[4].replaceAll("\\s+", "");

                    Matcher emailMatcher = emailPattern.matcher(emailAddress);
                    Matcher mobileMatcher = mobilPattern.matcher(mobileNumber);
                    Matcher idMatcher = idPattern.matcher(id);

                    // Object[] sanitisedData = { id, name, mobileNumber, emailAddress, gender };

                    if (emailMatcher.matches() && mobileMatcher.matches() && idMatcher.matches() && !gender.trim().isEmpty())
                    {
                        Object[] sanitisedData = { id, name, mobileNumber, emailAddress, gender, (gender.equalsIgnoreCase("male") ? maleSheet : femaleSheet)};
                        csvQueue.put(sanitisedData);
                    }
                    else if (emailMatcher.matches() && mobileMatcher.matches() && idMatcher.matches() && gender.trim().isEmpty())
                    {
                        Object[] sanitisedData = { id, name, mobileNumber, emailAddress, gender, noGenderSheet};
                        csvQueue.put(sanitisedData);
                    }
                    else if (!emailMatcher.matches() || !mobileMatcher.matches() || !idMatcher.matches())
                    {
                        Object[] sanitisedData = { id, name, mobileNumber, emailAddress, gender, errSheet};
                        csvQueue.put(sanitisedData);
                    }
                }
            }

            catch (Exception e)
            {
               e.printStackTrace();
            }
            finally
            {
                try
                {
                    csvQueue.put(POISON_PILL);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }

            }

        });

        Thread xlsWriter = new Thread(() -> 
        {
            try
            {
                while (true)
                {
                    Object[] line = csvQueue.take();
                    if (line == POISON_PILL) break;

                    Sheet currentSheet = (Sheet) line[line.length - 1];
                    synchronized (currentSheet)
                    {
                        int rowNum = currentSheet.getLastRowNum() + 1;
                        Row row = currentSheet.createRow(rowNum);
                        for (int i = 0; i < (line.length - 1); i++)
                        {
                            Cell cell = row.createCell(i);
                            cell.setCellValue((String) line[i]);
                        }
                    }

                }

            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
            
        });

        csvProcessor.start();
        xlsWriter.start();

        try
        {
            csvProcessor.join();
            xlsWriter.join();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        try (FileOutputStream fos = new FileOutputStream("member_details.xlsx"))
        {
            workbook.write(fos);
            ((SXSSFWorkbook) workbook).dispose();
            workbook.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        
    }
}
