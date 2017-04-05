package com.myles.fun.liba;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class App{
    public static void main( String[] args ){
        LibA liba = new LibA();
        String excelAddress = "";
            if (args.length == 1){
	        
                if (args[0].compareTo("-help") == 0){
                    liba.help();           //liba -help
		}

	    }else if (args.length == 2){
                
                if (args[0].compareTo("-show") == 0){
                    liba.load(args[1]);
                    liba.show();           //liba -show file
		}

                if (args[0].compareTo("-showfull") == 0){
                    liba.load(args[1]);
                    liba.showfull();       //liba -showfull file
		}
                
            }else if (args.length == 3){
                
                if (args[0].compareTo("-show") == 0){
                    liba.load(args[1]);
                    liba.show(Integer.parseInt(args[2])); //liba -show file int
                }
                
            }else{
                System.out.println("Args format wrong.");
            }
        
    }
}
