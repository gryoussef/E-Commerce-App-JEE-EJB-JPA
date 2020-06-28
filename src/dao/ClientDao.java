package dao;

import java.util.List;

import javax.ejb.Stateless;

import javax.management.Query;
import javax.persistence.*;
import entities.*;
@Stateless
public class ClientDao  {
	
	   private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Client u WHERE u.email=:email";
	   private static final String JPQL_LOGIN = "SELECT u FROM Client u WHERE u.email=:email And u.password=:passe";
	    private static final String PARAM_EMAIL           = "email";
	    private static final String PARAM_Password           ="passe";
	    // Injection du manager, qui s'occupe de la connexion avec la BDD
	    @PersistenceContext( unitName = "shop-pu" )
	    private EntityManager em;
	    public void creer(Client user)throws DAOException {
	    	try {
	    		em.persist(user);
	    	}
	    	catch(Exception e) {
	    		throw new DAOException(e);
	    	}
	    }
	    // Recherche d'un utilisateur à partir de son adresse email
	    public Client trouver( String email ) throws DAOException {
	        Client utilisateur = null;
	        
	        javax.persistence.Query requete=null;
	        requete= (javax.persistence.Query)em.createQuery(JPQL_SELECT_PAR_EMAIL);
	        requete.setParameter( PARAM_EMAIL, email );
	        try {
	            utilisateur = ( Client) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	        return utilisateur;
	    }
	    /*
	     * Connecter un client
	     */
	    public  Client Login( String email ,String Mot_de_passe) throws DAOException {
	    	 Client utilisateur = null;
	        javax.persistence.Query requete=null;
	        requete= (javax.persistence.Query)em.createQuery(JPQL_LOGIN );
	        
	        requete.setParameter( PARAM_EMAIL, email );
	        requete.setParameter( PARAM_Password,Mot_de_passe );
	        try {
	            utilisateur = ( Client) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	        return utilisateur;
	    }


}