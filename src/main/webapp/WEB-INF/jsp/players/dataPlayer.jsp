<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

<div class="card-group">
  <div class="card">
    <img class="card-img-top" src="resources/static/resources/images/${player.profilePicture}" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">Player <b>${player.username}</b> statistics </h5>
      <p class="card-text">Games played ${games.length}</p>
      <p class="card-text"><small class="text-muted">Last login ${player.lastLogin}</small></p>
    </div>
  </div>

</petclinic:layout>
