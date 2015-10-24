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
    
    
    
    public static void ChangeArquivo(Map<String,String> userdata){
        if(allUsers.containsKey(userdata)){
            AddUser(userdata);
        }
        else{
            EditUser(userdata);
        }
    }
    
    public static void EditUser(Map<String,String> userdata){
       
        
        
        File fileName = new File("arquivo.txt");
        File tempFile = new File("temp.txt");
        String line = null;
        int aux=0;
        String[] splitLine;

        try {
            
            
            FileWriter fileWriter = new FileWriter(tempFile);
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            
            
            
            while((line = bufferedReader.readLine()) != null) {
                
                
                if(line.startsWith("nome")){
                    splitLine = line.split(":");
                    if(splitLine[1].equals(userdata.get("nome"))){
                        
                        System.out.println("wooow");
                        aux=1;
                        
                    }
                        
                }
                
                
                if(aux==1){  
                    System.out.println(line);
                    //System.out.println("line: "+line+ " nome"+userdata.get("nome"));
                    
                    if(line.contains(">>>")){
                        System.out.println("aquiiiiii");
                        aux=0;
                    }
                    continue;
                }
                else{
                    //System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                }
                
                
                    
            }           
             
            
            
            bw.close();
            bufferedReader.close();
            fileName.delete();
            boolean successful = tempFile.renameTo(fileName);
        }
           
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'"); 
            
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");  
            
        }      
       
    }
    
    public static void AddUser(Map<String,String> userdata){
    
        
        try{
            
            File file =new File("arquivo.txt");

            if(!file.exists()){
               file.createNewFile();
            }

            //Here true is to append the content to file
            FileWriter fw = new FileWriter(file,true);
            //BufferedWriter writer give better performance
            BufferedWriter bw = new BufferedWriter(fw);
            
            for( String key : userdata.keySet()){
            bw.newLine();   
            bw.write(key+":"+userdata.get(key));
            //System.out.println(userdata.get(key));              
            }
            bw.newLine();   
            bw.write(">>>");        
            //Closing BufferedWriter Stream
            bw.close();
            System.out.println("Salvo com sucesso no arquivo");

        }
        catch(IOException ioe){
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
