package service;

import java.sql.Timestamp;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.ClientDao;
import dao.DAOException;
import entities.Client;


public class InscriptionForm {
	private ClientDao clientDao;

	private String   resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	public InscriptionForm( ClientDao clientDao ) {
	    this.clientDao = clientDao;
	}
	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreur(String nomErreur,String  erreur) {
		getErreurs().put(nomErreur, erreur);
	}
	/*
	 * Inscription
	 */
	
	public Client inscrireClient( HttpServletRequest request ) {
	    String email = (String) request.getParameter("email" );
	    String motDePasse = (String) request.getParameter("pass" );
	    String confirmation = (String) request.getParameter("conf" );
	    String nom = (String) request.getParameter("nom" );
	    String telephone = (String) request.getParameter("tele" );
	    String adresse= (String) request.getParameter("adresse" );
	    Client client = new Client();
	    
	    try {
	        traiterEmail( email, client );
	        traiterMotsDePasse( motDePasse, confirmation, client );
	        traiterNom(  nom,  client);
	       traiterAdresse(adresse,client);
	       traiterTelephone(telephone, client);
	       
			if ( erreurs.isEmpty() ) {
	            clientDao.creer( client );
	            resultat = "Succès de l'inscription.";
	        } else {
	            resultat = "Échec de l'inscription.";
	        }
	    } catch ( DAOException e ) {
	        resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        e.printStackTrace();
	    }

	    return client;
	}
	
	/*
	 * TRAITEMENT
	 */
	private void traiterAdresse( String adresse, Client client ) {
		client.setAdresse(adresse);
	}
	private void traiterTelephone( String tele, Client client ) {
		client.setTelephone(tele);
	}
	private void traiterEmail( String email, Client client ) {
	    try {
	        validationEmail( email );
	    } catch ( FormValidationException e ) {
	        setErreur( "email", e.getMessage() );
	    }
	    client.setEmail( email );
	}
	private void traiterNom( String nom, Client client ) {
    
    try {
        validationNom( nom );
    } catch ( FormValidationException e ) {
        setErreur( "nom", e.getMessage() );
    }
    client.setNom(nom);
	}
	private void traiterMotsDePasse( String motDePasse, String confirmation, Client client ) {
	    try {
	        validationMotsDePasse( motDePasse, confirmation );
	    } catch ( FormValidationException e ) {
	        setErreur( "pass", e.getMessage() );
	        
	    } 
	    client.setPassword(motDePasse);

	}

	private void validationNom( String nom ) throws FormValidationException {
	    if ( nom != null && nom.trim().length() < 3 ) {
	    	
	        throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
	    }
	    if(nom==null) {throw new FormValidationException( "Le nom d'utilisateur vide" );}
	}
	/**
	 * Valide les mots de passe saisis.
	 */
	private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException{
	    if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
	        if (!motDePasse.equals(confirmation)) {
	        	   System.out.println("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
	            throw new FormValidationException("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
	         
	        } else if (motDePasse.trim().length() < 3) {
	        	   System.out.println("Les mots de passe doivent contenir au moins 3 caractères.");
	            throw new FormValidationException("Les mots de passe doivent contenir au moins 3 caractères.");
	        }
	    } else {
	    	
	        throw new FormValidationException("Merci de saisir et confirmer votre mot de passe.");
	    }
	}
	/* Validation de l'adresse email */
	private void validationEmail( String email ) throws FormValidationException {
	    if ( email != null ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new FormValidationException( "Merci de saisir une adresse mail valide." );
	        } else if ( clientDao.trouver( email ) != null ) {
	            throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
	        }
	    } else {
	        throw new FormValidationException( "Merci de saisir une adresse mail." );
	    }
	}
	
}
