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
            <th>Acquire Date</th>
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
                    <c:out value="${achievement.percentage} "/>
                </td>
                <td>       
                    <c:out value="${achievement.trophy} "/>
                </td>
                <td>       
                    <c:out value="${achievement.acquireDate} "/>
                </td>
                <td>                    
                    <c:if test="${achievement.badgeImage == ''}">none</c:if>
                    <c:if test="${achievement.badgeImage != ''}">
                        <img src="resources/images/logros/${achievement.badgeImage}" width="100px"  /> 
                    </c:if>
                </td>
                <td>
				<a href="/statistics/achievements/${achievement.id}/edit" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>
				&nbsp;
				<a href="/statistics/achievements/${achievement.id}/delete"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/statistics/achievements/new">Create new achievement</a>

</dobble:layout>