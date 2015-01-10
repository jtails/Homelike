package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	
	public Cuenta(){
		Calendar calendar=Calendar.getInstance();
		fechaHoraCreacion=calendar.getTime();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cuenta")
	private int idCuenta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_creacion")
	private Date fechaHoraCreacion;

	private String telefono;
	
	private String usuario;

	@Transient
	private int numPedidos;
	
	@Transient
	private int status;
	
	@Transient 
	private Date fechaHoraUltimoPedido;

	public Date getFechaHoraUltimoPedido() {
		return fechaHoraUltimoPedido;
	}

	public void setFechaHoraUltimoPedido(Date fechaHoraUltimoPedido) {
		this.fechaHoraUltimoPedido = fechaHoraUltimoPedido;
	}

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="cuenta",cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<Direccion> direcciones;

	//bi-directional many-to-one association to Dispositivo
	@OneToMany(mappedBy="cuenta",cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<Dispositivo> dispositivos;

	//bi-directional many-to-one association to Pedido
	@JsonIgnore
	@OneToMany(mappedBy="cuenta",cascade = {CascadeType.PERSIST},fetch=FetchType.LAZY)
	@Size(min=1, max=10)
	private List<Pedido> pedidos;


	public int getIdCuenta() {
		return this.idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Date getFechaHoraCreacion() {
		return this.fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Direccion> getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDirecciones().add(direccion);
		direccion.setCuenta(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDirecciones().remove(direccion);
		direccion.setCuenta(null);

		return direccion;
	}

	public List<Dispositivo> getDispositivos() {
		return this.dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Dispositivo addDispositivo(Dispositivo dispositivo) {
		getDispositivos().add(dispositivo);
		dispositivo.setCuenta(this);

		return dispositivo;
	}

	public Dispositivo removeDispositivo(Dispositivo dispositivo) {
		getDispositivos().remove(dispositivo);
		dispositivo.setCuenta(null);

		return dispositivo;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setCuenta(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setCuenta(null);

		return pedido;
	}
	
	public int getNumPedidos() {
		return numPedidos;
	}

	public void setNumPedidos(int numPedidos) {
		this.numPedidos = numPedidos;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}