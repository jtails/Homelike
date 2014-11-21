package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Proveedor;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ProveedorManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Proveedor> listProveedor(String cursorString,Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Proveedor> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Proveedor as Proveedor");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Proveedor>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Proveedor obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Proveedor> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}
	
	public int countProveedores() {
		EntityManager mgr = null;
		Long numProveedores = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select count(Proveedor) from Proveedor as Proveedor");
			numProveedores = (Long)query.getSingleResult();
		} finally {
			mgr.close();
		}
		return numProveedores.intValue();
	}
	
	
	@SuppressWarnings({ "unchecked"})
	public List<Proveedor> getProveedoresinRagne(String latitud,String longitud, int idServicio) {
		EntityManager mgr = null;
		List<Proveedor> proveedores = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Proveedor as Proveedor where nelatitud>:latitud and swlatitud<:latitud and swlongitud>:longitud and nelongitud<:longitud and servicio.idServicio=:idServicio and status=:status").setParameter("latitud",latitud).setParameter("longitud",longitud).setParameter("idServicio", idServicio).setParameter("status",1);
			proveedores = (List<Proveedor>) query.getResultList();
		} finally {
			mgr.close();
		}
		return proveedores;
	}
	

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Proveedor getProveedor(Long id) {
		EntityManager mgr = getEntityManager();
		Proveedor proveedor = null;
		try {
			proveedor = mgr.find(Proveedor.class, id);
		} finally {
			mgr.close();
		}
		return proveedor;
	}

	
	@SuppressWarnings("unchecked")
	public Proveedor getProveedorByUser(Proveedor p) {

		EntityManager mgr = null;
		Proveedor proveedor = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Proveedor as Proveedor where usuario=:usuario").setParameter("usuario",p.getUsuario());
			List<Proveedor> proveedores=(List<Proveedor>) query.getResultList();
			if(proveedores!=null && proveedores.size()>0){
				proveedor = proveedores.get(0);
				if(proveedor!=null){
					for(Pedido pedido:proveedor.getPedidos()){
						if(pedido.getStatus()==2){//Pedido terminado
							proveedor.setNumPedidos(proveedor.getNumPedidos()+1);
							proveedor.setCalificacion(proveedor.getCalificacion()+pedido.getCalificacion());
						}
					}
					if(proveedor.getNumPedidos()>0)
						proveedor.setCalificacion(proveedor.getCalificacion()/proveedor.getNumPedidos());
				}
			}
		} finally {
			mgr.close();
		}
		return proveedor;
	}

	
	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param proveedor the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Proveedor insertProveedor(Proveedor proveedor) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsProveedor(proveedor)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(proveedor);
		} finally {
			mgr.close();
		}
		return proveedor;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param proveedor the entity to be updated.
	 * @return The updated entity.
	 */
	public Proveedor updateProveedor(Proveedor proveedor) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsProveedor(proveedor)) {
				throw new EntityNotFoundException("Object does not exist");
			} 
			mgr.merge(proveedor);
		} finally {
			mgr.close();
		}
		return proveedor;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeProveedor(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Proveedor proveedor = mgr.find(Proveedor.class, id);
			mgr.remove(proveedor);
		} finally {
			mgr.close();
		}
	}

	private boolean containsProveedor(Proveedor proveedor) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Proveedor item = mgr.find(Proveedor.class,
					proveedor.getIdProveedor());
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