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
        
        <c:forEach items="${usuarios}" var="usuarios">
            <tr>
                <td>
                    <c:out value="${usuarios.name}"/>
                </td>
                <td>                    
                      <c:out value="${usuarios.contraseña} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuarios.fechaRegistro} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuarios.fechaModificacion} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuarios.ultimoInicioSesion} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuarios.nombreUsuario} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuarios.email} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuarios.administrador} "/>                                        
                </td>
                <td>                    
                      <c:out value="${usuarios.fechaNacimiento} "/>                                        
                </td>
                <td>                    
                    <c:if test="${achievement.fotoPerfil == ''}">none</c:if>
                    <c:if test="${achievement.fotoPerfil != ''}">
                        <img src="resources/images/${usuarios.fotoPerfil}" width="100px"  /> 
                    </c:if>
                </td>
                <td>
				<a href="/usuarios/edit/${usuario.id}" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>
				&nbsp;
				<a href="/usuarios/delete/${usuario}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/usuarios/create">Create new usuario</a>

</petclinic:layout>