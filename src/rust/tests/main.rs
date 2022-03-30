use std::fs;
use test_generator::test_resources;

#[test_resources("src/rust/tests/*/*.txt")]
fn run(resource: &str) {
    let contents = fs::read_to_string(resource).expect("Could not read test file");

    assert_eq!(contents, "1");
}
