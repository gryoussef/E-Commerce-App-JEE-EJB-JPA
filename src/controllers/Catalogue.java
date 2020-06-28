package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDao;
import dao.CommandeDao;
import dao.ProductDao;
import entities.Client;
import entities.Commande;
import entities.Product;

/**
 * Servlet implementation class Catalogue
 */
@WebServlet("/Catalogue")
public class Catalogue extends HttpServlet {
	 int items;
	 Double montant;
	 List <Integer>  id_produits_Commandee;
	 List <Product> cart;
	private static final long serialVersionUID = 1L;
	@EJB
	ProductDao productDao;
	@EJB
	CommandeDao commandeDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalogue() {
        super();
      
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	 id_produits_Commandee=new ArrayList<Integer>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		  if (request.getParameterMap().containsKey("catalogue")) {
			   String product_id=request.getParameter("product_id");
		
		       if(product_id!=null) {
		    	   items++;
		    	   id_produits_Commandee.add(Integer.parseInt( product_id));
		         }
		       request.setAttribute("items", items);
		  	 request.setAttribute("cartss",  id_produits_Commandee);
		       List<Product> produits=productDao.getProducts();
		       request.setAttribute("produits", produits);
		       this.getServletContext().getRequestDispatcher( "/content/Catalogue.jsp"  ).forward( request, response );
         }
		 if (request.getParameterMap().containsKey("panier"))
		 {
			 request.setAttribute("items", items);
			  cart=productDao.panier(id_produits_Commandee);
			  montant=productDao.getMontant(id_produits_Commandee);
			  request.setAttribute("montant",  montant);
			  request.setAttribute("cart",  cart);
			  this.getServletContext().getRequestDispatcher( "/content/panier.jsp"  ).forward( request, response );
		 }
		 if (request.getParameterMap().containsKey("valider")){
			 
			  HttpSession session=request.getSession();
			  Client cl=(Client) session.getAttribute("sessionClient");
			  commandeDao.commander(cart, cl, montant);
				// this.getServletContext().getRequestDispatcher( "/shop-project/Catalogue?commande"  ).forward( request, response );
		 
			 List<Commande> commandes=commandeDao.getCommands();
			 request.setAttribute("commandes",  commandes);
			 this.getServletContext().getRequestDispatcher( "/content/Commande.jsp"  ).forward( request,response );
		 }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
