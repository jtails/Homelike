package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Producto;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Repartidor;
import mx.jtails.homelike.model.emanagers.ProductoManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.emanagers.RepartidorManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;


@Api(name = "repartidorendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class RepartidorEndpoint {
	private static final Logger logger = Logger.getLogger(RepartidorEndpoint.class.getName());

	
	/**
	 * Persiste el objeto repartidor,si el repartidor ya esta persistido realiza una operacion update
	 * @param repartidor
	 * El repartidor a ser agregado o actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto repartidor persistido,este contiene el ID del repartidor generado
	 */
	@ApiMethod(name = "insertRepartidor")
	public Repartidor insertRepartidor(Repartidor repartidor/*,User user*/)throws OAuthRequestException, IOException {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			RepartidorManager repartidorM=new RepartidorManager();
			
			//Obtenemos referencia al objeto Proveedor persistido
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(repartidor.getProveedor().getIdProveedor()));
			Repartidor prepartidor=repartidorM.getRepartidor(Long.valueOf(repartidor.getIdRepartidor()));
			
			//Repartidor nuevo
			if(prepartidor==null || prepartidor.getIdRepartidor()==0){
				logger.warning("Repartidor nuevo");
				repartidor.setProveedor(pproveedor);
				return repartidorM.insertRepartidor(repartidor);
			}else{//Actualización de Repartidor
				logger.warning("Actualizacion de repartidor : "+repartidor.getIdRepartidor());
				prepartidor.setNombres(repartidor.getNombres());
				prepartidor.setApaterno(repartidor.getApaterno());
				prepartidor.setAmaterno(repartidor.getAmaterno());
				prepartidor.setUsuario(repartidor.getUsuario());
				return repartidorM.updateRepartidor(prepartidor);
			}
		//}
		//return null;
	}
	
	
	@ApiMethod(name = "deshabilitarRepartidor")
	public void deshabilitarRepartidor(@Named("id") Long id,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			RepartidorManager repartidorM=new RepartidorManager();
			Repartidor prepartidor=repartidorM.getRepartidor(id);
			//-3 Borrado logico
			prepartidor.setStatus(-3);
			repartidorM.updateRepartidor(prepartidor);
		//}
	}
	
	@ApiMethod(name = "habilitarRepartidor")
	public void habilitarRepartidor(@Named("id") Long id,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			RepartidorManager repartidorM=new RepartidorManager();
			Repartidor prepartidor=repartidorM.getRepartidor(id);
			//0 Activo
			prepartidor.setStatus(0);
			repartidorM.updateRepartidor(prepartidor);
		//}
	}
	
	/**
	 * Permite obtener la lista de repartidores de un proveedor
	 * El Proveedor para la busqueda de sus repartidores  
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de repartidores
	 */
	@ApiMethod(name = "listAllRepartidoresByProveedor",path="listAllRepartidoresByProveedor",httpMethod="POST")
	public List<Repartidor> listAllRepartidoresByProveedor(Proveedor proveedor/*,User user*/)throws OAuthRequestException, IOException  {
		//if(user!=null){
			RepartidorManager repartidorM=new RepartidorManager();
			logger.warning("idProveedor: "+proveedor.getIdProveedor());
			List<Repartidor> repartidores=repartidorM.listAllRepartidoresByProveedor(proveedor);
			logger.warning("Repartidores encontrados: "+repartidores.size());
			return repartidores;
		//}
		//return null;
	}
	
	
	/**
	 * Permite obtener referencia al objeto Repartidor persistido a partir de su Usuario - Cuenta de correo
	 * @param repartidor
	 * El repartidor con su usuario
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto Repartidor persistido, si el repartidor no existe retornara null
	 */
	@ApiMethod(name = "getRepartidorByUser" , path="getRepartidorByUser" , httpMethod = HttpMethod.POST)
	public Repartidor getRepartidorByUser(Repartidor repartidor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			RepartidorManager repartidorM=new RepartidorManager();
			Repartidor prepartidor=repartidorM.getRepartidorByUser(repartidor);
			if(prepartidor!=null && prepartidor.getIdRepartidor()!=0)
				logger.warning("Repartidor ID: "+prepartidor.getIdRepartidor()+",User : "+user);
			else
				logger.warning("Repartidor no existente: "+repartidor.getUsuario()+", User : "+user);
			return prepartidor;
		//}
		//return null;
	}
	
	
}
