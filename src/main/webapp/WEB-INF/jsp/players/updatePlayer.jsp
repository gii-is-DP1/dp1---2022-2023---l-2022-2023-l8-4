<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="players">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${player['new']}">New </c:if> Player
        </h2>
        <form:form modelAttribute="player"
                   class="form-horizontal">
            <div class="form-group has-feedback">                
                <dobble:inputField label="Username" name="user.username"/>
                <dobble:inputField label="Password" name="user.password"/>
                <dobble:inputField label="Email" name="email"/>
                <dobble:inputField label="BirthDate" name="birthDate"/>
                <div id="selector"> 
                <select name="profilePicture" id="selectorPicture">
				  <option value="/resources/images/logo.png">logo</option>
				  <option value="/resources/images/logros/kinglogo.png">king</option>
				  <option value="/resources/images/logros/viciado.png">viciado</option>
				  <option value="/resources/images/logros/soldado.png">soldado</option>
				</select>
				<img id="imagenSelect" src="${player.profilePicture}">
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
    </jsp:body>
</dobble:layout>

<script>

	const img= document.getElementById('imagenSelect');
	const select=document.getElementById('selectorPicture');
	
		select.addEventListener('change', function(){
			img.src=select.value;	
		})

</script>