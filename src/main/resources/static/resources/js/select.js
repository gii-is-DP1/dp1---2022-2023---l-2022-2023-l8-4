const description = {
	'ESTANDAR': "Descripcion estandar",
	'EL_FOSO': "Descripcion foso",
	'LA_PATATA_CALIENTE': "Descripcion patata"
}

const selectId = 'gameMode';
const paragraphId = 'description';
const selectGameModes = document.getElementById(selectId);

const handleEvent = (event) => {
	const paragraph = document.getElementById(paragraphId);
	const gameMode = event.target.value;
	paragraph.textContent = gameMode;
};
	
selectGameModes.addEventListener('change', handleEvent);
