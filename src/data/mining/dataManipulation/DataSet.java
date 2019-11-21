package data.mining.dataManipulation;

import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;

/**
 *
 * @author Dadjerci and hassaine
 */
public class DataSet {

    private Instances instances;
    private int numInstance;
    private int numAttributs;
    private ArrayList<Attribute> attributes;

    //Constructor
    public DataSet(String path) throws Exception {
        DataSource source = new DataSource(path);
        this.instances = source.getDataSet();
        this.numInstance = this.instances.numInstances();
        this.numAttributs = this.instances.numAttributes();
        this.attributes = new ArrayList<>();
        for (int i = 0; i < this.numAttributs; i++) {
            this.attributes.add(instances.attribute(i));
        }

        System.out.println("crating the dataSet");

    }

    /**
     * @return the instances
     */
    public Instances getInstances() {
        return instances;
    }

    /**
     * @param instances the instances to set
     */
    public void setInstances(Instances instances) {
        this.instances = instances;
    }

    // Methods 
    public void display_All_Attributes() {
        int numberOfAttributes = instances.numAttributes();
        for (int i = 0; i < numberOfAttributes; i++) {
            Attribute current_att = instances.attribute(i);
            System.out.println(current_att.toString());
            System.out.println("Nombre de valeur possible pour cet attribut :" + current_att.numValues() + "\n");

        }
    }

    public void display_DataSet(TableView table,TextArea details) {
      Instances data = this.instances;
            System.out.println("INFORMATIONS :\n\nData Set " + data.relationName() + "\nNombre d\'instances : " + Integer.toString(data.numInstances()) + "\n\n");
            String detailsContenue = "INFORMATIONS :\n\nData Set " + data.relationName() + "\nNombre d\'instances : " + Integer.toString(data.numInstances()) + "\n\n";
            int i = 0;
            while (i < data.numAttributes()) {
                detailsContenue += ("L\'attribut " + data.attribute(i).name() + " est de type " + Attribute.typeToString(data.attribute(i))) + "\n";
                i++;
            }
            details.setText(detailsContenue);
            details.setVisible(true);
            ArrayList<TableColumn<Instance, String>> atrributes = new ArrayList<>();
            ArrayList<Instance> instances = new ArrayList<>();
            for (i = 0; i < data.size(); i++) {
                instances.add(data.get(i));
            }
            ObservableList<Instance> tableContent = FXCollections.observableArrayList(instances);
            for (i = 0; i < data.numAttributes(); i++) {
                TableColumn<Instance, String> column = new TableColumn<Instance, String>(data.attribute(i).name());
                final int attIndex = i;
                column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString(attIndex)));
                atrributes.add(column);
            }
             table.getColumns().clear();
            table.getColumns().addAll(atrributes);
            table.setItems(tableContent);
            table.setVisible(true);
    }
    public void display_DataSet() {
        Instance current_instance;
        int Nb_ist = instances.numInstances();
        System.err.println("*** Data Set ***");
        for (int i = 0; i < Nb_ist; i++) {
            current_instance = instances.get(i);
            System.out.println(current_instance);
        }
    }
    public void display_Default_DS() {
        System.out.println(instances);
    }

    public void display_Default_Att() {
        System.out.println("Ce data set contient " + instances.numAttributes() + " dont un est un attribut de classe :\n");
        for (int i = 0; i < instances.numAttributes(); i++) {
            Attribute current_att = instances.attribute(i);

            System.out.println("L'attribut :" + current_att.name() + " de type : " + Attribute.typeToString(current_att));

        }
    }

    public int getNumAttributs() {
        return numAttributs;

    }

    public int getNumInstance() {    
        return numInstance;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public double[] getValuesByAttribut(Attribute attribut) {
        double [] values = new double[this.numInstance];
        Instance current_instance;       
        int Nb_ist = instances.numInstances();      
        for (int i = 0; i < Nb_ist; i++) {
            current_instance = instances.get(i);
            values[i]=current_instance.value(attribut);                      
        }
        return values;
    }
    public double[] getValuesByAttributName(String attribut) {
        
        double [] values = new double[this.numInstance];
        Instance current_instance;       
        int Nb_ist = instances.numInstances();     
        Attribute attTmp = null;
        
        for (int i = 0; i < this.numAttributs; i++) {
            if(this.attributes.get(i).name().equals(attribut)){
                attTmp=this.attributes.get(i);
                break;
            }
            
        }
        for (int i = 0; i < Nb_ist; i++) {
            current_instance = instances.get(i);
            values[i]=current_instance.value(attTmp);                      
        }
        return values;
    }
}
