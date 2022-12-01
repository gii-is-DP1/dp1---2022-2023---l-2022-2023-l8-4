<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
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
    			<form action="/games/board" method="GET">
             		<button class="btn btn-default" type="submit">Beguin the Game</button>
				</form>
    		</c:when>
    		<c:otherwise>
    			Waiting for players...
    		</c:otherwise>
    	</c:choose> 
    </c:if>
</dobble:layout>
