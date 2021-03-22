package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MediatorResult {
	private HashMap<String, Integer> result;
	// is use for the all graphic could be create 
	private String pedagogie;
	private String information;
	// is use for circular graphic
	List<DataforCiruclarGraphic> cicularGraphic = new ArrayList<DataforCiruclarGraphic>();
	
	// is use for linear graphic 
	List<DataForLinearGraphic> linearGraphics = new ArrayList<DataForLinearGraphic>();
	private String valueX;
	private String valueY;
	private String nameCourbe;
	
	public String getInformation() {
		return information;
	}

	public List<DataforCiruclarGraphic> getCicularGraphic() {
		return cicularGraphic;
	}

	public List<DataForLinearGraphic> getLinearGraphics() {
		return linearGraphics;
	}

	public String getValueX() {
		return valueX;
	}

	public String getValueY() {
		return valueY;
	}

	public String getNameCourbe() {
		return nameCourbe;
	}

	public List<DataForBarChartGraphic> getBarChartGraphic() {
		return BarChartGraphic;
	}

	public String getValueCompare() {
		return valueCompare;
	}

	public void setCicularGraphic(List<DataforCiruclarGraphic> cicularGraphic) {
		this.cicularGraphic = cicularGraphic;
	}

	public void setLinearGraphics(List<DataForLinearGraphic> linearGraphics) {
		this.linearGraphics = linearGraphics;
	}

	public void setValueX(String valueX) {
		this.valueX = valueX;
	}

	public void setValueY(String valueY) {
		this.valueY = valueY;
	}

	public void setNameCourbe(String nameCourbe) {
		this.nameCourbe = nameCourbe;
	}

	public void setBarChartGraphic(List<DataForBarChartGraphic> barChartGraphic) {
		BarChartGraphic = barChartGraphic;
	}

	public void setValueCompare(String valueCompare) {
		this.valueCompare = valueCompare;
	}
	// is use for BarChart graphic 
	List<DataForBarChartGraphic> BarChartGraphic = new ArrayList<DataForBarChartGraphic>();
	private String valueCompare;
	
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
