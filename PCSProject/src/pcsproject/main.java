/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcsproject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matheus
 */
public class main {
    
    
    public static void main(String[] args) {

        
        UserAccounts.LoadAllUsers();
        
        
        
        Map<String,String> user = new HashMap<String, String>();
        user.put("nome", "iiih");
        user.put("kkkkkkkkkkkk", "asdh");
        user.put("a3a4h", "ghgghh");
        user.put("6aa4h", "kjkjkjkh");
        UserAccounts.ChangeArquivo(user);
    }
    
}
