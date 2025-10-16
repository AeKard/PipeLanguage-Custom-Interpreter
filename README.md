# Java Custom Interpreter: PIPE Language

## Getting Started
<p>The **PIPE meme language**, a practice programming language interpreter using java <br>
This project made for final project on Programming Languages.</p>

### Reserved Keywords
**Variable Decleration**
- `let` -> `tap`
- `const` -> `sealed`

**Control Structure**
- `if` -> `pipe`
- `elif` -> `branch`
- `else` -> `drain`

**CLI (Command Line I/O)**
- `cout` -> `flow`

**Repetition**
- `while` -> `cycle`
- `break` -> `clog` 
- `continue` -> `flush` 

**Function**
- `def` -> `faucet` 
- `return` -> `spill` 

**Reserved Word**
- `true` 
- `false` 
- `null` 
- `and`
- `or`
---

### Interpreter Diagram
<img src="./img/level1.png" alt="Interpreter Diagram" width="800">


### Compile
To make changes edit `source.mpipe` 

**Sample Code**
```bash
faucet multiplicationTable(l, r)
{
    cycle(l < 5)
    {
        r = 0;
        cycle(r < 5){
            r = r + 1;
            flow("l:", l, " * ","r:", r, " = ", l*r);
        }
        l = l + 1;
        flow("l :",l);
    }
}
faucet else_if_return_test(x, y) 
{
    pipe(x > y) 
    {
        spill x - y;
    } 
    branch (x == y) 
    { 
        spill x * y;
    } 
    drain 
    {
        spill;
    }
}
multiplicationTable(1,1);
flow(else_if_return_test(5,5));
```

Compile all Java files into the `out` folder:
```bash
javac -d out *.java
```
### Run
To run the interpreter from `out` folder. If no target file default to source.mpipe:
```bash
java -cp out Main
java -cp out Main "filename.mpipe"
```