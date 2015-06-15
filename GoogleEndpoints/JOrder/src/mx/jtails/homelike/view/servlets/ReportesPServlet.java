package mx.jtails.homelike.view.servlets;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.jtails.homelike.model.beans.DetallePedido;
import mx.jtails.homelike.model.beans.Direccion;
import mx.jtails.homelike.model.beans.Pedido;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.PedidoManager;

public class ReportesPServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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
		HttpSession session=request.getSession();
		
		response.setHeader("Content-Type", "text/html");
		response.setHeader("Content-Disposition", "attachment;filename=\"pedidos.html\"");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
		PedidoManager pedidoM=new PedidoManager();
		
		//Obtenemos referencia al proveedor
		Proveedor proveedor=(Proveedor)session.getAttribute("proveedor");
		List<Pedido> pedidos=pedidoM.listPedidosByProveedor(proveedor);
		writer.write("<html><body>");
		writer.write("<script>this.print();</script>");
		writer.write("<table border='1'><thead style='background-color:#C0C0C0'>");
		writer.write(
			"<tr><td>Direccion de Envio</td><td>Total</td><td>Productos</td><td>Region</td></tr>"
		);
		writer.write(
				"</thead><tbody>"
		);
		for(Pedido pedido:pedidos){
			Direccion direccion=pedido.getDireccion();
			List<DetallePedido> detallePedido=pedido.getDetallePedido();
			float total=0;
			StringBuilder dpedido=new StringBuilder();
			for(DetallePedido detalleP:detallePedido){
				total+=detalleP.getProducto().getCostoUnitario()*detalleP.getCantidad();
				if(detalleP.getProducto().getCproducto()!=null)
					dpedido.append(detalleP.getCantidad()+" "+detalleP.getProducto().getCproducto().getDescripcion()+" "+detalleP.getProducto().getCproducto().getPresentacion()+"-");
				else
					dpedido.append(detalleP.getCantidad()+" "+detalleP.getProducto().getDescripcion()+" "+detalleP.getProducto().getPresentacion()+"-");
			}
			
			writer.write(
				"<tr><td widt='150px'>"+direccion.getCalle()+" "+
				direccion.getNexterior()+" "+
				direccion.getNinterior()+" "+
				direccion.getColonia()+" "+
				direccion.getDelegacion()+" "+
				direccion.getNinterior()+" "+
				direccion.getCp()+" "+
				direccion.getEstado()+" "+
				"</td>"+
				"<td>"+total+"</td>"+
				"<td>"+dpedido+"</td>"+
				"<td>"+pedido.getRegion().getLabel()+"</td></tr>"
			);
		}
		writer.write("</tbody></table></body></html>");
		writer.flush();
		writer.close();
	}
	
}