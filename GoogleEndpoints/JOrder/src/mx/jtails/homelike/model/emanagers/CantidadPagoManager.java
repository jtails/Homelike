package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.CantidadPago;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CantidadPagoManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<CantidadPago> listCantidadPago(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<CantidadPago> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from CantidadPago as CantidadPago");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<CantidadPago>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (CantidadPago obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<CantidadPago> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public CantidadPago getCantidadPago(Long id) {
		EntityManager mgr = getEntityManager();
		CantidadPago cantidadpago = null;
		try {
			cantidadpago = mgr.find(CantidadPago.class, id);
		} finally {
			mgr.close();
		}
		return cantidadpago;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param cantidadpago the entity to be inserted.
	 * @return The inserted entity.
	 */
	public CantidadPago insertCantidadPago(CantidadPago cantidadpago) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCantidadPago(cantidadpago)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(cantidadpago);
		} finally {
			mgr.close();
		}
		return cantidadpago;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param cantidadpago the entity to be updated.
	 * @return The updated entity.
	 */
	public CantidadPago updateCantidadPago(CantidadPago cantidadpago) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCantidadPago(cantidadpago)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(cantidadpago);
		} finally {
			mgr.close();
		}
		return cantidadpago;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeCantidadPago(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			CantidadPago cantidadpago = mgr.find(CantidadPago.class, id);
			mgr.remove(cantidadpago);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCantidadPago(CantidadPago cantidadpago) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			CantidadPago item = mgr.find(CantidadPago.class,
					cantidadpago.getIdCantidadPago());
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
