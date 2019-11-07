/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mining;

import dataManipulation.ManipData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Attribute;

/**
 *
 * @author Hassaine
 */
public class DataMining extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
         //launch(args);
         
          DataSource source = new DataSource("data/HEART_Stat.arff");
        Instances instances = source.getDataSet();
        // Make the last attribute be the class
        instances.setClassIndex(instances.numAttributes() - 1);

        // Print header and instances.
        System.out.println("\nDataset:\n");
        //System.out.println(instances);
       // System.out.println(instances.attribute(0));
        ManipData.display_All_Attributes(instances);

       
    }
    
}
