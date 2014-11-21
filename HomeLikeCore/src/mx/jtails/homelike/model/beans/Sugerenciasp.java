package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the sugerenciasp database table.
 * 
 */
@Entity
@NamedQuery(name="Sugerenciasp.findAll", query="SELECT s FROM Sugerenciasp s")
public class Sugerenciasp implements Serializable {
	private static final long serialVersionUID = 1L;

	public Sugerenciasp() {
		Calendar calendar=Calendar.getInstance();
		fechaHoraSugerencia=calendar.getTime();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sugerencia")
	private int idSugerencia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_sugerencia")
	private Date fechaHoraSugerencia;

	@JoinColumn(name="id_proveedor")
	@ManyToOne(fetch=FetchType.EAGER)
	private Proveedor proveedor;

	private String sugerencia;

	public int getIdSugerencia() {
		return this.idSugerencia;
	}

	public void setIdSugerencia(int idSugerencia) {
		this.idSugerencia = idSugerencia;
	}

	public Date getFechaHoraSugerencia() {
		return this.fechaHoraSugerencia;
	}

	public void setFechaHoraSugerencia(Date fechaHoraSugerencia) {
		this.fechaHoraSugerencia = fechaHoraSugerencia;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getSugerencia() {
		return this.sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}

}