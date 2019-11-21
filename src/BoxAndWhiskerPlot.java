import data.mining.dataManipulation.DataSet;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Log;
import org.jfree.util.LogContext;
import weka.core.Instances;

import javax.swing.*;



public class BoxAndWhiskerPlot extends JFrame {

   
    private static final LogContext LOGGER = Log.createContext(BoxAndWhiskerPlot.class);

    
    
    public BoxAndWhiskerPlot(final String title, Instances instances) {

        super(title);

        final BoxAndWhiskerCategoryDataset dataset = createDataset(instances);

        final CategoryAxis xAxis = new CategoryAxis("Attributs");
        final NumberAxis yAxis = new NumberAxis("Valeurs");
        yAxis.setAutoRangeIncludesZero(false);
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(false);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        final JFreeChart chart = new JFreeChart(
                "Boxplots (Boite à moustache)",
                new Font("SansSerif", Font.BOLD, 14),
                plot,
                true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(450, 270));
        setContentPane(chartPanel);

    }

    
    private BoxAndWhiskerCategoryDataset createDataset(Instances instances) {
        final int categoryCount = instances.numAttributes();
        final int entityCount = instances.size();

        final DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
            for (int j = 0; j < categoryCount; j++) {
                if ( j == 3 || j == 4 || j == 7){
                    final List list = new ArrayList();
                for (int k = 0; k < entityCount; k++) {
                    list.add(instances.get(k).value(j));
                }
                dataset.add(list, "DataSet : "+instances.relationName(), instances.attribute(j).name());
                }
            }
            

        return dataset;
    }

   
    public void render() {
        //Log.getInstance().addTarget(new PrintStreamLogTarget(System.out));
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
    
    public static void main(final String[] args) throws Exception {
        DataSet data = new DataSet("data/HEART_Stat.arff");
        Instances instances = data.getInstances();
        //Log.getInstance().addTarget(new PrintStreamLogTarget(System.out));
        final BoxAndWhiskerPlot demo = new BoxAndWhiskerPlot("Boite à moustache",instances);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
    
}