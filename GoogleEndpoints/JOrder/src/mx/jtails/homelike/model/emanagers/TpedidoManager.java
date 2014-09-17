package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Tpedido;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TpedidoManager {

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
