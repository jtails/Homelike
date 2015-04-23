package mx.jtails.homelike.model.endpoints;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import mx.jtails.homelike.model.beans.Corte;
import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.CorteManager;
import mx.jtails.homelike.model.emanagers.PedidoManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

@Api(name = "corteendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,}
,audiences = {Constants.WEB_CLIENT_ID}
)
public class CorteEndpoint {
	private static final Logger logger = Logger.getLogger(CorteEndpoint.class.getName());

	
	
	/**
	 * Permite realizar un corte con pedidos en status terminado a dia vencido
	 * @param corte
	 * El objeto Corte que contiene una referncia al objeto proveedor
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna un objeto corte en caso que el corte sea exitoso, y un corte con status -1 si ya se cuenta con un corte realizado el mismo dia
	 */
	@ApiMethod(name = "insertCorte",path="insertCorte",httpMethod="POST")
	public Corte insertCorte(Corte corte,User user)throws OAuthRequestException, IOException  {
		if(user!=null){
			CorteManager corteM=new CorteManager();
			PedidoManager pedidoM=new PedidoManager();
			ProveedorManager proveedorM=new ProveedorManager();
			//No tiene corte actual
			if(!corteM.haveCurrentCorteByProveedor(corte)){
				//Obtenemos referencia al objeto proveedor persistido
				Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(corte.getProveedor().getIdProveedor()));
				//Verificamos si es el primer corte del Proveedor, o si debe generarse a partir de la fecha de su ultimo corte
				Date fechaUltimoCorte=corteM.getLastCorteByProveedor(corte);
				
				List<Pedido> pedidos=null;
				Date FechaHoraInicio=null;
				Date FechaHoraFin=null;
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_MONTH,-1);
				FechaHoraFin=calendar.getTime();
				
				if(fechaUltimoCorte!=null){
					//Obtenemos los pedidos a partir de la fecha del ultimo corte con status terminado
					calendar.setTime(fechaUltimoCorte);
					calendar.add(Calendar.DAY_OF_MONTH,-1);
					fechaUltimoCorte=calendar.getTime();
					pedidos=pedidoM.listPedidosByFechaCorteProveedor(pproveedor, fechaUltimoCorte);
					FechaHoraInicio=fechaUltimoCorte;
					logger.warning("Corte recurrente");
				}else{
					//Obtenemos los pedidos a dia vencido con status terminado
					pedidos=pedidoM.listPedidosByInicioCorteProveedor(pproveedor);
					if(pedidos!=null && pedidos.size()>0){
						FechaHoraInicio=pedidos.get(0).getFechaHoraPedido();
						logger.warning("Primer corte proveedor");
					}
				}
				
				//Verificamos que existan pedidos en el rango de fechas
				if(pedidos!=null && pedidos.size()>0){
					//Preparamos el corte
					logger.warning("Fecha inicio: "+FechaHoraInicio.toString());
					logger.warning("Fecha fin: "+FechaHoraFin.toString());
					logger.warning("No.Pedidos: "+pedidos.size());
					
					corte.setFechaHoraInicio(FechaHoraInicio);
					corte.setFechaHoraFin(FechaHoraFin);
					corte.setNopedidos(pedidos.size());
					corte.setAdeudo(pedidos.size());
					corte.setStatus(0);
					corte.setDescripcion("Corte - Inicio:"+FechaHoraInicio+", Fin:"+FechaHoraFin);
					corte.setProveedor(pproveedor);
					//Se realiza corte con pedidos a dia vencido
					return corteM.insertCorte(corte);
				}else{
					Corte Ncorte=new Corte();
					Ncorte.setStatus(-2);//No existen pedidos para realizar un corte
					Ncorte.setDescripcion("No existen pedidos para el periodo de corte");
					logger.warning("No existen pedidos para el periodo de corte");
					return Ncorte;
				}
			}else{
				Corte Ncorte=new Corte();
				Ncorte.setStatus(-1);//Tiene corte actual
				Ncorte.setDescripcion("Usted ya cuenta con un corte vigente el dia de hoy");
				logger.warning("Tiene Corte Actual: No es posible realizar otro corte el dia de hoy");
				return Ncorte;
			}
		}else{
			logger.warning("Usuario no autorizado");
			return null;	
		}
	}
	
	/**
	 * Permite obtener los cortes de un proveedor
	 * @param corte
	 * El objeto Corte que contiene una referncia al objeto proveedor
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista que contiene los cortes del proveedor
	 */
	@ApiMethod(name = "listCortes",path="listCortes",httpMethod="POST")
	public List<Corte> listCortes(Corte corte,User user)throws OAuthRequestException, IOException  {
		if(user!=null){
			CorteManager corteM=new CorteManager();
			return corteM.listCortesByProveedor(corte);
		}else{
			logger.warning("Usuario no autorizado");
			return null;	
		}
	}

}
