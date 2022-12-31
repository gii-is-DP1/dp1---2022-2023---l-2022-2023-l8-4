async function fetchGameModeDescriptions() {
	const url = 'http://localhost:8080/games/description';
	const response = await fetch(url);
	const descriptions = await response.json();
	return descriptions;
}


const selectId = 'gameMode';
const paragraphId = 'description';
const selectGameModes = document.getElementById(selectId);
let descriptions;
fetchGameModeDescriptions().then(data =>{ descriptions = data});

const handleEvent = (event) => {
	const paragraph = document.getElementById(paragraphId);
	const gameMode = event.target.value;
	paragraph.textContent = descriptions[gameMode];
};


selectGameModes.addEventListener('click', handleEvent);
