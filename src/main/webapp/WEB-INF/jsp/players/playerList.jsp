<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <h2>Players</h2>

    <table id="playersTable" class="table table-condensed">
        <thead>
        <tr class="tabla">
            <th>Statistics</th>
            <th>Username</th>
            <th>Password</th>
            <th>Register_Date</th>
            <th>Modification_Date</th>
            <th>Last_Login</th>
            <th>Email</th>
            <th>BirthDate</th>
            <th>Profile_Picture</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
       
        <c:forEach items="${players}" var="player">
            <tr class="tabla">
                <td>
                    <a href="/players/data/${player.id}"><span class="glyphicon glyphicon glyphicon-duplicate warning" aria-hidden="true"></span></a>
                </td>
                <td>
                  	<c:out value="${player.user.username}"/>
                </td>
                <td>                    
                      <c:out value="${player.user.username} "/>
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
                    <c:if test="${player.profilePicture == ''}">none</c:if>
                    <c:if test="${player.profilePicture != ''}">
                        <img src="resources/images/${player.profilePicture}" width="100px"  /> 
                    </c:if>
                </td>
                <td>
				<a href="/players/edit/${player.id}" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>
				&nbsp;
				<a href="/players/delete/${player.id}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></span></a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${pages.size() > 1}">
     <div class="row" id="pagination-disposition">
		<div class="col-md-6">
			<nav class="nav" id="pagination-disposition">
				<ul class="pagination">
					<c:if test="${prev != 0}">
						<li class="page-item">
							<a class="page-link" href="/players?page=${prev}">Previous</a>
						</li>
					</c:if>
					<c:forEach items="${pages}" var="page">
						<c:choose>
						 <c:when test="${current == page}">
						  <li class="page-item active">
							<a class="page-link" href="/players?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:when>
						 <c:otherwise>
						  <li class="page-item">
							<a class="page-link" href="/players?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${current != last}">
						<li class="page-item">
							<a class="page-link" href="/players?page=${next}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>    
    </div>
    </c:if>
    <a class="btn btn-default" href="/players/new">Create new player</a>

</petclinic:layout>
