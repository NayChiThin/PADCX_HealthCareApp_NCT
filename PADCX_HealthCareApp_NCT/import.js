const firestoreService = require('firestore-export-import');
const firebaseConfig = require('./config.js');
const serviceAccount = require('./serviceAccount.json');

// 	json to firestore
const jsonToFirestore = async() => {
	try {
		console.log("Initializing firebase...");
		await firestoreService.initializeApp(serviceAccount,firestoreService.databaseUrl);
		console.log('Firbase Initialized.');

		await firestoreService.restore('./care2.json');
		console.log('Import Success')
	}catch(error) {
		console.log(error)
	}
}

jsonToFirestore();