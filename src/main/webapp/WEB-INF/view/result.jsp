<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>
   <body>

      <h2>Submitted Student Information</h2>
      <table>
         <tr>
            <td>Name</td>
            <td>${name}</td>
         </tr>
         <tr>
            <td>Category</td>
            <td>${category}</td>
         </tr>
         <tr>
            <td>Price before discount</td>
            <td>${priceBeforeDiscount}</td>
         </tr>
         <tr>
            <td>Discount</td>
            <td>${discount}</td>
         </tr>
      </table>  
   </body>
</html>