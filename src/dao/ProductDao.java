package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Commande;
import entities.Commande_detail;
import entities.Product;
@Stateless
public class ProductDao {
	 // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "shop-pu" )
    private EntityManager em;
    public List<Product> getProducts()throws DAOException{
    	try {
    		TypedQuery<Product>query=em.createQuery( "SELECT c FROM Product c ORDER BY c.id", Product.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
    public List<Product> panier(List<Integer> products_id) throws DAOException{
    	List<Product> panier=new ArrayList<Product>();
    	for (Integer i : products_id) {
    		Long id_pr=Long.valueOf(i);
			Product pr=em.find(Product.class,  id_pr);
			panier.add(pr);
		}
		return panier;
    }
 
    public Double getMontant(List<Integer> products_id) {
    	List<Product> panier=panier(products_id);
    	Double montant=0.0;
    	for (Product product : panier) {
			montant+=product.getPrice();
		}
    	return montant;
    }
    
}
