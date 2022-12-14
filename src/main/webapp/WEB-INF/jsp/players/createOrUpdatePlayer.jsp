<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="players">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${player['new']}">New </c:if> Player
        </h2>
        <form:form modelAttribute="player"
                   class="form-horizontal">
            <div class="form-group has-feedback">                
                <dobble:inputField label="Username" name="user.username"/>
                <dobble:inputField label="Password" name="user.password"/>
                <dobble:inputField label="Email" name="email"/>
                <dobble:inputField label="BirthDate" name="birthDate"/>
                <dobble:inputField label="Profile Picture" name="profilePicture"/>
        
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${player['new']}">
                            <button class="btn btn-default" type="submit">Add Player</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Player</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>       
    </jsp:body>
</dobble:layout>