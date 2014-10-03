package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Servicio;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServicioManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Servicio> listServicio(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Servicio> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Servicio as Servicio");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Servicio>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Servicio obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Servicio> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Servicio getServicio(Long id) {
		EntityManager mgr = getEntityManager();
		Servicio servicio = null;
		try {
			servicio = mgr.find(Servicio.class, id);
		} finally {
			mgr.close();
		}
		return servicio;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param servicio the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Servicio insertServicio(Servicio servicio) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsServicio(servicio)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(servicio);
		} finally {
			mgr.close();
		}
		return servicio;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param servicio the entity to be updated.
	 * @return The updated entity.
	 */
	public Servicio updateServicio(Servicio servicio) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsServicio(servicio)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(servicio);
		} finally {
			mgr.close();
		}
		return servicio;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeServicio(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Servicio servicio = mgr.find(Servicio.class, id);
			mgr.remove(servicio);
		} finally {
			mgr.close();
		}
	}

	private boolean containsServicio(Servicio servicio) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Servicio item = mgr.find(Servicio.class, servicio.getIdServicio());
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
