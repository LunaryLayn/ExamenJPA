package controller;

import entities.Cita;
import entities.Modelo;
import entities.Modeloacabado;
import entities.Tipo;
import entities.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import util.JPAUtil;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
       Query q;
       List<Modelo> modelos;
       List<Tipo> tipos;
       Usuario usuario=null;
       
       
        EntityManager em = (EntityManager) session.getAttribute("em");
        if (em == null) {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            session.setAttribute("em", em);
        }

        String op = request.getParameter("op");
        if (op.equals("inicio")) {
             q = em.createNamedQuery("Modelo.findAll");
             modelos = q.getResultList();
             session.setAttribute("modelos", modelos);
             session.setAttribute("todosmodelos", modelos);
             
             q = em.createNamedQuery("Tipo.findAll");
             tipos = q.getResultList();
             session.setAttribute("tipos", tipos);
            
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else if (op.equals("filtered")) {
            String filter = request.getParameter("filter");
            Tipo tiposelected = em.find(Tipo.class, Short.valueOf(filter));
            List<Modelo> thisModelos = tiposelected.getModeloList();
            
            session.setAttribute("modelos", thisModelos);
            request.getRequestDispatcher("home.jsp").forward(request, response);
            
        } else if (op.equals("login")) {
            String nick = request.getParameter("nick");
            String pass = request.getParameter("pass");
            
            q = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = '"+nick+"' AND u.pass = '"+pass+"'");
            try {
                usuario = (Usuario) q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e);
            }

            if (usuario==null) { // Login incorrecto procedemos a insertarlo
                usuario = new Usuario();
                //Si metes numero haces Short.ValueOf("1")
                usuario.setId(Short.valueOf("1"));
                usuario.setNick(nick);
                usuario.setPass(pass);
                EntityTransaction t = em.getTransaction();
                t.begin();
                em.persist(usuario);
                t.commit();
            }
           
            session.setAttribute("usuario", usuario);
           request.getRequestDispatcher("home.jsp").forward(request, response);
        }
        else if (op.equals("logout")) {
            session.removeAttribute("usuario");
           request.getRequestDispatcher("home.jsp").forward(request, response);
        } 
        
        else if (op.equals("acabado")) {
            String modeloid = request.getParameter("modeloid");
            Modelo modeloselected = em.find(Modelo.class, Short.valueOf(modeloid));
            
            List<Modeloacabado> acabados = modeloselected.getModeloacabadoList();
            
            session.setAttribute("modeloselected", modeloselected);
            session.setAttribute("acabados", acabados);
            
           request.getRequestDispatcher("acabado.jsp").forward(request, response);
        } else if (op.equals("reserva")) {
            
            String reserva = request.getParameter("reserva");
            Modelo modeloselected = em.find(Modelo.class, Short.valueOf(reserva));
            
            Usuario thisuser = (Usuario) session.getAttribute("usuario");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = new Date();
            
        
            Cita cita = new Cita();
            cita.setModelo(modeloselected);
            cita.setUsuario(thisuser);
            cita.setId(Short.valueOf("1"));
            cita.setFecha(sdf.format(fecha));
            
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(cita);
            t.commit();
                
           request.getRequestDispatcher("home.jsp").forward(request, response);
        } 
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
