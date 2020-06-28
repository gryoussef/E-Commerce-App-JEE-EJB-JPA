package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commande_detail")
public class Commande_detail {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
private Long id_commandeDetail;
    @ManyToOne
    @JoinColumn( name = "id_commande" )
private Commande commande;
    @ManyToOne
    @JoinColumn( name = "id_produit" )
private Product produit;
public Long getId_commandeDetail() {
	return id_commandeDetail;
}
public void setId_commandeDetail(Long id_commandeDetail) {
	this.id_commandeDetail = id_commandeDetail;
}
public Commande getCommande() {
	return commande;
}
public void setCommande(Commande commande) {
	this.commande = commande;
}
public Product getProduit() {
	return produit;
}
public void setProduit(Product produit) {
	this.produit = produit;
}

}
