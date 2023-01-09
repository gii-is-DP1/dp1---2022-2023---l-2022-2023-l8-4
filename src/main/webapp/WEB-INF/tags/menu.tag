<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div>
		<div class="navbar-header">
			<a href="<c:url value="/" />">
				<img id="image" src="/resources/images/logo.png"/>
			</a>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">
			<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'players'}" url="/players?page=1"
					title="Players">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					<span>Players</span>
				</petclinic:menuItem>
			</sec:authorize>
				
				
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'achievements'}" url="/statistics/achievements"
					title="Achievements" dropdown="${true}">										
						<ul class="dropdown-menu">
							<li>
								<a href="<c:url value="/statistics/achievements" />">Achievements listing</a>
							</li>
							<li class="divider"></li>
						</ul>					
				</petclinic:menuItem>
				</sec:authorize>
				
				
	
				
				
				<sec:authorize access="hasAuthority('admin')">
				<petclinic:menuItem active="${name eq 'games'}" url="/games"
					title="Games" dropdown="${true}">										
						<ul class="dropdown-menu">
							<li>
								<a href="<c:url value="/games/finalized" />">Finalized Games</a>
							</li>
							<li class="divider"></li>
							<li>								
								<a href="<c:url value="/games/inProgress" />">In Progress Games <span class="glyphicon glyphicon-certificate" aria-hidden="true"></span></a>
							</li>
							</ul>					
				</petclinic:menuItem>
				</sec:authorize>
				
				
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'play'}" url="/games"
					title="Play" dropdown="${true}">										
						<ul class="dropdown-menu">
							
							<li>								
								<a href="<c:url value="/games/new" />">New Game <span class="glyphicon glyphicon-certificate" aria-hidden="true"></span></a>
							</li>
							<li>								
								<a href="<c:url value="/games/join" />">Join Game <span class="glyphicon glyphicon-certificate" aria-hidden="true"></span></a>
							</li>
						</ul>					
				</petclinic:menuItem>
				</sec:authorize>

			</ul>




			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong id="identify"><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a href="/logout"><span>Logout </span><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a></li>
							<li><a id="enlace" href="/players/data/"><span>My profile </span><span class="glyphicon glyphicon-cog" aria-hidden="true"></span></a></li>
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>

<script>

	const nombre=document.getElementById("identify").textContent;
	const enlace=document.getElementById("enlace");
	
	enlace.addEventListener('click', () => {
		enlace.href=enlace.href+nombre;
	})

</script>