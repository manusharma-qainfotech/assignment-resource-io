package com.qainfotech.tap.training.resourceio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    
    public String getOptionValue(String optionKey) throws IOException {
   //     throw new UnsupportedOperationException("Not implemented.");
        //String path = "D:\\assignment-resource-io-master\src\test\resources\options.properties";
         FileReader fr =new FileReader(new File("D:\\\\assignment-resource-io-master\\src\\test\\resources\\options.properties"));
          
          Properties p=new Properties();  
          p.load(fr);  
           
         String s = p.getProperty(optionKey);
         return s;
            
         
          
         }

    public void addOption(String optionKey, Object optionValue) throws IOException {
    //    throw new UnsupportedOperationException("Not implemented.");
      FileWriter fw = new FileWriter(new File("D:\\\\assignment-resource-io-master\\src\\test\\resources\\options.properties"),true);
    Properties p=new Properties();  
    String s = optionValue.toString();
   // System.out.println(s);
    p.setProperty(optionKey,s);    
    p.store(fw,null);
    fw.close();
    //p.store(new FileWriter("info.properties"),"Javatpoint Properties Example");  
    
    }
}
