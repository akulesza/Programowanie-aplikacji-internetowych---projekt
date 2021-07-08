package diffy.demo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Activity {
    public String name;
    public double t;


    public int getIndexTabela_Aktywnosci() throws IOException {
        int index = 0;
        File excelFile = new File("Tabela_aktywnosci.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator rowIt = sheet.iterator();

        while(rowIt.hasNext()) {
            Row row = (Row)rowIt.next();
            Iterator cellIt = row.cellIterator();

            while(cellIt.hasNext()) {
                Cell cell = (Cell)cellIt.next();
                if (cell.toString().equals(name)) {
                    index = cell.getRowIndex();
                }
            }
        }

        workbook.close();
        fis.close();
        return index;
    }

    public String[] getWholeRowTabela_Aktywnosci(int index) throws IOException {
        String[] tbl = new String[2];
        int counter = 0;
        File excelFile = new File("Tabela_aktywnosci.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator rowIt = sheet.iterator();

        while(rowIt.hasNext()) {
            Row row = (Row)rowIt.next();
            Iterator cellIt = row.cellIterator();

            while(cellIt.hasNext()) {
                Cell cell = (Cell)cellIt.next();
                if (cell.getRowIndex() == index) {
                    tbl[counter] = cell.toString();
                    ++counter;
                }
            }
        }

        workbook.close();
        fis.close();
        return tbl;
    }
    public String[] searchTabela_Aktywnosci() {
        try {
            int index = this.getIndexTabela_Aktywnosci();
            if (index == 0) {
                System.out.println("No such row exists.");
                return null;
            } else {
                String[] tbl = this.getWholeRowTabela_Aktywnosci(index);
                return tbl;
            }
        } catch (IOException var4) {
            System.err.print(var4);
            return null;
        }
    }
    public ActivityWithKcal toActivityWithKcal() {
        String[] tbl = this.searchTabela_Aktywnosci();
        double basic = Float.parseFloat(tbl[1]);
        double kcal = basic * t/60;
        return new ActivityWithKcal(name, t, kcal);
    }
}