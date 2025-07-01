package com.habil;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class WriteXL implements Callable<Void>
{
    String[] rowData;
    Sheet currentSheet;
    AtomicInteger rowCounter;

    public WriteXL(String[] rowData, AtomicInteger rowCounter, Sheet currentSheet)
    {
        this.rowData = rowData;
        this.currentSheet = currentSheet;
        this.rowCounter = rowCounter;
    }

    @Override
    public Void call()
    {
        int rownum = rowCounter.getAndIncrement();
        synchronized (currentSheet)
        {
            // int rownum = currentSheet.getLastRowNum();
            // int rownum = currentSheet.getPhysicalNumberOfRows();
            Row row = currentSheet.createRow(rownum);
            for (int i = 0; i < rowData.length; i++)
            {
                Cell cell = row.createCell(i);
                cell.setCellValue(rowData[i]);
            }
        }

        return null;
    }
}
