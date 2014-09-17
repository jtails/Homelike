package mx.jtails.homelike.view.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.jtails.homelike.model.beans.Cuenta;
import mx.jtails.homelike.model.beans.Direccion;
import mx.jtails.homelike.model.beans.Dispositivo;
import mx.jtails.homelike.model.beans.Proveedor;
import mx.jtails.homelike.model.beans.Servicio;
import mx.jtails.homelike.model.emanagers.DispositivoManager;

public class CuentaServlet extends HttpServlet{
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
		Cuenta cuenta=(Cuenta)session.getAttribute("cuenta");
		if(cuenta==null)
			cuenta=new Cuenta();
		if(opcion.equalsIgnoreCase("registrodc")){
			cuenta=cuenta(request,cuenta);
			session.setAttribute("cuenta",cuenta);
			session.setAttribute("dispositivo",cuenta.getDispositivos().get(0));
			request.getRequestDispatcher("registrodc.jsp").forward(request,response);
		}else{
			if(opcion.equalsIgnoreCase("registroc")){
				cuenta=cuenta2(request,cuenta);
				session.setAttribute("cuenta",cuenta);
				session.setAttribute("dispositivo",cuenta.getDispositivos().get(0));
				session.setAttribute("direccion",cuenta.getDirecciones().get(0));
				request.getRequestDispatcher("registroc.jsp").forward(request,response);
			}
		}
	}
	
	private Cuenta cuenta(HttpServletRequest request,Cuenta cuenta){
		String telefono=request.getParameter("telefono");
		String plataforma=request.getParameter("plataforma");
		String gcmid=request.getParameter("gcmid");
		String imei=request.getParameter("imei");
		String modelo=request.getParameter("modelo");
		String tipo=request.getParameter("tipo");
		String usuario=request.getParameter("usuario");
		
		cuenta.setTelefono(telefono);
		cuenta.setUsuario(usuario);
		List<Dispositivo> dispositivos=cuenta.getDispositivos();
		Dispositivo dispositivo=null;
		if(dispositivos!=null)//El dispositivo ya se encuentra en session
			dispositivo=dispositivos.get(0);
		else{
			dispositivos=new ArrayList<Dispositivo>();
			dispositivo=new Dispositivo();
			dispositivos.add(dispositivo);
		}
		dispositivo.setPlataforma(plataforma);
		dispositivo.setGcmid(gcmid);
		dispositivo.setImei(imei);
		dispositivo.setModelo(modelo);
		dispositivo.setTipoDispositivo(tipo);
		cuenta.setDispositivos(dispositivos);
		return cuenta;
	}
	
	private Cuenta cuenta2(HttpServletRequest request,Cuenta cuenta){	
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
		
		List<Direccion> direcciones=cuenta.getDirecciones();
		Direccion direccion=null;
		if(direcciones!=null)//La direccion ya se encuentra en session
			direccion=direcciones.get(0);
		else{
			direcciones=new ArrayList<Direccion>();
			direccion=new Direccion();
			direcciones.add(direccion);
		}

		direccion.setCalle(calle);
		direccion.setNexterior(numeroe);
		direccion.setNinterior(numeroi);
		direccion.setColonia(colonia);
		direccion.setDelegacion(delegacion);
		direccion.setCp(cp);
		direccion.setEstado(estado);
		direccion.setPais(pais);
		direccion.setCalle1(calle1);
		direccion.setCalle2(calle2);
		direccion.setReferencia1(referencia1);
		direccion.setReferencia2(referencia2);
		cuenta.setDirecciones(direcciones);
		return cuenta;
	}

}
