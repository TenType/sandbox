export class CustomError extends Error {
	constructor(message: string, name?: string) {
		super(message);
		if (name) {
			this.name = name;
		} else {
			this.name = 'CustomError';
		}
	}
}