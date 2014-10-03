package mx.jtails.homelike.model.endpoints;

import mx.jtails.homelike.model.beans.CProducto;
import mx.jtails.homelike.model.beans.Producto;
import mx.jtails.homelike.model.emanagers.CProductoManager;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.emanagers.ProductoManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;


@Api(name = "productoendpoint", 
namespace = @ApiNamespace(ownerDomain = "jtails.mx", ownerName = "jtails.mx", packagePath = "homelike.model.beans"),
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID,Constants.ANDROID_CLIENT_ID}
,audiences = {Constants.ANDROID_AUDIENCE}
)
public class ProductoEndpoint {
	private static final Logger logger = Logger.getLogger(ProductoEndpoint.class.getName());


	@ApiMethod(name = "listProducto")
	public CollectionResponse<Producto> listProducto(User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProductoManager productoM=new ProductoManager();
			return productoM.listProducto(null,null);
		//}
		//return null;
	}

	/**
	 * Permite obtener los productos que ofrece un proveedor
	 * @param proveedor
	 * El proveedor con el ID, para obtener los productos
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna una lista de Productos
	 */
	@ApiMethod(name = "listProductosByProveedor",path="listProductosByProveedor")
	public List<Producto> listProductosByProveedor(Proveedor proveedor,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProductoManager productoM=new ProductoManager();
			List<Producto> productos=productoM.listProductosByProveedor(proveedor);
			logger.warning("Productos encontrados "+productos.size()+" :"+user);
			return productos;
		//}
		//return null;
	}
	
	/**
	 * Inserta un nuevo producto para un proveedor, Si el producto ya esta persistido realiza una operacion Update
	 * @param producto
	 * El producto con referencia al Proveedor para agregar o actualizar el producto
	 * @param user
	 * El usuario autenticado con Google
	 * @return
	 * Retorna el objeto Producto persistido con su ID
	 */
	@ApiMethod(name = "insertProducto")
	public Producto insertProducto(Producto producto,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProductoManager productoM=new ProductoManager();
			CProductoManager cproductoM=new CProductoManager();
			ProveedorManager proveedorM=new ProveedorManager();
			//Obtenemos Referencia a los Objetos CProducto y Proveedor, ya que estos se encuentran persistidos		
			Proveedor proveedor=proveedorM.getProveedor(Long.valueOf(producto.getProveedor().getIdProveedor()));
			CProducto cproducto=cproductoM.getCProducto(Long.valueOf(producto.getCproducto().getIdCProducto()));
			producto.setCproducto(cproducto);
			producto.setProveedor(proveedor);
			
			//Verificamos si el producto ya se encontra persistido (CProducto y Proveedor)
			Producto pproducto=productoM.getProductoByCatalogoProveedor(cproducto,proveedor);
			if(pproducto==null || pproducto.getIdProducto()==0){
				logger.warning("Producto nuevo : "+user);
				return productoM.insertProducto(producto);
			}
			else{
				//Si el producto existe lo actualizamos
				pproducto.setCostoUnitario(producto.getCostoUnitario());
				logger.warning("Producto existente : "+user);
				return productoM.updateProducto(pproducto);
			}
		//}
		//return null;
	}

	@ApiMethod(name = "removeProducto")
	public void removeProducto(@Named("id") Long id,User user)throws OAuthRequestException, IOException  {
		//if(user!=null){
			ProductoManager productoM=new ProductoManager();
			productoM.removeProducto(id);
		//}
	}

}
