package mx.jtails.homelike.view.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Dispositivo;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.emanagers.CuentaManager;
import mx.jtails.homelike.model.emanagers.DispositivoManager;
import mx.jtails.homelike.model.emanagers.ProveedorManager;
import mx.jtails.homelike.model.endpoints.CuentaEndpoint;

public class PanelCServlet extends HttpServlet{
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
		CuentaManager cuentaM=new CuentaManager();
		Cuenta cuenta=new Cuenta();
		cuenta.setUsuario(usuario);
		
		Cuenta pcuenta=cuentaM.getCuentaByUser(cuenta);
		if(pcuenta!=null){
			HttpSession session=request.getSession();
			session.setAttribute("cuenta",pcuenta);//Para facilitar uso de Datos en JSP
			session.setAttribute("direccion",pcuenta.getDirecciones().get(0));//Para facilitar uso de Datos en JSP
			session.setAttribute("isLoginC",true);
			session.removeAttribute("isLoginI");
			//request.getRequestDispatcher("panelc.jsp").forward(request,response);
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("cuenta",cuenta);
			session.setAttribute("isLoginI",true);
		}/*else{
			request.setAttribute("isLoginF",true);
			request.getRequestDispatcher("loginc.jsp").forward(request,response);
		}*/
		
	}
	
	
	/*public void refresh(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		Dispositivo dispositivo=(Dispositivo)session.getAttribute("dispositivo");
		CuentaEndpoint cuentaE=new CuentaEndpoint();
		//Obtenemos referencia a los objetos persistidos para actualizarlos en session
		Cuenta cuenta=cuentaE.getCuenta(Long.valueOf(dispositivo.getCuenta().getIdCuenta()));
		session.setAttribute("dispositivo",cuenta.getDispositivos().get(0));
		session.setAttribute("cuenta",cuenta);//Para facilitar uso de Datos en JSP
		session.setAttribute("direccion",cuenta.getDirecciones().get(0));//Para facilitar uso de Datos en JSP
		request.getRequestDispatcher("panelc.jsp").forward(request,response);
	}*/
	
	public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}
	
}
