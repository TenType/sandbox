use std::io::{stdin};

pub fn get_input() {
    let mut input = String::new();
    stdin().read_line(&mut input).expect("Did not enter a correct string");
    println!("You entered {input}");

    let arg = match input.trim_end() {
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

fn block(mode: bool) -> Result<(), &'static str> {
    let res = statement(mode)?;

    println!("success: {}", res);
    Ok(())
}

fn statement(mode: bool) -> Result<&'static str, &'static str> {
    if mode {
        Ok("good")
    } else {
        Err("bad")
    }
}