<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="achievements">
    <h2>Achievements</h2>

    <table id="achievementsTable" class="table table-condensed">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Percentage</th>
            <th>Trophy</th>
            <th>BadgeImage</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${achievements}" var="achievement">
            <tr class="tabla">
                <td>
                    <c:out value="${achievement.name}"/>
                </td>
                <td>                    
                      <c:out value="${achievement.actualDescription} "/>                                        
                </td>
                
                <td>       
                    <c:out value="${achievement.percentage}% "/>
                </td>
                <td>       
                    <c:out value="${achievement.trophy} "/>
                </td>
                <td>                    
                    <c:if test="${achievement.badgeImage == ''}">none</c:if>
                    <c:if test="${achievement.badgeImage != ''}">
                        <img src="${achievement.badgeImage}" width="100px"  /> 
                    </c:if>
                </td>
                 <td>
				<a href="/statistics/achievements/admin/${achievement.name}/edit" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>
				&nbsp;
				<a href="/statistics/achievements/admin/${achievement.id}/delete"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
     <a class="btn btn-default" href="/statistics/achievements/admin/new">Create achievement</a>
    <c:if test="${pages.size() > 1}">
     <div class="row" id="pagination-disposition">
		<div class="col-md-6">
			<nav class="nav" id="pagination-disposition">
				<ul class="pagination">
					<c:if test="${prev != 0}">
						<li class="page-item">
							<a class="page-link" href="/statistics/achievements/admin?page=${prev}">Previous</a>
						</li>
					</c:if>
					<c:forEach items="${pages}" var="page">
						<c:choose>
						 <c:when test="${current == page}">
						  <li class="page-item active">
							<a class="page-link" href="/statistics/achievements/admin?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:when>
						 <c:otherwise>
						  <li class="page-item">
							<a class="page-link" href="/statistics/achievements/admin?page=${page}"><span>${page}</span></a>
					   	  </li>
						 </c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${current != last}">
						<li class="page-item">
							<a class="page-link" href="/statistics/achievements/admin?page=${next}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div> 
		   
    </div>
    </c:if>
</dobble:layout>