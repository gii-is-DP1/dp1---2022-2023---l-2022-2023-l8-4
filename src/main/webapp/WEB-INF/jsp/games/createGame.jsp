<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script defer type="text/javascript" src="/resources/js/select.js"></script>

<dobble:layout pageName="play">
    <jsp:body>
        <h2>
            <c:if test="${game['new']}">New </c:if> Game
        </h2>
        <h2>Reglas del juego</h2>
            <ul id="rules" class="nav nav-tabs nav-justified">
  				<li><a href="#setup" data-toggle="tab">Preparaci�n</a></li>
  				<li><a href="#objective" data-toggle="tab">Objectivo</a></li>
  				<li><a href="#howto" data-toggle="tab">C�mo jugar</a></li>
  				<li id = "gameEnding"><a href="#ending" data-toggle="tab">Final</a></li>
			</ul>
			
			<div class="tab-content">
    			<div role="tabpanel" class="tab-pane fade" id="setup">Hola</div>
    			<div role="tabpanel" class="tab-pane fade" id="objective">Buenas</div>
   				<div role="tabpanel" class="tab-pane fade" id="howto">Tardes</div>
    			<div role="tabpanel" class="tab-pane fade" id="ending">Miguelon</div>
			</div>
			
        <form:form modelAttribute="game" Sclass="form-horizontal">
            <input type="hidden" name="id" value="${game.id}"/>
            <input type="hidden" name="date" value="${game.date}"/>
            <input type="hidden" name="gameState" value="${game.gameState}"/>
            <dobble:selectField label="Game mode" name="gameMode" size = "${fn:length(gameModes)}" names = "${gameModes}"/>
          
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Create Game</button>
                </div>
            </div>
        </form:form>      
    </jsp:body>
</dobble:layout>
