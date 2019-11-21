/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mining.plot;
import data.mining.dataManipulation.DataSet;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javafx.embed.swing.SwingNode;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;



public class BoxPlot {


    public BoxPlot(DataSet weka, SwingNode swing) {

        final BoxAndWhiskerCategoryDataset dataset = createSampleDataset(weka);

        final CategoryAxis xAxis = new CategoryAxis("Attribut");
        final NumberAxis yAxis = new NumberAxis("Valeur");
        yAxis.setUpperBound(500);
        yAxis.setAutoRangeIncludesZero(false);

        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer() {
            public CategoryItemRendererState initialise(Graphics2D g2,
                                                        Rectangle2D dataArea,
                                                        CategoryPlot plot,
                                                        int rendererIndex,
                                                        PlotRenderingInfo info) {
                CategoryItemRendererState state = super.initialise(g2, dataArea, plot, rendererIndex, info);
                if (state.getBarWidth() > 35)
                    state.setBarWidth(35); // Keeps the circle and chart from being huge
                return state;
            }
        };
          
        renderer.setFillBox(true);
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);
        renderer.setUseOutlinePaintForWhiskers(true);
        renderer.setMeanVisible(false);
      
      
        
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        final JFreeChart chart = new JFreeChart(
                "Boite à moustache",
                new Font("SansSerif", Font.BOLD, 14),
                plot,
                true
        );
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawWidth(2000);  
        chartPanel.setSize(new Dimension(1000, 500));
        swing.setContent(chartPanel);

    }



    private BoxAndWhiskerCategoryDataset createSampleDataset(DataSet weka) {

        final int seriesCount = 1;
        final Double categoryCount = Double.valueOf(weka.getNumAttributs());
        final int entityCount = weka.getNumInstance();

        final DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        for (int i = 0; i < seriesCount; i++) {
            for (int j = 0; j < categoryCount; j++) {
                final List list = new ArrayList();
                // adding the values from dataset

                if(weka.getAttributes().get(j).isNumeric()){
                    for (int k = 0; k < entityCount; k++) {
                        final double value1 = weka.getInstances().get(k).value(j);
                        list.add(new Double(value1));
                    }

                    dataset.add(list, " " , weka.getAttributes().get(j).name());
                }
            }

        }
        return dataset;
    }


}