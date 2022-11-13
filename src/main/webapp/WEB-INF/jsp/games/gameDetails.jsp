<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>Game <c:out value="${game.id}"/></h2>

    <table id="gameDetailsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Game</th>
            <th>Players</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${game.players}" var="player">
            <tr>
            	<td>
                </td>
                <td>
                    <c:out value="${player.user.username}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
