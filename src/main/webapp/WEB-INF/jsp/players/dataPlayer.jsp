<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

<div id="dataPlayer">
<div id="dataPlayer-superior">
	<div id="dataPlayer-superior-divImage">
		<img id="dataPlayer-superior-image" alt="Without image" src="${player.profilePicture}">
	</div>
	<div id="dataPlayer-superior-data">
		<div class="dataPlayer-superior-data-value">
			<p class="font" >Username: <span class="values-Player">${ player.user.username}</span></p>
		</div>
		<div class="dataPlayer-superior-data-value">
			<p class="font" >Email: <span class="values-Player">${ player.email}</span></p>
		</div>
		<div class="dataPlayer-superior-data-value">
			<p class="font" >Last Modification: <span class="values-Player">${ player.modificationDate}</span></p>
		</div>
	</div>
</div>
  
  <h2>My Games</h2>

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
                     	<c:out value="${player.user.username}"></c:out><br/>
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
							<a class="page-link" href="/players/data/${player.user.username}?page=${prev}">Previous</a>
						</li>
					</c:if>
					<c:forEach items="${pages}" var="page">
						<c:choose>
						 <c:when test="${current == page}">
						  <li class="page-item active">
							<a class="page-link" href="/players/data/${player.user.username}?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:when>
						 <c:otherwise>
						  <li class="page-item">
							<a class="page-link" href="/players/data/${player.user.username}?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${current != last}">
						<li class="page-item">
							<a class="page-link" href="/players/data/${player.user.username}?page=${next}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>    
    </div>
    </c:if>
    <div id="dataPlayer-inferior-statistics">
	    <div class="div1" class="font">Total Points: <span class="values-Player">${player.statistic.totalPoints}</span></div>
		<div class="div2" class="font">Played Games: <span class="values-Player">${player.statistic.gamesPlayed}</span></div>
		<div class="div3" class="font">Won Games: <span class="values-Player">${player.statistic.gamesWon}</span></div>
		<div class="div4" class="font">Lost Games: <span class="values-Player">${player.statistic.gamesLost}</span></div>
	</div>

<c:if test="${player.user.username == username}">
	<div class="dataPlayer-buttons">
		<spring:url value="/players/edit/{playerId}" var="editPlayer">
	       <spring:param name="playerId" value="${player.id}"></spring:param>
	    </spring:url>
		<a href="${editPlayer}"><button class="btn btn-default">Edit Profile</button></a>
		
		<spring:url value="/players/{playerId}/achievements" var="AchievementsPlayer">
	       <spring:param name="playerId" value="${player.id}"></spring:param>
	    </spring:url>
		<a href="${AchievementsPlayer}"><button class="btn btn-default">My Achievements</button></a>
		
		<spring:url value="/players/delete/{playerId}" var="deletePlayer">
	       <spring:param name="playerId" value="${player.id}"></spring:param>
	    </spring:url>
		<button class="btn btn-default" data-toggle="modal" data-target="#myModal">Delete Profile</button>
	</div>
</c:if>

</div>
<petclinic:modal hrefConfirm="${deletePlayer}" nameModal="myModal"></petclinic:modal>
</petclinic:layout>
