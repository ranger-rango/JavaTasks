package com.habil;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;

public class CleanCSV {
    public static void cleanCSV() throws IOException {
        Pattern emailPattern = Pattern.compile("^[\\w-.]+@[\\w-.]+\\.[a-zA-Z]{2,}$");
        Pattern mobilPattern = Pattern.compile("^(?:\\d{3}|\\d{1})\\d{9}$");
        Pattern idPattern = Pattern.compile("^[\\d]+$");
        AtomicInteger maleCounter = new AtomicInteger();
        AtomicInteger femaleCounter = new AtomicInteger();
        AtomicInteger noGenderCounter = new AtomicInteger();
        AtomicInteger errCounter = new AtomicInteger();

        Workbook workbook = new XSSFWorkbook();
        Sheet maleSheet = workbook.createSheet("male_member_details");
        Sheet femaleSheet = workbook.createSheet("female_member_details");
        Sheet noGenderSheet = workbook.createSheet("gender_missing_member_details");
        Sheet errSheet = workbook.createSheet("err_member_details");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try (CSVReader reader = new CSVReader(new FileReader("member_details.csv"))) {
            String[] line;
            boolean isFirst = true;
            while ((line = reader.readNext()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                // id, name, mobile_number, email_address, gender
                String id = line[0].replaceAll("\\s+", "");
                String name = line[1].replaceAll("\\s+", "");
                String mobileNumber = line[2].replaceAll("\\s+", "");
                String emailAddress = line[3].replaceAll("\\s+", "");
                String gender = line[4].replaceAll("\\s+", "");

                Matcher emailMatcher = emailPattern.matcher(emailAddress);
                Matcher mobileMatcher = mobilPattern.matcher(mobileNumber);
                Matcher idMatcher = idPattern.matcher(id);

                String[] sanitisedData = { id, name, mobileNumber, emailAddress, gender };

                if (emailMatcher.matches() && mobileMatcher.matches() && idMatcher.matches()
                        && !gender.trim().isEmpty()) {
                    executorService.submit(
                            new WriteXL(sanitisedData, gender.equalsIgnoreCase("male") ? maleCounter : femaleCounter,
                                    gender.equalsIgnoreCase("male") ? maleSheet : femaleSheet));
                } else if (emailMatcher.matches() && mobileMatcher.matches() && idMatcher.matches()
                        && gender.trim().isEmpty()) {
                    executorService.submit(new WriteXL(sanitisedData, noGenderCounter, noGenderSheet));
                } else if (!emailMatcher.matches() || !mobileMatcher.matches() || !idMatcher.matches()) {
                    executorService.submit(new WriteXL(sanitisedData, errCounter, errSheet));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.err.println("Executor did not terminate in time");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream("member_details.xlsx")) {
            workbook.write(fos);
        }
        workbook.close();
    }
}
