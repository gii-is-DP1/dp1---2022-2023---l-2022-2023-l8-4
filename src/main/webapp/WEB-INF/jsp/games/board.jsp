<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">

<div id="middle-card" hidden>${card.icons}</div>
<div id="player-card" hidden>${playerCard.icons}</div>
<div id="middle-card-id" hidden>${cardId}</div>
<div id="main-player-id" hidden>${player}</div>
<div id="game-id" hidden>${game.id}</div>

    <ul class = "list-group">
    <c:forEach items="${players}" var="companion">
    <li class="list-group-item">
    			<img class = "profilePicture" src="${companion.player.profilePicture}"/>
    			<div class="alert alert-success" role="alert">
    				${companion.pointsNumber}
    			</div>
    			<div class="alert alert-primary" role="alert">
                    ${companion.player.user.username}
                </div>
    		</li>
    </c:forEach>
	</ul>
	<div class="card" style="width: 18rem; float: right;"> <!-- NO TOCAR -->
		<p>Central</p>
		<div class="card-body" id = "mycard">
		</div>
	</div>
	<div class="card" style="width: 18rem; float: right;"> <!-- NO TOCAR -->
		<p>My card</p>
		<div class="card-body" id = "middlecard">
		</div>
	</div>
<script>
		     function loadSymbols( className, stringOfSymbols , deskId )
		     {
			     const symbols = stringOfSymbols.split( " " );
			     for( let i = 0 ; i < symbols.length ; i += 2 )
			     {
				let img_figure_left = document.createElement( "img" );
				let img_figure_right = document.createElement( "img" );

				img_figure_left.setAttribute( "src", "/resources/images/animals/" +  symbols[i] + ".png" )
				img_figure_right.setAttribute( "src", "/resources/images/animals/" +  symbols[i+1] + ".png" )

				img_figure_left.setAttribute( "class", className );
				img_figure_left.setAttribute( "id", symbols[i] );
				img_figure_right.setAttribute( "class", className );
				img_figure_right.setAttribute( "id", symbols[i+1] );

				document.getElementById( deskId ).appendChild( img_figure_left );
				document.getElementById( deskId ).appendChild( img_figure_right );
			     }
		     }

		     // Load cards
		     loadSymbols( "own_symbols", document.getElementById( "player-card" ).innerHTML, "mycard" ); // El listado de nombres debe de sustituirse por la cadena de figuras que venga del backend
		     loadSymbols( "middle_symbols", document.getElementById( "middle-card" ).innerHTML, "middlecard" ); // Idem con las cartas del backend


		     // Onclick event for each symbol of a card
		     Array.from( document.getElementsByClassName( "own_symbols" ) )
		     .forEach( ( symbol ) => {
			     symbol.addEventListener( "click", () =>
			     {
				     const figure = symbol.getAttribute( "id" );
				     const middleCard = document.getElementsByClassName( "middle_symbols" );
				     for ( let j = 0 ; j < middleCard.length ; j++ )
				     {
					     if ( figure == middleCard[j].getAttribute( "id" ) )
					     {
		     				     const gameId = document.getElementById( "game-id" ).innerHTML;
		     				     const playerId = document.getElementById( "main-player-id" ).innerHTML;
		     				     const middleCardId = document.getElementById( "middle-card-id" ).innerHTML;
						         console.log( "Elemento que machea es el " + figure );
		     				     window.location.replace(`localhost:8080/games/${gameId}/${playerId}/${middleCardId}`);
					     }
				     }
				}
				     )
			     }
		     )
</script>
</petclinic:layout>
