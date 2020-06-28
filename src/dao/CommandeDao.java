package dao;
import  java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Client;
import entities.Commande;
import entities.Commande_detail;
import entities.Product;
@Stateless
public class CommandeDao {
	
	  @EJB
	  ProductDao productDao;
	  @PersistenceContext( unitName = "shop-pu" )
	  private EntityManager em;
	  public Commande initialiserCommande(Client client,Double montant) {
		  Commande cm=new Commande();
		  cm.setClient(client);
		  cm.setModePaiement("cache");
		  cm.setMontant(montant);
		  cm.setStatutPaiement("n'est pas pays");
		  cm.setStatutLivraison("n'est pas livree");
		  Date commande_date = new Date(Calendar.getInstance().getTimeInMillis());
		  cm.setDate_commande(commande_date);
	    	try {
	    		em.persist(cm);
	    	}
	    	catch(Exception e) {
	    		throw new DAOException(e);
	    	}
	    	return cm;
		  
	  }
	  public void commander(List<Product>product,Client client,Double montant) {
		  Commande commande=initialiserCommande(client, montant);
	      for (Product item : product) {
			commanderProduit(item, commande);
		}
	  }
	   public void commanderProduit(Product pr,Commande cm) throws DAOException{
	    	Commande_detail commandedetailDetail=new Commande_detail();
	    	commandedetailDetail.setProduit(pr);
	    	commandedetailDetail.setCommande(cm);
	    	em.persist(commandedetailDetail);
	    }
	  
	    public List<Commande> getCommands()throws DAOException{
	    	try {
	    		TypedQuery<Commande>query=em.createQuery( "SELECT c FROM Commande c ORDER BY c.id", Commande.class );
	            return query.getResultList();
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	    }
	    public List<Commande_detail> trouver( Commande cm ) throws DAOException {
  
	        javax.persistence.Query requete=null;
	        requete= (javax.persistence.Query)em.createQuery("SELECT c FROM Commande_detail c ORDER BY c.id Where c.id_commande=:id_commande");
	        requete.setParameter( "id_commande", cm.getId() );
	        try {
	        	 return ( List<entities.Commande_detail>) requete.getResultList();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	    }
	    
}
