<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<html>
<head>
<title>Cart Products</title>
</head>
<body>
	<div align="center">
		<h2>Shopping cart</h2>
		<div>
			<th align="center"><button type="button"><a href="<c:url value="/products" />">Home</a></button></th>
			<th align="center"><a href="<c:url value="/shoppingCart/flush/1"/>">Delete all products from shopping cart</a></th>
		</div><br>

		<table border="1" cellpadding="10">
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Initial price</th>
				<th>Discount</th>
				<th>End price</th>
				<th>Remove product</th>
			</tr>
			<c:forEach var="product" items="${cartproducts}" varStatus="index">
				<tr>
					<td align="center">${index.count}</td>
					<td width="400">${product.name}</td>
					<td align="center">€${product.priceBeforeDiscount}</td>
					<td align="center">${product.discount}%</td>
					<td align="center">€${product.endPrice}</td>
					<td align="center"><a href="<c:url value="/shoppingCart/delete/1/${product.id}"/>">-</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>