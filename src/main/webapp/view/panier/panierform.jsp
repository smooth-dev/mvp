<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>Add New Employee</h1><form:form method="POST" action="save" modelAttribute="panierVO">  
        <table >    
       <%--  <tr>  
        <td></td>    
         <td><form:hidden  path="id" /></td>  
         </tr>  --%>  
      
		<tr> <td> choisissez le proprietaire du panier :</td></tr>
<tr> <td> 

	<form:select path="user.id">  
        <form:options items="${list}" itemLabel="nom" itemValue="id"/>  

        </form:select>   
        </td> </tr> 

		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
