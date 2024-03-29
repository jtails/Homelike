package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Tpedido;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TpedidoManager {

	@SuppressWarnings("unchecked")
	public List<Tpedido> listTpedidosByCuenta(Cuenta cuenta) {
		EntityManager mgr = null;
		List<Tpedido> tpedidos = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Tpedido as Tpedido where Tpedido.key.cuenta.idCuenta=:idCuenta").setParameter("idCuenta",cuenta.getIdCuenta());
			tpedidos = (List<Tpedido>) query.getResultList();
		} finally {
			mgr.close();
		}
		return tpedidos;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
