package data;

public class DataforCircularGraphic {
	private int values=0;
	private String WhoValues;
	public int getValues() {
		return values;
	}
	public String getWhoValues() {
		return WhoValues;
	}
	public void setValues(int values) {
		this.values = values;
	}
	public void setWhoValues(String whoValues) {
		WhoValues = whoValues;
	}
	public DataforCircularGraphic(int values, String whoValues) {
		this.values = values;
		this.WhoValues = whoValues;
	}
	
}
