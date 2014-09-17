package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.CProducto;
import mx.jtails.homelike.model.beans.Servicio;

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

public class CProductoManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<CProducto> listCProducto(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<CProducto> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from CProducto as CProducto");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<CProducto>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (CProducto obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<CProducto> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}
	
	public List<CProducto> listCProductoByServicio(Servicio servicio) {
		EntityManager mgr = null;
		List<CProducto> cproductos = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from CProducto as CProducto where CProducto.servicio.idServicio=:idServicio").setParameter("idServicio",servicio.getIdServicio());
			cproductos = (List<CProducto>) query.getResultList();
		} finally {
			mgr.close();
		}
		return cproductos;
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public CProducto getCProducto(Long id) {
		EntityManager mgr = getEntityManager();
		CProducto cproducto = null;
		try {
			cproducto = mgr.find(CProducto.class, id);
		} finally {
			mgr.close();
		}
		return cproducto;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param cproducto the entity to be inserted.
	 * @return The inserted entity.
	 */
	public CProducto insertCProducto(CProducto cproducto) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCProducto(cproducto)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(cproducto);
		} finally {
			mgr.close();
		}
		return cproducto;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param cproducto the entity to be updated.
	 * @return The updated entity.
	 */
	public CProducto updateCProducto(CProducto cproducto) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCProducto(cproducto)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(cproducto);
		} finally {
			mgr.close();
		}
		return cproducto;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeCProducto(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			CProducto cproducto = mgr.find(CProducto.class, id);
			mgr.remove(cproducto);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCProducto(CProducto cproducto) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			CProducto item = mgr.find(CProducto.class,
					cproducto.getIdCProducto());
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
