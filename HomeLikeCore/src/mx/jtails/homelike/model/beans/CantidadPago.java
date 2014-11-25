package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the cantidad_pago database table.
 * 
 */
@Entity
@Table(name="cantidad_pago")
@NamedQuery(name="CantidadPago.findAll", query="SELECT c FROM CantidadPago c")
public class CantidadPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cantidad_pago")
	private int idCantidadPago;

	@Column(name="cantidad_numero")
	private int cantidadNumero;

	@Column(name="cantidad_texto")
	private String cantidadTexto;

	public CantidadPago() {
	}

	public int getIdCantidadPago() {
		return this.idCantidadPago;
	}

	public void setIdCantidadPago(int idCantidadPago) {
		this.idCantidadPago = idCantidadPago;
	}

	public int getCantidadNumero() {
		return this.cantidadNumero;
	}

	public void setCantidadNumero(int cantidadNumero) {
		this.cantidadNumero = cantidadNumero;
	}

	public String getCantidadTexto() {
		return this.cantidadTexto;
	}

	public void setCantidadTexto(String cantidadTexto) {
		this.cantidadTexto = cantidadTexto;
	}

}