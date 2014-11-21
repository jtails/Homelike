package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.HorariosProveedor;
import mx.jtails.homelike.model.beans.Pedido;

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


public class HorariosProveedorManager {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings("unchecked")
	public HorariosProveedor getHorariosProveedorByTipo(HorariosProveedor horariosproveedor) {
		EntityManager mgr = null;
		List<HorariosProveedor> phorariosproveedor;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from HorariosProveedor as HorariosProveedor where HorariosProveedor.tipoHorario=:tipoHorario and HorariosProveedor.proveedor.idProveedor=:idProveedor").setParameter("tipoHorario",horariosproveedor.getTipoHorario()).setParameter("idProveedor",horariosproveedor.getProveedor().getIdProveedor());
			phorariosproveedor = (List<HorariosProveedor>) query.getResultList();
			if(phorariosproveedor!=null && phorariosproveedor.size()>0)
				horariosproveedor = phorariosproveedor.get(0);
		} finally {
			mgr.close();
		}
		return horariosproveedor;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<HorariosProveedor> getHorariosProveedor(HorariosProveedor horariosproveedor) {
		EntityManager mgr = null;
		List<HorariosProveedor> phorariosproveedor=null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from HorariosProveedor as HorariosProveedor where HorariosProveedor.proveedor.idProveedor=:idProveedor").setParameter("idProveedor",horariosproveedor.getProveedor().getIdProveedor());
			phorariosproveedor = (List<HorariosProveedor>) query.getResultList();
		} finally {
			mgr.close();
		}
		return phorariosproveedor;
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public HorariosProveedor getHorariosProveedor(Long id) {
		EntityManager mgr = getEntityManager();
		HorariosProveedor horariosproveedor = null;
		try {
			horariosproveedor = mgr.find(HorariosProveedor.class, id);
		} finally {
			mgr.close();
		}
		return horariosproveedor;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param horariosproveedor the entity to be inserted.
	 * @return The inserted entity.
	 */
	public HorariosProveedor insertHorariosProveedor(
			HorariosProveedor horariosproveedor) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsHorariosProveedor(horariosproveedor)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(horariosproveedor);
		} finally {
			mgr.close();
		}
		return horariosproveedor;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param horariosproveedor the entity to be updated.
	 * @return The updated entity.
	 */
	public HorariosProveedor updateHorariosProveedor(
			HorariosProveedor horariosproveedor) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsHorariosProveedor(horariosproveedor)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.merge(horariosproveedor);
		} finally {
			mgr.close();
		}
		return horariosproveedor;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeHorariosProveedor(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			HorariosProveedor horariosproveedor = mgr.find(
					HorariosProveedor.class, id);
			mgr.remove(horariosproveedor);
		} finally {
			mgr.close();
		}
	}

	private boolean containsHorariosProveedor(
			HorariosProveedor horariosproveedor) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			HorariosProveedor item = mgr.find(HorariosProveedor.class,
					horariosproveedor.getIdHorario());
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
