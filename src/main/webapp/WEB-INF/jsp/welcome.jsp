<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<html>
<petclinic:htmlHeader/>

<body id="fondo">
	
	<div id="contenedor">
	<img id="imagen" src="/resources/images/logo.png"/>	
	<a href="<c:url value="/login" />"><div class="button">
	<span style="color: black" class="glyphicon glyphicon-log-in" aria-hidden="true"></span><p class="texto">Login</p></div></a>
	<a href="<c:url value="/users/new" />"><div class="button">
	<span style="color: black" class="glyphicon glyphicon-user" aria-hidden="true"></span><p class="texto">Register</p></div></a>
	</div>

</body>
</html>
