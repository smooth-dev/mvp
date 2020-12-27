<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>Add New Article</h1>
<form:form method="post" action="save" modelAttribute="empVo">
	<table>
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
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
