package io.pragra.framework.data;

import io.pragra.framework.config.Config;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    private Workbook workbook;
    private static ExcelReader reader;
    private static List<Object[]> data = new ArrayList<>();

    private ExcelReader(){

        try {
            InputStream stream = new FileInputStream(Paths.get(Config.getProperty("master.excel")).toFile());
            workbook = new XSSFWorkbook(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public synchronized static List<Object[]> getDataFromSheet(String sheetName, boolean skipHeader){
        if( reader == null) {
            reader = new ExcelReader();
        }
        Sheet sheet = reader.workbook.getSheet(sheetName);

        Iterator<Row> rowIterator = sheet.rowIterator();

        if( rowIterator.hasNext() && skipHeader ){
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            List<Object> cellData = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if( cell.getCellType() == CellType.STRING) {
                    String val = cell.getStringCellValue();
                    cellData.add(val);
                }
                if( cell.getCellType() == CellType.BOOLEAN) {
                    Boolean val = cell.getBooleanCellValue();
                    cellData.add(val);
                }
                if( cell.getCellType() == CellType.NUMERIC) {
                    Double val = cell.getNumericCellValue();
                    cellData.add(val);
                }

            }
            if(cellData.size()>0) {
                data.add(cellData.toArray());
            }

        }
        return data;

    }
}
