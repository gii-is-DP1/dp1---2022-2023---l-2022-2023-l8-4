<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    <jsp:body>
        <h2>Join Game</h2>
        <form action="#" th:action="@{/join}" th:object="gameCode" method="post">
        	<label for="gc">Game code:</label>
        	<input type = "text" id = "gc" name = "gameCode" />
         	<input type = "submit" value = "Join Game" />
      	</form>      
    </jsp:body>
</dobble:layout>