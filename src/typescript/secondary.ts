class Logger {
    constructor(
        private type: 'error' | 'warn',
        private name: string,
        private details: string,
    ) {
    }
}


class BaseError extends Logger {
    // private name: string;
    constructor(
        name: string,
        details: string,
    ) {
        super('error', name, details);
        console.log('details BaseError', details);
    }

    static emit(details: string): never {
        // console.log(this.constructor.name);
        console.log('details emit', details);
        throw new this(this.name, details);
    }
}

class InvalidSyntaxError extends BaseError {
    constructor(
        details: string,
    ) {
        super('invalid syntax', details);
        console.log('details InvalidSyntaxError', details);
    }
}
try {
    InvalidSyntaxError.emit('missing semicolon');
} catch (error) {
    if (error instanceof InvalidSyntaxError) {
        console.log(error);
    } else if (error instanceof BaseError) {
        console.log('base error');
    } else {
        console.log('not an error?');
    }
}

// console.log(new InvalidSyntaxError('bad indentation'));
// console.log(new BaseError('Unrecognized input', 'input is not in range'));