package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.CantidadPago;
import mx.jtails.homelike.model.emanagers.CantidadPagoManager;
import mx.jtails.homelike.model.emanagers.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.log.AppLogLine;
import com.google.appengine.api.users.User;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.google.apphosting.api.logservice.LogServicePb;
import com.google.apphosting.api.logservice.LogServicePb.LogLine;
import com.google.apphosting.api.logservice.LogServicePb.RequestLog;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "cantidadpagoendpoint",
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class CantidadPagoEndpoint {

	/**
	 * Retorna las cantidades de Pago disponibles para identificar el monto con el cual pagara el cliente
	 * @param user
	 * El usuario autenticado con Google
	 */
	@ApiMethod(name = "listCantidadPago")
	public CollectionResponse<CantidadPago> listCantidadPago(User user) {
		//if(user!=null){
			CantidadPagoManager cantidadPagoM=new CantidadPagoManager();
			return cantidadPagoM.listCantidadPago(null,null);
		//}
		//return null;
	}
}
