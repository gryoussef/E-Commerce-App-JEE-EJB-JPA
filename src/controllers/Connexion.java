package controllers;

import java.io.IOException;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import dao.ClientDao;
import entities.Client;
import service.ConnexionForm;
@WebServlet( urlPatterns = { "/connexion" } )
public class Connexion extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "client";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionClient";
    public static final String VUE_CONNEXION            = "/connexion.jsp";
     public static final String HOME_PAGE ="/content/home.jsp";

    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private ClientDao daoUser;
  
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        this.getServletContext().getRequestDispatcher( VUE_CONNEXION   ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
    
        ConnexionForm form = new ConnexionForm(daoUser);

        Client client = form.connecterClient(request);

        HttpSession session = request.getSession();

        /*
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
     
        if ( form.connected ) {
            session.setAttribute( ATT_SESSION_USER, client );
            
            response.sendRedirect( request.getContextPath()+HOME_PAGE );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
            request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, client );
            this.getServletContext().getRequestDispatcher( VUE_CONNEXION   ).forward( request, response );
        }

    
        
    }
}