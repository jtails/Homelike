package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Admin;
import mx.jtails.homelike.model.emanagers.EMF;

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

@Api(name = "adminendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,}
,audiences = {Constants.WEB_CLIENT_ID}
)
public class AdminEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listAdmin")
	public CollectionResponse<Admin> listAdmin(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Admin> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Admin as Admin");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Admin>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Admin obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Admin> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getAdmin")
	public Admin getAdmin(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Admin admin = null;
		try {
			admin = mgr.find(Admin.class, id);
		} finally {
			mgr.close();
		}
		return admin;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param admin the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertAdmin")
	public Admin insertAdmin(Admin admin) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsAdmin(admin)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(admin);
		} finally {
			mgr.close();
		}
		return admin;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param admin the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateAdmin")
	public Admin updateAdmin(Admin admin) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsAdmin(admin)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(admin);
		} finally {
			mgr.close();
		}
		return admin;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeAdmin")
	public void removeAdmin(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Admin admin = mgr.find(Admin.class, id);
			mgr.remove(admin);
		} finally {
			mgr.close();
		}
	}

	private boolean containsAdmin(Admin admin) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Admin item = mgr.find(Admin.class, admin.getIdAdmin());
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
