package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.HorariosProveedor;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.HorariosProveedorManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Api(name = "horariosproveedorendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class HorariosProveedorEndpoint {
	private static final Logger logger = Logger.getLogger(HorariosProveedorEndpoint.class.getName());

	/**
	 * Lista los horarios de servicio de un proveedor en especifico
	 * @param horariosproveedor
	 * El Horario del proveedor, contiene referencia al proveedor
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna la lista de HorariosProveedor de un proveedor en especifico
	 */
	@ApiMethod(name = "getHorariosProveedor",path="getHorariosProveedor")
	public List<HorariosProveedor> getHorariosProveedor(HorariosProveedor horariosproveedor,User user)throws OAuthRequestException, IOException {
		//if(user!=null){
			HorariosProveedorManager horariosM=new HorariosProveedorManager();
			List<HorariosProveedor> horarios=horariosM.getHorariosProveedor(horariosproveedor);
			logger.warning("\"Horarios proveedor\" Proveedor: "+horariosproveedor.getProveedor().getIdProveedor()+", No.horarios: "+horarios.size()+", User: "+user);
			return horarios;
		//}
		//return null;	
	}
	
	
	@ApiMethod(name = "deleteHorariosProveedor",path="deleteHorariosProveedor")
	public void deleteHorariosProveedor(HorariosProveedor horariosproveedor,User user)throws OAuthRequestException, IOException {
		//if(user!=null){
			HorariosProveedorManager horariosM=new HorariosProveedorManager();
			horariosproveedor=horariosM.getHorariosProveedorByTipo(horariosproveedor);
			logger.warning("\"Eliminacion Horarios proveedor\" Proveedor: "+horariosproveedor.getProveedor().getIdProveedor()+", Horario: "+horariosproveedor.getTipoHorario()+", User: "+user);
			horariosM.removeHorariosProveedor(Long.valueOf(horariosproveedor.getIdHorario()));			
		//}
		//return null;	
	}
	

	/**
	 * Persiste el objeto HorariosProveedor, si el horario ya esta persistido realiza una operacion update
	 * @param horariosproveedor
	 * El Horario del proveedor a ser agregado o actualizado
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto pedido persistido,este contiene el ID del horario generado
	 */ 
	@ApiMethod(name = "insertHorariosProveedor")
	public HorariosProveedor insertHorariosProveedor(HorariosProveedor horariosproveedor,User user)throws OAuthRequestException, IOException {
		//if(user!=null){
			HorariosProveedorManager horariosM=new HorariosProveedorManager();
			ProveedorManager proveedorM=new ProveedorManager();
			//Obtenemos referencia a los objetos persistidos
			Proveedor pproveedor=proveedorM.getProveedor(Long.valueOf(horariosproveedor.getProveedor().getIdProveedor()));
			
			//Verificamos si es un horario nuevo, o una actualización a uno existente
			HorariosProveedor phorariosproveedor = horariosM.getHorariosProveedorByTipo(horariosproveedor);
			if(phorariosproveedor.getIdHorario()==0){
				logger.warning("\"Nuevo Horario proveedor\" Proveedor: "+horariosproveedor.getProveedor().getIdProveedor()+", Inicio: "+horariosproveedor.getAbrimos()+", Fin: "+horariosproveedor.getCerramos()+", User: "+user);
				horariosproveedor.setProveedor(pproveedor);
				horariosproveedor=horariosM.insertHorariosProveedor(horariosproveedor);
			}else{
				logger.warning("\"Actualización Horario proveedor\" Proveedor: "+horariosproveedor.getProveedor().getIdProveedor()+", Inicio: "+horariosproveedor.getAbrimos()+", Fin: "+horariosproveedor.getCerramos()+", User: "+user);
				phorariosproveedor.setAbrimos(horariosproveedor.getAbrimos());
				phorariosproveedor.setCerramos(horariosproveedor.getCerramos());
				horariosproveedor=horariosM.updateHorariosProveedor(phorariosproveedor);
			}
			
			return horariosproveedor;
		//}
		//return null;	
	}

}
