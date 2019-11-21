/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mining;

//import dataManipulation.DataSet;
//import dataManipulation.ManipCentralTend;
//import dataManipulation.ManipData;
//import dataManipulation.MeasAttribut;
//import java.util.EnumSet;
//import java.nio.file.Files;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import java.io.File;
//import weka.core.converters.ConverterUtils.DataSource;
//import weka.core.Instances;
//import weka.core.Attribute;
//import java.util.*;

/**
 *
 * @author Hassaine
 */
public class DataMining extends Application {

    @Override
    public void start(Stage stage)  {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("ui/mainScreen.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);        
        
        stage.show();
        
        
            
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
       
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
         launch(args);

    }

}
