/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.model;

import moviecollection.be.Movie;
import moviecollection.bll.BllManager;

/**
 *
 * @author Pepe15224
 */
public class MovieModel {
    BllManager manager = new BllManager();
    
    public void addMovie(Movie movie)
    {
        manager.addMovie(movie);
    }
}
