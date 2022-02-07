/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstjavaproject;

/**
 *
 * @author Zach Brown (ZCBMKW)
 */
public class MyFirstJavaProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        for(int i = 0; i < 100; i++){
//            System.out.println(i); 
            
            if(i == 99){
                System.out.print(i);
            }
            else {
                System.out.print(i + ",");
            }
        }
        System.out.println();
        
        System.out.println(weAreHungry());
    }
    
    public static String weAreHungry(){
        String firstString = "Yo yo yo"; 
        
        String concat = firstString.concat(" we are hungry");
        
        //System.out.println(concat); 
        
        String replace = " ladies and gentlemen".replace("and", "/"); 
        
        String result = concat.concat(replace); 
        
        return result; 
        
        //return concat.concat(replace);
    }
    
}
