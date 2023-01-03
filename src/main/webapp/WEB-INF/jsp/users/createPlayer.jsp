<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${jugador['new']}">New </c:if> Player
        </h2>
        <form:form modelAttribute="player"
                   class="form-horizontal" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${player.id}"/>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Username" name="user.username"/>
                <petclinic:inputField label="Contraseña" name="user.password"/>
                <petclinic:inputField label="email" name="email"/>
                <petclinic:inputField label="birthDate" name="birthDate"/>
               	<div id="selector"> 
                <select name="profilePicture" id="selectorPicture">
				  <option value="/resources/images/logo.png">logo</option>
				  <option value="/resources/images/logros/kinglogo.png">king</option>
				  <option value="/resources/images/logros/viciado.png">viciado</option>
				  <option value="/resources/images/logros/soldado.png">soldado</option>
				</select>
				<img id="imagenSelect" src="/resources/images/logo.png">
				</div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${player['new']}">
                            <button class="btn btn-default" type="submit">Add Player</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Player</button>
                        </c:otherwise>
                    </c:choose>
            	</div>
            </div>
        </form:form>
        <c:if test="${!player['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>

<script>

	const img= document.getElementById('imagenSelect');
	const select=document.getElementById('selectorPicture');
	
		select.addEventListener('change', function(){
			img.src=select.value;	
		})

</script>
