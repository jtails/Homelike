package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the pagos database table.
 * 
 */
@Entity
@Table(name="pagos")
@NamedQuery(name="Pago.findAll", query="SELECT p FROM Pago p")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	public Pago(){
		Calendar calendar=Calendar.getInstance();
		fechaHoraPago=calendar.getTime();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pago")
	private int idPago;

	private float cantidad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora_pago")
	private Date fechaHoraPago;

	@JsonBackReference
	@Column(name="id_corte")
	private Corte corte;

	public int getIdPago() {
		return this.idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaHoraPago() {
		return this.fechaHoraPago;
	}

	public void setFechaHoraPago(Date fechaHoraPago) {
		this.fechaHoraPago = fechaHoraPago;
	}

	public Corte getCorte() {
		return this.corte;
	}

	public void setCorte(Corte corte) {
		this.corte = corte;
	}

}