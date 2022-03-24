class Primary {
	constructor(
		private type: 'error' | 'warn',
		protected title: string,
		protected details: string,
	) {
		console.log('primary', details);
	}
}

type SecondaryParam = { details: string, b?: number };
class Secondary extends Primary {
	constructor(title: string, details: string) {
		super('error', title, details);
		console.log('secondary', details);
	}

	static emit({ details, b }: SecondaryParam): never {
		const error = new this(details, null);
		throw error;
	}
}

type TertiaryParam = { details: string, b: number };
class Tertiary extends Secondary {
	// protected static title = 'Invalid syntax';
	constructor(details: string) {
		super('invalid syntax', details);
		console.log('tertiary', details);
	}

	static emit({ details, b }: TertiaryParam) {
		return super.emit({ details, b });
	}
}

try {
	Tertiary.emit({ details: 'an error occurred!', b: 5 });
	console.log('hello');
} catch (error) {
	if (error instanceof Primary) {
		console.log(error);
	} else {
		throw error;
	}
}