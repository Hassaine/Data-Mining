package data.mining.algorithms;

public class Instance {
	private String tab[];
	
	public Instance(String tab[]) {
		this.tab=tab;
	}
	
	
	public String getAge() {
		return tab[0];
	}
	public String getSex() {
		return tab[1];
	}
	public String getChest() {
		return tab[2];
	}
	public String getResting_blood_pressure() {
		return tab[3];
	}
	public String getSerum_cholestoral() {
		return tab[4];
	}
	public String getFasting_blood_sugar() {
		return tab[5];
	}
	public String getResting_electrocardiographic_results() {
		return tab[6];
	}
	public String getMaximum_heart_rate_achieved() {
		return tab[7];
	}
	public String getExercise_induced_angina() {
		return tab[8];
	}
	public String getOldpeak() {
		return tab[9];
	}
	public String getSlope() {
		return tab[10];
	}
	public String getNumber_of_major_vessels() {
		return tab[11];
	}
	public String getThal() {
		return tab[12];
	}
	public String getClassPredected() {
		return tab[13];
	}
	
	public String[] getTab() {
		return tab;
	}

	public void setTab(String[] tab) {
		this.tab = tab;
	}
	
	public String toString() {
		String s="[";
		for(int i=0; i<tab.length-1;i++) {
			s+=tab[i]+",";
		}
		s+=tab[tab.length-1]+"]\n";
		return s;
	}
	
}
