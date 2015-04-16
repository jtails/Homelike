package mx.jtails.homelike.model.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Producto;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.ProveedorManager;


@Api(name = "proveedoradminendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,}
,audiences = {Constants.WEB_CLIENT_ID}
)
public class ProveedorAdminEndpoint {
	private static final Logger logger = Logger.getLogger(ProveedorAdminEndpoint.class.getName());
	
	/**
	 * Permite obtener la lista de Proveedores existentes
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Proveedores
	 */
	@ApiMethod(name = "listProveedor")
	public CollectionResponse<Proveedor> listProveedor(User user)throws OAuthRequestException, IOException  {
		if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			CollectionResponse<Proveedor> proveedores=proveedorM.listProveedor(null,null);
			logger.warning("Proveedores: "+proveedores.getItems().size()+" User :"+user);
			/*for(Proveedor proveedor:proveedores.getItems()){
				//Generamos la calificacion
				for(Pedido pedido:proveedor.getPedidos()){
					if(pedido.getStatus()==2){//Pedido terminado
						proveedor.setNumPedidos(proveedor.getNumPedidos()+1);
						proveedor.setCalificacion(proveedor.getCalificacion()+pedido.getCalificacion());
					}
				}
				if(proveedor.getNumPedidos()>0)
					proveedor.setCalificacion(proveedor.getCalificacion()/proveedor.getNumPedidos());
			}*/
			return proveedores;
		}else{
			logger.warning("Usuario no autorizado");
			return null;	
		}
	}
	
	
	/**
	 * Actualiza el objeto proveedor, modificando el status
	 * @param proveedor
	 * El proveedor a ser actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto proveedor persistido
	 */
	//0  Usuario Deshabiliado
	//1  Usuario Habilitado
	@ApiMethod(name = "updateStatus",path="updateStatus",httpMethod="POST")
	public Proveedor updateStatus(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			//Obtenemos referencia al objeto persistido 
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(proveedor.getIdProveedor()));
			pproveedor.setStatus(proveedor.getStatus());
			logger.warning("User :"+user+", status :"+pproveedor.getStatus());
			return proveedorM.updateProveedor(pproveedor);
		}else{
			logger.warning("Usuario no autorizado");
			return null;	
		}
	}

}
