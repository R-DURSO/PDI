package data;

import java.util.HashMap;

public class MediatorResult {
private HashMap<String, Integer> result;
private String pedagogie;
public HashMap<String, Integer> getResult() {
	return result;
}
public String getPedagogie() {
	return pedagogie;
}
public void setResult(HashMap<String, Integer> result) {
	this.result = result;
}
public void setPedagogie(String pedagogie) {
	this.pedagogie = pedagogie;
}
public MediatorResult(HashMap<String, Integer> result, String pedagogie) {
	super();
	this.result = result;
	this.pedagogie = pedagogie;
}

}
