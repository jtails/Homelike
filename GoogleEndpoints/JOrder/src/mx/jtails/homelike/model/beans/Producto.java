package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;
import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idProducto;

	@Column(name="costo_unitario")
	private float costoUnitario;

	private String descripcion;

	private String presentacion;
	
	
	//-3 Borrado logico
	private int status;

	//bi-directional many-to-one association to CProducto
	@JoinColumn(name="id_c_producto")	
	@ManyToOne(fetch=FetchType.EAGER)
	private CProducto cproducto;

	//bi-directional many-to-one association to Proveedor
	@JsonBackReference
	@JoinColumn(name="id_proveedor")
	@ManyToOne(fetch=FetchType.EAGER)
	private Proveedor proveedor;

	public Producto() {
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public float getCostoUnitario() {
		return this.costoUnitario;
	}

	public void setCostoUnitario(float costoUnitario) {
		this.costoUnitario = costoUnitario;
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

	public CProducto getCproducto() {
		return this.cproducto;
	}

	public void setCproducto(CProducto cproducto) {
		this.cproducto = cproducto;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}