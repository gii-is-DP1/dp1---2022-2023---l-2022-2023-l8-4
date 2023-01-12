const fetchGameModeDescriptions = async () => {
	const url = 'http://localhost:8080/games/description';
	const response = await fetch(url);
	const descriptions = await response.json();
	return descriptions;
}

const handleGameModeEvent = async (event) => {
	const gameModeData = await fetchGameModeDescriptions();
	const gameMode = event.target.value;
	const endingTab = document.querySelector("a[href='#ending']");
	const endingTitle = gameModeData[gameMode].hasWinner ? 'Winner' : 'Loser'
	
	setup.innerHTML = gameModeData[gameMode].setup;
	objective.innerHTML = gameModeData[gameMode].objective;
	howto.innerHTML = gameModeData[gameMode].howToPlay;
	ending.innerHTML = gameModeData[gameMode].ending;
	endingTab.textContent = endingTitle;
};


const selectId = 'gameMode';
const rulesId = 'rules';

const selectGameModes = document.getElementById(selectId);
const setup = document.getElementById('setup');
const objective = document.getElementById('objective');
const howto = document.getElementById('howto');
const ending = document.getElementById('ending');
const rules = document.getElementById(rulesId);


selectGameModes.addEventListener('click', handleGameModeEvent);

