package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the direccion database table.
 * 
 */
@Entity
@NamedQuery(name="Direccion.findAll", query="SELECT d FROM Direccion d")
public class Direccion implements Serializable {
	private static final long serialVersionUID = 1L;

	public Direccion(){
		Calendar calendar=Calendar.getInstance();
		fechaHoraCreacion=calendar.getTime();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_direccion")
	private int idDireccion;

	private String calle;

	private String calle1;

	private String calle2;

	private String colonia;

	private String cp;

	private String delegacion;

	@Column(name="es_default")
	private int esDefault;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_creacion")
	private Date fechaHoraCreacion;

	private String latitud;

	private String longitud;

	private String nexterior;

	private String ninterior;

	private String pais;

	private String referencia1;

	private String referencia2;

	//bi-directional many-to-one association to Cuenta
	//@JsonBackReference para evitar recursividad durante la lectura del JSON
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;

	public int getIdDireccion() {
		return this.idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCalle1() {
		return this.calle1;
	}

	public void setCalle1(String calle1) {
		this.calle1 = calle1;
	}

	public String getCalle2() {
		return this.calle2;
	}

	public void setCalle2(String calle2) {
		this.calle2 = calle2;
	}

	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDelegacion() {
		return this.delegacion;
	}

	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}

	public int getEsDefault() {
		return this.esDefault;
	}

	public void setEsDefault(int esDefault) {
		this.esDefault = esDefault;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaHoraCreacion() {
		return this.fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getNexterior() {
		return this.nexterior;
	}

	public void setNexterior(String nexterior) {
		this.nexterior = nexterior;
	}

	public String getNinterior() {
		return this.ninterior;
	}

	public void setNinterior(String ninterior) {
		this.ninterior = ninterior;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getReferencia1() {
		return this.referencia1;
	}

	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}

	public String getReferencia2() {
		return this.referencia2;
	}

	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}