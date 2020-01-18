package data.mining.controller;


import com.jfoenix.controls.JFXTextField;
import data.mining.plot.BarChar;
import data.mining.plot.BoxPlot;
import data.mining.plot.ScatterPlot;
import data.mining.dataManipulation.DataSet;
import data.mining.dataManipulation.LinearRegression;
import data.mining.dataManipulation.Statistic;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Instance;
import javafx.scene.layout.Pane;
import javafx.embed.swing.SwingNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TablePosition;
import data.mining.algorithms.AlgorithmManager;

public class MainScreenController implements Initializable {

    @FXML
    private JFXTextField confidence;

    @FXML
    private JFXTextField support;

    @FXML
    private MenuButton xAxisScutterPlot;

    @FXML
    private MenuButton yAxisScutterPlot;

    @FXML
    private Pane BoxPlotPane;

    @FXML
    private SwingNode BoxPlotSwingNode;

    @FXML
    private Button plotButton;

    @FXML
    private TableView table = new TableView<>();

    @FXML
    private TextArea details;
    private Attribute barCharSelectedattribut;

    private DataSet dataSet;
    private Statistic dataStatistic;

    @FXML
    void MinMaxDeterminator(ActionEvent event) {
        //to add in the future
    }


    @FXML
    void apriori(ActionEvent event) {
        try {
            StringBuilder freqItemSets = new StringBuilder("");
            StringBuilder associationRules=new StringBuilder("");

            String support =this.support.getText();
            String confidence =this.confidence.getText();
            if(Pattern.matches("[a-zA-Z]+", support) || Pattern.matches("[a-zA-Z]+", confidence)){
                throw new SecurityException("the confidance and the support most be a numerical value");
            }

            if( Integer.parseInt(support)<=100 && Integer.parseInt(support)>=0 && Integer.parseInt(confidence)<=100 && Integer.parseInt(confidence)>=0 ){
                AlgorithmManager.startAprioriAlgo(support,confidence,freqItemSets,associationRules);
                this.details.setText(this.details.getText()+"\n"+freqItemSets+"\n" +associationRules);
            }



        }catch (SecurityException e){
            e.printStackTrace();
        }

    }

