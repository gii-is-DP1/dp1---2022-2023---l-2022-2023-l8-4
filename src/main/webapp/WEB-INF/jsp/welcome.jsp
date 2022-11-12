<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
	<img src="/resources/images/logoPNG_3.png">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
    <h2>Project ${title}</h2>
    <p><h2>Group ${group}</h2></p>
    <p><ul>
    <c:forEach items="${people}" var = "person">
    	<li>${person.firstName}  ${person.lastName}</li>
    </c:forEach>
    </ul>
    <p/>
    </div>
    <div class="col-md-12">
            <spring:url value="/resources/images/logo.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}" width='25%'/>
    </div>
</petclinic:layout>
