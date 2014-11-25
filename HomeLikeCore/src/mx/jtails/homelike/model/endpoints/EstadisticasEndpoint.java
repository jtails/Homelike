package mx.jtails.homelike.model.endpoints;


import mx.jtails.homelike.model.beans.Grafico;
import mx.jtails.homelike.model.emanagers.CuentaManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Api(name = "estadisticasendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class EstadisticasEndpoint {
	private static final Logger logger = Logger.getLogger(EstadisticasEndpoint.class.getName());

	@ApiMethod(name = "getEstadisticas",path="getEstadisticas")
	public List<Grafico> getEstadisticas(){
		CuentaManager cuentaM=new CuentaManager();
		ProveedorManager proveedorM=new ProveedorManager();
		List<Grafico> grafico=new ArrayList<Grafico>();
		Grafico g=new Grafico();
		int numCuentas=cuentaM.countCuentas();
		int numProveedores=proveedorM.countProveedores();
		g.setLabel("numCuentas");
		g.setValue(numCuentas);
		grafico.add(g);
		g=new Grafico();
		g.setLabel("numProveedores");
		g.setValue(numProveedores);
		grafico.add(g);
		logger.warning("Cuentas encontradas: "+numCuentas);
		logger.warning("Proveedores encontrados: "+numProveedores);
		return grafico;
	}


}
