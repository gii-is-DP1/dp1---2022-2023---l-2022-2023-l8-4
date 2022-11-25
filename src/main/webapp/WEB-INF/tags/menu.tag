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
				
				<petclinic:menuItem active="${name eq 'players'}" url="/players"
					title="Players">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					<span>Players</span>
				</petclinic:menuItem>
				<petclinic:menuItem active="${name eq 'achievements'}" url="/statistics/achievements"
					title="Achievements" dropdown="${true}">										
						<ul class="dropdown-menu">
							<li>
								<a href="<c:url value="/statistics/achievements" />">Achievements listing</a>
							</li>
							<li class="divider"></li>
							<li>							
								<a href="<c:url value="/players/{id}/achievements" />">My Achievements <span class="glyphicon glyphicon-certificate" aria-hidden="true"></span></a>
							</li>
						</ul>					
				</petclinic:menuItem>
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
							<li>								
								<a href="<c:url value="/games/new" />">New Game <span class="glyphicon glyphicon-certificate" aria-hidden="true"></span></a>
							</li>
							<li>								
								<a href="<c:url value="/games/join" />">Join Game <span class="glyphicon glyphicon-certificate" aria-hidden="true"></span></a>
							</li>
						</ul>					
				</petclinic:menuItem>

			</ul>




			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-xl-center">
												<span class="glyphicon glyphicon-log-out"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-center">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
