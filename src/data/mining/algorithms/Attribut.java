package data.mining.algorithms;

import java.util.ArrayList;

public class Attribut {
	private String nom;
	private String type;
	private String valeurs;
	private float moyenne;
	private float mediane;
	private ArrayList<Float> mode;
	private float[] valeursBoxPlot;
	

	public Attribut(String nom, String type,String valeurs) {
		this.nom = nom;
		this.type = type;
		this.valeurs = valeurs;
		this.valeursBoxPlot = new float[5];
	}
	
	public float[] getValeursBoxPlot() {
		return valeursBoxPlot;
	}

	public void setValeursBoxPlot(float[] valeursBoxPlot) {
		this.valeursBoxPlot = valeursBoxPlot;
	}
	
	public float getMoyenne() {
		return moyenne;
	}
	
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	
	public float getMediane() {
		return mediane;
	}
	
	public void setMediane(float mediane) {
		this.mediane = mediane;
	}
	
	public ArrayList<Float> getMode() {
		return mode;
	}
	
	public void setMode(ArrayList<Float> mode) {
		this.mode = mode;
	}
	
	public String getValeurs() {
		return valeurs;
	}
	
	public void setValeurs(String valeurs) {
		this.valeurs = valeurs;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
}
