import dataManipulation.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import weka.core.Instance;
import weka.core.Instances;



public class BarChart extends ApplicationFrame {

    
   
    public BarChart(final String title,Instances instances,String attribut) {

        super(title);

        final CategoryDataset dataset = createDataset(instances,attribut);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }


    
    private CategoryDataset createDataset(Instances instances,String attribut) {
        //MeasAttribut att = new MeasAttribut(instances);
        ArrayList<Integer> freq_list = this.calculate_freq(instances,attribut);
        // row keys...
        /*final String series1 = "resting_blood_pressure";
        final String series2 = "serum_cholestoral";
        final String series3 = "maximum_heart_rate_achieved";*/

        // column keys...
        /*final String category1 = "Attribut 1";
        final String category2 = "Attribut 2";
        final String category3 = "Attribut 3";*/

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       /* int val1 = getMax_Attribut_Val(instances,"resting_blood_pressure" );
        int val2 = getMax_Attribut_Val(instances, "serum_cholestoral");
        int val3 = getMax_Attribut_Val(instances, "maximum_heart_rate_achieved");       
        dataset.addValue(val1, series1, category1);
        dataset.addValue(val2, series2, category2);
        dataset.addValue(val3, series3, category3);*/
        
        for (int i=0;i<freq_list.size();i+=2){
            dataset.addValue(freq_list.get(i+1), attribut, freq_list.get(i));
        }
        
        
        return dataset;
        
    }
    

    
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        
        final JFreeChart chart = ChartFactory.createBarChart(
            "Histogramme",         // chart title
            "Attribut :",               // domain axis label
            "Frequence Apparition",      // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        

        // set the background color for the chart
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    
    public static int getMax_Attribut_Val(Instances instances,String attribut){
            //Getting all the value of attribut
            ArrayList<Integer> listOf_Values = new ArrayList<Integer>();
            Instance current_instance;
            int Nb_Ins = instances.numInstances();
                for(int i=0;i<Nb_Ins;i++){
                    current_instance = instances.get(i);
                    listOf_Values.add((int)(current_instance.value(instances.attribute(attribut))));
                }

                //Sorting values of attribut
                Collections.sort(listOf_Values);
                return listOf_Values.get((Nb_Ins-1));
    }
    
    public ArrayList<Integer> calculate_freq(Instances instances,String attribut){
        
        Instance current_instance;
        int Nb_Ins = instances.numInstances();
        ArrayList<Integer> listOf_Values = new ArrayList<Integer>();
        int max=0,index=0,count=0;
        int val1;
        ArrayList<Integer> freq_list = new ArrayList<Integer>();
        
        //Getting all the value of attribut 
                for(int i=0;i<Nb_Ins;i++){
                    current_instance = instances.get(i);
                    listOf_Values.add((int)(current_instance.value(instances.attribute(attribut))));
                }
                Collections.sort(listOf_Values);
                val1 = listOf_Values.get(0);
        // Calculating the occurance of each val in the listOf_Values     
                for(int i=0;i<=listOf_Values.size()-1;i++){        
                        if(val1 ==  listOf_Values.get(i)){
                            count++;
                        }
                        else{    
                        freq_list.add(val1);
                        freq_list.add(count);
                        val1 = listOf_Values.get(i);
                        count=1;
                        }

                }
                freq_list.add(val1);
                freq_list.add(count);
                return freq_list;
    }
    
    public static void main(final String[] args) throws Exception {

        DataSet data = new DataSet("data/HEART_Stat.arff");
        Instances instances = data.getInstances();
        final BarChart demo = new BarChart("Histogramme",instances,"age");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}