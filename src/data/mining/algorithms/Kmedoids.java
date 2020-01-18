package data.mining.algorithms;

import java.util.ArrayList;
import java.util.Random;
public class Kmedoids {
	String url = "data/HEART_Stat.arff";
	Extraction ex = new Extraction(url);
	public ArrayList<Cluster> clusterList;
	public int k;
	
	public Object[] AlgoKmedoids(int k) {
		clusterList = new ArrayList<Cluster>();
		
		this.k =k;
		if(k>ex.getData().getContenu().size()-1) {return null;}
		
		//calculer le temps d'execution
		long startTime = System.currentTimeMillis(); 
	//Partie 01: Initialisation
		//selectionner k medoids aleatoirement ( de notre dataset)
		//pour cela on remplit une  list avec les indices de dataset
		ArrayList<Integer> listIndex = new ArrayList<Integer>();
		for(int i= 0;i<ex.getData().getContenu().size();i++) {
			listIndex.add(i);
		}
		//apres, on retire a chaque fois un indice de list (pour eviter le cas de generer des indices random)
		Random rand = new Random();
		for(int i=0;i<k;i++) {
			int r = rand.nextInt(listIndex.size());
			int index = listIndex.remove(r);
			Instance medoid = ex.getData().getContenu().get(index);
			Cluster c = new Cluster(medoid,index);
			clusterList.add(c);
		}
		
	//Partie 02: 
		boolean change =true;
		
		while(change) {
			//initialisation of the arraylist of dissimilarities
			for(int i=0;i<k;i++) {
				clusterList.get(i).dissimilarity = new ArrayList<Double>();
			}
			//calculate dissimilarity
			for(int i=0; i<ex.getData().getContenu().size();i++) {
				for(int j =0;j<k;j++) {
					double dis = distance(ex.getData().getContenu().get(i), clusterList.get(j).medoid);
					clusterList.get(j).getDissimilarity().add(dis);
					//System.out.println(clusterList.get(j).getDissimilarity().get(i));
					//System.out.println("[C"+j+",Inst"+i+"] = "+dis);
				}
			}
			//r�initialiser les elements des cluster
			for(int j =0;j<k;j++) {
				clusterList.get(j).elements = new ArrayList<Instance>(); //en les mettre vides
			}
			//attribuer chaque instance a un cluster
			for(int i=0; i<ex.getData().getContenu().size();i++) {
				int minClusterIndex = 0;
				double minDistance = clusterList.get(0).getDissimilarity().get(i);
				//System.out.println(minDistance+" , "+minClusterIndex);
				for(int j =1;j<k;j++) {
					//System.out.println(clusterList.get(j).getDissimilarity().get(i));
					if(clusterList.get(j).getDissimilarity().get(i) < minDistance) { 
						minClusterIndex = j;
						//System.out.println(j);
						minDistance =  clusterList.get(j).getDissimilarity().get(i);
					}
					//System.out.println(clusterList.get(j).getDissimilarity().get(i)+" , "+minClusterIndex);
				}
				//System.out.println(minDistance+" , "+minClusterIndex);
				

				//System.out.println("old = "+clusterList.get(0).getDissimilarity().get(i)+", new = "+minDistance+",index = "+minClusterIndex);
					//ajouter l'instance au cluster
					clusterList.get(minClusterIndex).getElements().add(ex.getData().getContenu().get(i));
				//	System.out.println("cluster= "+minClusterIndex+", instance= "+i);
			//break;
			}
			//choisir al�atoirement un des k medoid cluster
			int Oj = rand.nextInt(k);
			//on le remplace par une instance choisit al�atoirement qui est diff�rente des autres medoids
			listIndex = new ArrayList<Integer>();
			for(int i= 0;i<ex.getData().getContenu().size();i++) {
				listIndex.add(i);
			}
			for(int i =0;i<k;i++) {
				int index = listIndex.indexOf(clusterList.get(i).index);
				listIndex.remove(index);
			}
			int Orand = rand.nextInt(listIndex.size());
			//remplacement de Oj par Orand
			//pour cela nous avons cree une variable temporelle, qui va nous aider � calcluer la difference avant et apres le remplacement d'un des medoids
			ArrayList<Cluster> clusterListTemp = new ArrayList<Cluster>();
			clusterListTemp = clusterList;
			clusterListTemp.get(Oj).medoid = ex.getData().getContenu().get(Orand);
			//calculer le cost total
			
			double absE_Before = absoluteErrorSom(clusterList);
			double absE_After = absoluteErrorSom(clusterListTemp);
			double S = absE_After - absE_Before;
			if(S<0) { //la quality de clustring s'ameliore : donc on accepte le swap 
				clusterList = clusterListTemp;
			}else { //on  arrete le processus car la quality de clustring s'ameiore pas
				change = false;
			}
		}
		//le temps d'execution estim�
		long endTime = System.currentTimeMillis();
		double duration = endTime - startTime;
		//retourner les variables
		Object obj[] =  new Object[2];
		obj[0] = clusterList;
		obj[1] =  duration;
		
		return obj;
	}
	
	
	//cette fonction calcule la distance entre deux instances de dataset
	//la distance est la somme des differences pour chaque attribut des deux instance ( pour le cas numeric)
	public double distance(Instance instance1, Instance instance2) {
		double dist = 0;
		for(int i =0;i<instance1.getTab().length;i++) {
			double dif = Math.pow(Float.parseFloat(instance1.getTab()[i]) - Float.parseFloat(instance2.getTab()[i]),2);
			dist+=dif;
		}
		return Math.sqrt(dist);
	}

	//the sum of absolute error
	public double absoluteErrorSom(ArrayList<Cluster> clusterList) {
		double absE=0;
		for(int i=0;i<k;i++) {
			Cluster c = clusterList.get(i);
			double somC=0;
			for(int j=0;j<c.getElements().size();j++) {
				somC+=Math.abs(distance(c.getElements().get(j),c.medoid));
			}
			absE+=somC;
		}
		return absE;
	}
}
