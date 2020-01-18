package data.mining.algorithms;

import java.util.ArrayList;


public class TransactionDataSet {
	private ArrayList<Attribut> attributs;
	private ArrayList<Transaction> contenu;
	
	public TransactionDataSet() {
		createTransactionDataSet();
	}
	
	public ArrayList<Attribut> getAttributs() {
		return attributs;
	}
	public void setAttributs(ArrayList<Attribut> attributs) {
		this.attributs = attributs;
	}
	public ArrayList<Transaction> getContenu() {
		return contenu;
	}
	public void setContenu(ArrayList<Transaction> contenu) {
		this.contenu = contenu;
	}
	
	public void createTransactionDataSet() {
		String url = "data/HEART_Stat.arff";
		Extraction ex = new Extraction(url);
		ArrayList<Transaction> tempContenu = new ArrayList<Transaction>();
		
		for(int i =0 ;i<ex.getData().getContenu().size();i++) {
			Instance temp = ex.getData().getContenu().get(i);
			ArrayList<String> itemList = new ArrayList<String>();
			//traitement sur une instance
			
			//attribut age
			if(Integer.parseInt(temp.getAge())>29 && Integer.parseInt(temp.getAge())<=45) {
				itemList.add("Age1");			
				}
			if(Integer.parseInt(temp.getAge())>45 && Integer.parseInt(temp.getAge())<=52) {
				itemList.add("Age2");			
				}
			if(Integer.parseInt(temp.getAge())>52 && Integer.parseInt(temp.getAge())<=58) {
				itemList.add("Age3");			
				}
			if(Integer.parseInt(temp.getAge())>58 && Integer.parseInt(temp.getAge())<=62) {
				itemList.add("Age4");			
				}
			if(Integer.parseInt(temp.getAge())>62 && Integer.parseInt(temp.getAge())<=77) {
				itemList.add("Age5");
			}
			
			//attribut resting_blood_pressure
			if(Integer.parseInt(temp.getResting_blood_pressure())>=94 && Integer.parseInt(temp.getResting_blood_pressure())<=118) {
				itemList.add("RBD1");
			}
			if(Integer.parseInt(temp.getResting_blood_pressure())>118 && Integer.parseInt(temp.getResting_blood_pressure())<=125) {
				itemList.add("RBD2");
			}
			if(Integer.parseInt(temp.getResting_blood_pressure())>125 && Integer.parseInt(temp.getResting_blood_pressure())<=134) {
				itemList.add("RBD3");
			}
			if(Integer.parseInt(temp.getResting_blood_pressure())>134 && Integer.parseInt(temp.getResting_blood_pressure())<=144) {
				itemList.add("RBD4");
			}
			if(Integer.parseInt(temp.getResting_blood_pressure())>144 && Integer.parseInt(temp.getResting_blood_pressure())<=200) {
				itemList.add("RBD5");
			}

//			if(Integer.parseInt(temp.getResting_blood_pressure())>118 && Integer.parseInt(temp.getResting_blood_pressure())<=125) {
//				itemList.add("RBPMedium");
//			}
//			if(Integer.parseInt(temp.getResting_blood_pressure())>140) {
//				itemList.add("RBPHigh");
//			}
			
			//attribut serum_cholestoral
			if(Integer.parseInt(temp.getSerum_cholestoral())>126 && Integer.parseInt(temp.getSerum_cholestoral())<=226) {
				itemList.add("CTR1");
			}
			if(Integer.parseInt(temp.getSerum_cholestoral())>226 && Integer.parseInt(temp.getSerum_cholestoral())<=267) {
				itemList.add("CTR2");
			}
			if(Integer.parseInt(temp.getSerum_cholestoral())>267 && Integer.parseInt(temp.getSerum_cholestoral())<=564) {
				itemList.add("CTR3");
			}
			
			//attribut maximum_heart_rate_achieved
			if(Integer.parseInt(temp.getMaximum_heart_rate_achieved())>71 && Integer.parseInt(temp.getMaximum_heart_rate_achieved())<=133) {
				itemList.add("MHR1");
			}
			if(Integer.parseInt(temp.getMaximum_heart_rate_achieved())>133 && Integer.parseInt(temp.getMaximum_heart_rate_achieved())<=153) {
				itemList.add("MHR2");
			}
			if(Integer.parseInt(temp.getMaximum_heart_rate_achieved())>153 && Integer.parseInt(temp.getMaximum_heart_rate_achieved())<=166) {
				itemList.add("MHR3");
			}
			if(Integer.parseInt(temp.getMaximum_heart_rate_achieved())>166 && Integer.parseInt(temp.getMaximum_heart_rate_achieved())<=202) {
				itemList.add("MHR4");
			}


			tempContenu.add(new Transaction(itemList));
		}
		//fin du traitement
		this.attributs=ex.getData().getAttributs();
		this.contenu = tempContenu;	
	}
	
	
}
