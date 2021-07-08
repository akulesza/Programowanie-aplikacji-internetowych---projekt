package diffy.demo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;


public class Meal {
    public String food;
    public double quantity;


    public int getIndexTabela_Jedzenia() throws IOException {
        int index = 0;
        File excelFile = new File("Tabela_jedzenia.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator rowIt = sheet.iterator();

        while(rowIt.hasNext()) {
            Row row = (Row)rowIt.next();
            Iterator cellIt = row.cellIterator();

            while(cellIt.hasNext()) {
                Cell cell = (Cell)cellIt.next();
                if (cell.toString().equals(food)) {
                    index = cell.getRowIndex();
                }
            }
        }

        workbook.close();
        fis.close();
        return index;
    }

    public String[] getWholeRowTabela_Jedzenia(int index) throws IOException {
        String[] tbl = new String[5];
        int counter = 0;
        File excelFile = new File("Tabela_jedzenia.xlsx");
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

    public String[] searchTabela_Jedzenia() {
        try {
            int index = this.getIndexTabela_Jedzenia();
            if (index == 0) {
                System.out.println("No such row exists.");
                return null;
            } else {
                String[] tbl = this.getWholeRowTabela_Jedzenia(index);
                return tbl;
            }
        } catch (IOException var4) {
            System.err.print(var4);
            return null;
        }
    }

    public MealWithCalories toMealWithCalories() {
        String[] tbl = this.searchTabela_Jedzenia();
        double basic = quantity / 100.0F;
        double kcal = Float.parseFloat(tbl[1]) * basic;
        double protein = Float.parseFloat(tbl[2]) * basic;
        double fat = Float.parseFloat(tbl[3]) * basic;
        double carbon = Float.parseFloat(tbl[4]) * basic;
        return new MealWithCalories(food, Math.round(kcal), Math.round(protein*100.0)/100.0, Math.round(carbon*100.0)/100.0, Math.round(fat*100.0)/100.0);
    }
}
