<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            Usuarios
        </h2>
        <form:form modelAttribute="usuario"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${usuario.id}"/>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Usuario name" name="name"/>
                <petclinic:inputField label="Usuario contraseña" name="contraseña"/>
                <petclinic:inputField label="Usuario fechaRegistro" name="fechaRegistro"/>
                <petclinic:inputField label="Usuario fechaModificacion" name="fechaModificacion"/>
                <petclinic:inputField label="Usuario ultimoInicioSesion" name="ultimoInicioSesion"/>
                <petclinic:inputField label="Usuario nombreUsuario" name="nombreUsuario"/>
                <petclinic:inputField label="Usuario email" name="email"/>
                <petclinic:checkboxField label="Secure room?" name="secure"/>
                <petclinic:inputField label="Usuario fechaNacimiento" name="fechaNacimiento"/>
                <petclinic:inputField label="Usuario fotoPerfil" name="fotoPerfil"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${usuario['create']}">
                            <button class="btn btn-default" type="submit">Add Usuario</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Usuario</button>
                        </c:otherwise>
                    </c:choose>
            	</div>
            </div>
        </form:form>
        <c:if test="${!usuario['create']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
