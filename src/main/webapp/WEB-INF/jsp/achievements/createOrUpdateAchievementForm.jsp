<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="achievements">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#acquireDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${achievement['new']}">New </c:if> Achievement
        </h2>
        <form:form modelAttribute="achievement"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${achievement.id}"/>
            <div class="form-group has-feedback">                
                <dobble:inputField label="Name" name="name"/>
                <dobble:inputField label="Description" name="description"/>
                <dobble:inputField label="Badge" name="badgeImage"/>
                <dobble:inputField label="Threshold" name="threshold"/>
                <dobble:inputField label="Percentage" name="percentage"/>
                <dobble:inputField label="Achievement Throphy" name="trophy"/>
        
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${achievement['new']}">
                            <button class="btn btn-default" type="submit">Add Achievement</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Achievement</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>       
    </jsp:body>
</dobble:layout>