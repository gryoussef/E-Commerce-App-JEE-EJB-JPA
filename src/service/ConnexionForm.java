package service;
import java.util.HashMap;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.ClientDao;
import entities.Client;


public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";

    private String resultat;
    public boolean connected=false;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private ClientDao daouser;
    public ConnexionForm(ClientDao daoUser2) {
		super();
		this.daouser =  daoUser2;
	}

	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Client connecterClient( HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

         new Client();

        try {
         validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
     

    
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        
        Client utilisateur =daouser.Login(  email, motDePasse );
     
        if ( erreurs.isEmpty() ) {
        	
        	if(utilisateur!=null ) {
            resultat = "Succès de la connexion.";
            connected=true;
            }
        	else {
                resultat = "Échec de la connexion.";
            }
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }


    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

  
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

 
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

   
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}