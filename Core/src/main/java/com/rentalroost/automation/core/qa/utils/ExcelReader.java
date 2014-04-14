package com.rentalroost.automation.core.qa.utils;

import jxl.*;
import jxl.Workbook;
import jxl.read.biff.*;
import java.io.File;
import java.io.IOException;
import jxl.write.WriteException;
import java.util.Locale;
public class ExcelReader {
	private File excelFile;
	private String file = "test.xls";
	private Sheet readsheet;
	private WorkbookSettings ws;
	private LabelCell labelCellObj;
	private Workbook workbook;
	private int sheetNumber = 0;
	
	public ExcelReader(String fileName){
		file = fileName;
//		this.sheetNumber = sheetNumber;
		readFile(fileName);
	}

	private void readFile(String fileName){
        try
        {
        	ClassLoader loader = ExcelReader.class.getClassLoader();
        	
        	excelFile = new File("src/test/resources/" + fileName);
        	ws = new WorkbookSettings();
        	ws.setLocale(new Locale("er","ER"));
        	
        	// opening the existing workbook.
        	workbook = Workbook.getWorkbook(excelFile,ws); 
        	
        	readsheet = getSheet(this.sheetNumber);
        	
////        	System.out.println("Get Rows: "+ readsheet.getRows() + " Sheet Name: " + readsheet.getName());    // getting the name of sheet contained in the workbook
////        	labelCellObj = readsheet.findLabelCell("Factor");    // finding the label name Sum in the sheet
////        	System.out.println("Get Column: " + labelCellObj.getColumn() + "Get String: " + labelCellObj.getString());             // if exists displaying the name of label
//        	
////        	NumberCell nc=(NumberCell)readsheet.getCell(0,1);   // getting the NumberCell of sheet via getCell() method by doing proper casting
//        	System.out.println(readsheet.getCell(0,1).getContents());                   // printing the value contained int the cell
////        	nc=(NumberCell)readsheet.getCell(0,2);
//        	System.out.println(readsheet.getCell(0,2).getContents());
////        	NumberFormulaCell nfc=(NumberFormulaCell)readsheet.getCell(0, 3); // getting the FormulaNumberCell of sheet via getCell()method
//        	System.out.println(readsheet.getCell(0, 3).getContents());                            //printing the value
        	
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
       
        catch(BiffException e)
        {
        	e.printStackTrace();
        }		
	}
	
	/*
	 * This method is used to return the specific sheet.
	 */
	public Sheet getSheet(int sheetNumber){
		return this.workbook.getSheet(sheetNumber);
	}
	
	/*
	 * The following method is used to return the total number of sheets.
	 */
	public int getSheetCount(){
		return this.workbook.getNumberOfSheets();
	}
	
	/*
	 * The following method is used to return the total number of rows
	 * in the particular sheet.
	 */
	public int getTotoalNumberOfRows(){
		return this.readsheet.getRows();
	}
	
	/*
	 * The following method can be used to retrieve the cell object containing a
	 * a specific label.
	 */
	public LabelCell getLabelCell(String label){
		return this.readsheet.findLabelCell(label);
	}
	
	/*
	 * The following method can be used to retrieve content of a single cell.
	 */
	public String getSingleCellContent(int col, int row){
		return this.readsheet.getCell(col, row).getContents();
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Sheet getReadsheet() {
		return readsheet;
	}

	public void setReadsheet(Sheet readsheet) {
		this.readsheet = readsheet;
	}

	public WorkbookSettings getWs() {
		return ws;
	}

	public void setWs(WorkbookSettings ws) {
		this.ws = ws;
	}

	public LabelCell getLabelCellObj() {
		return labelCellObj;
	}

	public void setLabelCellObj(LabelCell labelCellObj) {
		this.labelCellObj = labelCellObj;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, WriteException, BiffException{
		// TODO Auto-generated method stub
		ExcelReader excelReader = new ExcelReader("test_.xls");
		System.out.println("Total number of rows: " + excelReader.getTotoalNumberOfRows());
		System.out.println("Content for Row 1 and Col 1: " + excelReader.getSingleCellContent(1, 3));
		
	}

	public int getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(int sheetNumber) {
		this.sheetNumber = sheetNumber;
	}
}