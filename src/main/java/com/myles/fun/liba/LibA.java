package com.myles.fun.liba;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.util.CellReference;

import java.util.*;
import java.lang.Integer;
import java.lang.Exception;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class LibA {
    
    private ArrayList<LibAEntry> mData;
    private String mExcelAddress;
    
    public LibA(){
        System.out.println( "<LibA utililty>" );
        this.mData = new ArrayList<LibAEntry>();
    }
    
    public void load(String filePath){
        this.mExcelAddress = filePath;
        try{
            Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell0 = row.getCell(0);
                Cell cell1 = row.getCell(1);
                Cell cell2 = row.getCell(2);
                if (cell0 != null && cell1 != null && cell2 != null){
                    if ( cell0.toString().compareTo("#") != 0 ){
                        LibAEntry entry = new LibAEntry();
                        entry.setCategory(cell0.toString());
                        entry.setDescription(cell1.toString());
                        entry.setDetail(cell2.toString());
                        this.mData.add(entry);
                    }
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void show(){
        this.printHeader();
        if (this.mData.isEmpty()){
            System.out.println("--nothin--");
        }else{
            for (int i = 0; i < mData.size() ; i++ ){
	        System.out.print(i + "\t");
                System.out.println(mData.get(i).toString());
            }
        }
    }
    
    public void show(int index){
        this.printHeader();
        System.out.println("in show(int) --");
    }
    
    public void showfull(){
        this.printHeader();
        if (this.mData.isEmpty()){
            System.out.println("--nothin--");
        }else{
            for (int i = 0; i < mData.size() ; i++ ){
	        System.out.print(i + "\t");
                System.out.println(mData.get(i).toStringFull());
            }
        }
    }

    public void help(){
        System.out.println("Options:");
        System.out.println("\t-help\tthis help text.");
        System.out.println("\t-show {filename}\tshow an xlsx format brief content.");
        System.out.println("\t-show {filename} {index}\tshow the index row of an xlsx format content.");
        System.out.println("\t-showfull {filename}\tshow an xlsx format full content.");
    }
    
    private void printHeader(){
        System.out.print("Ind     ");
        System.out.print("Category       ");
        System.out.print("Description                   ");
        System.out.println("Detail");
        System.out.print("------- ");
        System.out.print("-------------- ");
        System.out.print("----------------------------- ");
        System.out.println("------------------------ ");
    }
    
    /**
     * Inner Class : Data Structure of Item Entry
     */
    public class LibAEntry {
        
        private String mCategory;
        private String mDescription;
        private String mDetail;
        
        public String getCategory() {return this.mCategory;}
        public String getDescription() {return this.mDescription;}
        public String getDetail() {return this.mDetail;}
        
        public void setCategory(String category){this.mCategory = category;}
        public void setDescription(String desc){this.mDescription = desc;}
        public void setDetail(String detail){this.mDetail = detail;}
        
        public String toString(){
	    int categoryLength = this.mCategory.length();
            String categoryString = toFixLengthString(this.mCategory, 14);

	    int descriptionLength = this.mDescription.length();
            String descriptionString = toFixLengthString(this.mDescription, 29);

	    int detailLength = this.mDetail.length();
            String detailString = toFixLengthString(this.mDetail, 24);
            //String detail = this.mDetail.length()>15?this.mDetail.substring(0,15)+"...":this.mDetail;

            return categoryString + " " + descriptionString + " " + detailString; 
        }

        public String toStringFull(){
            String category = this.mCategory;
            String description = this.mDescription;
            String detail = this.mDetail;
            return category + " " + description + " " + detail; 
        }

	private String toFixLengthString(String str, int len){
	    String resultString;
	    if ( str.length() > len - 3 ){
	        resultString = str.replaceAll("\\n+", "").substring(0, len - 3) + "...";
	    }else{
	        resultString = str.replaceAll("\\n+", "");
	        for (int i = 0; i < len - str.length() ; i++){
	            resultString = resultString + " ";
		}
	    }
            return resultString;
	}
    }
}
	
