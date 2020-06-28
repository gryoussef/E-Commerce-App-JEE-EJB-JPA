<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>votre panier</title>
</head>
<body>

<center>
<h4>${items} articles dans votre panier </h4>
<h5> vous devez payer une somme de ${montant}</h5>
<h4><a style="color:green;" href="/shop-project/Catalogue?valider">Valider votre commande</a></h4>
<div >
<table>
   <tr>
    <td>Reference</td>
       <td>Image</td>
       <td>Nom</td>
       <td>Prix</td>
       <td></td>
   </tr>
<c:forEach items="${cart}" var="produit">
 <tr>
 <td>${produit.reference}</td>
       <td><img alt="" src="${produit.photo}" ></td>
       <td>${produit.title}</td>
       <td>${produit.price}</td>
       <td><a style="color:red;" href="/shop-project/Catalogue?catalogue&product_id=${produit.id}">Supprimer</a><td>
  </tr>
</c:forEach>


</table>
</div>
</center>
</body>
</html>