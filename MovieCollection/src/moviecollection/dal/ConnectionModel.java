/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.be.MovieInCategory;

/**
 *
 * @author Pepe15224
 */
public class ConnectionModel {
    
    private ConnectionManager cm = new ConnectionManager();
   // Adds Movie to Database
    public void addMovie(Movie movie) {
       try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO Movie"
                    + "(name, rating, personalrating, filelink, lastview) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, movie.getName());
            pstmt.setDouble(2, movie.getRating());
            pstmt.setDouble(3, movie.getPersonalrating());
            pstmt.setString(4, movie.getFilelink());
            pstmt.setString(5, movie.getLastview());

            int affected = pstmt.executeUpdate();
            
            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                movie.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
   // Returns list of all movies form Database
    public List<Movie> getAllMovies()
    {
        List<Movie> allMovies = new ArrayList();
        try (Connection con = cm.getConnection())
        {
         PreparedStatement pstmt = con.prepareCall("SELECT * FROM Movie");
         ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                Movie m = new Movie(rs.getInt("id"),
                       rs.getString("name"),
                        rs.getDouble("rating"),
                        rs.getDouble("personalrating"),
                        rs.getString("filelink"), rs.getString("lastview"));
      
                allMovies.add(m);
            }
         }
         catch (SQLException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(
                    Level.SEVERE, null, ex);
          }
        return allMovies;
}
   // Adds Category to Database     
    public void addCategory(Category category) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO Category"
                    + "(name)"
                    + "VALUES(?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, category.getName());
            
            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Movie could not be added");

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
   // Returns list of all categories from Database
    public List<Category> getAllCategories()
    {
        List<Category> allCategories = new ArrayList();
        try (Connection con = cm.getConnection())
        {
         PreparedStatement pstmt = con.prepareCall("SELECT * FROM Category");
         ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                Category c = new Category(rs.getInt("id"), rs.getString("name"));
                allCategories.add(c);
            }
         }
         catch (SQLException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(
                    Level.SEVERE, null, ex);
          }
        return allCategories;
}
   //Deletes selected Category and Movies inside this Category 
    public void deleteCategory(Category selectedCategory) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "DELETE FROM CatMovie WHERE CategoryId=?"
                    + " DELETE FROM Category WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedCategory.getId());
            pstmt.setInt(2, selectedCategory.getId());
            pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   // Adds CatMovie to Database    
    public void addMovieToCategory(Category category, Movie movie)  {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO CatMovie(CategoryId, MovieId)"
                    + "VALUES(?, ?)");
            pstmt.setInt(1, category.getId());
            pstmt.setInt(2, movie.getId());
            int affected = pstmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("");
        }
    }
   // Returns list of CatMovies by CategoryId
    public List<MovieInCategory> getMoviesById(int id)
    {
        List<MovieInCategory> moviesById = new ArrayList();

        try (Connection con = cm.getConnection()) 
        {
          String query
                    = "SELECT * FROM CatMovie "
                    + "WHERE CategoryId LIKE ?";
             
            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setInt(1,id);
           ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MovieInCategory moviesInC = new MovieInCategory(rs.getInt("CategoryId"), rs.getInt("MovieId"), rs.getInt("Id"));
                
                moviesById.add(moviesInC);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moviesById;
    }
   //Updates Movie
    public void editMovies(MovieInCategory movieinC) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "UPDATE Movie SET "
                    + "name=?, rating=?, personalrating=?, filelink=? "
                    + "WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setString(1, movieinC.getName());
            pstmt.setDouble(2, movieinC.getRating());
            pstmt.setDouble(3, movieinC.getPersonalrating());
            pstmt.setString(4, movieinC.getFilelink());
            pstmt.setInt(5, movieinC.getMovieId());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Movie could not be updated");
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }    
    }
   // Updates CatMovie    
    public void editDate(MovieInCategory movieinC) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "UPDATE Movie SET "
                    + "lastview=? "
                    + "WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setString(1, movieinC.getLastview());
            pstmt.setInt(2, movieinC.getMovieId());
            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Date could not be updated");
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }    
}
   //Deletes Movie form Database   
    public void deleteMovie(MovieInCategory movieinC) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "DELETE FROM CatMovie WHERE MovieId=?"
                    + " DELETE FROM Movie WHERE id=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, movieinC.getMovieId());
            pstmt.setInt(2, movieinC.getMovieId());
            pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
