package data;

import java.util.HashMap;

public class MediatorResult {
	private HashMap<String, Integer> result;
	private String pedagogie;
	private String information;
	
	
	public MediatorResult( String pedagogie) {
		this.result= new HashMap<String, Integer>();
		this.pedagogie = pedagogie;
	}
	
	public HashMap<String, Integer> getResult() {
		return result;
	}
	
	public String getPedagogie() {
		return pedagogie+"\n ---\n"+information;
	}
	
	public void setInformation(String information) {
		this.information = information;
	}
	
	public void setResult(HashMap<String, Integer> result) {
		this.result = result;
	}
	public void put (String name,Integer value) {
		result.put(name, value);
	}
	public void setPedagogie(String pedagogie) {
		this.pedagogie = pedagogie;
	}
}
