/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcsproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;


public class UserAccounts {
    
    static Map<String, Map<String,String> > allUsers;
            

    public static List<String> ReadFile() {
      
        String fileName = "arquivo.txt";
        String line = null;
        List<String> readLines = new ArrayList<String>();

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                readLines.add(line);
            }           
            bufferedReader.close();  
            return readLines;
        }
           
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'"); 
            return null;
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");  
            return null;
        }      
    }
    
    public static void WriteFile(){
    
        



            try{
    	String content = "This is my content which would be appended " +
        	"at the end of the specified file";
        //Specify the file name and path here
    	File file =new File("arquivo.txt");

    	/* This logic is to create the file if the
    	 * file is not already present
    	 */
    	if(!file.exists()){
    	   file.createNewFile();
    	}

    	//Here true is to append the content to file
    	FileWriter fw = new FileWriter(file,true);
    	//BufferedWriter writer give better performance
    	BufferedWriter bw = new BufferedWriter(fw);
    	bw.write(content);
    	//Closing BufferedWriter Stream
    	bw.close();

	System.out.println("Data successfully appended at the end of file");

      }catch(IOException ioe){
         System.out.println("Exception occurred:");
    	 ioe.printStackTrace();
       }
    

    
    
    
    }
    
    
    public static void LoadAllUsers(){
        
             Map<String, Map<String,String> > map = new HashMap<String, Map<String,String>>();
             Map<String,String> user = new HashMap<String, String>();
             
             
             
             List<String> lines = ReadFile();
             int found =0;
             String[] splitedString;
             for( String line: lines)  
            {  
                 
                
                if(line.contains(">>>")){
                    String nome = user.get("nome");
                    map.put(nome, user);
    
                } 
                else{
                    splitedString = line.split(":");   
                    user.put(splitedString[0],splitedString[1]);  
         
                }
            }
             allUsers = map;
             
    }
    
    
    public  static Map<String, Map<String,String> > GetallUsers(){
        return allUsers;
    }
}
