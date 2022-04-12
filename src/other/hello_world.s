    .globl    _main                 # define main

_main:
    push     %rbp                   # push onto stack
    lea      message(%rip), %rdi    # load address of message
    call     _printf                # call printf()
    pop      %rbp                   # pop off stack
    ret                             # return

message:
    .string    "Hello world!"       # define string literal
