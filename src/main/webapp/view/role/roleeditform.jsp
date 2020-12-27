<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
  
        <h1>Edit Role</h1>  
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
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form:form>  