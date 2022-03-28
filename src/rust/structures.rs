struct Animal {
	hunger: u8,
	age: u8,
}

impl Animal {
	fn new(hunger: u8, age: u8) -> Self {
		Self { hunger, age }
	}

	fn stats(self) -> String {
		format!("Animal has {} hunger and is {} years old", self.hunger, self.age)
	}
}

#[allow(dead_code)]
enum TT {
	ADD,
	SUB,
	MUL,
	DIV
}

fn parse(token: TT) {
	match token {
		TT::ADD => println!("Adding two numbers"),
		TT::SUB => println!("Subtracting two numbers"),
		TT::MUL => println!("Multiplying two numbers"),
		TT::DIV => println!("Dividing two numbers"),
	}
}

pub fn run() {
	let mut arr = [1, 2, 3, 4];

	for i in arr.iter_mut() {
		*i *= 2;
	}

	println!("arr {:?}", arr);

	let animal = Animal::new(0, 5);
	println!("{}", animal.stats());

	let token = TT::ADD;
	parse(token);
}