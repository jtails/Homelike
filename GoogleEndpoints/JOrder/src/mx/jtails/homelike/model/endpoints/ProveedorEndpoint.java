package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.ServicioManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

@Api(name = "proveedorendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class ProveedorEndpoint {
	private static final Logger logger = Logger.getLogger(ProveedorEndpoint.class.getName());
	
	/**
	 * Permite obtener una lista de proveedores cercanos a un cliente que busca un servicio en particular
	 * @param latitud
	 * La latitud de la ubicacion
	 * @param longitud
	 * La longitud de la ubicacion
	 * @param idServicio
	 * El ID del Servicio  
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de proveedores cercanos a un cliente que brindan un servicio en particular
	 */
	@ApiMethod(name = "listProveedoresinRange",path="listProveedoresinRange")
	public List<Proveedor> getProveedoresinRange(@Named("latitud") String latitud,@Named("longitud") String longitud,@Named("idServicio") int idServicio,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			List<Proveedor> proveedores=proveedorM.getProveedoresinRagne(latitud, longitud,idServicio);
			logger.warning("Proveedores encontrados: "+proveedores.size());
			for(Proveedor proveedor:proveedores){
				for(Pedido pedido:proveedor.getPedidos()){
					if(pedido.getStatus()==2){//Pedido terminado
						proveedor.setNumPedidos(proveedor.getNumPedidos()+1);
						proveedor.setCalificacion(proveedor.getCalificacion()+pedido.getCalificacion());
					}
				}
				if(proveedor.getNumPedidos()>0)
					proveedor.setCalificacion(proveedor.getCalificacion()/proveedor.getNumPedidos());
				//Limpiamos los campos que no deben ser mostrados
				proveedor.setUsuario("");
			}
			return proveedores;
		//}
		//return null;
	}


	/**
	 * Permite obtener referencia al objeto Proveedor persistido a partir de su ID
	 * @param id
	 * El ID del proveedor
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto Proveedor persistido
	 */
	@ApiMethod(name = "getProveedor")
	public Proveedor getProveedor(@Named("id") Long id,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			Proveedor proveedor=proveedorM.getProveedor(id);
			for(Pedido pedido:proveedor.getPedidos()){
				if(pedido.getStatus()==2){//Pedido terminado
					proveedor.setNumPedidos(proveedor.getNumPedidos()+1);
					proveedor.setCalificacion(proveedor.getCalificacion()+pedido.getCalificacion());
				}
			}
			if(proveedor.getNumPedidos()>0)
				proveedor.setCalificacion(proveedor.getCalificacion()/proveedor.getNumPedidos());
			return proveedor;
		//}
		//return null;
	}

	/**
	 * Persiste el objeto proveedor,si el proveedor ya esta persistido realiza una operacion update
	 * @param proveedor
	 * El proveedor a ser agregado o actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto proveedor persistido,este contiene el ID del proveedor generado,en caso de un error regresara un Status -1
	 */
	@ApiMethod(name = "insertProveedor")
	public Proveedor insertProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			ServicioManager servicioM=new ServicioManager();
		
			//Validamos que el nombre de Usuario no este registrado
			//La primer clausula del if es la condicion que cubre el caso del update del proveedor
			if(proveedor.getIdProveedor()==0 && proveedorM.getProveedorByUser(proveedor)!=null){
				proveedor.setStatus(-1);//-1 Nombre de Usuario existente
				logger.warning("Proveedor existente,regresando error : "+user);
				return proveedor;
			}
		
			//Obtenemos referencia al objeto persistido
			Servicio servicio=servicioM.getServicio(Long.valueOf(proveedor.getServicio().getIdServicio()));
			proveedor.setServicio(servicio);
			if(proveedor.getIdProveedor()==0){
				logger.warning("Nueva proveedor : "+user);
				return proveedorM.insertProveedor(proveedor);
			}
			else{
				//Obtenemos referencia al objeto persistido 
				Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(proveedor.getIdProveedor()));
				try {
					//Copiamos los valores del Bean JSON al Bean Persistido
					//BeanUtils.copyProperties(pproveedor, proveedor);
					pproveedor.setServicio(servicio);
					pproveedor.setCalle(proveedor.getCalle());
					pproveedor.setCalle1(proveedor.getCalle1());
					pproveedor.setCalle2(proveedor.getCalle2());
					pproveedor.setColonia(proveedor.getColonia());
					pproveedor.setCp(proveedor.getCp());
					pproveedor.setDelegacion(proveedor.getDelegacion());
					pproveedor.setEstado(proveedor.getEstado());
					pproveedor.setLatitud(proveedor.getLatitud());
					pproveedor.setLogo(proveedor.getLogo());
					pproveedor.setLongitud(proveedor.getLongitud());
					pproveedor.setNelatitud(proveedor.getNelatitud());
					pproveedor.setNelongitud(proveedor.getNelongitud());
					pproveedor.setNexterior(proveedor.getNexterior());
					pproveedor.setNinterior(proveedor.getNinterior());
					pproveedor.setNombre(proveedor.getNombre());
					pproveedor.setPais(proveedor.getPais());
					pproveedor.setRazonSocial(proveedor.getRazonSocial());
					pproveedor.setReferencia1(proveedor.getReferencia1());
					pproveedor.setReferencia2(proveedor.getReferencia2());
					pproveedor.setRfc(proveedor.getRfc());
					pproveedor.setSlogan(proveedor.getSlogan());
					pproveedor.setSwlatitud(proveedor.getSwlatitud());
					pproveedor.setSwlongitud(proveedor.getSwlongitud());
					pproveedor.setTelefono(proveedor.getTelefono());
					pproveedor.setUsuario(proveedor.getUsuario());
				} catch (Exception e) {
					e.printStackTrace();
				}
				logger.warning("Actualizacion de proveedor : "+user);
				return proveedorM.updateProveedor(pproveedor);
			}
		//}
		//return null;
	}
	
}
