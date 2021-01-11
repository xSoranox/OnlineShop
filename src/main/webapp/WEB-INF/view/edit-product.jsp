<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>Edit Product</title>
</head>
<body>

<div align="center">
    <h2>Edit product</h2>

    <form:form action="saveEditedProduct" modelAttribute="product">

        <table border="0" cellpadding="10">
            <tr>
                <td>Database ID:</td>
                <td>${product.id}
                    <form:hidden path="id"/></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
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
                <td>Price before discount:</td>
                <td><form:input path="priceBeforeDiscount"/></td>
            </tr>
            <tr>
                <td>Discount:</td>
                <td><form:input path="discount"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Update product"></td>
            </tr>
        </table>
    </form:form>

</div>
</body>
</html>