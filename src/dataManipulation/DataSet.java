package dataManipulation;
import java.util.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;

/**
 *
 * @author hads
 */
public class DataSet {
    
    private Instances instances;
    

        //Constructor

            public DataSet(String path) throws Exception{
                DataSource source = new DataSource(path);
                this.instances = source.getDataSet();
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
            public void display_All_Attributes(){
                int numberOfAttributes = instances.numAttributes();
                for(int i=0;i<numberOfAttributes;i++){
                    Attribute current_att = instances.attribute(i);
                    System.out.println(current_att.toString());
                    System.out.println("Nombre de valeur possible pour cet attribut :"+current_att.numValues()+"\n");
                }
            }

            public void display_DataSet(){
                Instance current_instance;
                int Nb_ist = instances.numInstances();
                System.err.println("*** Data Set ***");
                for(int i=0;i<Nb_ist;i++){
                    current_instance = instances.get(i);
                    System.out.println(current_instance);
                }
            }
            
            public void display_Default_DS(){
                System.out.println(instances);
            }
            
            public void display_Default_Att(){
                System.out.println("Ce data set contient "+instances.numAttributes()+" dont un est un attribut de classe :\n");
                for(int i=0;i<instances.numAttributes();i++){
                    Attribute current_att = instances.attribute(i);
                    
                    System.out.println("L'attribut "+current_att.name()+" de type : "+Attribute.typeToString(current_att));
                }
            }
    
    
    
    
}    