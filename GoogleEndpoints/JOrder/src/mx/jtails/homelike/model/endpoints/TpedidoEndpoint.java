package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Tpedido;
import mx.jtails.homelike.model.emanagers.TpedidoManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.users.User;

import java.util.List;

@Api(name = "tpedidoendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class TpedidoEndpoint {

	@ApiMethod(name = "listTpedidosByCuenta",path="listTpedidosByCuenta")
	public List<Tpedido> listTpedidosByCuenta(Cuenta cuenta,User user) {
		//if(user!=null){
			TpedidoManager tpedidoM=new TpedidoManager();
			return tpedidoM.listTpedidosByCuenta(cuenta);
		//}
		//return null;
	}


}
