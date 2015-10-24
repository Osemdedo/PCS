
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

/*
    Esta classe tem como função manipular um arquivo de texto(txt) para gerenciar os registros de todos 
    os jogadores.
    Esta função é realizada por uma estrutura de tags no documento(arquivo.txt) e por uma estrutura de 
    dicionarios na classe UserAccounts.
    Tal estrutura de Dicionario tem a seguinte caracteristica:
    Map<String, Map<String,String> >
    Onde a sua key é o nome do usuário e leva a outro dicionário
    Neste outro dicionário tem-se como key a tag que foi definida no file e sua definição como conteúdo.

    O Documento é formado por tag, qualquer tag é aceitavel, porém algumas regas devem ser tomadas
    Regras:
    A primeira tag de um usuário deve ser seu nome e depois de todas as tags 
    a string ">>>" deve ser adicionada para a separacção dos elementos

*/
public class UserAccounts {
    
    // armazena todos os usuarios cadastrados
    // é definido na função LoadAllUsers
    static Map<String, Map<String,String> > allUsers;
            

    // Le o TxT e retorna uma lista de String contendo todas as linhas fo arquivo 
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
    
    
    // essa função deve ser chamada quando se quer Adicionar um novo Usuário ou Editar um 
    //usuário existente.
    // Recebe um dicionario com todas as caracteristicas 
    public static void ChangeArquivo(Map<String,String> userdata){
        if(allUsers.containsKey(userdata)){
            AddUser(userdata);
        }
        else{
            EditUser(userdata);
        }
    }
    
    //Recebe o dicionario com os dados do usuario e insere no documento
    //Ao editar um usuario, ele é removido de sua posição e adicionado no final com os novos valores
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
    
    //Recebe os dados do usuria por um dicionário e adiciona-o no final do documento
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
    
    //Carrega para o Dicinonario allUsers todos os usuario do file
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
      
    //Retorna o dicionario com todos os usuarios
    public  static Map<String, Map<String,String> > GetallUsers(){
        return allUsers;
    }
}
