package data.mining.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Extraction {
	private DataSet data;
	
	public Extraction(String url) {
		try {
			data = extraireDonnees(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DataSet getData() {
		return data;
	}

	public void setData(DataSet data) {
		this.data = data;
	}
	
	private DataSet extraireDonnees(String url) throws IOException {
		File fichier = new File(url);
		InputStream fichierIn = new FileInputStream(fichier);
		{
			InputStreamReader lecture = new InputStreamReader(fichierIn);
			BufferedReader bufIn = new BufferedReader(lecture);
			String ligne;
			
			ArrayList<Attribut> attributs = new ArrayList<Attribut>();
			ArrayList<Instance> contenu = new ArrayList<Instance>();
			int misingValues=0;
			ligne = bufIn.readLine();
			while(ligne!= null && !ligne.equalsIgnoreCase("@data")) {
				
				if(ligne.startsWith("@attribute")) {
					String tempTab[] = ligne.split(" ");
					String nom = tempTab[1];
					String type = tempTab[2];
					String valeurs="";
					if(!type.matches("[a-zA-Z]+")) { 
						type =" nominal";
						valeurs = ligne.substring(tempTab[0].length()+tempTab[1].length()+2, ligne.length());
					}
					attributs.add(new Attribut(nom, type,valeurs));
				}
				if(ligne.contains("missing values")) {
					String tempTab[] = ligne.split(" ");
					if(!tempTab[1].equalsIgnoreCase("no")){
						misingValues = Integer.parseInt(tempTab[1]);
					}
				}
				ligne = bufIn.readLine();	
			}
			ligne = bufIn.readLine();
			while(ligne!=null) {
				String tempTab[] = ligne.split(",");
				if(tempTab[tempTab.length-1].equalsIgnoreCase("present")) {
					tempTab[tempTab.length-1]="1";
				}
				else {
					tempTab[tempTab.length-1]="0";
				}
				contenu.add(new Instance(tempTab));
				ligne = bufIn.readLine();
			}
			bufIn.close();
			
			return new DataSet(attributs,contenu,misingValues);
		}
	}
}
