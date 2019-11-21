package data.mining.dataManipulation;
import java.util.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;
/**
 *
 * @author hads
 */
public class ManipData {
    
    public static void display_All_Attributes(Instances instances){
        int numberOfAttributes = instances.numAttributes();
        for(int i=0;i<numberOfAttributes;i++){
            Attribute current_att = instances.attribute(i);
            System.out.println(current_att.toString());
            System.out.println("Nombre de valeur possible pour cet attribut :"+current_att.numValues()+"\n");
        }
    }
        
    
    public static void display_Data_Set(String  Path) throws Exception{
        
        DataSource source = new DataSource(Path);
        Instances instances = source.getDataSet();
        // Make the last attribute be the class
        instances.setClassIndex(instances.numAttributes() - 1);

        // Print header and instances.
        System.out.println("\nDataset:\n");
        System.out.println(instances);
    }    
        
    public static int Number_Instances(Instances instances){
        
        return instances.numInstances();
    }   
    
    public static void calculate_Median(Instances instances,String attribut){
        double some=0;
        Instance current_instance;
        int Nb_Ins = Number_Instances(instances);
        int index = (Nb_Ins / 2);
        ArrayList<Integer> listOf_Values = new ArrayList<Integer>();
        
        for(int i=0;i<Nb_Ins;i++){
            current_instance = instances.get(i);
            listOf_Values.add((int)(current_instance.value(instances.attribute(attribut))));
            some = some + listOf_Values.get(i);
            
        }
        System.out.println(some/270.0+"DLL");
        Collections.sort(listOf_Values);
        if ( Nb_Ins%2 == 0 ){
            //Pair
            //SOme prints for tests
            double Val1 = listOf_Values.get(index);
            double Val2 = listOf_Values.get(index-1);
            System.out.println(listOf_Values.get(index));
            System.out.println(listOf_Values.get(index-1));
            System.out.println((Val1+Val2)/2.0);
        }
        else{
            //Impair 
            double Val1 = listOf_Values.get(index);
            System.out.println(Val1);
        }
        System.out.println(listOf_Values);
    }
    
    
   
    
    
}
