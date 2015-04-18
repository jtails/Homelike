package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the corte database table.
 * 
 */
@Entity
@NamedQuery(name="Corte.findAll", query="SELECT c FROM Corte c")
public class Corte implements Serializable {
	private static final long serialVersionUID = 1L;

	public Corte(){
		Calendar calendar=Calendar.getInstance();
		fechaHoraCorte=calendar.getTime();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_corte")
	private int idCorte;

	private float adeudo;

	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_fin")
	private Date fechaHoraFin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_inicio")
	private Date fechaHoraInicio;

	@JsonBackReference
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_corte")
	private Date fechaHoraCorte;
	

	private int nopedidos;

	// 0 - Corte Nuevo
	// 1 - Corte finalizado "Pagado"
	private int status;

	
	//bi-directional many-to-one association to Pagos
	@OneToMany(mappedBy="corte",cascade = {CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<Pago> pagos;

	public int getIdCorte() {
		return this.idCorte;
	}

	public void setIdCorte(int idCorte) {
		this.idCorte = idCorte;
	}

	public float getAdeudo() {
		return this.adeudo;
	}

	public void setAdeudo(float adeudo) {
		this.adeudo = adeudo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaHoraFin() {
		return this.fechaHoraFin;
	}

	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public Date getFechaHoraInicio() {
		return this.fechaHoraInicio;
	}

	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public int getNopedidos() {
		return this.nopedidos;
	}

	public void setNopedidos(int nopedidos) {
		this.nopedidos = nopedidos;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public Date getFechaHoraCorte() {
		return this.fechaHoraCorte;
	}

	public void setFechaHoraCorte(Date fechaHoraCorte) {
		this.fechaHoraCorte = fechaHoraCorte;
	}
	
	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

}