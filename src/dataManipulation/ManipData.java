package dataManipulation;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
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
    
    
    
}
