<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="players">
         
        <form:form action="login" method='POST' class="form-horizontal">
        <div class="login">
        	<c:choose>
	        	<c:when test="${param.error!=null}">
	        		<div class="alert alert-danger" role="alert">
  						Bad Credentials, Try again!
					</div>
	        	</c:when>
	        	
	        	<c:otherwise>
	        		<h2>Log in to play</h2>
	        	</c:otherwise>
        	</c:choose>
        	
            <div class="login">   
               <label for="Username">Username</label>             
               <input type='text' name='username' id="Username">
               <label for="Password">Password</label> 
               <input type='password' name='password' id="Password"/>
            </div>
            <button class="btn btn-default" type="submit">Login</button> 
        </div>
        </form:form>      
</dobble:layout>