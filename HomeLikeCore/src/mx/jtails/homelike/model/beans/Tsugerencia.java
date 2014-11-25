package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the tsugerencia database table.
 * 
 */
@Entity
@NamedQuery(name="Tsugerencia.findAll", query="SELECT t FROM Tsugerencia t")
public class Tsugerencia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Tsugerencia() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tsugerencia")
	private int idTsugerencia;

	@Column(name="tipo_sugerencia")
	private String tipoSugerencia;

	
	public int getIdTsugerencia() {
		return idTsugerencia;
	}

	public void setIdTsugerencia(int idTsugerencia) {
		this.idTsugerencia = idTsugerencia;
	}

	public String getTipoSugerencia() {
		return tipoSugerencia;
	}

	public void setTipoSugerencia(String tipoSugerencia) {
		this.tipoSugerencia = tipoSugerencia;
	}
}
