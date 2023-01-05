<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="players">

        <form:form action="/logout" method='POST' class="form-horizontal">
        	<div class="login">
        	<h2>Do you want to log out?</h2>
            <button class="btn btn-default" type="submit">Logout</button> 
	        </div>     
        </form:form> 
</dobble:layout>