fn main() {
	say_hello();
	let mut input = String::new();
	std::io::stdin().read_line(&mut input).expect("Did not enter a correct string");

	let arg = match input.as_str() {
		"true" => true,
		"false" => false,
		_ => {
			panic!("Input either 'true' or 'false'");
		}
	};

	match block(arg) {
		Ok(_) => (),
		Err(e) => {
			println!("error: {}", e);
		}
	}
}

fn say_hello() -> i8 {
	println!("Hello!");
	1
}

fn block<'a>(mode: bool) -> Result<(), &'a str> {
	let res = statement(mode)?;

	println!("success: {}", res);
	Ok(())
}

fn statement<'a>(mode: bool) -> Result<&'a str, &'a str> {
	if mode {
		Ok("good")
	} else {
		Err("bad")
	}
}