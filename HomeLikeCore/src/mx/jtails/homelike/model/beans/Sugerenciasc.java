package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the sugerenciasc database table.
 * 
 */
@Entity
@NamedQuery(name="Sugerenciasc.findAll", query="SELECT s FROM Sugerenciasc s")
public class Sugerenciasc implements Serializable {
	private static final long serialVersionUID = 1L;

	public Sugerenciasc() {
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

	@JoinColumn(name="id_cuenta")
	@ManyToOne(fetch=FetchType.EAGER)
	private Cuenta cuenta;

	
	@JoinColumn(name="id_tsugerencia")
	@ManyToOne(fetch=FetchType.EAGER)
	private Tsugerencia tsugerencia;
		
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

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getSugerencia() {
		return this.sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}
	
	public Tsugerencia getTsugerencia() {
		return tsugerencia;
	}

	public void setTsugerencia(Tsugerencia tsugerencia) {
		this.tsugerencia = tsugerencia;
	}


}