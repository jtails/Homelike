package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the dispositivop database table.
 * 
 */
@Entity
@NamedQuery(name="Dispositivop.findAll", query="SELECT d FROM Dispositivop d")
public class Dispositivop implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	public Dispositivop() {
		Calendar calendar=Calendar.getInstance();
		fechaHoraCreacion=calendar.getTime();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dispositivo")
	private int idDispositivo;

	@Column(name="es_default")
	private int esDefault;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_creacion")
	private Date fechaHoraCreacion;

	private String gcmid;

	//@JsonBackReference para evitar recursividad durante la lectura del JSON
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	private String imei;

	private String modelo;

	private String plataforma;

	@Column(name="tipo_dispositivo")
	private String tipoDispositivo;

	public int getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public int getEsDefault() {
		return this.esDefault;
	}

	public void setEsDefault(int esDefault) {
		this.esDefault = esDefault;
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

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

}