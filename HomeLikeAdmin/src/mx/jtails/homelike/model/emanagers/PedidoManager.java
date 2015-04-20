package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Proveedor;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PedidoManager {
	private static final Logger logger = Logger.getLogger(PedidoManager.class.getName());

	@SuppressWarnings({ "unchecked"})
	public List<Pedido> listPedidosByInicioCorteProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		List<Pedido> pedidos = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor and Pedido.status=:status and Pedido.fechaHoraPedido<CURRENT_DATE order by (Pedido.fechaHoraPedido) asc").setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("status",2);
			pedidos = (List<Pedido>) query.getResultList();
		} finally {
			mgr.close();
		}
		return pedidos;
	}
	
	@SuppressWarnings({ "unchecked"})
	public List<Pedido> listPedidosByFechaCorteProveedor(Proveedor proveedor,Date inicioCorte) {
		EntityManager mgr = null;
		List<Pedido> pedidos = null;
		logger.warning(inicioCorte.toString());
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor and Pedido.status=:status and Pedido.fechaHoraPedido >:fechaInicioCorte and Pedido.fechaHoraPedido<CURRENT_DATE order by (Pedido.fechaHoraPedido) asc").setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("fechaInicioCorte",inicioCorte).setParameter("status",2);
			pedidos = (List<Pedido>) query.getResultList();
		} finally {
			mgr.close();
		}
		return pedidos;
	}
	
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}
	
}
