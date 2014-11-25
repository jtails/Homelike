package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.CProducto;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.CProductoManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.List;

@Api(name = "cproductoendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
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
	public CollectionResponse<CProducto> listCProducto(User user)throws OAuthRequestException, IOException  {
		//if (user!=null){
			CProductoManager cproductoM=new CProductoManager();
			return cproductoM.listCProducto(null,null);
		//}
		//return null;
	}
	
	@ApiMethod(name = "listCProductoByServicio",path="listCProductoByServicio")
	public List<CProducto> listCProductoByServicio(Servicio servicio,User user)throws OAuthRequestException, IOException  {
		//if (user!=null){
			CProductoManager cproductoM=new CProductoManager();
			return cproductoM.listCProductoByServicio(servicio);
		//}
		//return null;
	}

}
