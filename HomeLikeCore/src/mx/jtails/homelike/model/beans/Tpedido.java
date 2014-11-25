package mx.jtails.homelike.model.beans;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the tpedido database table.
 * 
 */
@Entity
@NamedQuery(name="Tpedido.findAll", query="SELECT t FROM Tpedido t")
public class Tpedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TpedidoPK key;

	private BigInteger total;

	//bi-directional many-to-one association to Cuenta

	public Tpedido() {
	}

	public BigInteger getTotal() {
		return this.total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}
	
	public TpedidoPK getKey() {
		return key;
	}

	public void setKey(TpedidoPK key) {
		this.key = key;
	}	

}

@Embeddable
class TpedidoPK{
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;

	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;
	
	
	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
}