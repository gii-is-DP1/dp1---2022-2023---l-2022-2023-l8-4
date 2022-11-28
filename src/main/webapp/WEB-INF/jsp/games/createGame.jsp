<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<script defer type="text/javascript" src="/resources/js/select.js"></script>

<dobble:layout pageName="games">
    <jsp:body>
        <h2>
            <c:if test="${game['new']}">New </c:if> Game
        </h2>
        <form:form modelAttribute="game" Sclass="form-horizontal">
            <input type="hidden" name="id" value="${game.id}"/>
            <input type="hidden" name="date" value="${game.date}"/>
            <input type="hidden" name="gameState" value="${game.gameState}"/>
            <dobble:selectField label="Game mode" name="gameMode" size = "3" names = "${gameModes}"/>
            <h3>Description</h3>
            <p id="description">
            </p>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Create Game</button>
                </div>
            </div>
        </form:form>      
    </jsp:body>
</dobble:layout>
