package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Proveedor;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

public class PedidoManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Pedido> listPedido(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Pedido> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Pedido as Pedido");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Pedido>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Pedido obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Pedido> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Pedido> listPedidosByCuenta(Cuenta cuenta) {
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Pedido> pedidos = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Pedido as Pedido where Pedido.cuenta.idCuenta=:idCuenta and Pedido.status<>:status order by (Pedido.fechaHoraPedido)").setParameter("idCuenta",cuenta.getIdCuenta()).setParameter("status",2);
			pedidos = (List<Pedido>) query.getResultList();
		} finally {
			mgr.close();
		}
		return pedidos;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Pedido> listHistoricoPedidosByCuenta(Cuenta cuenta) {
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Pedido> pedidos = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Pedido as Pedido where YEAR(Pedido.fechaHoraPedido)=YEAR(:fechaHoraUltimoPedido) and MONTH(Pedido.fechaHoraPedido)=MONTH(:fechaHoraUltimoPedido) and DAY(Pedido.fechaHoraPedido)=DAY(:fechaHoraUltimoPedido) and Pedido.cuenta.idCuenta=:idCuenta and Pedido.status=:status").setParameter("fechaHoraUltimoPedido",cuenta.getFechaHoraUltimoPedido(),TemporalType.TIMESTAMP).setParameter("idCuenta",cuenta.getIdCuenta()).setParameter("status",2);
			pedidos = (List<Pedido>) query.getResultList();
		} finally {
			mgr.close();
		}
		return pedidos;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Pedido> listPedidosByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Pedido> pedidos = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor and Pedido.status<>:status order by Pedido.region.idRegion,Pedido.fechaHoraPedido").setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("status",2);
			pedidos = (List<Pedido>) query.getResultList();
		} finally {
			mgr.close();
		}
		return pedidos;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Pedido> listHistoricoPedidosByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Pedido> pedidos = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Pedido as Pedido where YEAR(Pedido.fechaHoraPedido)=YEAR(:fechaHoraUltimoPedido) and MONTH(Pedido.fechaHoraPedido)=MONTH(:fechaHoraUltimoPedido) and DAY(Pedido.fechaHoraPedido)=DAY(:fechaHoraUltimoPedido) and Pedido.proveedor.idProveedor=:idProveedor and Pedido.status=:status").setParameter("fechaHoraUltimoPedido",proveedor.getFechaHoraUltimoPedido(),TemporalType.TIMESTAMP).setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("status",2);
			pedidos = (List<Pedido>) query.getResultList();
		} finally {
			mgr.close();
		}
		return pedidos;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Object[]> listHistoricoComentariosPedidosByProveedor(int idProveedor,int limit) {
		EntityManager mgr = null;
		Cursor cursor = null;
		List pedidos = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select Pedido.comentarioEntregaCliente,Pedido.fechaHoraEntrega,Pedido.calificacion from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor and Pedido.status=:status and Pedido.comentarioEntregaCliente IS NOT NULL and Pedido.comentarioEntregaCliente<>''").setParameter("idProveedor",idProveedor).setParameter("status",2);
			query.setMaxResults(limit);
			pedidos = (List<Object[]>) query.getResultList();
		} finally {
			mgr.close();
		}
		return pedidos;
	}
	
	
	public Proveedor countActivePedidosByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		Long numPedidos;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select count(Pedido) from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor and Pedido.status<>:status").setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("status",2);
			numPedidos = (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		proveedor.setNumPedidos(numPedidos.intValue());
		return proveedor;
	}
	
	public Proveedor countTotalPedidosByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		Long numPedidos;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select count(Pedido) from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor").setParameter("idProveedor",proveedor.getIdProveedor());
			numPedidos = (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		proveedor.setNumPedidos(numPedidos.intValue());
		return proveedor;
	}
	
	public int countTotalPedidosByCuentaProveedor(Proveedor proveedor,Cuenta cuenta) {
		EntityManager mgr = null;
		Long numPedidos;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select count(Pedido) from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor and Pedido.cuenta.idCuenta=:idCuenta").setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("idCuenta",cuenta.getIdCuenta());
			numPedidos = (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		return numPedidos.intValue();
	}
	
	public int countHistoricoPedidosByDayProveedor(Proveedor proveedor,java.util.Date fecha) {
		EntityManager mgr = null;
		Long numPedidos;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select count(Pedido) from Pedido as Pedido where YEAR(Pedido.fechaHoraPedido)=YEAR(:fecha) and MONTH(Pedido.fechaHoraPedido)=MONTH(:fecha) AND DAY(Pedido.fechaHoraPedido)=DAY(:fecha) and Pedido.proveedor.idProveedor=:idProveedor and Pedido.status=:status").setParameter("fecha",fecha,TemporalType.DATE).setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("status",2);
			numPedidos = (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		return numPedidos.intValue();
	}
	
	
	public int countHistoricoPedidosByMesProveedor(Proveedor proveedor,String from,String to) {
		EntityManager mgr = null;
		Long numPedidos;
		try {
			mgr = getEntityManager();
			Query query = mgr.createNativeQuery("select count(*) from pedido as p where p.fecha_hora_pedido BETWEEN '"+from+"' AND '"+to+"' and p.id_proveedor ="+proveedor.getIdProveedor()+" and p.status=2");
			numPedidos = (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		return numPedidos.intValue();
	}
	
	
	public float countHistoricoGananciaByMesProveedor(Proveedor proveedor,String from,String to) {
		EntityManager mgr = null;
		Double numPedidos;
		try {
			mgr = getEntityManager();
			Query query = mgr.createNativeQuery("select sum(d.cantidad*pr.costo_unitario) from detalle_pedido d,producto pr where d.id_pedido in (select p.id_pedido from pedido p where p.fecha_hora_pedido BETWEEN '"+from+"' AND '"+to+"' and p.id_proveedor ="+proveedor.getIdProveedor()+" and p.status=2)");
			numPedidos = (Double)query.getSingleResult();
		} finally {
			mgr.close();
		}
		if(numPedidos!=null)
			return numPedidos.floatValue();
		else
			return 0;
	}
	
	
	
	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */

	public Pedido getPedido(Long id) {
		EntityManager mgr = getEntityManager();
		Pedido pedido = null;
		try {
			pedido = mgr.find(Pedido.class, id);
		} finally {
			mgr.close();
		}
		return pedido;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param pedido the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Pedido insertPedido(Pedido pedido) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsPedido(pedido)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(pedido);
		} finally {
			mgr.close();
		}
		return pedido;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param pedido the entity to be updated.
	 * @return The updated entity.
	 */
	public Pedido updatePedido(Pedido pedido) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsPedido(pedido)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.merge(pedido);
		} finally {
			mgr.close();
		}
		return pedido;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removePedido(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Pedido pedido = mgr.find(Pedido.class, id);
			mgr.remove(pedido);
		} finally {
			mgr.close();
		}
	}

	private boolean containsPedido(Pedido pedido) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Pedido item = mgr.find(Pedido.class, pedido.getIdPedido());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
