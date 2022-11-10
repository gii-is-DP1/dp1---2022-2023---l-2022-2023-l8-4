<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <h2>Jugadores</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre_Usuario</th>
            <th>Contraseña</th>
            <th>Fecha_Registro</th>
            <th>Fecha_Modificacion</th>
            <th>Fecha_Ultimo_Inicio_sesion</th>
            <th>Email</th>
            <th>Fecha_Nacimiento</th>
            <th>Foto_Perfil</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <td>
                    <c:out value="${jugador.user.username}"/>
                </td>
                <td>                    
                      <c:out value="${jugador.user.password} "/>                                        
                </td>
                <td>                    
                      <c:out value="${jugador.fechaRegistro} "/>                                        
                </td>
                <td>                    
                      <c:out value="${jugador.fechaModificacion} "/>                                        
                </td>
                <td>                    
                      <c:out value="${jugador.ultimoInicioSesion} "/>                                        
                </td>
                <td>                    
                      <c:out value="${jugador.email} "/>                                        
                </td>
                <td>                    
                      <c:out value="${jugador.fechaNacimiento} "/>                                        
                </td>
                <td>                    
                    <c:if test="${jugador.fotoPerfil == ''}">none</c:if>
                    <c:if test="${jugador.fotoPerfil != ''}">
                        <img src="resources/images/${jugador.fotoPerfil}" width="100px"  /> 
                    </c:if>
                </td>
                <td>
				<a href="/jugadores/edit/${usuario.id}" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>
				&nbsp;
				<a href="/jugadores/delete/${usuario.id}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/jugadores/create">Crear nuevo jugador</a>

</petclinic:layout>