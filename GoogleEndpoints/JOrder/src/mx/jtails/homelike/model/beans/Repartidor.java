package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the repartidor database table.
 * 
 */
@Entity
@NamedQuery(name="Repartidor.findAll", query="SELECT r FROM Repartidor r")
public class Repartidor implements Serializable {
	private static final long serialVersionUID = 1L;

	public Repartidor(){
		Calendar calendar=Calendar.getInstance();
		fechaHoraCreacion=calendar.getTime();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_repartidor")
	private int idRepartidor;

	private String amaterno;

	private String apaterno;
	
	//-3 Borrado logico
	private int status;

	//@JsonBackReference para evitar recursividad durante la lectura del JSON
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	private String nombres;

	private String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_creacion")
	private Date fechaHoraCreacion;

	//bi-directional many-to-one association to Dispositivor
	@OneToMany(mappedBy="repartidor",cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<Dispositivor> dispositivos;

	public int getIdRepartidor() {
		return this.idRepartidor;
	}

	public void setIdRepartidor(int idRepartidor) {
		this.idRepartidor = idRepartidor;
	}

	public String getAmaterno() {
		return this.amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public String getApaterno() {
		return this.apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Dispositivor> getDispositivos() {
		return this.dispositivos;
	}

	public void setDispositivos(List<Dispositivor> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Dispositivor addDispositivo(Dispositivor dispositivo) {
		getDispositivos().add(dispositivo);
		dispositivo.setRepartidor(this);

		return dispositivo;
	}

	public Dispositivor removeDispositivo(Dispositivor dispositivo) {
		getDispositivos().remove(dispositivo);
		dispositivo.setRepartidor(null);

		return dispositivo;
	}

	public Date getFechaHoraCreacion() {
		return this.fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}