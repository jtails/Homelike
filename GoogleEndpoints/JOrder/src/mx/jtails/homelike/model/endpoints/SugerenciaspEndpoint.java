package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Sugerenciasp;
import mx.jtails.homelike.model.emanagers.EMF;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.emanagers.SugerenciaspManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.users.User;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "sugerenciaspendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class SugerenciaspEndpoint {
	private static final Logger logger = Logger.getLogger(SugerenciaspEndpoint.class.getName());

	@ApiMethod(name = "listSugerenciasp")
	public CollectionResponse<Sugerenciasp> listSugerenciasp(User user) {
		//if(user!=null){
			SugerenciaspManager sugerenciaspM=new SugerenciaspManager();
			return sugerenciaspM.listSugerenciasp(null,null);
		//}
		//return null;
	}


	@ApiMethod(name = "insertSugerenciasp")
	public Sugerenciasp insertSugerenciasp(Sugerenciasp sugerenciasp,User user) {
		//if(user!=null){
			SugerenciaspManager sugerenciaspM=new SugerenciaspManager();
			ProveedorManager proveedorM=new ProveedorManager();
			//Obtenemos referencia a los objetos persistidos
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(sugerenciasp.getProveedor().getIdProveedor()));
			sugerenciasp.setProveedor(pproveedor);
			logger.warning("Nueva sugerencia: "+sugerenciasp.getSugerencia());
			return sugerenciaspM.insertSugerenciasp(sugerenciasp);
		//}
		//return null;
	}

}
