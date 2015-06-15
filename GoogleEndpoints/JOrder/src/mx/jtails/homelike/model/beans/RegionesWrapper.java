package mx.jtails.homelike.model.beans;

import java.io.Serializable;
import java.util.List;

public class RegionesWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Regiones> regiones;

	public List<Regiones> getRegiones() {
		return regiones;
	}

	public void setRegiones(List<Regiones> regiones) {
		this.regiones = regiones;
	}
}