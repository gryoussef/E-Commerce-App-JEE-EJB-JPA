<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
       
        <title>Connexion</title>
        
    </head>
    <body>
    <center>
        <form method="POST" action="<c:url value="../connexion" />">
            <fieldset>
                <legend>Connexion</legend>
               

                <label for="nom">Adresse email <span class="requis">*</span></label>
                <input type="email"  name="email"  value="${client.email}" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password"  name="motdepasse" value="${client.password}" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />
                
                <p>Si vous n'est pas inscrit ,<a href="inscription.jsp">inscrit</a></p><br>
                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
                
                <p >${form.resultat}</p><br>
                
               
                  
               
            
               
            </fieldset>
        </form>
        </center>
    </body>
</html>