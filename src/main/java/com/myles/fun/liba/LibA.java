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
            for (LibAEntry entry : mData){
                System.out.println(entry);
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
            for (LibAEntry entry : mData){
                System.out.println(entry.toStringFull());
            }
        }
    }
    
    private void printHeader(){
        System.out.println("Category   Description      Detail");
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
            String category = this.mCategory;
            String description = this.mDescription;
            String detail = this.mDetail.length()>15?this.mDetail.substring(0,15)+"...":this.mDetail;
            return category + " " + description + " " + detail; 
        }

        public String toStringFull(){
            String category = this.mCategory;
            String description = this.mDescription;
            String detail = this.mDetail;
            return category + " " + description + " " + detail; 
        }
    }
}
	