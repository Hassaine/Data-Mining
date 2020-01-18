package data.mining.algorithms;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AlgorithmManager {
    public static void main(String[] args) {
        StringBuilder transaction = new StringBuilder();
        StringBuilder  associationRule= new StringBuilder("");
        //startEclatAlgo("14","16",transaction,associationRule);
        //startAprioriAlgo("14","14");
        //System.out.println(associationRule);
        statKmedoidsAlgo("5",transaction);

    }

    public static void startEclatAlgo(String min_support,String min_confidence,StringBuilder freqItemSet,StringBuilder associationRule){

        Eclat a = new Eclat();
        int minSupp = Integer.parseInt(min_support);

        double minConf = Double.parseDouble(min_confidence);

        minConf = minConf/100;

        Object[] obj = a.EclatAlgo(minSupp,minConf);

        HashSet<ArrayList<String>> frequentItemsets;
        frequentItemsets = (HashSet<ArrayList<String>>) obj[0];

        HashMap<ArrayList<String>, ArrayList<String>> transactionIdSets;
        transactionIdSets = (HashMap<ArrayList<String>, ArrayList<String>>) obj[1];


        HashMap<ArrayList<String>, Float>  regleAssociation;
        regleAssociation = (HashMap<ArrayList<String>, Float>) obj[2];
        double timeExecution = (double) obj[3];
        timeExecution = timeExecution/1000;

        freqItemSet.append("*******************Eclat Algorithme*******************:\n");
        freqItemSet.append("temps d'execution:"+String.valueOf(timeExecution)+"s\n");
        freqItemSet.append("frequent itemset :\n");
       for(ArrayList<String> temp : frequentItemsets) {

            if(temp.size()>1) {

                freqItemSet.append("\n");
               for(String s:temp) {
                   freqItemSet.append(s+" ");
       }
            }
                }
        associationRule.append("association Rules :\n");
        for(ArrayList<String> temp : regleAssociation.keySet()) {

            associationRule.append("\n");
            for(String s:temp) {

                associationRule.append(s+" ");


            }
            associationRule.append(" , confidence :" + regleAssociation.get(temp));


        }

    }

    public static void startAprioriAlgo(String min_support,String min_confidence,StringBuilder freqItemSet,StringBuilder associationRule){


        Apriori a = new Apriori();
        int minSupp = Integer.parseInt(min_support);
        double minConf = Double.parseDouble(min_confidence);
        minConf = minConf/100;
        Object[] obj = a.AprioriAlgo(minSupp,minConf);

        HashSet<ArrayList<String>> frequentItemsets;
        frequentItemsets = (HashSet<ArrayList<String>>) obj[0];
        HashMap<ArrayList<String>, Float>  regleAssociation;
        regleAssociation = (HashMap<ArrayList<String>, Float>) obj[1];






        freqItemSet.append("*******************Apriori Algorithme******************************:\n");

        freqItemSet.append("frequent itemset :\n");

        for(ArrayList<String> temp : frequentItemsets) {
            //affichage des items frequents
            if(temp.size()>1) {
                freqItemSet.append("\n");
                for(String s:temp) {
                    //Affichage

                    freqItemSet.append(s+" ");

                }

            }
        }
        associationRule.append("association Rules :\n");

        for(ArrayList<String> temp : regleAssociation.keySet()) {
            //affichage des regles d'associations
            associationRule.append("\n");
            for(String s:temp) {
                associationRule.append(s+" ");
            }

            associationRule.append(" ,  confidence :" + regleAssociation.get(temp));

        }






    }

    public static void statKmedoidsAlgo(String nbr_classes, StringBuilder result){

        Kmedoids a = new Kmedoids();
        int k = Integer.parseInt(nbr_classes);

        if(k>0) {
            Object[] obj = a.AlgoKmedoids(k);
            if(obj!=null) {
                //affichage des classe
                ArrayList<Cluster> clusterList;
                clusterList = (ArrayList<Cluster>) obj[0];
                clusterList.get(0);
                result.append("*******************Kmedoids************************\n");
                result.append("nombre de classes: "+nbr_classes+"\n");

                for(int i =0;i<k;i++) {
                    result.append("La Classe "+i+" :\n");

                    Cluster c = clusterList.get(i);
                    result.append("Nombre d'instance : "+c.elements.size()+"\n");
                    result.append("le Medoid : \n"+c.medoid.toString());
                    result.append("Les element de la classe : \n");


                    for(Instance temp : c.getElements()) {
                        result.append(temp.toString());

                        //clustring_result.appendText(temp.toString());
                    }
                    result.append("---------------------------------------------------\n");
                    //clustring_result.appendText("---------------------------------------------------\n");
                }

                //affichage le temps d'execution
                double timeExecution = (double) obj[1];
                timeExecution = timeExecution/1000; //car il est en milliseconde
                result.append("temp d'execution :"+timeExecution+"s\n");
                //timeClustring.setText(String.valueOf(timeExecution));
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("le nombre de clusters k est plus grand que la taile du dataset");
                alert.showAndWait();
                //nbr_classes.setText("");
            }
        }

    }


}
