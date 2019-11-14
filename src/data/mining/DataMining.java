/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mining;

import dataManipulation.DataSet;
import dataManipulation.ManipCentralTend;
import dataManipulation.ManipData;
import dataManipulation.MeasAttribut;
import java.util.EnumSet;
/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;*/
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Attribute;
import java.util.*;

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
         
        //Initialising data set
        DataSet data = new DataSet("data/HEART_Stat.arff");
        Instances instances = data.getInstances();
        //Initialising attributs in this data set 
        MeasAttribut attribut = new MeasAttribut(instances);
        
        ArrayList<Integer> list = attribut.calculate_Effectif("maximum_heart_rate_achieved");
        double Q3 = attribut.calculate_Quartile3("maximum_heart_rate_achieved",list);
        System.out.println("Le Q3 est : "+Q3);
        double mediane = attribut.calculate_Median("maximum_heart_rate_achieved");
        System.out.println("The median is: "+mediane);
        System.out.println("THe mean is :"+attribut.calculate_Mean("maximum_heart_rate_achieved"));
        int i = attribut.calculate_Mode("maximum_heart_rate_achieved");
        System.out.println(instances);
    }
    
}
