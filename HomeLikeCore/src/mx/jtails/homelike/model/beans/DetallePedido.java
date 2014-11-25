package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the detalle_pedido database table.
 * 
 */
@Entity
@Table(name="detalle_pedido")
@NamedQuery(name="DetallePedido.findAll", query="SELECT d FROM DetallePedido d")
public class DetallePedido implements Serializable {
	private static final long serialVersionUID = 1L;

	private int cantidad;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_pedido")
	private int idDetallePedido;

	//bi-directional many-to-one association to Pedido
	//@JsonBackReference para evitar recursividad durante la lectura del JSON
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public DetallePedido() {
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public int getIdDetallePedido() {
		return idDetallePedido;
	}

	public void setIdDetallePedido(int idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}

}