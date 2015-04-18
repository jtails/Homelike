package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.Corte;
import mx.jtails.homelike.model.beans.Pago;
import mx.jtails.homelike.model.emanagers.CorteManager;
import mx.jtails.homelike.model.emanagers.EMF;
import mx.jtails.homelike.model.emanagers.PagoManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "pagoendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,}
,audiences = {Constants.WEB_CLIENT_ID}
)
public class PagoEndpoint {
	private static final Logger logger = Logger.getLogger(PagoEndpoint.class.getName());
	
	@ApiMethod(name = "insertPago",path="insertPago",httpMethod="POST")
	public Pago insertPago(Pago pago,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			PagoManager pagoM=new PagoManager();
			CorteManager corteM=new CorteManager();
			//Obtenemos referencia al objeto corte persistido
			Corte pcorte=corteM.getCorte(Long.valueOf(pago.getCorte().getIdCorte()));
			pago.setCorte(pcorte);
			return pagoM.insertPago(pago);
		//}else{
		//	logger.warning("Usuario no autorizado");
		//	return null;	
		//}
	}
	
}
