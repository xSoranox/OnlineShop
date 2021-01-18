<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>New Product</title>
</head>
<body>

<div align="center">
    <h1>Add product</h1>
    <form:form method = "POST" action = "saveProduct">
        <div><table>
            <form:hidden path="id"/>
            <tr>
              <td><form:label path = "name">Name:</form:label></td>
              <td><form:input path = "name" /></td>
            </tr>
            <tr>
                <td><form:label path = "category">Category:</form:label></td>
                <td>
                	<form path = "category">
 						<input type="radio" id="fruitAndVegetables" name="category" value="fruitAndVegetables"><label for="fruitAndVegetables">Fruit and vegetables</label><br>
 						<input type="radio" id="dairyAndEggs" name="category" value="dairyAndEggs"><label for="dairyAndEggs">Dairy and eggs</label><br>
 						<input type="radio" id="meatAndFish" name="category" value="meatAndFish"><label for="meatAndFish">Meat and fish</label><br>
 						<input type="radio" id="breadAndPastries" name="category" value="breadAndPastries"><label for="breadAndPastries">Bread and pastries</label><br>
 						<input type="radio" id="sweetsAndSnacks" name="category" value="sweetsAndSnacks"><label for="sweetsAndSnacks">Sweets and snacks</label><br>
 						<input type="radio" id="drinks" name="category" value="drinks"><label for="drinks">Drinks</label>
 					</form>
 				</td>
            </tr>
            <tr>
            	<td><form:label path = "priceBeforeDiscount">Price before discount:</form:label></td>
                <td><form:input path="priceBeforeDiscount"/></td>
            </tr>
            <tr>
            	<td><form:label path = "discount">Discount:</form:label></td>
                <td><form:input path="discount"/></td>
            </tr>
            <tr>
            	<td></td>
            	<td>${message}</td>
            </tr>
        </table>
        </div><br>

        <input type="submit" value="Save product"></td>&nbsp;&nbsp;&nbsp;
        <button type="button"><a href="<c:url value="/adminmode"/>">Admin Home</a></button></td>

    </form:form>
</div>

</body>
</html>