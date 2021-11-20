
package zcbmkwmoviesf21;

import java.util.Calendar;

public class ZcbmkwMoviesF21 {

    public static void main(String[] args) {
        
        
        Calendar movie1ReleaseDate = Calendar.getInstance();
        movie1ReleaseDate.set(2021, 7, 9);
        Movie movie1 = new Movie("BLACK WIDOW", "Cate Shortland", "2h29m");
        movie1.setGenre(Genre.ACTION);
        movie1.setSummary("Natasha  Romanoff,  aka  Black  Widow, confronts the darker "
                + "parts of her ledger when a dangerous conspiracy with ties to her past "
                + "arises. Pursued by a force that will stop at nothing to bring her down, "
                + "Natasha must deal with her history as a spy, and the broken relationships "
                + "left in her wake long before she became an Avenger.");
        movie1.setRating("PG-13");
        movie1.setRevenue(181500000);
        movie1.setReleaseDate(movie1ReleaseDate);
        
        
        Calendar movie2ReleaseDate = Calendar.getInstance();
        movie2ReleaseDate.set(2020, 10, 25);
        Movie movie2 = new Movie("TOYS OF TERROR", 
                                "Nicholar Verso", 
                                "Evil toys magically come to life to terrorize a couple and their children inside a secluded mansion.",
                                "R",
                                0,
                                Genre.HORROR,
                                movie2ReleaseDate,
                                "1h29m");
        
        
        Calendar movie3ReleaseDate = Calendar.getInstance();
        movie3ReleaseDate.set(2019, 7, 27);
        Movie movie3 = new Movie("THE LION KING",
                                "Jon Favreau",
                                "After the murder of his father, a young lion prince flees his kingdom "
                                    + "only to learn the true meaning of responsibility and bravery.",
                                Genre.KIDS,
                                movie3ReleaseDate,
                                "1h58m"
                                );
        movie3.setRating("PG");
        movie3.setRevenue(543600000);
        
        
        Calendar movie4ReleaseDate = Calendar.getInstance();
        movie4ReleaseDate.set(2012, 12, 21);
        Movie movie4 = new Movie();
        movie4.setName("KILLER JOE");
        movie4.setDirector("William Friedkin");
        movie4.setSummary("A cop (Matthew McConaughey) who moonlights as a hit man agrees to kill the hated mother of a desperate "
                + "drug dealer (Emile Hirsch) in exchange for a tumble with the young man's virginal sister (Juno Temple)");
        movie4.setGenre(Genre.DRAMA);
        movie4.setRating("TVMA");
        movie4.setRevenue(2000000);
        movie4.setReleaseDate(movie4ReleaseDate);
        movie4.setRuntime("1h41m");
        
        
        // Wikipedia Article for Avengers: Infinity War - https://en.wikipedia.org/wiki/Avengers:_Infinity_War
        Calendar myFavMovieReleaseDate = Calendar.getInstance();
        myFavMovieReleaseDate.set(2018, 4, 23);
        Movie myFavMovie = new Movie("AVENGERS: INFINITY WAR",
                                    "Anthony and Jon Russo",
                                    "Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy "
                                            + "yet -- the evil Thanos. On a mission to collect all six Infinity Stones, Thanos plans to use "
                                            + "the artifacts to inflict his twisted will on reality. The fate of the planet and existence itself "
                                            + "has never been more uncertain as everything the Avengers have fought for has led up to this moment.",
                                    "PG-13",
                                    2048359754,
                                    Genre.ACTION,
                                    myFavMovieReleaseDate,
                                    "2h29m");
        
        
        System.out.println("MOVIE 1:");
        System.out.printf("Name: %s\n", movie1.getName());
        System.out.printf("Director: %s\n", movie1.getDirector());
        System.out.printf("Summary: %s\n", movie1.getSummary());
        System.out.printf("Genre: %s\n", movie1.getGenre());
        System.out.printf("Rating: %s\n", movie1.getRating());
        System.out.println("Revenue: " + movie1.getRevenueString());
        System.out.println("Release Date: " + movie1.getReleaseDateString());
        System.out.printf("Runtime: %s\n", movie1.getRuntime());
        System.out.printf("Version: %d\n", movie1.getVersion());
        movie1.playMovie();
        System.out.println("");
        
        
        System.out.println("MOVIE 2:");
        System.out.printf("Name: %s\n", movie2.getName());
        System.out.printf("Director: %s\n", movie2.getDirector());
        System.out.printf("Summary: %s\n", movie2.getSummary());
        System.out.printf("Genre: %s\n", movie2.getGenre());
        System.out.printf("Rating: %s\n", movie2.getRating());
        System.out.println("Revenue: " + movie2.getRevenueString());
        System.out.println("Release Date: " + movie2.getReleaseDateString());
        System.out.printf("Runtime: %s\n", movie2.getRuntime());
        System.out.printf("Version: %d\n", movie2.getVersion());
        movie2.playMovie();
        System.out.println("");
        
        
        System.out.println("Movie 3:");
        movie3.print();
        System.out.println("");
        
        
        System.out.println("Movie 4:");
        movie4.print();
        System.out.println("");
        
        
        System.out.println("Movie 5:");
        myFavMovie.print();
        System.out.println("");
        
        
        System.out.println("Number of Movies: " + Movie.numOfMovies);
    }
    
}
