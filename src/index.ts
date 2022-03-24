import readline from 'readline';

const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});


rl.question('Hi!', (reply) => {
	console.log(`You replied with ${reply}`);
});