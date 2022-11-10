<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${jugador['new']}">Nuevo </c:if> Jugador
        </h2>
        <form:form modelAttribute="jugador"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${jugador.id}"/>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Username" name="user.username"/>
                <petclinic:inputField label="Contraseña" name="user.password"/>
                <petclinic:inputField label="email" name="email"/>
                <petclinic:inputField label="fechaNacimiento" name="fechaNacimiento"/>
                <petclinic:inputField label="fotoPerfil" name="fotoPerfil"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${jugador['new']}">
                            <button class="btn btn-default" type="submit">Add Jugador</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Jugador</button>
                        </c:otherwise>
                    </c:choose>
            	</div>
            </div>
        </form:form>
        <c:if test="${!jugador['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>