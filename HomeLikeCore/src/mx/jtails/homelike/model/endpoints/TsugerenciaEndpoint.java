package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Tsugerencia;
import mx.jtails.homelike.model.emanagers.TsugerenciaManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.logging.Logger;


@Api(name = "tsugerenciaendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class TsugerenciaEndpoint {
	private static final Logger logger = Logger.getLogger(TsugerenciaEndpoint.class.getName());

	
	/**
	 * Permite obtener la lista de Tipos de Sugerencias
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Tipos de Sugerencias
	 */
	@ApiMethod(name = "listTsugerencia")
	public CollectionResponse<Tsugerencia> listTsugerencia(User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			TsugerenciaManager tsugerenciaM=new TsugerenciaManager();
			logger.warning("listTsugerencia : "+user);
			return tsugerenciaM.listTsugerencia(null,null);
		//}
		//return null;
	}
}
