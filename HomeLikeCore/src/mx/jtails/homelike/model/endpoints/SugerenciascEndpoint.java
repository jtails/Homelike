package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Sugerenciasc;
import mx.jtails.homelike.model.beans.Tsugerencia;
import mx.jtails.homelike.model.emanagers.CuentaManager;
import mx.jtails.homelike.model.emanagers.SugerenciascManager;
import mx.jtails.homelike.model.emanagers.TsugerenciaManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.logging.Logger;


@Api(name = "sugerenciascendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class SugerenciascEndpoint {
	private static final Logger logger = Logger.getLogger(SugerenciascEndpoint.class.getName());

	/**
	 * Permite obtener la lista de sugerencias provenientes de clientes
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de sugerencias
	 */
	@ApiMethod(name = "listSugerenciasc")
	public CollectionResponse<Sugerenciasc> listSugerenciasc(User user) throws OAuthRequestException, IOException  {
		//if(user!=null){
			SugerenciascManager sugerenciascM=new SugerenciascManager();
			return sugerenciascM.listSugerenciasc(null,null);
		//}
		//return null;
	}

	
	/**
	 * Persiste el objeto sugerenciasc con referencias a la cuenta del cliente y al tipo de Sugerencia
	 * @param sugerenciasc
	 * El objeto sugerenciasc a ser persistido
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto sugerenciasc persistido con referencias a la cuenta y al tipo de Sugerencia
	 */
	@ApiMethod(name = "insertSugerenciasc")
	public Sugerenciasc insertSugerenciasc(Sugerenciasc sugerenciasc,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			SugerenciascManager sugerenciascM=new SugerenciascManager();
			CuentaManager cuentaM=new CuentaManager();
			TsugerenciaManager tsegurenciaM=new TsugerenciaManager();
			//Obtenemos referencia a los objetos persistidos
			Cuenta pcuenta=cuentaM.getCuenta(Long.valueOf(sugerenciasc.getCuenta().getIdCuenta()));
			Tsugerencia ptsugerencia=tsegurenciaM.getTsugerencia(Long.valueOf(sugerenciasc.getTsugerencia().getIdTsugerencia()));
			
			sugerenciasc.setCuenta(pcuenta);
			sugerenciasc.setTsugerencia(ptsugerencia);
			logger.warning("Nueva sugerencia cliente: "+sugerenciasc.getSugerencia()+" "+user);
			return sugerenciascM.insertSugerenciasc(sugerenciasc);
		//}
		//return null;
	}

}
