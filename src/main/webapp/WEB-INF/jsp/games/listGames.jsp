<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    <h2>Games</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Date</th>
            <th>Game Mode</th>
            <th>Game Code</th>
            <th>Players</th>
        </tr>
        </thead>
        <tbody>
       
        <c:forEach items="${games}" var="game">
            <tr>
                <td>
                    <c:out value="${game.date}"/>
                </td>
                <td>                    
                      <c:out value="${game.gameMode} "/>                                        
                </td>
                <td>                    
                      <c:out value="${game.gameCode} "/>                                        
                </td>
                <td>                    
                     <c:forEach items="${game.players}" var="player">
                     	<spring:url value="/players/{playerId}" var="playerProfile">
                     		<spring:param name="playerId" value="${player.id}"></spring:param>
                     	</spring:url>
                     	<a href="${fn:escapeXml(playerProfile)}"><c:out value="${player.user.username}"></c:out></a><br/>
                     </c:forEach>                                        
                </td>
             </c:forEach>
        </tbody>
    </table>
    

</dobble:layout>
