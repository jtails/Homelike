package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the c_producto database table.
 * 
 */
@Entity
@Table(name="c_producto")
@NamedQuery(name="CProducto.findAll", query="SELECT c FROM CProducto c")
public class CProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_c_producto")
	private int idCProducto;

	private String descripcion;

	private String presentacion;

	//bi-directional many-to-one association to Servicio
	//Para Evitar Recursividad,solo aplica para Tipos, no para Collections
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	public CProducto() {
	}

	public int getIdCProducto() {
		return this.idCProducto;
	}

	public void setIdCProducto(int idCProducto) {
		this.idCProducto = idCProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPresentacion() {
		return this.presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}