<!-- core jstl servlet tag library -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- date format tag library -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> -->

<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
	
	<div class="container">
	<table class = "table table-striped">
		<caption> Your todos are </caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.desc}</td>
				<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
				<td>${todo.done}</td>
				<td><a type="button" class="btn btn-warning" href="/update-todo?id=${todo.id}">Update</a></td>
				<td><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
	<a class="button" href="/add-todo">Add ToDo</a>
	</div>
	</div>
	
<%@ include file="common/footer.jspf"%>