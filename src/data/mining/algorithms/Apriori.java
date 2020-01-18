package data.mining.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Apriori {
	
	public Object[] AprioriAlgo(int supportMin,double confidenceMin) {
		TransactionDataSet data = new TransactionDataSet();
		HashSet<String> items = new HashSet<String>();
		HashSet<ArrayList<String>> itemsPris = new HashSet<ArrayList<String>>();
		HashSet<ArrayList<String>> frequentItemsets = new HashSet<ArrayList<String>>(); //pour garder l'historique
		HashMap<ArrayList<String>, Integer> listItemFrequence = new HashMap<ArrayList<String>, Integer>();
		HashMap<ArrayList<String>, Float> regleAssociation = new HashMap<ArrayList<String>, Float>(); //pour garder les regles d'association		
		
		//calculer le temps d'execution
		long startTime = System.currentTimeMillis(); 
		
		//recuperer les itemset de longueur 1 (premier niveau)
		for(int i =0 ;i<data.getContenu().size();i++) {
			for(String s : data.getContenu().get(i).getItemList()) {
				items.add(s);
			}
		}

		//calculer les supports des items
		for(String item : items) {
			ArrayList<String> temp = new ArrayList<String>();
			int cpt=0;
			for(int i =0 ;i<data.getContenu().size();i++) {
				if(data.getContenu().get(i).getItemList().contains(item)) {
					cpt++;
				}
			}
			if(cpt>=supportMin) { //on prend l'item
				temp.add(item);
				itemsPris.add(temp);
				listItemFrequence.put(temp, cpt);
			}
		}
		
		//sauvgrader le 1er niveau
		frequentItemsets.addAll(itemsPris);
		//construction d'autres niveaux
		while(!itemsPris.isEmpty()) {
			itemsPris = aprioriGenerator(itemsPris);
			//supprimer les itemSets qui ne verifie pas la condition de min support
			HashSet<ArrayList<String>> itemsPrisNew = new HashSet<ArrayList<String>>();
			for(ArrayList<String> listItems : itemsPris) {
				int cpt=0;
				for(int i =0 ;i<data.getContenu().size();i++) {
					if(data.getContenu().get(i).getItemList().containsAll(listItems)) { //si la transaction contient tous l'itemset
						cpt++;
					}
				}
				if(cpt>=supportMin) { //on prend l'item
					itemsPrisNew.add(listItems);
					listItemFrequence.put(listItems, cpt);
				}
			}
			//mettre a jour de liste
			itemsPris = itemsPrisNew;
			//ajouter a la hashset global
			if(!itemsPris.isEmpty()) {
			frequentItemsets.addAll(itemsPris);
			}
		}
		
		//Regle d'association
		for(ArrayList<String> a:frequentItemsets) {
			for(ArrayList<String> b : frequentItemsets) {
				if(a.containsAll(b)) {
					float confidence;
					float var1 = listItemFrequence.get(a); 
					float var2 = listItemFrequence.get(b);
					confidence= var1/var2;
					if(confidence>=confidenceMin) { //ajouter la regle d'association
						ArrayList<String> temp = new  ArrayList<String>();
						if(!a.equals(b) ) {
							temp.addAll(b);
							temp.add("=>");
							for(String s : a) {
								if(!b.contains(s)) {
									temp.add(s);
								}
							}
							regleAssociation.put(temp,confidence);
						}
					}
				}
			}
		}
		//le temps d'execution estime
		long endTime = System.currentTimeMillis();
		double duration = endTime - startTime;
		
		//fin d'algo
		Object[] obj = new Object[3];
		
		obj[0] = frequentItemsets; 
		obj[1] = regleAssociation;
		obj[2] = duration;
		
		return obj;
	}
	
	public HashSet<ArrayList<String>> aprioriGenerator(HashSet<ArrayList<String>> listIn){
		
		HashSet<ArrayList<String>> listOutput = new HashSet<ArrayList<String>>();
		ArrayList<String> newItem;
		Object tab[] =(Object[])listIn.toArray();
		
		for(int i=0;i<tab.length-1;i++) {
			for(int j=i+1; j<tab.length;j++) {
				//recuperer les deux listes L1 et L2
				ArrayList<String> L1 = (ArrayList<String>) tab[i];
				ArrayList<String> L2 = (ArrayList<String>) tab[j];
				
				boolean b = true;
				newItem = new ArrayList<String>();
				
				for(int k=0;k<L1.size()-1;k++) {
					if(!L1.get(k).equals(L2.get(k))) {b=false; break;}
					newItem.add(L1.get(k));
				}
				if(b==true) { // les element de L1 et L2 sont egaux jusqu'a taille-1
					if(L1.size()>=1) {
					newItem.add(L1.get(L1.size()-1));
					newItem.add(L2.get(L2.size()-1));
					//ajouter le nouveau item a notre hashset
					Collections.sort(newItem); // il faut le trie tous d'abord
					listOutput.add(newItem);
					}
				}
			}
		}
		return listOutput;	
	}
}
