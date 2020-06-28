<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h4>les commandes que vous avez effectue</h4>

<div >
<table border="2">
   <tr>
    <td>Reference</td>
       <td>mode paiement</td>
       <td>statutPaiement</td>
       <td>statut Livraison</td>
       <td>montant</td>
       <td></td>
   </tr>
<c:forEach items="${commandes}" var="commande">
 <tr>
       <td>${commande.id}</td>
       <td>${commande.modePaiement}</td>
       <td>${commande.statutPaiement}</td>
        <td>${commande.statutLivraison}</td>
         <td>${commande.date_commande}</td>
        <td>${commande.montant}</td>
       <td><a style="color:green;" href="/shop-project/Catalogue?catalogue&commande_id=${commande.id}">Plus de detail</a><td>
  </tr>
</c:forEach>


</table>
</div>
</center>
</body>
</html>