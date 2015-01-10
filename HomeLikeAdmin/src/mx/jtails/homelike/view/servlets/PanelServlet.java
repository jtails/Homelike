package mx.jtails.homelike.view.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.jtails.homelike.model.beans.Admin;
import mx.jtails.homelike.model.emanagers.AdminManager;

public class PanelServlet extends HttpServlet{
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
		if(opcion.equalsIgnoreCase("logout")){
			logout(request,response);
		}
		
	}
	
	public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String usuario=request.getParameter("usuario");
		String logotipo=request.getParameter("logotipo");
		AdminManager adminM=new AdminManager();
		Admin admin=new Admin();
		admin.setUsuario(usuario);
		admin.setLogo(logotipo);
		
		Admin padmin=adminM.getAdminByUser(admin);
		if(padmin!=null){
			HttpSession session=request.getSession();
			//Actualizamos el Logotipo del Admin en cada ingreso
			padmin.setLogo(logotipo);
			session.setAttribute("admin",padmin);
			session.setAttribute("isLoginA",true);
			session.removeAttribute("isLoginI");
		}
	}
	
	
	public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}
	
}
