// mod secondary;
mod structures;
use std::env;

fn main() {
    say_hello();
    structures::run();
    // secondary::run();

    let args: Vec<String> = env::args().collect();
    if args.len() > 1 {
        let input = args[1].clone();
        println!("You passed the argument {input}");
    }
}

fn say_hello() -> i8 {
    println!("Hello!");
    1
}

#[test]
fn test_hello() {
    assert_eq!(say_hello(), 1);
}
