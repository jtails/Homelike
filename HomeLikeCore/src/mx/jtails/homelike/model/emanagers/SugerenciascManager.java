package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Sugerenciasc;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SugerenciascManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Sugerenciasc> listSugerenciasc(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Sugerenciasc> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Sugerenciasc as Sugerenciasc");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Sugerenciasc>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Sugerenciasc obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Sugerenciasc> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Sugerenciasc getSugerenciasc(Long id) {
		EntityManager mgr = getEntityManager();
		Sugerenciasc sugerenciasc = null;
		try {
			sugerenciasc = mgr.find(Sugerenciasc.class, id);
		} finally {
			mgr.close();
		}
		return sugerenciasc;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param sugerenciasc the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Sugerenciasc insertSugerenciasc(Sugerenciasc sugerenciasc) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsSugerenciasc(sugerenciasc)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(sugerenciasc);
		} finally {
			mgr.close();
		}
		return sugerenciasc;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param sugerenciasc the entity to be updated.
	 * @return The updated entity.
	 */
	public Sugerenciasc updateSugerenciasc(Sugerenciasc sugerenciasc) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsSugerenciasc(sugerenciasc)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(sugerenciasc);
		} finally {
			mgr.close();
		}
		return sugerenciasc;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeSugerenciasc(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Sugerenciasc sugerenciasc = mgr.find(Sugerenciasc.class, id);
			mgr.remove(sugerenciasc);
		} finally {
			mgr.close();
		}
	}

	private boolean containsSugerenciasc(Sugerenciasc sugerenciasc) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Sugerenciasc item = mgr.find(Sugerenciasc.class,
					sugerenciasc.getIdSugerencia());
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
