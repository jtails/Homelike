package mx.jtails.homelike.view.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.server.spi.response.CollectionResponse;

import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.ServicioManager;


public class ProveedorServlet extends HttpServlet{
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
		HttpSession session=request.getSession();
		Proveedor proveedor=(Proveedor)session.getAttribute("proveedor");
		if(proveedor==null)
			proveedor=new Proveedor();
		
		if(opcion.equalsIgnoreCase("registrodp")){
			proveedor=proveedor(request,proveedor);
			session.setAttribute("proveedor",proveedor);
			request.getRequestDispatcher("registrodp.jsp").forward(request,response);
		}else{
			if(opcion.equalsIgnoreCase("registrop")){
				proveedor=proveedor2(request,proveedor);
				session.setAttribute("proveedor",proveedor);
				request.getRequestDispatcher("registrop.jsp").forward(request,response);
			}
		}
		
	}
	
	private Proveedor proveedor(HttpServletRequest request,Proveedor proveedor){
		String nombre=request.getParameter("nombre");
		String logo=request.getParameter("logo");
		String slogan=request.getParameter("slogan");
		String rfc=request.getParameter("rfc");
		String razon=request.getParameter("razon");
		String telefono=request.getParameter("telefono");
		String idservicio=request.getParameter("servicio");
		String usuario=request.getParameter("usuario");
		
		proveedor.setNombre(nombre);
		proveedor.setLogo(logo);
		proveedor.setSlogan(slogan);
		proveedor.setRfc(rfc);
		proveedor.setRazonSocial(razon);
		proveedor.setTelefono(telefono);
		Servicio servicio=new Servicio();
		servicio.setIdServicio(Integer.valueOf(idservicio));
		proveedor.setServicio(servicio);
		proveedor.setUsuario(usuario);
		return proveedor;
	}
	
	private Proveedor proveedor2(HttpServletRequest request,Proveedor proveedor){	
		String calle=request.getParameter("calle");
		String numeroe=request.getParameter("numeroe");
		String numeroi=request.getParameter("numeroi");
		String colonia=request.getParameter("colonia");
		String delegacion=request.getParameter("delegacion");
		String cp=request.getParameter("cp");
		String estado=request.getParameter("estado");
		String pais=request.getParameter("pais");
		String calle1=request.getParameter("calle1");
		String calle2=request.getParameter("calle2");
		String referencia1=request.getParameter("referencia1");
		String referencia2=request.getParameter("referencia2");
		
		proveedor.setCalle(calle);
		proveedor.setNexterior(numeroe);
		proveedor.setNinterior(numeroi);
		proveedor.setColonia(colonia);
		proveedor.setDelegacion(delegacion);
		proveedor.setCp(cp);
		proveedor.setEstado(estado);
		proveedor.setPais(pais);
		proveedor.setCalle1(calle1);
		proveedor.setCalle2(calle2);
		proveedor.setReferencia1(referencia1);
		proveedor.setReferencia2(referencia2);
		return proveedor;
	}
	
}
