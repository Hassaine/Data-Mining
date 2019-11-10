/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mining;

import dataManipulation.ManipData;
import java.util.EnumSet;
/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;*/
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Attribute;
import java.util.Enumeration;

/**
 *
 * @author Hassaine
 */
public class DataMining /*extends Application*/ {
    
    /*@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
         //launch(args);
        //Initialisation of instances 
        DataSource source = new DataSource("data/HEART_Stat.arff");
        Instances instances = source.getDataSet();
        
        
        ManipData.display_All_Attributes(instances);
        System.out.println("\n****\n");
        ManipData.display_Data_Set("data/HEART_Stat.arff");
        System.out.println("Le nombre d'instances est : "+ManipData.Number_Instances(instances));
        
        System.out.println(instances.meanOrMode(7));
        ManipData.calculate_Median(instances, "maximum_heart_rate_achieved");
        System.out.println((((double)5)/((double)2)));
       
    }
    
}
