<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
    <h2>Usuarios</h2>

    <table id="usuariosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Contraseña</th>
            <th>Fecha_Registro</th>
            <th>Fecha_Modificacion</th>
            <th>Fecha_Ultimo_Inicio_sesion</th>
            <th>NombreUsuario</th>
            <th>Email</th>
            <th>Es_Administrador</th>
            <th>Fecha_Nacimiento</th>
            <th>Foto_Perfil</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${usuarios}" var="usuario">
            <tr>
                <td>
                    <c:out value="${usuario.name}"/>
                </td>
                <td>                    
                      <c:out value="${usuario.contraseña} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuario.fechaRegistro} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuario.fechaModificacion} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuario.ultimoInicioSesion} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuario.nombreUsuario} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuario.email} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuario.administrador} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuario.fechaNacimiento} "/>                                        
                </td>
                <td>                    
                    <c:if test="${usuario.fotoPerfil == ''}">none</c:if>
                    <c:if test="${usuario.fotoPerfil != ''}">
                        <img src="resources/images/${usuario.fotoPerfil}" width="100px"  /> 
                    </c:if>
                </td>
                <td>
				<a href="/usuarios/edit/${usuario.id}" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>
				&nbsp;
				<a href="/usuarios/delete/${usuario.id}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/usuarios/create">Create new usuario</a>

</petclinic:layout>