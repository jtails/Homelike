package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonBackReference;
import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the horarios_proveedor database table.
 * 
 */
@Entity
@Table(name="horarios_proveedor")
@NamedQuery(name="HorariosProveedor.findAll", query="SELECT h FROM HorariosProveedor h")
public class HorariosProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_horario")
	private int idHorario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date abrimos;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cerramos;

	//bi-directional many-to-one association to Proveedor
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	
	//1- Lunes-Viernes
	//2- Sabado
	//3- Domingo
	@Column(name="tipo_horario")
	private int tipoHorario;

	public HorariosProveedor() {
	}

	public int getIdHorario() {
		return this.idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public Date getAbrimos() {
		return this.abrimos;
	}

	public void setAbrimos(Date abrimos) {
		this.abrimos = abrimos;
	}

	public Date getCerramos() {
		return this.cerramos;
	}

	public void setCerramos(Date cerramos) {
		this.cerramos = cerramos;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public int getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(int tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

}