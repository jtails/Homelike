package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.CProducto;
import mx.jtails.homelike.model.beans.Producto;
import mx.jtails.homelike.model.beans.Proveedor;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProductoManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Producto> listProducto(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Producto> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Producto as Producto");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Producto>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Producto obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Producto> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}
	
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Producto> listProductosByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Producto> productos = null;
		mgr = getEntityManager();
		Query query = mgr.createQuery("select from Producto as Producto where Producto.proveedor.idProveedor=:idProveedor and Producto.status=:status").setParameter("idProveedor",proveedor.getIdProveedor()).setParameter("status",0);
		productos = (List<Producto>) query.getResultList();
		return productos;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Producto> listAllProductosByProveedor(Proveedor proveedor) {
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Producto> productos = null;
		mgr = getEntityManager();
		Query query = mgr.createQuery("select from Producto as Producto where Producto.proveedor.idProveedor=:idProveedor").setParameter("idProveedor",proveedor.getIdProveedor());
		productos = (List<Producto>) query.getResultList();
		return productos;
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Producto getProducto(Long id) {
		EntityManager mgr = getEntityManager();
		Producto producto = null;
		try {
			producto = mgr.find(Producto.class, id);
		} finally {
			mgr.close();
		}
		return producto;
	}
	
	
	@SuppressWarnings("unchecked")
	public Producto getProductoByCatalogoProveedor(CProducto cproducto,Proveedor proveedor) {
		EntityManager mgr = null;
		Producto producto = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Producto as Producto where Producto.cproducto.idCProducto=:idCProducto and Producto.proveedor.idProveedor=:idProveedor").setParameter("idCProducto",cproducto.getIdCProducto()).setParameter("idProveedor",proveedor.getIdProveedor());
			List<Producto> productos=(List<Producto>) query.getResultList();
			if(productos!=null && productos.size()>0)
				producto = productos.get(0);
		} finally {
			mgr.close();
		}
		return producto;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param producto the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Producto insertProducto(Producto producto) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsProducto(producto)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(producto);
		} finally {
			mgr.close();
		}
		return producto;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param producto the entity to be updated.
	 * @return The updated entity.
	 */
	public Producto updateProducto(Producto producto) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsProducto(producto)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.merge(producto);
		} finally {
			mgr.close();
		}
		return producto;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeProducto(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Producto producto = mgr.find(Producto.class, id);
			mgr.remove(producto);
		} finally {
			mgr.close();
		}
	}

	private boolean containsProducto(Producto producto) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Producto item = mgr.find(Producto.class, producto.getIdProducto());
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
