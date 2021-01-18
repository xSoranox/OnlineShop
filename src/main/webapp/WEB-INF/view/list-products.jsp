<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<html>
<head>
<title>Products</title>
</head>
<body>
	<div align="center">
		<table border="0" cellpadding="19">
				<tr>
		<th><h2>Product storage</h2> </th>
		<th><a href="/onlineshop/shoppingCart/1" data-gtm-click-name="Your cart">
		<div>${cartSize}</div>
        	<div>
        		<svg width="25" height="25" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 48 48">
        			<g fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="2">	
        				<path d="M44 36H19.2c-3.9 0-7.2-2.8-7.9-6.6L6.5 1H0"></path>
        				<path d="M8 9h39l-2.4 11.6c-.9 4.4-4.7 7.6-9.1 7.9l-24 1.5"></path>
        				<circle cx="15.5" cy="43.5" r="3.5"></circle>
        				<circle cx="39.5" cy="43.5" r="3.5"></circle>
        			</g>
        		</svg> 
        	</div> 
        </a></th>
        </tr>
		</table>
        
		<form align="center" action="/onlineshop/products/search">
			<button type="button"><a href="<c:url value="/connectadmin" />">Admin mode</a></button> &nbsp;
			<button type="button"><a href="<c:url value="/products" />">All products</a></button> &nbsp;
			<input type="text" name="productName" size="45" />  
			<input type="submit" value="Search by name" />
		</form>
		<div>
			<table border="0" cellpadding="19">
				<tr>
		    		<th align="center"><a href="<c:url value="/products/findbytype/fruitAndVegetables"/>">Fruits and vegetables</a></th>
					<th align="center"><a href="<c:url value="/products/findbytype/breadAndPastries"/>">Bread and pastries</a></th>
					<th align="center"><a href="<c:url value="/products/findbytype/dairyAndEggs"/>">Dairy and eggs</a></th>
					<th align="center"><a href="<c:url value="/products/findbytype/meatAndFish"/>">Meat and fish</a></th>
					<th align="center"><a href="<c:url value="/products/findbytype/sweetsAndSnacks"/>">Sweets and snacks</a></th>
					<th align="center"><a href="<c:url value="/products/findbytype/drinks"/>">Drinks</a></th>
				</tr>
			</table>
		</div>

		<table border="1" cellpadding="10">
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
					<td align="center">${index.count}</td>
					<td width="400">${product.name}</td>
					<td align="center">€${product.priceBeforeDiscount}</td>
					<td align="center">${product.discount}%</td>
					<td align="center">€${product.endPrice}</td>
					<td align="center"><a href="<c:url value="/shoppingCart/addProduct/1/${product.id}"/>">+</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br></br>
	<button type="button">
		<a href="<c:url value="/products/example"/>">Add sample products</a>
	</button>
	<button type="button">
		<a href="<c:url value="/products/flush"/>">Remove all products</a>
	</button>
</body>
</html>