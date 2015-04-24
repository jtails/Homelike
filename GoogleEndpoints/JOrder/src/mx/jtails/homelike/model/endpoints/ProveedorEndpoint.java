package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Dispositivop;
import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Producto;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.ServicioManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.ArrayList;
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
			List<Proveedor> proveedores=proveedorM.getProveedoresinRange(latitud, longitud,idServicio);
			logger.warning("Proveedores encontrados: "+proveedores.size());
			
			for(Proveedor proveedor:proveedores){
				//Limpiamos los campos que no deben ser mostrados
				proveedor.setUsuario("");
				
				logger.warning("Proveedor: "+proveedor.getIdProveedor()+" User :"+user);
				
				//Eliminamos los productos que no deben ser mostrados - Productos inactivos
				List<Producto> productose=new ArrayList<Producto>();//Implementado por ConcurrentModificationException, al iterar y eliminar
				logger.warning("Productos antes de excluir: "+(proveedor.getProductos()!=null?proveedor.getProductos().size():"null ,")+" User :"+user);
				for(Producto producto:proveedor.getProductos()){
					logger.warning("Producto Id: "+producto.getIdProducto()+" ,status: "+producto.getStatus()+" User :"+user);
					if(producto.getStatus()==0){//Copiar producto a la nueva lista
						logger.warning("Producto copiado Id: "+producto.getIdProducto()+" User :"+user);
						productose.add(producto);
					}
				}
				proveedor.setProductos(productose);
				logger.warning("Productos despues de excluir : "+(productose!=null?productose.size():"null ,")+" User :"+user);
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
	@ApiMethod(name = "getProveedor",path="getProveedor",httpMethod="POST")
	public Proveedor getProveedor(Proveedor proveedor/*,User user*/)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(proveedor.getIdProveedor()));
			return pproveedor;
		//}
		//return null;
	}
	
	
	
	/**
	 * Permite obtener referencia al objeto Proveedor persistido a partir de su Usuario - Cuenta de correo
	 * @param proveedor
	 * El proveedor con su usuario
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto Proveedor persistido, si el proveedor no existe retornara null
	 */
	@ApiMethod(name = "getProveedorByUser" , path="getProveedorByUser" , httpMethod = HttpMethod.POST)
	public Proveedor getProveedorByUser(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			Proveedor pproveedor=proveedorM.getProveedorByUser(proveedor);
			if(pproveedor!=null && pproveedor.getIdProveedor()!=0)
				logger.warning("Proveedor ID: "+pproveedor.getIdProveedor()+",User : "+user);
			else
				logger.warning("Proveedor no existente: "+proveedor.getUsuario()+", User : "+user);
			return pproveedor;
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
	public Proveedor insertProveedor(Proveedor proveedor/*,User user*/)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProveedorManager proveedorM=new ProveedorManager();
			ServicioManager servicioM=new ServicioManager();
		
			//Validamos que el nombre de Usuario no este registrado
			//La primer clausula del if es la condicion que cubre el caso del update del proveedor
			if(proveedor.getIdProveedor()==0 && proveedorM.getProveedorByUser(proveedor)!=null){
				proveedor.setStatus(-1);//-1 Nombre de Usuario existente
				logger.warning("Proveedor existente,regresando error : "/*+user*/);
				return proveedor;
			}
		
			//Obtenemos referencia al objeto persistido
			Servicio servicio=servicioM.getServicio(Long.valueOf(proveedor.getServicio().getIdServicio()));
			proveedor.setServicio(servicio);
			if(proveedor.getIdProveedor()==0){
				logger.warning("Nuevo proveedor : "/*+user*/+"Calificacion:"+proveedor.getCalificacion());
				proveedor.setStatus(0);//Proveedor Deshabilitado, hasta su confirmación
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
				logger.warning("Actualizacion de proveedor : "/*+user*/);
				return proveedorM.updateProveedor(pproveedor);
			}
		//}
		//return null;
	}
	
	/**
	 * Persiste el objeto dispositivo,si el dispositivo ya esta persistido realiza una operacion update,
	 * es necesario que previo a la generación del dispositivo el proveedor exista
	 * @param dispositivo
	 * El dispositivo a ser agregado o actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto dispositivo persistido,este contiene el ID del dispositivo generado
	 */
	@ApiMethod(name = "insertDispositivo")
	public Dispositivop insertDispositivop(Dispositivop dispositivo /*,User user*/)throws OAuthRequestException, IOException  {
		//if(user!=null){
			//Obtenemos referencia al objeto proveedor persistido
			ProveedorManager proveedorM=new ProveedorManager();
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(dispositivo.getProveedor().getIdProveedor()));
			if(pproveedor.getDispositivos()!=null && pproveedor.getDispositivos().size()>0){
				//Actualizamos el dispositivo del proveedor
				Dispositivop pdispositivo=pproveedor.getDispositivos().get(0);
				pdispositivo.setEsDefault(dispositivo.getEsDefault());
				pdispositivo.setGcmid(dispositivo.getGcmid());
				pdispositivo.setImei(dispositivo.getImei());
				pdispositivo.setModelo(dispositivo.getModelo());
				pdispositivo.setPlataforma(dispositivo.getPlataforma());
				pdispositivo.setTipoDispositivo(dispositivo.getTipoDispositivo());
				logger.warning("Actualizacion de dispositivo : IdDispositivo "+pdispositivo.getIdDispositivo()+" : "/*+user*/);
			}else{
				//Generamos un dispositivo para el proveedor
				List<Dispositivop> dispositivos=new ArrayList<Dispositivop>();
				//Establecemos el proveedor persistido para el dispositivo
				dispositivo.setProveedor(pproveedor);
				dispositivos.add(dispositivo);
				pproveedor.setDispositivos(dispositivos);
				logger.warning("Nuevo dispositivo"/*+user*/);
			}
			return proveedorM.updateProveedor(pproveedor).getDispositivos().get(0);
		//}
		//return null;
	}
	
}
