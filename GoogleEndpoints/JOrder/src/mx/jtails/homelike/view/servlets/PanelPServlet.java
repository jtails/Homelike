package mx.jtails.homelike.view.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.endpoints.ProveedorEndpoint;

public class PanelPServlet extends HttpServlet{
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
		if(opcion.equalsIgnoreCase("login")){
			login(request,response);
		}
		/*if(opcion.equalsIgnoreCase("refresh")){
			refresh(request,response);
		}*/
		if(opcion.equalsIgnoreCase("logout")){
			logout(request,response);
		}
		
	}
	
	public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String usuario=request.getParameter("usuario");
		String logotipo=request.getParameter("logotipo");
		ProveedorManager proveedorM=new ProveedorManager();
		Proveedor proveedor=new Proveedor();
		proveedor.setUsuario(usuario);
		proveedor.setLogo(logotipo);
		
		Proveedor pproveedor=proveedorM.getProveedorByUser(proveedor);
		if(pproveedor!=null){
			HttpSession session=request.getSession();
			session.setAttribute("proveedor",pproveedor);
			session.setAttribute("isLoginP",true);
			session.removeAttribute("isLoginI");
			//request.getRequestDispatcher("panelp.jsp").forward(request,response);
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("proveedor",proveedor);
			session.setAttribute("isLoginI",true);
		}
		/*else{
			request.setAttribute("isLoginF",true);
			request.getRequestDispatcher("loginp.jsp").forward(request,response);
		}*/
	}
	
	/*public void refresh(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		Proveedor proveedor=(Proveedor)session.getAttribute("proveedor");
		ProveedorEndpoint proveedorE=new ProveedorEndpoint();
		//Obtenemos referencia a los objetos persistidos para actualizarlos en session
		proveedor=proveedorE.getProveedor(Long.valueOf(proveedor.getIdProveedor()));
		session.setAttribute("proveedor",proveedor);
		request.getRequestDispatcher("panelp.jsp").forward(request,response);
	}*/
	
	public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request,response);
		//response.sendRedirect("https://mail.google.com/mail/u/0/?logout&hl=en");
	}
	
}
