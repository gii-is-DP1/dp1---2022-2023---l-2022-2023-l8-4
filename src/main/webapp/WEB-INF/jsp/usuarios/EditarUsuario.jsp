<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
  <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaNacimiento").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            Usuarios
        </h2>
        <form:form modelAttribute="usuario">

            <div class="form-group has-feedback">
                <petclinic:inputField label="Nombre" name="name"/>
                <petclinic:inputField label="Nick" name="nombreUsuario"/>
                <petclinic:inputField label="Email" name="email"/>
                <petclinic:checkboxField label="Administrador" name="administrador"/>
                <petclinic:inputField label="Foto de perfil" name="fotoPerfil"/>
                <petclinic:inputField label="Vieja Contraseña" name="contrasena"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-default" type="submit">Add Usuario</button>
            	</div>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
