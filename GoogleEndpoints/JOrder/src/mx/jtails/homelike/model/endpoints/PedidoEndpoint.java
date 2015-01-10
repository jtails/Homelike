package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.CantidadPago;
import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.DetallePedido;
import mx.jtails.homelike.model.beans.Direccion;
import mx.jtails.homelike.model.beans.Dispositivo;
import mx.jtails.homelike.model.beans.Dispositivop;
import mx.jtails.homelike.model.beans.Grafico;
import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Producto;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.CantidadPagoManager;
import mx.jtails.homelike.model.emanagers.CuentaManager;
import mx.jtails.homelike.model.emanagers.DireccionManager;
import mx.jtails.homelike.model.emanagers.DispositivoManager;
import mx.jtails.homelike.model.emanagers.PedidoManager;
import mx.jtails.homelike.model.emanagers.ProductoManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.gcm.SendMessage;
import mx.jtails.homelike.model.gcm.Message;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.inject.Named;

@Api(name = "pedidoendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class PedidoEndpoint {
	private static final Logger logger = Logger.getLogger(PedidoEndpoint.class.getName());
	
	/**
	 * Permite obtener los pedidos con Status diferente a 2(Pedidos no terminados)
	 * @param cuenta
	 * La cuenta con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Pedidos con Status diferente a 2
	 */
	@ApiMethod(name = "listPedidosByCuenta",path="listPedidosByCuenta",httpMethod="POST")
	public List<Pedido> listPedidosByCuenta(Cuenta cuenta,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			return pedidoM.listPedidosByCuenta(cuenta);
		//}
		//return null;
	}
	
	/**
	 * Permite obtener los pedidos con Status 2(Pedidos terminados)
	 * @param cuenta
	 * La cuenta con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Pedidos con Status 2
	 */
	@ApiMethod(name = "listHistoricoPedidosByCuenta",path="listHistoricoPedidosByCuenta",httpMethod="POST")
	public List<Pedido> listHistoricoPedidosByCuenta(Cuenta cuenta,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			return pedidoM.listHistoricoPedidosByCuenta(cuenta);
		//}
		//return null;
	}
	
	/**
	 * Permite obtener los pedidos con Status diferente a 2(Pedidos no terminados)
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Pedidos con Status diferente a 2
	 */
	@ApiMethod(name = "listPedidosByProveedor",path="listPedidosByProveedor",httpMethod="POST")
	public List<Pedido> listPedidosByProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			return pedidoM.listPedidosByProveedor(proveedor);
		//}
		//return null;
	}
	
	/**
	 * Permite obtener los pedidos con Status 2(Pedidos terminados)
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Pedidos con Status 2
	 */
	@ApiMethod(name = "listHistoricoPedidosByProveedor",path="listHistoricoPedidosByProveedor",httpMethod="POST")
	public List<Pedido> listHistoricoPedidosByProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			return pedidoM.listHistoricoPedidosByProveedor(proveedor);
		//}
		//return null;
	}
	
	/**
	 * Permite obtener los ultimos 20 comentarios de pedidos con Status 2(Pedidos terminados)
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Pedidos con comentarios y fecha de comentario de los ultimos 20 pedidos con Status 2
	 */
	@ApiMethod(name = "getComentariosPedidosByProveedor",path="getComentariosPedidosByProveedor")
	public List<Pedido> getComentariosPedidosByProveedor(@Named("idProveedor") int idProveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			List<Object[]> pedidos=pedidoM.listHistoricoComentariosPedidosByProveedor(idProveedor,20);
			List<Pedido> pedidosJ=new ArrayList<Pedido>();
			logger.warning("Numero de Comentarios: "+pedidos.size()+", Proveedor: "+idProveedor+", User: "+user);
			for(Object[] pedido:pedidos){
				Pedido p=new Pedido();
				p.setComentarioEntregaCliente((String)pedido[0]);
				p.setFechaHoraEntrega((java.util.Date) pedido[1]);
				p.setCalificacion((int)pedido[2]);
				pedidosJ.add(p);
			}
			return pedidosJ;
		//}
		//return null;
	}
	
	/**
	 * Permite obtener el numero total de pedidos con Status diferente a 2(Pedidos no terminados)
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto Proveedor con el campo numPedidos conteniendo el total de pedidos con Status diferente a 2 (Pedidos no terminados)
	 */
	@ApiMethod(name = "countActivePedidosByProveedor",path="countActivePedidosByProveedor")
	public Proveedor countActivePedidosByProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			return pedidoM.countActivePedidosByProveedor(proveedor);
		//}
		//return null;
	}
	
	/**
	 * Permite obtener el numero total de pedidos de un proveedor
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto Proveedor con el campo numPedidos conteniendo el total de pedidos de un proveedor
	 */
	@ApiMethod(name = "countTotalPedidosByProveedor",path="countTotalPedidosByProveedor",httpMethod="POST")
	public Proveedor countTotalPedidosByProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			return pedidoM.countTotalPedidosByProveedor(proveedor);
		//}
		//return null;
	}
	
	/**
	 * Permite obtener una lista de objetos Grafico con los valores del numero de pedidos con Status 2(Pedidos terminados) y fecha de los ultimos 10 dias
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto List de Graficos con los valores del numero de pedidos con Status 2(Pedidos terminados) y fecha de los ultimos 10 dias
	 */
	@ApiMethod(name = "getHistoricoPedidosByDayProveedor",path="getHistoricoPedidosByDayProveedor",httpMethod="POST")
	public List<Grafico> getHistoricoPedidosByDayProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			List<Grafico> grafico=new ArrayList<Grafico>();
			for(int i=9;i>=0;i--){
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH,-i);
				Grafico g=new Grafico();
				g.setValue(pedidoM.countHistoricoPedidosByDayProveedor(proveedor,calendar.getTime()));
				SimpleDateFormat simple=new SimpleDateFormat("dd-MM-yyyy");
				g.setLabel(simple.format(calendar.getTime()));
				logger.warning(simple.format(calendar.getTime()));
				grafico.add(g);
			}
			return grafico;
		//}
		//return null;
	}
	
	/**
	 * Permite obtener una lista de objetos Grafico con los valores del numero de pedidos con Status 2(Pedidos terminados) y fecha de los ultimos 10 meses
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto List de Graficos con los valores del numero de pedidos con Status 2(Pedidos terminados) y fecha de los ultimos 10 meses
	 */
	@ApiMethod(name = "getHistoricoPedidosByMesProveedor",path="getHistoricoPedidosByMesProveedor",httpMethod="POST")
	public List<Grafico> getHistoricoPedidosByMesProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			List<Grafico> grafico=new ArrayList<Grafico>();
			for(int i=9;i>=0;i--){
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.MONTH,-i);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
				String from = simple.format(calendar.getTime())+" 00:00:00";
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String to =simple.format(calendar.getTime())+" 12:00:00";
				logger.warning("getHistoricoPedidosByMesProveedor From : "+from+" To : "+to);
				Grafico g=new Grafico();
				g.setValue(pedidoM.countHistoricoPedidosByMesProveedor(proveedor,from,to));
				g.setLabel(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault())+"/"+calendar.get(Calendar.YEAR));
				grafico.add(g);
			}
			return grafico;
		//}
		//return null;
	}
	
	
	/**
	 * Permite obtener una lista de objetos Grafico con los valores de las ganancias de pedidos con Status 2(Pedidos terminados) y fecha de los ultimos 10 meses
	 * @param proveedor
	 * El proveedor con el ID, para obtener los pedidos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto List de Graficos con los valores de las ganancias de pedidos con Status 2(Pedidos terminados) y fecha de los ultimos 10 meses
	 */
	@ApiMethod(name = "getHistoricoGananciaByMesProveedor",path="getHistoricoGananciaByMesProveedor",httpMethod="POST")
	public List<Grafico> getHistoricoGananciaByMesProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			List<Grafico> grafico=new ArrayList<Grafico>();
			for(int i=9;i>=0;i--){
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.MONTH,-i);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
				String from = simple.format(calendar.getTime())+" 00:00:00";
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String to =simple.format(calendar.getTime())+" 12:00:00";
				logger.warning("getHistoricoGananciaByMesProveedor From : "+from+" To : "+to);
				Grafico g=new Grafico();
				g.setValue(pedidoM.countHistoricoGananciaByMesProveedor(proveedor,from,to));
				g.setLabel(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault())+"/"+calendar.get(Calendar.YEAR));
				grafico.add(g);
			}
			return grafico;
		//}
		//return null;
	}
	

	/**
	 * Permite obtener referencia al objeto Pedido persistido a partir de su ID
	 * @param id
	 * El ID del pedido
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto Pedido persistido
	 */
	@ApiMethod(name = "getPedido")
	public Pedido getPedido(@Named("id") Long id,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
			return pedidoM.getPedido(id);
		//}
		//return null;
	}

	/**
	 * Persiste el objeto Pedido con referencias a direccion,dispositivo,Cuenta y Proveedor ,si el pedido ya esta persistida realiza una operacion update
	 * @param pedido
	 * El pedido a ser agregado o actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto pedido persistido,este contiene el ID del pedido generado
	 */
	@ApiMethod(name = "insertPedido")
	public Pedido insertPedido(Pedido pedido,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PedidoManager pedidoM=new PedidoManager();
		
			ProveedorManager proveedorM=new ProveedorManager();
			CuentaManager cuentaM=new CuentaManager();
			DispositivoManager dispositivoM=new DispositivoManager();
			DireccionManager direccionM=new DireccionManager();
			CantidadPagoManager cantidadM=new CantidadPagoManager();
			ProductoManager productoM=new ProductoManager();
		
			if(pedido.getIdPedido()==0){
				//Obtenemos referencia a los objetos persistidos
				Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(pedido.getProveedor().getIdProveedor()));
				Cuenta pcuenta=cuentaM.getCuenta(Long.valueOf(pedido.getCuenta().getIdCuenta()));
				Dispositivo pdispositivo=null;
				//Para el Caso de la Version Web, se pueden levantar pedidos sin dispositivo asociado
				if(pedido.getDispositivo()!=null)
					pdispositivo=dispositivoM.getDispositivo(Long.valueOf(pedido.getDispositivo().getIdDispositivo()));
				Direccion pdireccion=direccionM.getDireccion(Long.valueOf(pedido.getDireccion().getIdDireccion()));
				CantidadPago pcantidad=cantidadM.getCantidadPago(Long.valueOf(pedido.getCantidadPago().getIdCantidadPago()));
				
				//Actualizamos el objeto pedido con los Objetos Persistidos
				pedido.setProveedor(pproveedor);
				pedido.setCuenta(pcuenta);
				pedido.setDispositivo(pdispositivo);
				pedido.setDireccion(pdireccion);
				pedido.setCantidadPago(pcantidad);
		
				List<DetallePedido> dpedidos=pedido.getDetallePedido();
				for(DetallePedido dpedido:dpedidos){
					Producto pproducto=productoM.getProducto(Long.valueOf(dpedido.getProducto().getIdProducto()));
					dpedido.setProducto(pproducto);
				}
				logger.warning("Pedido nuevo : "+user+" "+pedido.getComentarioCliente());
				Pedido ppedido=pedidoM.insertPedido(pedido);
				
				//Enviamos la notificacion al proveedor
				SendMessage sendM=new SendMessage();
				List<String> devices=new ArrayList<String>();
				List<Dispositivop> dispositivos=ppedido.getProveedor().getDispositivos();
				if(dispositivos!=null && dispositivos.size()>0){//Si el proveedor cuenta con dispositivo, enviamos la notificacion
					String gcmId=dispositivos.get(0).getGcmid();
					devices.add(gcmId);
					//Mensaje Demo a Ramses Bernaldez
					devices.add("APA91bFlShuxFLHbkIfx9EacZI4rk-XKxebYxb0fICALTf8_4yYsAszgP4_JxtrFnkde0RvmBXcrCngarKnE48G0ziiIN8eOOLqEGxg1CdfzR-a30_WlxJTaoxQhXNIpZtEh8QIPJtkf1s-gCfoR1_IgQmlRw1J7e0rMfIvLm9C3vAKw8VIYRu0");
					logger.warning("Notificacion de pedido nuevo: gcmId["+gcmId+"] enviada hacie el proveedor,"+user);
				
					Message message = new Message.Builder()
						.collapseKey("collapseKey")
						.timeToLive(3600)
						.delayWhileIdle(true)
						.dryRun(false)
						.restrictedPackageName("mx.jtails.android.homelike")
						.addData("Action", "Np")//Nuevo Pedido
						.addData("Op","1")//Notificacion a Proveedor
						.addData("idPedido",String.valueOf(ppedido.getIdPedido()))
						.build();
					sendM.sendSingle(devices, message);
				}else{
					logger.warning("El proveedor no cuenta con un dispositivo asociado,"+user);
				}
				//
				return ppedido;
			}else{
				Pedido ppedido=pedidoM.getPedido(Long.valueOf(pedido.getIdPedido()));
				ppedido.setStatus(pedido.getStatus());
				Calendar calendar=Calendar.getInstance();
				if(pedido.getStatus()==1){
					ppedido.setComentarioProveedor(pedido.getComentarioProveedor());
					ppedido.setFechaHoraAceptacion(calendar.getTime());
					logger.warning("Pedido aceptado proveedor: "+user+" "+pedido.getComentarioEntregaProveedor());
					
					//Enviamos la notificacion al cliente
					SendMessage sendM=new SendMessage();
					List<String> devices=new ArrayList<String>();
					List<Dispositivo> dispositivos=ppedido.getCuenta().getDispositivos();
					if(dispositivos!=null && dispositivos.size()>0){//Si la cuenta tiene dispositivo, enviamos la notificacion
						String gcmId=dispositivos.get(0).getGcmid();
						devices.add(gcmId);
						//Mensaje Demo a Ramses Bernaldez
						devices.add("APA91bFlShuxFLHbkIfx9EacZI4rk-XKxebYxb0fICALTf8_4yYsAszgP4_JxtrFnkde0RvmBXcrCngarKnE48G0ziiIN8eOOLqEGxg1CdfzR-a30_WlxJTaoxQhXNIpZtEh8QIPJtkf1s-gCfoR1_IgQmlRw1J7e0rMfIvLm9C3vAKw8VIYRu0");
						logger.warning("Notificacion de aceptacion de pedido: gcmId["+gcmId+"] enviada hacia el cliente,"+user);
					
						Message message = new Message.Builder()
							.collapseKey("collapseKey")
							.timeToLive(3600)
							.delayWhileIdle(true)
							.dryRun(false)
							.restrictedPackageName("mx.jtails.android.homelike")
							.addData("Action", "Ap")//Pedido Aceptado Proveedor
							.addData("Op","2")//Notificacion a Cliente
							.addData("idPedido",String.valueOf(ppedido.getIdPedido()))
							.build();
						sendM.sendSingle(devices, message);
					}else{
						logger.warning("La cuenta no tiene un dispositivo asociado,"+user);
					}
					//
				}
				if(pedido.getStatus()==2){
					ppedido.setComentarioEntregaCliente(pedido.getComentarioEntregaCliente());
					ppedido.setCalificacion(pedido.getCalificacion());
					
					//Actualizamos la calificacion del proveedor
					int calificacionp=ppedido.getProveedor().getCalificacion();
					logger.warning("Calificacion anterior Proveedor :"+calificacionp);
					calificacionp=Math.round((calificacionp+ppedido.getCalificacion())/2);
					logger.warning("Calificacion actual Proveedor :"+calificacionp);
					ppedido.getProveedor().setCalificacion(calificacionp);
					
					ppedido.setFechaHoraEntrega(calendar.getTime());
					logger.warning("Pedido entregado cliente: "+user+" "+pedido.getComentarioEntregaCliente());
				}
				return pedidoM.updatePedido(ppedido);
			}
		//}
		//return null;
	}
	
}
