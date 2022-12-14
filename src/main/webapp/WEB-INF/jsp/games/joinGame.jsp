<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="play">
    <jsp:body>
        <h2>Join Game</h2>
        <form:form action="/games/join" object="gameCode" method="post">
        	<label for="gc">Game code:</label><br>
        	<input type = "number" id = "gc" name = "gameCode" /><br><br>
         	<button class="btn btn-default" type="submit">Join the Game </button>
      	</form:form>
    </jsp:body>
</dobble:layout>