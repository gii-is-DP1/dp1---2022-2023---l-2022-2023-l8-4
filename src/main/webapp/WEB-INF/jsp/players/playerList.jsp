<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <h2>Players</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Register_Date</th>
            <th>Modification_Date</th>
            <th>Last_Login</th>
            <th>Email</th>
            <th>BirthDate</th>
            <th>Profile_Picture</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${player}" var="player">
            <tr>
                <td>
                    <c:out value="${player.user.username}"/>
                </td>
                <td>                    
                      <c:out value="${player.user.password} "/>                                        
                </td>
                <td>                    
                      <c:out value="${player.registerDate} "/>                                        
                </td>
                <td>                    
                      <c:out value="${player.modificationDate} "/>                                        
                </td>
                <td>                    
                      <c:out value="${player.lastLogin} "/>                                        
                </td>
                <td>                    
                      <c:out value="${player.email} "/>                                        
                </td>
                <td>                    
                      <c:out value="${player.birthDate} "/>                                        
                </td>
                <td>                    
                    <c:if test="${jugador.profilePicture == ''}">none</c:if>
                    <c:if test="${jugador.profilePicture != ''}">
                        <img src="resources/images/${player.profilePicture}" width="100px"  /> 
                    </c:if>
                </td>
                <td>
				<a href="/players/edit/${player.id}" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>
				&nbsp;
				<a href="/players/delete/${player.id}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/players/create">Create new player</a>

</petclinic:layout>