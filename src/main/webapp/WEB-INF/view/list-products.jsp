<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<head>
	<title>Products</title>
</head>
<body>
	<div align = "center">
	<button type="button"><a href="<c:url value="/products" />">Home</a></button>
	<a href="<c:url value="/products/findbytype/fruitAndVegetables"/>" >Fruits and vegetables</a>
	<a href="<c:url value="/products/findbytype/breadAndPastries"/>" >Bread and pastries</a>
	<a href="<c:url value="/products/findbytype/dairyAndEggs"/>" >Dairy and eggs</a>
	<a href="<c:url value="/products/findbytype/meatAndFish"/>" >Meat and fish</a>
	<a href="<c:url value="/products/findbytype/sweetsAndSnacks"/>" >Sweets and snacks</a>
	<a href="<c:url value="/products/findbytype/drinks"/>" >Drinks</a> 
	
	    <h2>Product storage</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Initial price</th>
				<th>Discount</th>
				<th>End price</th>
				<th>Add product</th>
			</tr>
			<c:forEach var="product" items="${products}" varStatus="index">
				<tr>
					<td>${index.count}</td>
					<td>${product.name}</td>
					<td>€${product.priceBeforeDiscount}</td>
					<td>${product.discount}%</td>
					<td>€${product.endPrice}</td>
					<td>+</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br></br>
	<button type="button"><a href="<c:url value="/products/example"/>" >Add sample products</a></button>
	<button type="button"><a href="<c:url value="/products/flush"/>" >Remove all products</a></button>
</body>
</html>