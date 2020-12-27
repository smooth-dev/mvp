<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Panier List</h1>
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Id</th>
		<th>User id</th>
		<th>Articles contenues</th>
		
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="empVo" items="${list}">
		<tr>
			<td>${empVo.id}</td>
			<td>${empVo.user.id}</td>
			<td>
			<c:forEach var="empVod" items="${empVo.articles}">
			<ul><li> 
			${empVod.libelle} </li></ul>
			</c:forEach>
			<td>
			
			<td><a href="edit/${empVo.id}">Edit</a></td>
			<td><a href="delete/${empVo.id}">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<br />
<a href="form">Add New Article</a>
