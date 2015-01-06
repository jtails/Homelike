package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the dispositivor database table.
 * 
 */
@Entity
@NamedQuery(name="Dispositivor.findAll", query="SELECT d FROM Dispositivor d")
public class Dispositivor implements Serializable {
	private static final long serialVersionUID = 1L;

	public Dispositivor() {
		Calendar calendar=Calendar.getInstance();
		fechaHoraCreacion=calendar.getTime();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dispositivor")
	private int idDispositivor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_creacion")
	private Date fechaHoraCreacion;

	private String gcmid;

	private String imei;

	private String modelo;

	private String plataforma;

	@Column(name="tipo_dispositivo")
	private String tipoDispositivo;

	//@JsonBackReference para evitar recursividad durante la lectura del JSON
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_repartidor")
	private Repartidor repartidor;

	public int getIdDispositivor() {
		return this.idDispositivor;
	}

	public void setIdDispositivor(int idDispositivor) {
		this.idDispositivor = idDispositivor;
	}

	public Date getFechaHoraCreacion() {
		return this.fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	public String getGcmid() {
		return this.gcmid;
	}

	public void setGcmid(String gcmid) {
		this.gcmid = gcmid;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlataforma() {
		return this.plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getTipoDispositivo() {
		return this.tipoDispositivo;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public Repartidor getRepartidor() {
		return this.repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

}