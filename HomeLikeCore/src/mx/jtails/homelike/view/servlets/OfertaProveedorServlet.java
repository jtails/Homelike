package mx.jtails.homelike.view.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.response.CollectionResponse;

import mx.jtails.homelike.model.beans.CProducto;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.CProductoManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;

public class OfertaProveedorServlet extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
                                                 IOException {
		doPost(request,response);
	}
	
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
                                                 IOException {   

		String opcion=request.getParameter("opcion");
		String id_proveedor=request.getParameter("id_proveedor");
		if(opcion.equalsIgnoreCase("iniciar")){
			ProveedorManager proveedorM=new ProveedorManager();
			CProductoManager cproductoM=new CProductoManager();
			Proveedor proveedor=proveedorM.getProveedor(Long.valueOf(id_proveedor));
			CollectionResponse<CProducto> collectionR=cproductoM.listCProducto(null,null);
			request.setAttribute("CProductos",collectionR.getItems());
			//request.setAttribute("Productos",proveedor.getProductos());
			//if(proveedor!=null && proveedor.getIdProveedor()!=0)
			//	request.setAttribute("Productos",proveedor);
			request.getRequestDispatcher("producto.jsp").forward(request,response);
		}	
	}

}
