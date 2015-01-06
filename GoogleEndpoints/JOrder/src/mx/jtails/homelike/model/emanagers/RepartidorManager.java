package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Repartidor;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RepartidorManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Repartidor> listRepartidor(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Repartidor> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Repartidor as Repartidor");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Repartidor>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Repartidor obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Repartidor> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}
	
	
	
	@SuppressWarnings({ "unchecked"})
	public List<Repartidor> listAllRepartidoresByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		List<Repartidor> repartidores = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Repartidor as Repartidor where proveedor.idProveedor=:idProveedor").setParameter("idProveedor",proveedor.getIdProveedor());
			repartidores = (List<Repartidor>) query.getResultList();
		} finally {
			mgr.close();
		}
		return repartidores;
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Repartidor getRepartidor(Long id) {
		EntityManager mgr = getEntityManager();
		Repartidor repartidor = null;
		try {
			repartidor = mgr.find(Repartidor.class, id);
		} finally {
			mgr.close();
		}
		return repartidor;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param repartidor the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Repartidor insertRepartidor(Repartidor repartidor) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsRepartidor(repartidor)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(repartidor);
		} finally {
			mgr.close();
		}
		return repartidor;
	}
	
	
	@SuppressWarnings("unchecked")
	public Repartidor getRepartidorByUser(Repartidor r) {

		EntityManager mgr = null;
		Repartidor repartidor = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Repartidor as Repartidor where usuario=:usuario").setParameter("usuario",p.getUsuario());
			List<Repartidor> repartidores=(List<Repartidor>) query.getResultList();
			if(repartidores!=null && repartidores.size()>0){
				repartidor = repartidores.get(0);
			}
		} finally {
			mgr.close();
		}
		return repartidor;
	}
	

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param repartidor the entity to be updated.
	 * @return The updated entity.
	 */
	public Repartidor updateRepartidor(Repartidor repartidor) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsRepartidor(repartidor)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.merge(repartidor);
		} finally {
			mgr.close();
		}
		return repartidor;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeRepartidor(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Repartidor repartidor = mgr.find(Repartidor.class, id);
			mgr.remove(repartidor);
		} finally {
			mgr.close();
		}
	}

	private boolean containsRepartidor(Repartidor repartidor) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Repartidor item = mgr.find(Repartidor.class,
					repartidor.getIdRepartidor());
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
