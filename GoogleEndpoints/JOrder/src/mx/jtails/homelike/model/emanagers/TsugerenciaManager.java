package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Tsugerencia;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TsugerenciaManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Tsugerencia> listTsugerencia(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Tsugerencia> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Tsugerencia as Tsugerencia");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Tsugerencia>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Tsugerencia obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Tsugerencia> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Tsugerencia getTsugerencia(Long id) {
		EntityManager mgr = getEntityManager();
		Tsugerencia tsugerencia = null;
		try {
			tsugerencia = mgr.find(Tsugerencia.class, id);
		} finally {
			mgr.close();
		}
		return tsugerencia;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param tsugerencia the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Tsugerencia insertTsugerencia(Tsugerencia tsugerencia) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsTsugerencia(tsugerencia)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(tsugerencia);
		} finally {
			mgr.close();
		}
		return tsugerencia;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param tsugerencia the entity to be updated.
	 * @return The updated entity.
	 */
	public Tsugerencia updateTsugerencia(Tsugerencia tsugerencia) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsTsugerencia(tsugerencia)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(tsugerencia);
		} finally {
			mgr.close();
		}
		return tsugerencia;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeTsugerencia(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Tsugerencia tsugerencia = mgr.find(Tsugerencia.class, id);
			mgr.remove(tsugerencia);
		} finally {
			mgr.close();
		}
	}

	private boolean containsTsugerencia(Tsugerencia tsugerencia) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Tsugerencia item = mgr.find(Tsugerencia.class,
					tsugerencia.getIdTsugerencia());
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