    @FXML
    void dataBinning(ActionEvent event) {

        //
        // display data from the data set in TableView
        // display information about the dataset in the TextArea
        try {
            //load data
            this.dataSet = new DataSet("data/HEART_StatFinal.arff");
            //display data
            this.dataSet.display_DataSet(table, details);
            this.details.clear();
            this.dataSet.display_DataSetInfoCSV(details, "data/Datadescreption.csv");
            //create a statistique class on the data
            this.dataStatistic = new Statistic((this.dataSet.getInstances()));
        } catch (FileNotFoundException e) {


            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (IOException e1) {


            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, e1);

        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void eclat(ActionEvent event) {
        try {
            StringBuilder freqItemSets = new StringBuilder("");
            StringBuilder associationRules=new StringBuilder("");

            String support =this.support.getText();
            String confidence =this.confidence.getText();
            if(Pattern.matches("[a-zA-Z]+", support) || Pattern.matches("[a-zA-Z]+", confidence)){
                throw new SecurityException("the confidance and the support most be a numerical value");
            }

            if( Integer.parseInt(support)<=100 && Integer.parseInt(support)>=0 && Integer.parseInt(confidence)<=100 && Integer.parseInt(confidence)>=0 ){
                AlgorithmManager.startEclatAlgo(support,confidence,freqItemSets,associationRules);
                this.details.setText(this.details.getText()+"\n"+freqItemSets+"\n" +associationRules);
            }



        }catch (SecurityException e){
            e.printStackTrace();
        }






    }

    @FXML
    void saveOutput(ActionEvent event) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output/output.txt"), "utf-8"))) {
            writer.write(this.details.getText());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void kmedoids(ActionEvent event) {


            StringBuilder result = new StringBuilder("");

                AlgorithmManager.statKmedoidsAlgo("5",result);
                this.details.setText(this.details.getText()+"\n"+result);





    }

    @FXML
    void Q1MedianQ3Determinator(ActionEvent event) {

        String[] attributes = {"resting_blood_pressure", "serum_cholestoral", "maximum_heart_rate_achieved"};
        this.dataStatistic.display_Q1MedianQ3(details, attributes);

    }

    @FXML
    void doBoxPlot(ActionEvent event) {
        try {
            if (dataSet != null) {
                //display the box plot for the entire dataset
                BoxPlotPane.setVisible(true);
                final BoxPlot demo = new BoxPlot(dataSet, BoxPlotSwingNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void buttonBoxPlot() {
        try {
            if (dataSet != null) {
                //if no attributs is selected to display
                String xAttName = xAxisScutterPlot.getText().equals("X") ? "age" : xAxisScutterPlot.getText();
                String yAttName = yAxisScutterPlot.getText().equals("Y") ? "sex" : yAxisScutterPlot.getText();
                //get each attribute row values 
                double[] valeursxAtt = dataSet.getValuesByAttributName(xAttName);
                double[] valeursyAtt = dataSet.getValuesByAttributName(yAttName);
                
                
                LinearRegression model =  new LinearRegression(valeursxAtt, valeursyAtt);
                
                this.details.setText(this.details.getText()+'\n'+model.toString());
                
                
                BoxPlotPane.setVisible(true);
                final ScatterPlot demo = new ScatterPlot("correlation graphe", BoxPlotSwingNode, xAttName, yAttName, valeursxAtt, valeursyAtt,model.slope(),model.intercept());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void doGraph(ActionEvent event) {
        try {
            if (dataSet != null) {
                // intialize the two  MenuButtons for the ScatterPlot, they are used to chose the attributs to display
                xAxisScutterPlot.getItems().clear();
                yAxisScutterPlot.getItems().clear();
                
                //adding all the attributs 
                for (Iterator iterator = dataSet.getAttributes().iterator(); iterator.hasNext();) {

                    Attribute next = (Attribute) iterator.next();
                    MenuItem menuX = new MenuItem(next.name());
                    MenuItem menuY = new MenuItem(next.name());
                    
                    //to change the MenuButtons text by clicking on a menuItem
                    menuX.setOnAction((e) -> {
                        xAxisScutterPlot.setText(menuX.getText());
                    });

                    menuY.setOnAction((e) -> {
                        yAxisScutterPlot.setText(menuX.getText());
                    });

                    xAxisScutterPlot.getItems().add(menuX);
                    yAxisScutterPlot.getItems().add(menuY);

                }
                xAxisScutterPlot.setVisible(true);
                yAxisScutterPlot.setVisible(true);
                plotButton.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void doHist(ActionEvent event) {
        //
        // display histogram for a given attribute by clicking on it in the TableView
        //
        try {
            if (dataSet != null) {

                BoxPlotPane.setVisible(true);
                final BarChar demo = new BarChar("diagramme à baton", BoxPlotSwingNode, barCharSelectedattribut, dataSet.getValuesByAttribut(barCharSelectedattribut));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayData(ActionEvent event) {
        //
        // display data from the data set in TableView
        // display information about the dataset in the TextArea
        try {
            //load data
            this.dataSet = new DataSet("data/HEART_Stat.arff");
            //display data
            this.dataSet.display_DataSet(table, details);

            //create a statistique class on the data
            this.dataStatistic = new Statistic((this.dataSet.getInstances()));
        } catch (FileNotFoundException e) {

           
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, e);
        }
          catch (IOException e1) {

          
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, e1);
            
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void meanDeterminator(ActionEvent event) {
        
        this.dataStatistic.display_mean(details);

    }

    @FXML
    void modeDeterminator(ActionEvent event) {
        this.dataStatistic.display_mode(details);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        table.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                TablePosition tablePositionOne = (TablePosition) selectedCells.get(0);
                int one = tablePositionOne.getColumn();
                barCharSelectedattribut = dataSet.getInstances().attribute(one);

            }
        });
    }

}
