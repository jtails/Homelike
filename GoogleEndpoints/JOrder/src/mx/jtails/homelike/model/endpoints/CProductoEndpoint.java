package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.CProducto;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.CProductoManager;
import mx.jtails.homelike.model.emanagers.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.users.User;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "cproductoendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class CProductoEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@ApiMethod(name = "listCProducto")
	public CollectionResponse<CProducto> listCProducto(User user) {
		//if (user!=null){
			CProductoManager cproductoM=new CProductoManager();
			return cproductoM.listCProducto(null,null);
		//}
		//return null;
	}
	
	@ApiMethod(name = "listCProductoByServicio",path="listCProductoByServicio")
	public List<CProducto> listCProductoByServicio(Servicio servicio,User user) {
		//if (user!=null){
			CProductoManager cproductoM=new CProductoManager();
			return cproductoM.listCProductoByServicio(servicio);
		//}
		//return null;
	}

}
