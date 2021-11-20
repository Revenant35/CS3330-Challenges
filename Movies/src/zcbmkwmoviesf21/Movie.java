
package zcbmkwmoviesf21;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.NumberFormat;

public class Movie {
    private String name, director, summary, rating, runtime;
    private double revenue;
    private Genre genre;
    private Calendar releaseDate;
    private int version;
    static public int numOfMovies = 0;
    
    // Constructors (chained)
    public Movie(){
        this.name = "";
        this.director = "";
        this.version = 0;
        this.releaseDate = Calendar.getInstance();
        this.releaseDate.set(1888, Calendar.OCTOBER, 14);
        Movie.numOfMovies++;
    }
    public Movie(String name, 
                String director, 
                String runtime){
        
        this();
        this.name = name;
        this.director = director;
        this.runtime = runtime;
    }
    public Movie(String name, 
                String director, 
                String summary, 
                Genre genre, 
                Calendar releaseDate, 
                String runtime){
        
        this(name, director, runtime);
        this.version = 1;
        this.summary = summary;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }
    public Movie(String name, 
                String director, 
                String summary, 
                String rating, 
                double revenue, 
                Genre genre, 
                Calendar releaseDate, 
                String runtime){
        
        this(name, director, summary, genre, releaseDate, runtime);
        this.rating = rating;
        this.revenue = revenue;
    }
    
    // Method to increment the version, tied to setters currently, hidder from user
    private void incrementVersion(){
        this.version++;
    }
    
    // Setters (Ordered the same way the constructor is parameterized)
    public void setName(String name){
        this.name = name;
        incrementVersion();
    }
    public void setDirector(String director){
        this.director = director;
        incrementVersion();
    }
    public void setSummary(String summary){
        this.summary = summary;
        incrementVersion();
    }
    public void setRating(String rating){
        this.rating = rating;
        incrementVersion();
    }
    public void setRevenue(double revenue){
        this.revenue = revenue;
    }
    public void setGenre(Genre genre){
        this.genre = genre;
        incrementVersion();
    }
    public void setReleaseDate(Calendar releaseDate){
        this.releaseDate = releaseDate;
        incrementVersion();
    }
    public void setRuntime(String runtime){
        this.runtime = runtime;
    }
    
    // Getters (Ordered the same way as the Setters)
    // https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html
    // https://docs.oracle.com/javase/7/docs/api/java/text/NumberFormat.html
    public String getName(){
        return this.name;
    }
    public String getDirector(){
        return this.director;
    }
    public String getSummary(){
        return this.summary;
    }
    public String getRating(){
        return this.rating;
    }
    public double getRevenue(){
        return this.revenue;
    }
    public String getRevenueString(){
        
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return (dollarFormat.format(this.revenue).replace(".00", ""));
    }
    public Genre getGenre(){
        return this.genre;
    }
    public Calendar getReleaseDate(){
        return this.releaseDate;
    }
    public String getReleaseDateString(){
        DateFormat df = new SimpleDateFormat("LLL dd',' YYYY");
        String formattedTime = df.format(this.releaseDate.getTime());
        return formattedTime;
    }
    public String getRuntime(){
        return this.runtime;
    }
    public int getVersion(){
        return this.version;
    }
    
    // Creates and returns a Date object that contains the time the movie will end
    // https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html
    // https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
    private Date endOfMovie(){
        Calendar movieEnd = Calendar.getInstance();
        movieEnd.add(Calendar.HOUR, Integer.parseInt(this.runtime.split("h")[0]));
        movieEnd.add(Calendar.MINUTE, Integer.parseInt(this.runtime.split("h")[1].split("m")[0]));
        return movieEnd.getTime();
    }
    
    // Prints the name, runtime, and endtime of the movie
    // https://docs.oracle.com/javase/8/docs/api/java/text/DateFormat.html (parent class of date format)
    // https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
    public void playMovie(){
        System.out.printf("The runtime of %s is %s\n", this.name, this.runtime);
        DateFormat df = new SimpleDateFormat("dd-MM-YYYY h:mm a");
        String formattedTime = df.format(endOfMovie());
        System.out.println(this.name + " will end at " + formattedTime);
    } 
    
    public void print(){
        System.out.printf("Name: %s\n", this.getName());
        System.out.printf("Director: %s\n", this.getDirector());
        System.out.printf("Summary: %s\n", this.getSummary());
        System.out.printf("Genre: %s\n", this.getGenre());
        System.out.printf("Rating: %s\n", this.getRating());
        System.out.println("Revenue: " + this.getRevenueString());
        System.out.println("Release Date: " + this.getReleaseDateString());
        System.out.printf("Runtime: %s\n", this.getRuntime());
        System.out.printf("Version: %d\n", this.getVersion());
        this.playMovie();
        System.out.println("");
    }
}
