package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Pedido(){
		//java.util.TimeZone tz=java.util.TimeZone.getTimeZone("America/Mexico_City");
		Calendar calendar=Calendar.getInstance();
		fechaHoraPedido=calendar.getTime();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;

	private int calificacion;

	@Column(name="comentario_cliente")
	private String comentarioCliente;

	@Column(name="comentario_entrega_cliente")
	private String comentarioEntregaCliente;

	@Column(name="comentario_entrega_proveedor")
	private String comentarioEntregaProveedor;

	@Column(name="comentario_proveedor")
	private String comentarioProveedor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_aceptacion")
	private Date fechaHoraAceptacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_entrega")
	private Date fechaHoraEntrega;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_pedido")
	private Date fechaHoraPedido;

	private int status;

	//bi-directional many-to-one association to DetallePedido
	@OneToMany(mappedBy="pedido",cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<DetallePedido> detallePedido;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;

	//bi-directional many-to-one association to Direccion
	@ManyToOne
	@JoinColumn(name="id_direccion")
	private Direccion direccion;

	//bi-directional many-to-one association to Proveedor
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentarioCliente() {
		return this.comentarioCliente;
	}

	public void setComentarioCliente(String comentarioCliente) {
		this.comentarioCliente = comentarioCliente;
	}

	public String getComentarioEntregaCliente() {
		return this.comentarioEntregaCliente;
	}

	public void setComentarioEntregaCliente(String comentarioEntregaCliente) {
		this.comentarioEntregaCliente = comentarioEntregaCliente;
	}

	public String getComentarioEntregaProveedor() {
		return this.comentarioEntregaProveedor;
	}

	public void setComentarioEntregaProveedor(String comentarioEntregaProveedor) {
		this.comentarioEntregaProveedor = comentarioEntregaProveedor;
	}

	public String getComentarioProveedor() {
		return this.comentarioProveedor;
	}

	public void setComentarioProveedor(String comentarioProveedor) {
		this.comentarioProveedor = comentarioProveedor;
	}

	public Date getFechaHoraAceptacion() {
		return this.fechaHoraAceptacion;
	}

	public void setFechaHoraAceptacion(Date fechaHoraAceptacion) {
		this.fechaHoraAceptacion = fechaHoraAceptacion;
	}

	public Date getFechaHoraEntrega() {
		return this.fechaHoraEntrega;
	}

	public void setFechaHoraEntrega(Date fechaHoraEntrega) {
		this.fechaHoraEntrega = fechaHoraEntrega;
	}

	public Date getFechaHoraPedido() {
		return this.fechaHoraPedido;
	}

	public void setFechaHoraPedido(Date fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<DetallePedido> getDetallePedido() {
		return this.detallePedido;
	}

	public void setDetallePedidos(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public DetallePedido addDetallePedido(DetallePedido detallePedido) {
		getDetallePedido().add(detallePedido);
		detallePedido.setPedido(this);

		return detallePedido;
	}

	public DetallePedido removeDetallePedido(DetallePedido detallePedido) {
		getDetallePedido().remove(detallePedido);
		detallePedido.setPedido(null);

		return detallePedido;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}