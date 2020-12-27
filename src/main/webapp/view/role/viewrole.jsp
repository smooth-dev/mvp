<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Roles List</h1>
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Id</th>
		<th>Libelle</th>
	
		
	</tr>
	<c:forEach var="empVo" items="${list}">
		<tr>
			<td>${empVo.id}</td>
			<td>${empVo.libelle}</td>
		
			
			<td><a href="edit/${empVo.id}">Edit</a></td>
			<td><a href="delete/${empVo.id}">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<br />
<a href="form">Add New Article</a>
