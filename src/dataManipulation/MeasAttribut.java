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
public class MeasAttribut {
    
    private Instances instances;
    private double Mean,Mediane,Mode,Q1,Q3,Range;
    
    public MeasAttribut(Instances instances){
        this.instances = instances;
    
    }
    
    
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
            int effectif_total = this.instances.numInstances();
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

    
    public double calculate_Range(String attribut){
        
        return Q3-Q1;
    }
    
    public double calculate_Median(String attribut){
        double some=0;
        Instance current_instance;
        int Nb_Ins = instances.numInstances();
        int index = (Nb_Ins / 2);
        ArrayList<Integer> listOf_Values = new ArrayList<Integer>();
        
                for(int i=0;i<Nb_Ins;i++){
                    current_instance = instances.get(i);
                    listOf_Values.add((int)(current_instance.value(instances.attribute(attribut))));
                    some = some + listOf_Values.get(i);

                }
                Collections.sort(listOf_Values);
                if ( Nb_Ins%2 == 0 ){
                    //Pair
                    //SOme prints for tests
                    double Val1 = listOf_Values.get(index);
                    double Val2 = listOf_Values.get(index-1);
                    return ((Val1+Val2)/2.0);
                }
                else{
                    //Impair 
                    double Val1 = listOf_Values.get(index);
                    return Val1;
                }
    }
    
    public double calculate_Mean(String attribut){
        int some=0;
        int nb_total_instances = instances.numInstances();
        Instance current_instance;
        ArrayList<Integer> listOf_Values = new ArrayList<Integer>();
            for(int i=0;i<nb_total_instances;i++){
                current_instance = instances.get(i);
                listOf_Values.add((int)(current_instance.value(instances.attribute(attribut))));
                some = some + listOf_Values.get(i);
            }
            System.out.println("7sabhom "+instances.meanOrMode(7));
            System.out.println("SOme"+some);
            return ((double)some)/((double)nb_total_instances);
    }
    
    
    public int calculate_Mode(String attribut){
        
        Instance current_instance;
        int Nb_Ins = instances.numInstances();
        ArrayList<Integer> listOf_Values = new ArrayList<Integer>();
        int max=0,index=0,count=0;
        int val1;
        ArrayList<Integer> freq_list = new ArrayList<Integer>();
        
        //Getting all the value of attribut 
                for(int i=0;i<Nb_Ins;i++){
                    current_instance = instances.get(i);
                    listOf_Values.add((int)(current_instance.value(instances.attribute(attribut))));
                }
                Collections.sort(listOf_Values);
                val1 = listOf_Values.get(0);
        // Calculating the occurance of each val in the listOf_Values     
                for(int i=0;i<=listOf_Values.size()-1;i++){        
                        if(val1 ==  listOf_Values.get(i)){
                            count++;
                        }
                        else{    
                        freq_list.add(val1);
                        freq_list.add(count);
                        val1 = listOf_Values.get(i);
                        count=1;
                        }

                }
                freq_list.add(val1);
                freq_list.add(count);
        // Calculating the value that occurs the most in freq_list         
                for(int i=1;i<freq_list.size();i+=2){
                        if(freq_list.get(i)>max){
                            max = freq_list.get(i);
                            index = i-1;
                        }
                }
                
        return freq_list.get(index);        
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

    /**
     * @return the Mean
     */
    public double getMean() {
        return Mean;
    }

    /**
     * @param Mean the Mean to set
     */
    public void setMean(double Mean) {
        this.Mean = Mean;
    }

    /**
     * @return the Mediane
     */
    public double getMediane() {
        return Mediane;
    }

    /**
     * @param Mediane the Mediane to set
     */
    public void setMediane(double Mediane) {
        this.Mediane = Mediane;
    }

    /**
     * @return the Mode
     */
    public double getMode() {
        return Mode;
    }

    /**
     * @param Mode the Mode to set
     */
    public void setMode(double Mode) {
        this.Mode = Mode;
    }

    /**
     * @return the Q1
     */
    public double getQ1() {
        return Q1;
    }

    /**
     * @param Q1 the Q1 to set
     */
    public void setQ1(double Q1) {
        this.Q1 = Q1;
    }

    /**
     * @return the Q3
     */
    public double getQ3() {
        return Q3;
    }

    /**
     * @param Q3 the Q3 to set
     */
    public void setQ3(double Q3) {
        this.Q3 = Q3;
    }

    /**
     * @return the Range
     */
    public double getRange() {
        return Range;
    }

    /**
     * @param Range the Range to set
     */
    public void setRange(double Range) {
        this.Range = Range;
    }
    




}
