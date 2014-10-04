package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Sugerenciasp;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.emanagers.SugerenciaspManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.logging.Logger;

@Api(name = "sugerenciaspendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class SugerenciaspEndpoint {
	private static final Logger logger = Logger.getLogger(SugerenciaspEndpoint.class.getName());

	/**
	 * Permite obtener la lista de sugerencias provenientes de proveedores
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de sugerencias
	 */
	@ApiMethod(name = "listSugerenciasp")
	public CollectionResponse<Sugerenciasp> listSugerenciasp(User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			SugerenciaspManager sugerenciaspM=new SugerenciaspManager();
			return sugerenciaspM.listSugerenciasp(null,null);
		//}
		//return null;
	}


	/**
	 * Persiste el objeto sugerenciasp con referencias al proveedor y al tipo de Sugerencia
	 * @param sugerenciasp
	 * El objeto sugerenciasp a ser persistido
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto sugerenciasp persistido con referencias al proveedor y al tipo de Sugerencia
	 */
	@ApiMethod(name = "insertSugerenciasp")
	public Sugerenciasp insertSugerenciasp(Sugerenciasp sugerenciasp,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			SugerenciaspManager sugerenciaspM=new SugerenciaspManager();
			ProveedorManager proveedorM=new ProveedorManager();
			//Obtenemos referencia a los objetos persistidos
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(sugerenciasp.getProveedor().getIdProveedor()));
			sugerenciasp.setProveedor(pproveedor);
			logger.warning("Nueva sugerencia proveedor: "+sugerenciasp.getSugerencia()+" "+user);
			return sugerenciaspM.insertSugerenciasp(sugerenciasp);
		//}
		//return null;
	}

}
