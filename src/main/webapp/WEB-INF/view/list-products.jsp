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
	    <h2>Product storage</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Initial price</th>
				<th>Discount</th>
				<th>End price</th>
				<th></th>
			</tr>
			<c:forEach var="product" items="${products}" varStatus="index">
				<tr>
					<td>${index.count}</td>
					<td>${product.name}</td>
					<td>${product.priceBeforeDiscount}</td>
					<td>${product.discount}</td>
					<td>${product.endPrice}</td>
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br></br>
	<button type="button"><a href="example">Add Sample Products</a></button>
</body>
</html>