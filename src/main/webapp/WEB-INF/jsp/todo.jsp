<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container"> 
	
		<form:form method="post" modelAttribute="todo">
			<form:hidden path="id"/> 
			<fieldset class="form-group">
				<form:label path="desc">Description : </form:label>
				<form:input type="text" path="desc" class="form-control" required="required"/>
				<!--form:errors is used to display the error message like if we didnot enter the minimum number of characters in input field  -->
				<form:errors path="desc" cssClass="text-warning"/> 
			</fieldset>
			
			<fieldset class="form-group">
				<form:label path="targetDate">Target Date : </form:label>
				<form:input type="text" path="targetDate" class="form-control" required="required"/>
				<!--form:errors is used to display the error message like if we didnot enter the minimum number of characters in input field  -->
				<form:errors path="targetDate" cssClass="text-warning"/> 
			</fieldset>
			<button class="btn btn-success" type="submit">Add</button>
		</form:form>
</div>

<%@ include file="common/footer.jspf"%>