<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

<div id="dataPlayer-superior">
	<div id="dataPlayer-superior-divImage">
		<img id="dataPlayer-superior-image" alt="Without image" src="/resources/images/logo.png">
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
<div id="dataPlayer-medio">
  
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
</div>
<div id="dataPlayer-inferior">
</div>
<div id="dataPlayer-buttons">
	<spring:url value="/players/edit/{playerId}" var="editPlayer">
       <spring:param name="playerId" value="${player.id}"></spring:param>
    </spring:url>
	<a href="${editPlayer}"><button class="btn btn-default">Edit Profile</button></a>
	
	<spring:url value="/players/delete/{playerId}" var="deletePlayer">
       <spring:param name="playerId" value="${player.id}"></spring:param>
    </spring:url>
	<a href="${deletePlayer}"><button class="btn btn-default">Delete Profile</button></a>
</div>

</petclinic:layout>
