<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
  
        <h1>Edit Article</h1>  
       <form:form method="POST" action="../editsave" modelAttribute="empVo">  
        <table >    
        <tr>  
        <td></td>    
         <td><form:hidden  path="id" /></td>  
         </tr>   
      <tr>
			<td>libelle :</td>
			<td><form:input path="libelle" /></td>
		</tr>
		<tr>
			<td>description :</td>
			<td><form:input path="description" /></td>
		</tr>
		<tr>
			<td>prix :</td>
			<td><form:input path="prix" /></td>
		</tr>
		<tr>
			<td>quantite :</td>
			<td><form:input path="quantite" /></td>
		</tr>
           
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form:form>  