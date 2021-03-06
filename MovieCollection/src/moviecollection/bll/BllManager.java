/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.bll;

import java.util.List;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.be.MovieInCategory;
import moviecollection.dal.ConnectionModel;


/**
 *
 * @author Pepe15224
 */
public class BllManager {
    
  ConnectionModel connectionModel = new ConnectionModel(); 
  
  public void addNewMovie(Movie movie)
  {
      connectionModel.addMovie(movie);
   }
   
  public List<Movie> getAllMovies()
   {
       return connectionModel.getAllMovies();
   }
   
  public void addCategory(Category category)
   {
       connectionModel.addCategory(category);
   }
    
  public List<Category> getAllCategories() {
       return connectionModel.getAllCategories();
   }
  
  public void deleteCategory(Category selectedCategory) 
   {
     connectionModel.deleteCategory(selectedCategory);
   }
 
  public void addMovieToCategory(Category category, Movie movie) {
      connectionModel.addMovieToCategory(category, movie);
    }
  
  public List<MovieInCategory> getMoviesById(int id)
   {
       return connectionModel.getMoviesById(id);
   }
  
  public void editMovies(MovieInCategory movieinC)
   {
       connectionModel.editMovies(movieinC);
   }
  
  public void deleteMovie(MovieInCategory movieinC)
   {
       connectionModel.deleteMovie(movieinC);
   }
  
  public void editDate(MovieInCategory movieinC)
   {
       connectionModel.editDate(movieinC);
   }
}
