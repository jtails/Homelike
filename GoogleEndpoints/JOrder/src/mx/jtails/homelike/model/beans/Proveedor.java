package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Proveedor(){
		Calendar calendar=Calendar.getInstance();
		fechaHoraCreacion=calendar.getTime();
		calificacion=5;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor")
	private int idProveedor;

	private String calle;

	private String calle1;

	private String calle2;

	private String colonia;

	private String cp;

	private String delegacion;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_creacion")
	private Date fechaHoraCreacion;

	private String latitud;

	@Lob
	private String logo;

	private String longitud;

	private String nelatitud;

	private String nelongitud;

	private String nexterior;

	private String ninterior;

	private String nombre;

	private String pais;

	@Column(name="razon_social")
	private String razonSocial;

	private String referencia1;

	private String referencia2;

	private String rfc;

	private String slogan;
	
	private String swlatitud;

	private String swlongitud;

	private String telefono;

	private String usuario;
	
	//-1 Nombre de Usuario existente
	//-2 Usuario invalido
	//0  Usuario Deshabiliado
	//1  Usuario Habilitado
	private int status;
	@Transient
	private int numPedidos;
	@Transient
	private int numClientes;
	
	private int calificacion;
	@Temporal(TemporalType.DATE)
	@Transient 
	private Date fechaHoraUltimoPedido;
	
	

	public Date getFechaHoraUltimoPedido() {
		return fechaHoraUltimoPedido;
	}

	public void setFechaHoraUltimoPedido(Date fechaHoraUltimoPedido) {
		this.fechaHoraUltimoPedido = fechaHoraUltimoPedido;
	}

	
	//bi-directional many-to-one association to Dispositivo
	@OneToMany(mappedBy="proveedor",cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<Dispositivop> dispositivos;
	
	//bi-directional many-to-one association to Pedido
	@JsonIgnore
	@OneToMany(mappedBy="proveedor",fetch=FetchType.LAZY)
	//@OneToMany(mappedBy="proveedor",fetch=FetchType.EAGER)
	//@Size(min=1, max=10)
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy="proveedor",fetch=FetchType.EAGER)
	private List<HorariosProveedor> horarios;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="proveedor",fetch=FetchType.EAGER)
	private List<Producto> productos;

	//bi-directional many-to-one association to Servicio
	//El default para Referencias a Objetos(No Collections) es FetchType.EAGER (Recupera Dependencias por Default)
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	
	public List<HorariosProveedor> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorariosProveedor> horarios) {
		this.horarios = horarios;
	}

	public int getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
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

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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

	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getSlogan() {
		return this.slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
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

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setProveedor(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setProveedor(null);

		return pedido;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
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
	
	public int getNumPedidos() {
		return numPedidos;
	}

	public void setNumPedidos(int numPedidos) {
		this.numPedidos = numPedidos;
	}
	
	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public int getNumClientes() {
		return numClientes;
	}

	public void setNumClientes(int numClientes) {
		this.numClientes = numClientes;
	}
	
	
	public List<Dispositivop> getDispositivos() {
		return this.dispositivos;
	}

	public void setDispositivos(List<Dispositivop> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Dispositivop addDispositivo(Dispositivop dispositivo) {
		getDispositivos().add(dispositivo);
		dispositivo.setProveedor(this);

		return dispositivo;
	}

	public Dispositivop removeDispositivo(Dispositivop dispositivo) {
		getDispositivos().remove(dispositivo);
		dispositivo.setProveedor(null);

		return dispositivo;
	}

}