package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.CantidadPago;
import mx.jtails.homelike.model.emanagers.CantidadPagoManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;


@Api(name = "cantidadpagoendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class CantidadPagoEndpoint {

	/**
	 * Retorna las cantidades de Pago disponibles para identificar el monto con el cual pagara el cliente
	 * @param user
	 * El usuario autenticado con Google
	 */
	@ApiMethod(name = "listCantidadPago")
	public CollectionResponse<CantidadPago> listCantidadPago(User user)throws OAuthRequestException, IOException{
		//if(user!=null){
			CantidadPagoManager cantidadPagoM=new CantidadPagoManager();
			return cantidadPagoM.listCantidadPago(null,null);
		//}
		//return null;
	}
}
