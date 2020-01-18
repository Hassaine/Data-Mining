package data.mining.algorithms;

import java.util.ArrayList;

public class Cluster {
	public int index;
	public Instance medoid;
	public ArrayList<Instance> elements;
	public ArrayList<Double> dissimilarity;
	
	public Cluster(Instance medoid,int index) {
		this.index = index;
		this.medoid = medoid;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Instance getMedoid() {
		return medoid;
	}

	public void setMedoid(Instance medoid) {
		this.medoid = medoid;
	}

	public ArrayList<Instance> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Instance> elements) {
		this.elements = elements;
	}

	public ArrayList<Double> getDissimilarity() {
		return dissimilarity;
	}

	public void setDissimilarity(ArrayList<Double> dissimilarity) {
		this.dissimilarity = dissimilarity;
	}
	
	
	
	
}
