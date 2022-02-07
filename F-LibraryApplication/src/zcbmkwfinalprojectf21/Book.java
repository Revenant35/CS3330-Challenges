
package zcbmkwfinalprojectf21;

public class Book {
    
    private String title, author, date;
    
    
    public Book(){
        this.title = this.author = this.date = "";
    }
    
    public Book(String title){
        this();
        this.title = title;
    }
    
    public Book(String title, String author){
        this(title);
        this.author = author;
    }
    
    public Book(String title, String author, String date){
        this(title, author);
        this.date = date;
    }
    
    
    
    protected String getTitle(){
        return this.title;
    }
    
    protected String getAuthor(){
        return this.author;
    }
    
    protected String getDate(){
        return this.date;
    }
    
    protected boolean substringInBook(String query){
        return this.getReadableLabel().contains(query);
    }
    
    public String getReadableLabel(){
        return (this.title + " " + this.author + " " + this.date);
    }
    
    public void parseLabel(String str){
        this.title = str.split(" ")[0];
        this.author = str.split(" ")[1];
        this.date = str.split(" ")[2];
    }
}
