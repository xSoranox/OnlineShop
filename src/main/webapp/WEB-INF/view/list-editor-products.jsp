<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<html>
<head>
<title>Editor Products</title>
</head>
<body>
	<div align="center">
		<h2>Editor</h2>
		
				<div><table border="0" cellpadding="20"><tr>
			<th align="center">
				<div>
					<form align="center" action="/onlineshop/adminmode/editor/setDiscount">
						<button type="submit"><a href="<c:url value="/logout" />">Logout</a></button> &nbsp;
						<button type="button"><a href="<c:url value="/adminmode" />">Admin Home</a></button> &nbsp; 
						<input type="text" name="discount" size="10" />
						<input type="submit" value="Apply discount" />
					</form>
				</div>
			</th>
			<th align="center">
				<div>
					<button type="button"><a href="<c:url value="/adminmode/editor/flush" />">Clear editor</a></button> &nbsp; 
				</div>
			</th>
		</tr>
		</table></div><br>

		<table border="1" cellpadding="10">
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Initial price</th>
				<th>Discount</th>
				<th>End price</th>
				<th>Remove from editor</th>
			</tr>
			<c:forEach var="product" items="${editorProducts}" varStatus="index">
				<tr>
					<td align="center">${product.id}</td>
					<td width="400">${product.name}</td>
					<td align="center">€${product.priceBeforeDiscount}</td>
					<td align="center">${product.discount}%</td>
					<td align="center">€${product.endPrice}</td>
					<td align="center"><a href="<c:url value="/adminmode/editor/delete/${product.id}"/>">-</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>