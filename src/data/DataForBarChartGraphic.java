package data;

public class DataForBarChartGraphic {
	private int value=0;
	private String  compareValue;
	private String 	WhoHaveValue;
	public int getValue() {
		return value;
	}
	public String getCompareValue() {
		return compareValue;
	}
	public String getWhoHaveValue() {
		return WhoHaveValue;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setCompareValue(String compareValue) {
		this.compareValue = compareValue;
	}
	public void setWhoHaveValue(String whoHaveValue) {
		WhoHaveValue = whoHaveValue;
	}
	public DataForBarChartGraphic(int value, String compareValue, String whoHaveValue) {
		this.value = value;
		this.compareValue = compareValue;
		this.WhoHaveValue = whoHaveValue;
	}
	
	
}
