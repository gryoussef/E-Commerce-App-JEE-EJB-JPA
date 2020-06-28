package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RestrictionFilter
 */

@WebFilter("/RestrictionFilter")
public class RestrictionFilter implements Filter {
	public static final String ATT_SESSION_USER = "sessionClient";
	//private ServletContext context;
    /**
     * Default constructor. 
     */
    public RestrictionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session=request.getSession();
        
        /**
         * La journalisation
       
        this.context.log("La machine distanter du client "+request.getRemoteHost());
        this.context.log("adresse ip du client "+request.getRemoteAddr());
        this.context.log("le port du client "+request.getRemotePort());
        this.context.log("le nom du client distant "+request.getRemoteUser());
        this.context.log("--------------------------------------------------------");
          */
        /*
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */ try {
        if ( session.getAttribute(ATT_SESSION_USER).equals(null) ) {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() +"/connexion.jsp ");
        } else {
            /* Affichage de la page restreinte */
           chain.doFilter(request, response);

        }
        } catch(NullPointerException e) {
            /* Affichage de la page restreinte */
        	 response.sendRedirect( request.getContextPath() +"/connexion.jsp ");
        

        }
        
        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
