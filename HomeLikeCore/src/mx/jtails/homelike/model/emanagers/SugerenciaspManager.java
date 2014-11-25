package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Sugerenciasp;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SugerenciaspManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Sugerenciasp> listSugerenciasp(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Sugerenciasp> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Sugerenciasp as Sugerenciasp");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Sugerenciasp>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Sugerenciasp obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Sugerenciasp> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Sugerenciasp getSugerenciasp(Long id) {
		EntityManager mgr = getEntityManager();
		Sugerenciasp sugerenciasp = null;
		try {
			sugerenciasp = mgr.find(Sugerenciasp.class, id);
		} finally {
			mgr.close();
		}
		return sugerenciasp;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param sugerenciasp the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Sugerenciasp insertSugerenciasp(Sugerenciasp sugerenciasp) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsSugerenciasp(sugerenciasp)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(sugerenciasp);
		} finally {
			mgr.close();
		}
		return sugerenciasp;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param sugerenciasp the entity to be updated.
	 * @return The updated entity.
	 */
	public Sugerenciasp updateSugerenciasp(Sugerenciasp sugerenciasp) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsSugerenciasp(sugerenciasp)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(sugerenciasp);
		} finally {
			mgr.close();
		}
		return sugerenciasp;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeSugerenciasp(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Sugerenciasp sugerenciasp = mgr.find(Sugerenciasp.class, id);
			mgr.remove(sugerenciasp);
		} finally {
			mgr.close();
		}
	}

	private boolean containsSugerenciasp(Sugerenciasp sugerenciasp) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Sugerenciasp item = mgr.find(Sugerenciasp.class,
					sugerenciasp.getIdSugerencia());
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
