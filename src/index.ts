import { CustomError, SimpleLang } from './functions';
import readline from 'readline';

const input: string[] = [];

const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

console.log('Input code');
rl.prompt();

rl.on('line', cmd => {
	input.push(cmd);
});

rl.on('close', () => {
	const program = new SimpleLang();
	const output = program.init(input.join('\n'));
	if (output) {
		console.log(String(output));
	} else {
		console.log('No output');
	}
	rl.close();
});