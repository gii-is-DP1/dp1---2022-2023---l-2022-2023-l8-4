<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="results">
	<h2>Game <c:out value='${game.id}'/> </h2>
	<h3>Winner: <c:out value='${winner}'/> </h3>
	<h3>Your points: <c:out value='${points}'/> </h3>
	<h3>New achievements: </h3>
		<c:forEach items="${newAchievements}" var="achievement">
             <c:out value="${achievement.name}"></c:out><br/>
        </c:forEach>  
	<h3>Players: </h3>
		<c:forEach items="${game.players}" var="player">
             <c:out value="${player.user.username}"></c:out><br/>
        </c:forEach>  
</dobble:layout>