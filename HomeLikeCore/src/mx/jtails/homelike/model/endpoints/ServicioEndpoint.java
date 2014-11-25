package mx.jtails.homelike.model.endpoints;

import java.io.IOException;

import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.ServicioManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;


@Api(name = "servicioendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class ServicioEndpoint {

	/**
	 * Permite obtener la lista de servicios existentes (AGUA,GAS)
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Servicios
	 */
	@ApiMethod(name = "listServicio")
	public CollectionResponse<Servicio> listServicio(User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ServicioManager servicioM=new ServicioManager();
			return servicioM.listServicio(null,null);
		//}
		//return null;
	}

}
