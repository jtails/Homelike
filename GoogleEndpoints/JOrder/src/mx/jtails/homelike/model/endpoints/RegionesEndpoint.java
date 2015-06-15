package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Regiones;
import mx.jtails.homelike.model.beans.RegionesWrapper;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.EMF;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.emanagers.RegionesManager;
import mx.jtails.homelike.model.emanagers.ServicioManager;
import mx.jtails.homelike.model.gcs.FileManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.google.appengine.tools.cloudstorage.GcsFilename;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public Regiones insertRegion(RegionesWrapper regionesWrapper,@Named("idProveedor") Long idProveedor/*,User user*/)throws OAuthRequestException, IOException  {		
		//if(user!=null){
		logger.warning(regionesWrapper.getRegiones().size()+"");
		ProveedorManager proveedorM=new ProveedorManager();
		//Obtenemos referencia al proveedor persistido
		Proveedor pproveedor=proveedorM.getProveedor(idProveedor);
		List<Regiones> regiones=pproveedor.getRegiones();
		
		if(regionesWrapper!=null && regionesWrapper.getRegiones().size()>0){
			//Si el proveedor no cuenta con regiones, se persisten las regiones
			if(regiones.size()==0){
				logger.warning("Nuevas regiones");
				for(Regiones region:regionesWrapper.getRegiones())
					pproveedor.addRegion(region);
			}else{
				int sizeWrapper=regionesWrapper.getRegiones().size();
				int sizeRegiones=regiones.size();
				//Si el proveedor decide agregar regiones, se actualizan las regiones existentes, y se persisten las nuevas
				if(sizeWrapper>sizeRegiones){
					logger.warning("Nuevas regiones mayores a las existentes");
					for(int i=0;i<sizeWrapper;i++){
						if(i<sizeRegiones){
							Regiones currentRegion=regiones.get(i);
							Regiones wrapperRegion=regionesWrapper.getRegiones().get(i);
							currentRegion.setLabel(wrapperRegion.getLabel());
							currentRegion.setNelatitud(wrapperRegion.getNelatitud());
							currentRegion.setNelongitud(wrapperRegion.getNelongitud());
							currentRegion.setSwlatitud(wrapperRegion.getSwlatitud());
							currentRegion.setSwlongitud(wrapperRegion.getSwlongitud());
							currentRegion.setStatus(0);
							logger.warning("Region actualizada");
						}else{
							Regiones wrapperRegion=regionesWrapper.getRegiones().get(i);
							pproveedor.addRegion(wrapperRegion);
							logger.warning("Region nueva");
						}
					}
				}else{
					//Si el proveedor decide eliminar regiones, se actualizan las regiones existentes, y se eliminan logicamente las restantes
					logger.warning("Nuevas regiones menores a las existentes");
					for(int i=0;i<sizeRegiones;i++){
						if(i<sizeWrapper){
							Regiones currentRegion=regiones.get(i);
							Regiones wrapperRegion=regionesWrapper.getRegiones().get(i);
							currentRegion.setLabel(wrapperRegion.getLabel());
							currentRegion.setNelatitud(wrapperRegion.getNelatitud());
							currentRegion.setNelongitud(wrapperRegion.getNelongitud());
							currentRegion.setSwlatitud(wrapperRegion.getSwlatitud());
							currentRegion.setSwlongitud(wrapperRegion.getSwlongitud());
							currentRegion.setStatus(0);
							logger.warning("Region actualizada");
						}else{
							Regiones currentRegion=regiones.get(i);
							currentRegion.setLabel("");
							currentRegion.setStatus(1);
							logger.warning("Region eliminada logicamente");
						}
					}
				}
			}
		}else{
			//Eliminamos logicamente todas las regiones del proveedor
			logger.warning("Eliminacion logica de regiones existentes");
			for(Regiones region:regiones){
				region.setLabel("");
				region.setStatus(1);
			}
		}
		proveedorM.updateProveedor(pproveedor);
		return null;
		//}
		//return null;
	}
}
