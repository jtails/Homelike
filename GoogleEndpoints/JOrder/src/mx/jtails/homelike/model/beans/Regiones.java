package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the regiones database table.
 * 
 */
@Entity
@NamedQuery(name="Regiones.findAll", query="SELECT r FROM Regiones r")
public class Regiones implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Regiones(){
		Calendar calendar=Calendar.getInstance();
		fechaHoraSegmentacion=calendar.getTime();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_region")
	private int idRegion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_segmentacion")
	private Date fechaHoraSegmentacion;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	private String nelatitud;

	private String nelongitud;

	private String swlatitud;

	private String swlongitud;
	
	private String label;
	
	
	//0- Activo
	//1- Inactivo (Eliminado)
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public Date getFechaHoraSegmentacion() {
		return this.fechaHoraSegmentacion;
	}

	public void setFechaHoraSegmentacion(Date fechaHoraSegmentacion) {
		this.fechaHoraSegmentacion = fechaHoraSegmentacion;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getNelatitud() {
		return this.nelatitud;
	}

	public void setNelatitud(String nelatitud) {
		this.nelatitud = nelatitud;
	}

	public String getNelongitud() {
		return this.nelongitud;
	}

	public void setNelongitud(String nelongitud) {
		this.nelongitud = nelongitud;
	}

	public String getSwlatitud() {
		return this.swlatitud;
	}

	public void setSwlatitud(String swlatitud) {
		this.swlatitud = swlatitud;
	}

	public String getSwlongitud() {
		return this.swlongitud;
	}

	public void setSwlongitud(String swlongitud) {
		this.swlongitud = swlongitud;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}