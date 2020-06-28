package entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
@Entity
@Table(name = "commande")
public class Commande implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 
	    @Id
	    @GeneratedValue( strategy = GenerationType.IDENTITY )
	    private Long     id;
	    
	    @ManyToOne
	    @JoinColumn( name = "id_client" )
	    private Client   client;
	   
	    private Double   montant;
	  
	    private String   modePaiement;
	    
	    private String   statutPaiement;
	   
	    private String   statutLivraison;
	    
	    private Date date_commande;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public Double getMontant() {
			return montant;
		}

		public void setMontant(Double montant) {
			this.montant = montant;
		}

		public String getModePaiement() {
			return modePaiement;
		}

		public void setModePaiement(String modePaiement) {
			this.modePaiement = modePaiement;
		}

		public String getStatutPaiement() {
			return statutPaiement;
		}

		public void setStatutPaiement(String statutPaiement) {
			this.statutPaiement = statutPaiement;
		}

		public String getStatutLivraison() {
			return statutLivraison;
		}

		public void setStatutLivraison(String statutLivraison) {
			this.statutLivraison = statutLivraison;
		}

		public Date getDate_commande() {
			return date_commande;
		}

		public void setDate_commande(Date date_commande) {
			this.date_commande = date_commande;
		}


	

}
