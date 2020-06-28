<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<h4>${items} articles dans votre panier</h4>
<h4><a href="/shop-project/Catalogue?panier">Completer votre commande</a></h4>
<center>
<c:forEach items="${cartss}" var="pr">
${pr}
</c:forEach>
<div >
<table>
   <tr>
    <td>Reference</td>
       <td>Image</td>
       <td>Nom</td>
       <td>Prix</td>
       <td></td>
   </tr>
<c:forEach items="${produits}" var="produit">
 <tr>
 <td>${produit.reference}</td>
       <td><img alt="" src="${produit.photo}" ></td>
       <td>${produit.title}</td>
       <td>${produit.price}</td>
       <td><a href="/shop-project/Catalogue?catalogue&product_id=${produit.id}">Commander</a><td>
  </tr>
</c:forEach>


</table>
</div>
</center>
</body>
</html>