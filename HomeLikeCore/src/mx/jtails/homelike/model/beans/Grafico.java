package mx.jtails.homelike.model.beans;
import java.io.Serializable;

public class Grafico implements Serializable{
	private String label;
	private float value;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
}