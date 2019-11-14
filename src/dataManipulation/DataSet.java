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
    
    
    // Methodes 
    
    public ArrayList<Integer> calculate_Effectif(String attribut){
        
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
        return countFrequencies(listOf_Values);
        
        
    

    }
    
    public static ArrayList<Integer> countFrequencies(ArrayList<Integer> list) 
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
        return freq_list;
        
    } 

    public double calculate_Quartile1(String attribut,ArrayList<Integer> list){
        
        ArrayList<Integer> values_effectif = calculate_Effectif(attribut);
        int effectif_total = instances.numInstances();
        int position = effectif_total/4;
        if ( effectif_total % 4 != 0){
            position++;
        }
        for (int i=1;i<list.size();i+=2){
            if (list.get(i)>= position){
                return list.get(i-1);
            }
        }
        
        return 0;
    }
    
    
    public double calculate_Quartile3(String attribut,ArrayList<Integer> list){
        
        ArrayList<Integer> values_effectif = calculate_Effectif(attribut);
        int effectif_total = instances.numInstances();
        effectif_total = (effectif_total*3);
        int position = effectif_total/4;
        if ( effectif_total % 4 != 0){
            position++;
        }
        for (int i=1;i<list.size();i+=2){
            if (list.get(i)>= position){
                return list.get(i-1);
            }
            
        }
        
        return 0;
    }
    
    
    
    
}    