<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Articles List</h1>
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Id</th>
		<th>Libelle</th>
		<th>description</th>
		<th>prix</th>
	<th>quantite</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="empVo" items="${list}">
		<tr>
			<td>${empVo.id}</td>
			<td>${empVo.libelle}</td>
			<td>${empVo.description}</td>
			<td>${empVo.prix}</td>
			<td>${empVo.quantite}</td>
			
			<td><a href="edit/${empVo.id}">Edit</a></td>
			<td><a href="delete/${empVo.id}">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<br />
<a href="form">Add New Article</a>
