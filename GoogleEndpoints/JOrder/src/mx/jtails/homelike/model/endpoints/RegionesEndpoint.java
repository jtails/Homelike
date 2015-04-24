package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Regiones;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.EMF;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.emanagers.RegionesManager;
import mx.jtails.homelike.model.emanagers.ServicioManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "regionesendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class RegionesEndpoint {
	private static final Logger logger = Logger.getLogger(RegionesEndpoint.class.getName());
	
	/**
	 * Persiste el objeto region 
	 * @param region
	 * El objeto region  a ser persistido, contiene referencia al proveedor
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto region persistido
	 */
	@ApiMethod(name = "insertRegion",path="insertRegion",httpMethod="POST")
	public Regiones insertRegion(Regiones region/*,User user*/)throws OAuthRequestException, IOException  {
		//if(user!=null){
			RegionesManager regionesM=new RegionesManager();
			ProveedorManager proveedorM=new ProveedorManager(); 
			//Obtenemos referencia al proveedor persistido
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(region.getProveedor().getIdProveedor()));
			pproveedor.addRegion(region);
			proveedorM.updateProveedor(pproveedor);
			return region;
		//}
		//return null;
	}
	
	/**
	 * Elimina las regiones de un proveedor
	 * @param proveedor
	 * El proveedor con su ID
	 * @param user
	 * El usuario autenticado con Google
	 */
	@ApiMethod(name = "deleteRegiones",path="deleteRegiones",httpMethod="POST")
	public void deleteRegiones(Proveedor proveedor/*,User user*/)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(proveedor.getIdProveedor()));
			RegionesManager regionesM=new RegionesManager();
			for(Regiones region:pproveedor.getRegiones()){
				regionesM.removeRegiones(Long.valueOf(region.getIdRegion()));
			}
		//}
		//return null;
	}

}
