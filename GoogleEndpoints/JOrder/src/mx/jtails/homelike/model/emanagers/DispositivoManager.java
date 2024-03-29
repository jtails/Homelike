package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Dispositivo;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DispositivoManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Dispositivo> listDispositivo(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Dispositivo> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Dispositivo as Dispositivo");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Dispositivo>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Dispositivo obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Dispositivo> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Dispositivo getDispositivo(Long id) {
		EntityManager mgr = getEntityManager();
		Dispositivo dispositivo = null;
		try {
			dispositivo = mgr.find(Dispositivo.class, id);
		} finally {
			mgr.close();
		}
		return dispositivo;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param dispositivo the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Dispositivo insertDispositivo(Dispositivo dispositivo) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsDispositivo(dispositivo)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(dispositivo);
		} finally {
			mgr.close();
		}
		return dispositivo;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param dispositivo the entity to be updated.
	 * @return The updated entity.
	 */
	public Dispositivo updateDispositivo(Dispositivo dispositivo) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsDispositivo(dispositivo)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(dispositivo);
		} finally {
			mgr.close();
		}
		return dispositivo;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeDispositivo(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Dispositivo dispositivo = mgr.find(Dispositivo.class, id);
			mgr.remove(dispositivo);
		} finally {
			mgr.close();
		}
	}

	private boolean containsDispositivo(Dispositivo dispositivo) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Dispositivo item = mgr.find(Dispositivo.class,
					dispositivo.getIdDispositivo());
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
