<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">

	<h2>New game</h2>
	
	<form:form modelAttribute="game" class="form-horizontal" id="add-match-form">
		<input type="hidden" name="id" value="${game.id}">
		<div class="form-group has-feedback">
			<div class="control-group">
        		<dobble:selectField label="Game mode" name="gameMode" size = "3" names = "${gameModes}"/>
        	</div>
		</div>
		
		<div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                  <button class="btn btn-default" type="submit">CREATE GAME</button>
            </div>
        </div>
    </form:form>
	
</dobble:layout>
