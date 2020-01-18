package data.mining.algorithms;

import java.util.ArrayList;

public class Transaction {
	ArrayList<String> itemList = new ArrayList<String>();

	public Transaction(ArrayList<String> itemList) {
		this.itemList = itemList;
	}

	public ArrayList<String> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<String> itemList) {
		this.itemList = itemList;
	}
	
	
}
