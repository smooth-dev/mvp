<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>Add New Role</h1>
<form:form method="post" action="save" modelAttribute="empVo">
	<table>
		<tr>
			<td>libelle :</td>
			<td><form:input path="libelle" /></td>
		</tr>
	
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
