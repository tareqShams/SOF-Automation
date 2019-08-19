package fawry.sofAutomation.utils.strategy;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;

import fawry.sofAutomation.utils.ExcelHandler;

public class ExcelTestData implements TestDataStrategy{


                public ArrayList<ArrayList<Object>> loadTestData(String filePathAndSheetNo) {
                                
                                String filePath = filePathAndSheetNo.split(";")[0];
                                String sheetNo = filePathAndSheetNo.split(";")[1];
                                
                                ArrayList<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>();
                                
                                Iterator<Row> rows = ExcelHandler.loadExcelSheetRows(filePath, Integer.parseInt(sheetNo));
                                
                                //get get header columns number
                                short headerColumnsNum = rows.next().getLastCellNum();
                                
                                while(rows.hasNext())
                                {
                                                Row row = rows.next();
                                                
                                                
                                                ArrayList<Object> cellsObjects = new ArrayList<Object>();
                                                
                                                ArrayList<String> rowCells = ExcelHandler.getExcelRowCells(row, headerColumnsNum);
                                                for (int i=0; i < rowCells.size(); i++)
                                                {
                                                                Object cell = new Object();
                                                                cell = rowCells.get(i);
                                                                cellsObjects.add(cell);
                                                }
                                                results.add(cellsObjects);
                                }
                                
                                return results;
                }

}
