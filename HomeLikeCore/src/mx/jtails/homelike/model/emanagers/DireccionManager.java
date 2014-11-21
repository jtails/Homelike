package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Direccion;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DireccionManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Direccion> listDireccion(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Direccion> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Direccion as Direccion");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Direccion>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Direccion obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Direccion> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Direccion getDireccion(Long id) {
		EntityManager mgr = getEntityManager();
		Direccion direccion = null;
		try {
			direccion = mgr.find(Direccion.class, id);
		} finally {
			mgr.close();
		}
		return direccion;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param direccion the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Direccion insertDireccion(Direccion direccion) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsDireccion(direccion)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(direccion);
		} finally {
			mgr.close();
		}
		return direccion;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param direccion the entity to be updated.
	 * @return The updated entity.
	 */
	public Direccion updateDireccion(Direccion direccion) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsDireccion(direccion)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.merge(direccion);
		} finally {
			mgr.close();
		}
		return direccion;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeDireccion(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Direccion direccion = mgr.find(Direccion.class, id);
			mgr.remove(direccion);
		} finally {
			mgr.close();
		}
	}

	private boolean containsDireccion(Direccion direccion) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Direccion item = mgr.find(Direccion.class,
					direccion.getIdDireccion());
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
