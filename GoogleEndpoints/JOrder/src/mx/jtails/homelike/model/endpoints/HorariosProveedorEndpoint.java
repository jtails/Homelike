package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.HorariosProveedor;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.EMF;
import mx.jtails.homelike.model.emanagers.HorariosProveedorManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "horariosproveedorendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class HorariosProveedorEndpoint {
	private static final Logger logger = Logger.getLogger(HorariosProveedorEndpoint.class.getName());

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listHorariosProveedor")
	public CollectionResponse<HorariosProveedor> listHorariosProveedor(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit,User user) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<HorariosProveedor> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from HorariosProveedor as HorariosProveedor");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<HorariosProveedor>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (HorariosProveedor obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<HorariosProveedor> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getHorariosProveedor")
	public HorariosProveedor getHorariosProveedor(@Named("id") Long id,User user) {
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
	 * Persiste el objeto HorariosProveedor, si el horario ya esta persistido realiza una operacion update
	 * @param horariosproveedor
	 * El Horario del proveedor a ser agregado o actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto pedido persistido,este contiene el ID del horario generado
	 */ 
	@ApiMethod(name = "insertHorariosProveedor")
	public HorariosProveedor insertHorariosProveedor(HorariosProveedor horariosproveedor,User user)throws OAuthRequestException, IOException {
		//if(user!=null){
			HorariosProveedorManager horariosM=new HorariosProveedorManager();
			ProveedorManager proveedorM=new ProveedorManager();
			//Obtenemos referencia a los objetos persistidos
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(horariosproveedor.getProveedor().getIdProveedor()));
			
			//Verificamos si es un horario nuevo, o una actualización a uno existente
			HorariosProveedor phorariosproveedor = horariosM.getHorariosProveedorByTipo(horariosproveedor);
			if(phorariosproveedor.getIdHorario()==0){
				logger.warning("\"Nuevo Horario proveedor\" Proveedor: "+horariosproveedor.getProveedor().getIdProveedor()+", Inicio: "+horariosproveedor.getAbrimos()+", Fin: "+horariosproveedor.getCerramos()+", User: "+user);
				horariosproveedor.setProveedor(pproveedor);
				horariosproveedor=horariosM.insertHorariosProveedor(horariosproveedor);
			}else{
				logger.warning("\"Actualización Horario proveedor\" Proveedor: "+horariosproveedor.getProveedor().getIdProveedor()+", Inicio: "+horariosproveedor.getAbrimos()+", Fin: "+horariosproveedor.getCerramos()+", User: "+user);
				phorariosproveedor.setAbrimos(horariosproveedor.getAbrimos());
				phorariosproveedor.setCerramos(horariosproveedor.getCerramos());
				horariosproveedor=horariosM.updateHorariosProveedor(phorariosproveedor);
			}
			
			return horariosproveedor;
		//}
		//return null;	
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param horariosproveedor the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateHorariosProveedor")
	public HorariosProveedor updateHorariosProveedor(
			HorariosProveedor horariosproveedor,User user) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsHorariosProveedor(horariosproveedor)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(horariosproveedor);
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
	@ApiMethod(name = "removeHorariosProveedor")
	public void removeHorariosProveedor(@Named("id") Long id,User user) {
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
