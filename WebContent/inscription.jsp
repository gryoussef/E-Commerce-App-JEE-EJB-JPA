<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<center>
<h2>Inscrivez-vous </h2>
<form  methode="POST" action="inscription">
    <div >
      <label for="email">Email :</label>
      <input type="text" name="email" value="${user.email}">
      <span style="color:red">${form.erreurs['email']}</span>
      <br>
      <br>
      <label for="password">Password : </label>
      <input type="password" name="pass" value="">
      <span style="color:red">${form.erreurs['pass']}</span>
      <br>
      <br>
      <label for="conf">confirmation : </label>
      <input type="password" name="conf" value="">
      <span style="color:red">${form.erreurs['conf']}</span>
      <br>
      <br>
      <label for="nom">Utilisateur :</label>
      <input type="text" name="nom" value>
      <span style="color:red">${form.erreurs['nom']}</span>
      <br>
      <label for="nom">Telephone :</label>
      <input type="text" name="tele" value>
      
            <br>
      <label for="nom">Adresse :</label>
      <input type="text" name="adresse" value>
      
      
    </div>
    <br>
     <span style="color:red">${form.resultat}</span>
    <br>
    <button type="submit" formmethod="post" name="login">Se connecter</button>
    <button type="reset" >Annuler</button>
    </form>
    
  </center>
</form>
</body>
</html>