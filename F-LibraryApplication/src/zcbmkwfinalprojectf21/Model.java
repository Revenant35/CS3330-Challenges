
package zcbmkwfinalprojectf21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Model {
    
    private static String userID;
    
    private ArrayList<Book> cart;
    private ArrayList<Book> checkedOutBooks;
    private ArrayList<Book> library;
    
    final private String LIBRARY_PATH = "src/zcbmkwFinalProjectF21/LibraryFiles/books.txt";
    final private String USER_BOOKS = "src/zcbmkwFinalProjectF21/UserBooks/";
    final private String USER_CARTS = "src/zcbmkwFinalProjectF21/UserCarts/";
    
    public Model(){
        this.checkedOutBooks = populateFromFile("src/zcbmkwFinalProjectF21/UserBooks/" + userID + ".txt");
        this.cart = populateFromFile("src/zcbmkwFinalProjectF21/UserCarts/" + userID + ".txt");
        this.library = populateFromFile("src/zcbmkwFinalProjectF21/LibraryFiles/books.txt");
    }
    
    public static void setUserID(String userID){
        Model.userID = userID;
    }
    
    public ArrayList<Book> populateFromFile(String fileName){
        ArrayList<Book> books = new ArrayList<>();
        try{
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            Pattern pattern = Pattern.compile("\"([^\"]*)\"");
            while(reader.hasNextLine()){
                String[] inputArray = new String[3];
                String input = reader.nextLine();
                Matcher matcher = pattern.matcher(input);
                for(int i = 0; matcher.find(); i++){
                    inputArray[i] = matcher.group().replace("\"", "");
                }
                books.add(new Book(inputArray[0], inputArray[1], inputArray[2]));
            }
        } catch(FileNotFoundException e){
            System.err.println(e);
        }
        return books;
    }
    
    
    public ArrayList<Book> getCart(){
        return this.cart;
    }
    
    public ArrayList<Book> getCheckedOutBooks(){
        return this.checkedOutBooks;
    }
    
    public ArrayList<Book> getLibrary(){
        return this.library;
    }
    
    public String getUserID(){
        return userID;
    }
    
    
    
    public boolean addToCart(Book book){
        
        if (book != null)
            if(library.remove(book))
                cart.add(book);
        
        return cart.contains(book);
    }
    
    public boolean addToCart(ArrayList<Book> books){
        if (!books.isEmpty())
            books.forEach((book) -> {
                addToCart(book);
        });
        
        return cart.containsAll(books);
    }
    
    public boolean removeFromCart(Book book){
        
        if (book != null)
            if(cart.remove(book))
                library.add(book);
        
        return library.contains(book);
    }
    
    public boolean removeFromCart(ArrayList<Book> books){
        if (!books.isEmpty())
            books.forEach((book) -> {
                removeFromCart(book);
        });
        
        return library.containsAll(books);
    }
    
    
    public int emptyCart(){
        if(!cart.isEmpty()){
            cart.clear();
            if(cart.isEmpty())
                return 1;
            else 
                return 0;
        } 
        return -1;
    }
    
    public int checkOut(){
        if(!cart.isEmpty()){
            cart.forEach((book) -> checkedOutBooks.add(book));
            
            if(checkedOutBooks.containsAll(cart)){
                cart.clear();
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }
    
    public boolean returnBooks(){
        if(checkedOutBooks.isEmpty()){
            checkedOutBooks.forEach((book) -> {
                library.add(book);
            });
            if(library.containsAll(checkedOutBooks)){
                checkedOutBooks.clear();
                return true;
            }
        }
        return false;
    }
    
    public boolean returnBook(Book book){
        if(!checkedOutBooks.isEmpty()){
            if(checkedOutBooks.remove(book)){
                library.add(book);
                return true;
            }
        }
        return false;
    }
    
    public Book searchLibrary(String query){
        
        for(int i = 0; i < library.size(); i++){
            if(library.get(i).substringInBook(query))
                return library.get(i);
        }
        
        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).substringInBook(query))
                return cart.get(i);
        }
        
        for(int i = 0; i < checkedOutBooks.size(); i++){
            if(checkedOutBooks.get(i).substringInBook(query))
                return checkedOutBooks.get(i);
        }
        
        System.out.println("Could not find book " + query);
        return null;
    }
    
    public void saveBooks(){
        SaveToFile.saveToFile(USER_BOOKS + this.getUserID() + ".txt", this.getCheckedOutBooks());
        SaveToFile.saveToFile(USER_CARTS + this.getUserID() + ".txt", this.getCart());
        SaveToFile.saveToFile(LIBRARY_PATH, this.getLibrary());
    }
}
