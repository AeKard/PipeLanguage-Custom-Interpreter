# Java Custom Interpreter: PIPE Language

## Getting Started
<p>The **PIPE meme language**, a practice programming language interpreter using java
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
- `cin` -> `source` ❌

**Repetition**
- `while` -> `cycle`
- `break` -> `clog` ❌
- `continue` -> `flush` ❌

**Function**
- `def` -> `faucet` ❌
- `return` -> `spill` ❌

**Reserved Word**
- `true` 
- `false` 
- `null` 

---

### Compile
To change the algorithm file, edit `source.mpipe`

To compile all Java files into the `out` folder:
```bash
javac -d out *.java
```
### Run
To run the interpreter from `out` folder:
```bash
java -cp out Main
```