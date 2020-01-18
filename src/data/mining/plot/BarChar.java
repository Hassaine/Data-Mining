/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mining.plot;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import javafx.embed.swing.SwingNode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import weka.core.Attribute;

/**
 *
 * @author Hassaine
 */
public class BarChar {

    public BarChar(String chartTitle, SwingNode swing, Attribute att, double[] valeurs) {

        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                att.name(),
                "frequences",
                createDataset(valeurs),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);

        chartPanel.setMaximumDrawWidth(2000);

        chartPanel.setSize(new Dimension(1000, 500));
        swing.setContent(chartPanel);
    }

    private CategoryDataset createDataset(double[] valeurs) {
        
        valeurs = sortVector(valeurs);
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int[] frequences = new int[valeurs.length];
        double[] val = new double[valeurs.length];
        java.util.Arrays.fill(val, -1);
        int k = 0;
        for (int i = 0; i < valeurs.length - 1; i++) {
            if (valeurs[i] == -1.0) {
                continue;
            }
            frequences[k] = 0;
            val[k] = valeurs[i];
            for (int j = i + 1; j < valeurs.length; j++) {
                if (valeurs[i] == valeurs[j]) {
                    frequences[k] += 1;
                    valeurs[j] = -1;
                }
            }
            valeurs[i] = -1;
            k++;

        }

        k = 0;
        while (val[k] != -1) {

            dataset.addValue(frequences[k], String.valueOf(val[k]), "");
            k++;

        }

        return dataset;
    }

    private double[] sortVector(double[] valeurs) {

        //double max = 0.0;
        for (int i = 0; i < valeurs.length; i++) {
            double max = valeurs[0];
            int indicemax = 0;
            for (int j = 0; j < valeurs.length - i; j++) {
                if (valeurs[j] > max) {
                    indicemax = j;
                    max = valeurs[j];

                }

            }
            double tmp = valeurs[valeurs.length - i - 1];
            valeurs[valeurs.length - i - 1] = max;
            valeurs[indicemax] = tmp;

        }

        return valeurs;
    }

}
