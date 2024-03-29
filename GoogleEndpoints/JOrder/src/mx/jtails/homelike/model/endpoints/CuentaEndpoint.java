package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Direccion;
import mx.jtails.homelike.model.beans.Dispositivo;
import mx.jtails.homelike.model.beans.Dispositivop;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.CuentaManager;
import mx.jtails.homelike.model.emanagers.DireccionManager;
import mx.jtails.homelike.model.emanagers.PedidoManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;


@Api(name = "cuentaendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class CuentaEndpoint {
	private static final Logger logger = Logger.getLogger(CuentaEndpoint.class.getName());

	/**
	 * Persiste el objeto cuenta con referencias a direccion y dispositivo,si la cuenta ya esta persistida realiza una operacion update
	 * Este metodo tambien permite la actualizacion de una direccion asociada a la cuenta existente o la insercion de nuevas direcciones a una cuenta existente, siempre debe enviarse solo una direccion
	 * @param cuenta
	 * La cuenta a ser agregada o actualizada
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto cuenta persistido,este contiene el ID de la cuenta generado,en caso de un error regresara un Status -1
	 */
	@ApiMethod(name = "insertCuenta")
	public Cuenta insertCuenta(Cuenta cuenta,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			CuentaManager cuentaM=new CuentaManager();
		
			//Validamos que el nombre de Usuario no este registrado
			//La primer clausula del if es la condicion que cubre el caso del update de la cuenta
			//if(/*cuenta.getDirecciones().get(0).getIdDireccion()==0 &&*/ cuentaM.getCuentaByUser(cuenta)!=null){
				//cuenta.setStatus(-1);//-1 Nombre de Usuario existente
				//logger.warning("Cuenta existente,regresando error : "+user);
				//return cuenta;
			//}
		
			if(cuenta.getIdCuenta()==0){
				//Persistimos el Objeto Cuenta (Requiere cascade = {CascadeType.PERSIST} en Cuenta Bean)
				//Y (@JsonBackReference en DispositivoBean)
				logger.warning("Nueva cuenta : "+user);
				return cuentaM.insertCuenta(cuenta);
			}
			else{
				//Obtenemos referencia al objeto persistido 
				Cuenta pcuenta=cuentaM.getCuenta(Long.valueOf(cuenta.getIdCuenta()));
				try {
					//Copiamos los valores del Bean JSON al Bean Persistido
					//Descomentar para update de cuenta
					//pcuenta.setTelefono(cuenta.getTelefono());
					//pcuenta.setUsuario(cuenta.getUsuario());
					
					
					boolean addDireccion=true;
					for(Direccion pdireccion:pcuenta.getDirecciones()){
						//Actualizamos una direccion existente
						if(pdireccion.getIdDireccion()==cuenta.getDirecciones().get(0).getIdDireccion()){
							pdireccion.setCalle(cuenta.getDirecciones().get(0).getCalle());
							pdireccion.setCalle1(cuenta.getDirecciones().get(0).getCalle1());
							pdireccion.setCalle2(cuenta.getDirecciones().get(0).getCalle2());
							pdireccion.setColonia(cuenta.getDirecciones().get(0).getColonia());
							pdireccion.setCp(cuenta.getDirecciones().get(0).getCp());
							pdireccion.setDelegacion(cuenta.getDirecciones().get(0).getDelegacion());
							pdireccion.setEstado(cuenta.getDirecciones().get(0).getEstado());
							pdireccion.setLatitud(cuenta.getDirecciones().get(0).getLatitud());
							pdireccion.setLongitud(cuenta.getDirecciones().get(0).getLongitud());
							pdireccion.setNexterior(cuenta.getDirecciones().get(0).getNexterior());
							pdireccion.setNinterior(cuenta.getDirecciones().get(0).getNinterior());
							pdireccion.setPais(cuenta.getDirecciones().get(0).getPais());
							pdireccion.setReferencia1(cuenta.getDirecciones().get(0).getReferencia1());
							pdireccion.setReferencia2(cuenta.getDirecciones().get(0).getReferencia2());
							pdireccion.setAlias(cuenta.getDirecciones().get(0).getAlias());
							addDireccion=false;
							logger.warning("Actualizando direccion : "+user);
						}
					}
					if(addDireccion){//Agregamos una nueva direccion
						pcuenta.addDireccion(cuenta.getDirecciones().get(0));
						logger.warning("Agregando direccion : "+user);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				logger.warning("Actualizacion de cuenta : "+user);
				return cuentaM.updateCuenta(pcuenta);
			}
		//}
		//return null;
	}
	
	
	
	/**
	 * Persiste el objeto dispositivo,si el dispositivo ya esta persistido realiza una operacion update,
	 * es necesario que previo a la generación del dispositivo la cuenta exista
	 * @param dispositivo
	 * El dispositivo a ser agregado o actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto dispositivo persistido,este contiene el ID del dispositivo generado
	 */
	@ApiMethod(name = "insertDispositivo")
	public Dispositivo insertDispositivo(Dispositivo dispositivo/*,User user*/)throws OAuthRequestException, IOException  {
		//if(user!=null){
			//Obtenemos referencia al objeto cuenta persistido
			CuentaManager cuentaM=new CuentaManager();
			Cuenta pcuenta=cuentaM.getCuenta(Long.valueOf(dispositivo.getCuenta().getIdCuenta()));
			if(pcuenta.getDispositivos()!=null && pcuenta.getDispositivos().size()>0){
				//Actualizamos el dispositivo de la cuenta
				Dispositivo pdispositivo=pcuenta.getDispositivos().get(0);
				pdispositivo.setEsDefault(dispositivo.getEsDefault());
				pdispositivo.setGcmid(dispositivo.getGcmid());
				pdispositivo.setImei(dispositivo.getImei());
				pdispositivo.setModelo(dispositivo.getModelo());
				pdispositivo.setPlataforma(dispositivo.getPlataforma());
				pdispositivo.setTipoDispositivo(dispositivo.getTipoDispositivo());
				logger.warning("Actualizacion de dispositivo : IdDispositivo "+pdispositivo.getIdDispositivo()+" : "/*+user*/);
			}else{
				//Generamos un dispositivo para la cuenta
				List<Dispositivo> dispositivos=new ArrayList<Dispositivo>();
				//Establecemos la cuenta persistida para el dispositivo
				dispositivo.setCuenta(pcuenta);
				dispositivos.add(dispositivo);
				pcuenta.setDispositivos(dispositivos);
				logger.warning("Nuevo dispositivo"/*+user*/);
			}
			return cuentaM.updateCuenta(pcuenta).getDispositivos().get(0);
		//}
		//return null;
	}
	
	
	@ApiMethod(name = "deleteDireccion", path="deleteDireccion", httpMethod = HttpMethod.POST)
	public void deleteDireccion(@Named("idDireccion") int idDireccion,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			DireccionManager direccionM=new DireccionManager();
			//Obtenemos referencia al objeto direccion persistido
			Direccion pdireccion=direccionM.getDireccion(Long.valueOf(idDireccion));
			pdireccion.setStatus(0);//Borrado logico
			direccionM.updateDireccion(pdireccion);
			logger.warning("Eliminando Direccion : "+idDireccion+", User : "+user);
		//}
		//return null;
	}
	
	/**
	 * Permite obtener una cuenta persistida con su referencia al dispositivo y a la direccion a partir de un objeto cuenta con su ID
	 * @param cuenta
	 * La cuenta con el ID, para obtener referencia al objeto persistido
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto cuenta persistido,este contiene referencia al dispositivo y a la direccion
	 */
	@ApiMethod(name = "getCuenta", path="getCuenta")
	public Cuenta getCuenta(@Named("id") Long id,User user)throws OAuthRequestException, IOException {
	//	if(user!=null){
		CuentaManager cuentaM=new CuentaManager();
		Cuenta cuenta=cuentaM.getCuenta(id);	
		//Excluimos las direcciones inactivas
		List<Direccion> direccionese=new ArrayList<Direccion>();//Implementado por ConcurrentModificationException, al iterar y eliminar
		
		logger.warning("Direcciones en la cuenta antes de excluir: "+(cuenta.getDirecciones()!=null?cuenta.getDirecciones().size():"null ,")+" User :"+user);
		for(Direccion direccion:cuenta.getDirecciones()){
			logger.warning("Direccion Id: "+direccion.getIdDireccion()+" ,status: "+direccion.getStatus()+" User :"+user);
			if(direccion.getStatus()==1){//Copiar direccion a la nueva lista
				logger.warning("Direccion copiada Id: "+direccion.getIdDireccion()+" User :"+user);
				direccionese.add(direccion);
			}
		}
		cuenta.setDirecciones(direccionese);
		logger.warning("Direcciones en la cuenta despues de excluir : "+(direccionese!=null?direccionese.size():"null ,")+" User :"+user);
		return cuenta;
		//}
		//return null;
	}
	
	
	
	/**
	 * Permite obtener una lista de cuentas que contienen referencia a los dispositivos de los clientes cercanos a un proveedor
	 * @param proveedor
	 * El proveedor, para obtener los clientes cercanos a la ubicacion del proveedor 
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista con las cuentas que contienen referencia a los dispositivos cercanos a la ubicacion del proveedor
	 */
	@ApiMethod(name = "listClientesinRange",path="listClientesinRange",httpMethod="POST")
	public List<Cuenta> getClientesinRange(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			CuentaManager cuentaM=new CuentaManager();
			PedidoManager pedidoM=new PedidoManager();
			List<Cuenta> cuentas;
			logger.warning("Coordenadas Proveedor: "+proveedor.getNelatitud()+","+proveedor.getSwlatitud()+","+proveedor.getSwlongitud()+","+proveedor.getNelongitud());
			cuentas=cuentaM.getClientesinRange(proveedor.getNelatitud(), proveedor.getSwlatitud(),proveedor.getSwlongitud(),proveedor.getNelongitud());
			logger.warning("Clientes encontrados : "+cuentas.size());
			//Verificamos si el cliente tiene pedidos con el proveedor
			for(Cuenta cuenta:cuentas){
				cuenta.setNumPedidos(pedidoM.countTotalPedidosByCuentaProveedor(proveedor,cuenta));
				//Limpiamos los campos que no deben ser mostrados
				cuenta.setUsuario("");
			}
			return cuentas;
		//}
		//return null;
	}
	
	/**
	 * Permite obtener el numClientes de un proveedor
	 * @param proveedor
	 * El proveedor con el ID, para obtener el numClientes
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el proveedor con el campo numClientes conteniendo el total de clientes que han realizado pedidos con el proveedor
	 */
	@ApiMethod(name = "getClienteswithPedidoByProveedor",path="getClienteswithPedidoByProveedor",httpMethod="POST")
	public Proveedor getClienteswithPedidoByProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			CuentaManager cuentaM=new CuentaManager();
			return cuentaM.getClienteswithPedidoByProveedor(proveedor);
		//}
		//return null;
	}
	
	
	/**
	 * Permite obtener referencia al objeto cuenta,este objeto tiene referencia a la lista de direcciones y dispositivos
	 * @param cuenta
	 * La cuenta con el usuario, para obtener referencia al objeto cuenta persistido
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto cuenta persistido, con referencia a las direcciones y dispositivos
	 */
	@ApiMethod(name = "getCuentaByUser",path="getCuentaByUser")
	public Cuenta getCuentaByUser(Cuenta cuenta,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			CuentaManager cuentaM=new CuentaManager();
			cuenta=cuentaM.getCuentaByUser(cuenta);
			//Excluimos las direcciones inactivas
			List<Direccion> direccionese=new ArrayList<Direccion>();//Implementado por ConcurrentModificationException, al iterar y eliminar
			
			logger.warning("Direcciones en la cuenta antes de excluir: "+(cuenta.getDirecciones()!=null?cuenta.getDirecciones().size():"null ,")+" User :"+user);
			for(Direccion direccion:cuenta.getDirecciones()){
				logger.warning("Direccion Id: "+direccion.getIdDireccion()+" ,status: "+direccion.getStatus()+" User :"+user);
				if(direccion.getStatus()==1){//Copiar direccion a la nueva lista
					logger.warning("Direccion copiada Id: "+direccion.getIdDireccion()+" User :"+user);
					direccionese.add(direccion);
				}
			}
			cuenta.setDirecciones(direccionese);
			logger.warning("Direcciones en la cuenta despues de excluir : "+(direccionese!=null?direccionese.size():"null ,")+" User :"+user);
			return cuenta;
		//}
		//return null;
	}

}
