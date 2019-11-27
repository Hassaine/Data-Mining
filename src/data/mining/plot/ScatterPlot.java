/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mining.plot;

import data.mining.dataManipulation.LinearRegression;
import java.awt.Color;
import java.awt.Dimension;
import javafx.embed.swing.SwingNode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import weka.core.Attribute;

/**
 *
 * @author Hassaine
 */
public class ScatterPlot {

    public ScatterPlot(String chartTitle, SwingNode swing, String xAtt, String yAtt, double[] X, double[] Y,double beta, double alpha) {
        // Create dataset
        XYDataset dataset = createDataset(X, Y, alpha, beta);
        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                chartTitle,
                xAtt, yAtt, dataset);
        //Changes background color
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 255, 255));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMaximumDrawWidth(2000);
        panel.setSize(new Dimension(1000, 500));
        // Create Panel
        swing.setContent(panel);

    }

    private XYDataset createDataset(double[] X, double[] Y,double alpha, double beta) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("correlation");
        XYSeries series2= new XYSeries("regression");
       
        for (int i = 0; i < X.length; i++) {
            series1.add(X[i], Y[i]);
            series2.add(X[i],beta * X[i] + alpha);
        }
        
        
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

}
