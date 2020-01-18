package data.mining.algorithms;

import java.util.ArrayList;
import java.util.Collections;

public class DataSet {
	private ArrayList<Attribut> attributs;
	private ArrayList<Instance> contenu;
	private int misingValues;
	
	
	public DataSet(ArrayList<Attribut> attributs, ArrayList<Instance> contenu,int misingValues) {
		this.attributs = attributs;
		this.contenu = contenu;
		this.misingValues = misingValues;
	}
	public ArrayList<Attribut> getAttributs() {
		return attributs;
	}
	public void setAttributs(ArrayList<Attribut> attributs) {
		this.attributs = attributs;
	}
	public ArrayList<Instance> getContenu() {
		return contenu;
	}
	public void setContenu(ArrayList<Instance> contenu) {
		this.contenu = contenu;
	}
	
	public int getMisingValues() {
		return misingValues;
	}
	public void setMisingValues(int misingValues) {
		this.misingValues = misingValues;
	}
	public String getInfosAttributs() {
		String info="";
		ArrayList<String> tempListTypes = new ArrayList<String>();
		for(Attribut a :attributs) {
			if(!tempListTypes.contains(a.getType())) {tempListTypes.add(a.getType());}
		}
		int tempListCpt[] =new int[tempListTypes.size()];
		for(Attribut a :attributs) {
			tempListCpt[tempListTypes.indexOf(a.getType())]+=1;
		}
		String att="attribut";
		for(int i=0;i<tempListTypes.size();i++) {
			if(tempListCpt[i]>1) {att="attributs";}
			info +=tempListCpt[i]+" "+att+" de type : "+tempListTypes.get(i)+"\n";
		}
		info+= misingValues+" mising values\n";
		info+="Nom\t Type\t Valeurs\n";
		for(Attribut a : attributs) {
			info+=a.getNom()+"\t"+a.getType()+"\t"+a.getValeurs()+"\n";
		}
		
		return info;
	}
	
	public void calculer() {
		int i=0;
		for(Attribut a : attributs) {
			if(a.getNom().equalsIgnoreCase("resting_blood_pressure")||a.getNom().equalsIgnoreCase("serum_cholestoral")||a.getNom().equalsIgnoreCase("maximum_heart_rate_achieved")||a.getNom().equalsIgnoreCase("age")) {
				//calcule moyenne
				int som=0;
				float moyenne;
				
				ArrayList<Float> tempList = new ArrayList<Float>();
				for(int j=0;j<contenu.size();j++) {
					som+=Integer.parseInt(contenu.get(j).getTab()[i]);
					tempList.add(Float.parseFloat(contenu.get(j).getTab()[i]));
				}
				moyenne = som/contenu.size();
				attributs.get(i).setMoyenne(moyenne);
				
				//calcule mediane
				Collections.sort(tempList);
				float mediane;
				if(tempList.size()%2!=0) {
					mediane = tempList.get((tempList.size()/2)+1);
				}
				else {
					mediane = (tempList.get(tempList.size()/2)+tempList.get((tempList.size()/2)+1))/2;
				}
				attributs.get(i).setMediane(mediane);
				
				//calcule mode
				ArrayList<Float> tempValeurUnique = new ArrayList<Float>();
				ArrayList<Integer> tempValeurCpt =new ArrayList<Integer>();
				
				for(float val :tempList) {
					if(!tempValeurUnique.contains(val)) {
						tempValeurUnique.add(val);
						tempValeurCpt.add(1);
						}
					else {
						tempValeurCpt.set(tempValeurUnique.indexOf(val), tempValeurCpt.get(tempValeurUnique.indexOf(val))+1);
					}
				}
				int max = tempValeurCpt.get(0);
				for(int val : tempValeurCpt) {
					if(val>max) {
						max = val;
					}
				}
				
				ArrayList<Float> mode = new ArrayList<Float>();
				for(int j=0;j<tempValeurCpt.size();j++) {
					if(tempValeurCpt.get(j)==max) {
						mode.add(tempValeurUnique.get(j));
					}
				}
				
				attributs.get(i).setMode(mode);	
				
			}else {
				attributs.get(i).setMoyenne(0);
				attributs.get(i).setMediane(0);;
				attributs.get(i).setMode(new ArrayList<Float>());
			}
			
			i++;
		}
		
	}
	
	public void calculerValeurBoxPlot() {
		int i=0;
		for(Attribut a : attributs) {
					
			ArrayList<Float> tempList = new ArrayList<Float>();
			for(int j=0;j<contenu.size();j++) {
				tempList.add(Float.parseFloat(contenu.get(j).getTab()[i]));
			}
			Collections.sort(tempList);
			float temp[] = new float[5];
			temp[0] = tempList.get(0);
			temp[1] = tempList.get((int)(tempList.size()/4));
			float mediane;
			if(tempList.size()%2!=0) {
				mediane = tempList.get((tempList.size()/2)+1);
			}
			else {
				mediane = (tempList.get(tempList.size()/2)+tempList.get((tempList.size()/2)+1))/2;
			}
			temp[2] = mediane;
			temp[3] = tempList.get((int)(tempList.size()*3/4));
			temp[4] = tempList.get(tempList.size()-1);
			attributs.get(i).setValeursBoxPlot(temp);
			i++;
		}
	}
	
	public Object[] getCalcules(String nomAttribut) {
		Object temp[] = new Object[3];
			for(Attribut a : attributs) {
				if(nomAttribut.equalsIgnoreCase(a.getNom())) {
					temp[0] = a.getMoyenne();
					temp[1] = a.getMediane();
					temp[2] = a.getMode();
					return temp;
				}
			}
		
		return temp;
	}
	
	public float[] getvaleursBoxPot(String nomAttribut) {
		float temp[] = new float[5];
		for(Attribut a : attributs) {
			if(nomAttribut.equalsIgnoreCase(a.getNom())) {
				temp = a.getValeursBoxPlot();
				return temp;
			}
		}
		return temp;
	}
	
	
	
}
