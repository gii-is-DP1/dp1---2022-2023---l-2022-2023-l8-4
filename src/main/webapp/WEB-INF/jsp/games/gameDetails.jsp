<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">

    <p id="gameStatus" hidden>${gameState}</p>
    <c:choose>
        <c:when test="${ not creator }">

                        <form action="/games/join/${game.gameCode}/${playerId}" method="GET" id = "notStarted" ></form>
                        <form action="/games/board/${game.id}/${playerId}" method="GET" id = "hasStarted"></form>
                        <script>
                            if ( "IN_PROGRESS" == document.getElementById( "gameStatus" ).innerHTML )  document.getElementById( 'hasStarted' ).submit();
                        </script>
        </c:when>
        <c:otherwise>
            <form action="/games/${game.id}" method="GET" id = "notStarted" ></form>
        </c:otherwise>
    </c:choose>
    <script>
                   setTimeout( () => { document.getElementById( 'notStarted' ).submit() }, 5000 )
    </script>
    <h2>Game of <c:out value="${game.gameMode}"/></h2>
    <h2>CODE: <c:out value="${game.gameCode}"/></h2>

    <table id="gameDetailsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Players</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${game.players}" var="player">
            <tr>
                <td>
                    <c:out value="${player.user.username}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <c:if test="${creator}">
    	<c:choose>
    		<c:when test="${ game.players.size() >= 2}">
    			<form action="/games/board/${game.id}" method="GET">
             		<button class="btn btn-default" type="submit">Begin the Game</button>
				</form>
    		</c:when>
    		<c:otherwise>
    			Waiting for players...
    		</c:otherwise>
    	</c:choose> 
    </c:if>
</dobble:layout>
