<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    <h2>Games</h2>

    <table id="playersTable" class="table table-condensed">
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
            <tr class="tabla">
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
                     	<spring:url value="/players/data/{playerId}" var="playerProfile">
                     		<spring:param name="playerId" value="${player.id}"></spring:param>
                     	</spring:url>
                     	<a href="${fn:escapeXml(playerProfile)}"><c:out value="${player.user.username}"></c:out></a><br/>
                     </c:forEach>                                        
                </td>
             </c:forEach>
        </tbody>
    </table>
    <c:if test="${pages.size() > 1}">
     <div class="row" id="pagination-disposition">
		<div class="col-md-6">
			<nav class="nav" id="pagination-disposition">
				<ul class="pagination">
					<c:if test="${prev != 0}">
						<li class="page-item">
							<a class="page-link" href="/games/finalized?page=${prev}">Previous</a>
						</li>
					</c:if>
					<c:forEach items="${pages}" var="page">
						<c:choose>
						 <c:when test="${current == page}">
						  <li class="page-item active">
							<a class="page-link" href="/games/finalized?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:when>
						 <c:otherwise>
						  <li class="page-item">
							<a class="page-link" href="/games/finalized?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${current != last}">
						<li class="page-item">
							<a class="page-link" href="/games/finalized?page=${next}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>    
    </div>
    </c:if>

</dobble:layout>
