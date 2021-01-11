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
		<h2>Admin mode</h2>

		<div><table border="0" cellpadding="20"><tr>
			<th align="center">
				<div>
					<form align="center" action="/onlineshop/adminmode/searchByName">
						<button type="submit"><a href="<c:url value="logout" />">Logout</a></button> &nbsp;
						<button type="button"><a href="<c:url value="/adminmode" />">Admin Home</a></button> &nbsp; 
						<input type="text" name="productName" size="45" />
						<input type="submit" value="Search by name" />
					</form>
				</div>
			</th>
			<th align="center">
				<div>
					<form align="center" action="/onlineshop/adminmode/searchById">
						<input type="text" name="productId" size="10" /> 
						<input type="submit" value="Search by ID" />
					</form>
				</div>
			</th>
		</tr>
		</table></div>
		
		<div><table border="0" cellpadding="20">
			<tr>
				<th align="center"><a href="<c:url value="/adminmode/createProduct"/>">Create new product</a></th>
				<th align="center"><a href="<c:url value="/adminmode/editor"/>">Open editor</a></th>
			</tr>
		</table></div>
		
		

		<div>
			<table border="0" cellpadding="19">
				<tr>
					<th align="center"><a href="<c:url value="/adminmode/findbytype/fruitAndVegetables"/>">Fruits and vegetables</a></th>
					<th align="center"><a href="<c:url value="/adminmode/findbytype/breadAndPastries"/>">Bread and pastries</a></th>
					<th align="center"><a href="<c:url value="/adminmode/findbytype/dairyAndEggs"/>">Dairy and eggs</a></th>
					<th align="center"><a href="<c:url value="/adminmode/findbytype/meatAndFish"/>">Meat and fish</a></th>
					<th align="center"><a href="<c:url value="/adminmode/findbytype/sweetsAndSnacks"/>">Sweets and snacks</a></th>
					<th align="center"><a href="<c:url value="/adminmode/findbytype/drinks"/>">Drinks</a></th>
				</tr>
			</table>
		</div>

		<table border="1" cellpadding="10">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Initial price</th>
				<th>Discount</th>
				<th>End price</th>
				<th>Edit</th>
				<th>Add to editor</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="product" items="${products}" varStatus="index">
				<tr>
					<td align="center">${product.id}</td>
					<td width="400">${product.name}</td>
					<td align="center">€${product.priceBeforeDiscount}</td>
					<td align="center">${product.discount}%</td>
					<td align="center">€${product.endPrice}</td>
					<td align="center"><a href="<c:url value="/adminmode/edit/${product.id}"/>">Edit</a></td>
					<td align="center"><a href="<c:url value="/adminmode/editor/add/${product.id}"/>">Select</a></td>
					<td align="center"><a href="<c:url value="/adminmode/delete/${product.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>