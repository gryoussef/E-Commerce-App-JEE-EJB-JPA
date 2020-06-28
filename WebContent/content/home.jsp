<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h2 style="color:green;">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionClient.email}</h2><br>
 <h4> Bonjour Mr/Mme ${sessionScope.sessionClient.nom}</h4>
<center>
<a href="/shop-project/Catalogue?catalogue">voir le catalogue</a> <br>
<a href="/shop-project/Catalogue?panier">votre panier</a>  <br>
<a href="/shop-project/Catalogue?commande">suivre votre commande</a>  
<br><br>
<a href="/shop-project/deconnexion" style="color:red;">Deconnecter</a>
</center>
</body>
</html>