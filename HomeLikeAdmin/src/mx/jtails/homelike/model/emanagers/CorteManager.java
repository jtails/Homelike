package mx.jtails.homelike.model.emanagers;

import mx.jtails.homelike.model.beans.Corte;
import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.endpoints.CorteEndpoint;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

public class CorteManager {
	private static final Logger logger = Logger.getLogger(CorteManager.class.getName());

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Corte> listCortesByProveedor(Corte corte) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Corte> cortes = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Corte as Corte where Corte.proveedor.idProveedor=:idProveedor").setParameter("idProveedor",corte.getProveedor().getIdProveedor());
			cortes = (List<Corte>) query.getResultList();
		} finally {
			mgr.close();
		}
		return cortes;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public boolean haveCurrentCorteByProveedor(Corte corte) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Corte> cortes = null;
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select MAX(Corte.fechaHoraCorte) from Corte as Corte where Corte.proveedor.idProveedor=:idProveedor").setParameter("idProveedor",corte.getProveedor().getIdProveedor());
			if(query.getSingleResult()!=null){
				Date fechaHoraCorte=(Date)query.getSingleResult();
				Calendar fechaUltimoCorte=Calendar.getInstance();
				fechaUltimoCorte.setTime(fechaHoraCorte);
				
				Calendar fechaActual=Calendar.getInstance();
				logger.warning("Fecha ultimo Corte: "+fechaUltimoCorte.get(Calendar.DAY_OF_MONTH)+"-"+fechaUltimoCorte.get(Calendar.MONTH)+"-"+fechaUltimoCorte.get(Calendar.YEAR));
				logger.warning("Fecha Actual: "+fechaActual.get(Calendar.DAY_OF_MONTH)+"-"+fechaActual.get(Calendar.MONTH)+"-"+fechaActual.get(Calendar.YEAR));
				
				if(fechaUltimoCorte.get(Calendar.DAY_OF_MONTH)==fechaActual.get(Calendar.DAY_OF_MONTH) && fechaUltimoCorte.get(Calendar.MONTH)==fechaActual.get(Calendar.MONTH) && fechaUltimoCorte.get(Calendar.YEAR)==fechaActual.get(Calendar.YEAR))
					return true;
				else
					return false;
				
			}
		} finally {
			mgr.close();
		}
		return false;
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Corte getCorte(Long id) {
		EntityManager mgr = getEntityManager();
		Corte corte = null;
		try {
			corte = mgr.find(Corte.class, id);
		} finally {
			mgr.close();
		}
		return corte;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param corte the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Corte insertCorte(Corte corte) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCorte(corte)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(corte);
		} finally {
			mgr.close();
		}
		return corte;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param corte the entity to be updated.
	 * @return The updated entity.
	 */
	public Corte updateCorte(Corte corte) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCorte(corte)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(corte);
		} finally {
			mgr.close();
		}
		return corte;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	public void removeCorte(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Corte corte = mgr.find(Corte.class, id);
			mgr.remove(corte);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCorte(Corte corte) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Corte item = mgr.find(Corte.class, corte.getIdCorte());
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
