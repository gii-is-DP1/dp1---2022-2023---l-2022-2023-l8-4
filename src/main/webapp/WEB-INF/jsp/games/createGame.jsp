<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    <jsp:body>
        <h2>
            <c:if test="${game['new']}">New </c:if> Game
        </h2>
        <form:form modelAttribute="game"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${game.id}"/>
            <input type="hidden" name="date" value="${game.date}"/>
            <div class="form-group has-feedback">                
                <dobble:selectField label="Game mode" name="gameMode" size = "3" names = "${gameModes}"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    	<button class="btn btn-default" type="submit">Create Game</button>
                </div>
            </div>
        </form:form>       
    </jsp:body>
</dobble:layout>
