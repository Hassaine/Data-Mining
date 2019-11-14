
package dataManipulation;
import java.util.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;

/**
 *
 * @author hads
 * A class for Measuring the Central Tendency
 */
public class ManipCentralTend {
    
    public static void calculate_Effectif(Instances instances,String attribut){
        
        Instance current_instance;
        int Nb_Ins = instances.numInstances();
        ArrayList<Integer> listOf_Values = new ArrayList<Integer>();
        
        //Getting all the value of attribut
        for(int i=0;i<Nb_Ins;i++){
            current_instance = instances.get(i);
            listOf_Values.add((int)(current_instance.value(instances.attribute(attribut))));
        }
        
        //Sorting values of attribut
        Collections.sort(listOf_Values);
        countFrequencies(listOf_Values);
        
        
    }
    
    public static void countFrequencies(ArrayList<Integer> list) 
    { 
        int somme=0,count=0;
        int val1 = list.get(0);
        ArrayList<Integer> freq_list = new ArrayList<Integer>();
        for(int i=0;i<=list.size()-1;i++){        
                if(val1 ==  list.get(i)){
                    count++;
                }
                else{
                somme = somme + count;    
                freq_list.add(val1);
                freq_list.add(somme);
                val1 = list.get(i);
                count=1;
                }
                
        }
        somme = somme + count;
        freq_list.add(val1);
        freq_list.add(somme);
        System.out.println(freq_list);
        
    } 
    
}
