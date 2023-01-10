<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/spotit.png" var="spotItImage"/>
    <img src="${spotItImage}" width=300 height=300/><br><br><br><br>

    <h1>Unaccesible game</h1>
	
	<body>
		<p>${exception.errorMessage}</p>
		<p>Wether you joined a game you were already in or the game your were trying to join is full or is already being played.</p>
	</body>
    
</petclinic:layout>