<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="results">
	<h1>Game <c:out value='#${game.id}'/> </h1>
	<h2>Winner: <c:out value='${winner.user.username}'/> </h2>
	<h2>Your points: <c:out value='${points}'/> </h2>
	<div>
	<h2>New achievements: </h2>
    	<c:choose>
    		<c:when test="${newAchievements.size() == 0}">
    			No new achievements...
    		</c:when>
    		<c:otherwise>
    			<c:forEach items="${newAchievements}" var="achievement">
             		<c:out value="${achievement.name}"></c:out><br/>
        		</c:forEach> 
    		</c:otherwise>
    	</c:choose><br/>
	</div>
		 
	<table id="gamePlayersTable" class="table table-striped">
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
</dobble:layout>