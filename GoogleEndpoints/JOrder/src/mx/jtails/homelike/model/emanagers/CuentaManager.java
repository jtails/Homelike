package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Proveedor;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CuentaManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Cuenta> listCuenta(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Cuenta> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Cuenta as Cuenta");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Cuenta>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Cuenta obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Cuenta> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}
	
	public int countCuentas() {
		EntityManager mgr = null;
		Long numCuentas = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select count(Cuenta) from Cuenta as Cuenta");
			numCuentas = (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		return numCuentas.intValue();
	}
	
	
	@SuppressWarnings({ "unchecked"})
	public List<Cuenta> getClientesinRagne(String nelatitud,String swlatitud,String swlongitud,String nelongitud) {
		EntityManager mgr = null;
		List<Cuenta> clientes = null;
		try {
			mgr = getEntityManager();
			//El Query comentado deberia ser el correcto, por temas de la implementación de DATANUCLEUS se tuvo que mover la parte de la condicion de las longitudes negativas, si se ejecuta el query por fuera en Mysql si funciona
			//Query query = mgr.createQuery("select Direccion.cuenta from Direccion as Direccion where :nelatitud>Direccion.latitud and :swlatitud<Direccion.latitud and :swlongitud<Direccion.longitud and :nelongitud>Direccion.longitud").setParameter("nelatitud",nelatitud).setParameter("swlatitud",swlatitud).setParameter("swlongitud",swlongitud).setParameter("nelongitud",nelongitud);
			Query query = mgr.createQuery("select Direccion.cuenta from Direccion as Direccion where :nelatitud>Direccion.latitud and :swlatitud<Direccion.latitud and :swlongitud>Direccion.longitud and :nelongitud<Direccion.longitud").setParameter("nelatitud",nelatitud).setParameter("swlatitud",swlatitud).setParameter("swlongitud",swlongitud).setParameter("nelongitud",nelongitud);
			clientes = (List<Cuenta>) query.getResultList();
		} finally {
			mgr.close();
		}
		return clientes;
	}
	
	
	public Proveedor getClienteswithPedidoByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		Long numClientes;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select count(DISTINCT Pedido.cuenta) from Pedido as Pedido where Pedido.proveedor.idProveedor=:idProveedor").setParameter("idProveedor",proveedor.getIdProveedor());
			numClientes= (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		proveedor.setNumClientes(numClientes.intValue());
		return proveedor;
	}
	
	
	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Cuenta getCuenta(Long id) {
		EntityManager mgr = getEntityManager();
		Cuenta cuenta = null;
		try {
			cuenta = mgr.find(Cuenta.class, id);
		} finally {
			mgr.close();
		}
		return cuenta;
	}
	
	
	@SuppressWarnings("unchecked")
	public Cuenta getCuentaByUser(Cuenta c) {

		EntityManager mgr = null;
		Cuenta cuenta = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Cuenta as Cuenta where usuario=:usuario").setParameter("usuario",c.getUsuario());
			List<Cuenta> cuentas=(List<Cuenta>) query.getResultList();
			if(cuentas!=null && cuentas.size()>0)
				cuenta = cuentas.get(0);
		} finally {
			mgr.close();
		}
		return cuenta;
	}


	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param cuenta the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Cuenta insertCuenta(Cuenta cuenta) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCuenta(cuenta)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(cuenta);
		} finally {
			mgr.close();
		}
		return cuenta;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param cuenta the entity to be updated.
	 * @return The updated entity.
	 */
	public Cuenta updateCuenta(Cuenta cuenta) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCuenta(cuenta)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.merge(cuenta);
		} finally {
			mgr.close();
		}
		return cuenta;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeCuenta(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Cuenta cuenta = mgr.find(Cuenta.class, id);
			mgr.remove(cuenta);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCuenta(Cuenta cuenta) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Cuenta item = mgr.find(Cuenta.class, cuenta.getIdCuenta());
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
