/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import moviecollection.be.Movie;
import moviecollection.gui.model.MovieModel;



/**
 * FXML Controller class
 *
 * @author Mape
 */
public class NewMovieController implements Initializable {

    @FXML
    private Button SaveButton;
    @FXML
    private Button CloseButton;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField PRatingTextField;
    @FXML
    private TextField IMDBRatingTextField;
    @FXML
    private TextField FileTextField;
    @FXML
    private Button ChooseButton;
    private MovieModel model = new MovieModel();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    @FXML
    private void ChooseButtonClick(ActionEvent event) {
        
       
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtermP4 = new FileChooser.ExtensionFilter("select mp4","*.mp4");
        fileChooser.getExtensionFilters().add(filtermP4);
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.toString();
        
        FileTextField.setText(filePath); //insert path of the file into the textField        
     }
    
    
    
    /************* SAVE AND CLOSE METHODS *************/
    
    @FXML
    private void SaveButtonClick(ActionEvent event) {
        model.addMovie(new Movie(-1,
                TitleTextField.getText(),
                Double.valueOf(PRatingTextField.getText()),
                Double.valueOf(IMDBRatingTextField.getText()),
                FileTextField.getText(),
                null));
               closeWindow();
    }

    @FXML
    private void CloseButtonClick(ActionEvent event) {
        closeWindow();
    }

    
    
    
    /*********** OTHER METHODS *************/
    private void closeWindow()
    {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
}
