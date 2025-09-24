# First-Order Theorem Prover

A comprehensive Java implementation of a theorem prover for both propositional and predicate logic, originally developed as a CC301 Degree Project in 2005.

## 🎯 Features

- **Dual Logic Support**: Both propositional and first-order (predicate) logic
- **Resolution Theorem Proving**: Implements standard resolution rules
- **CNF Conversion**: Converts formulas to conjunctive normal form
- **Interactive Interface**: Command-line interface for formula input
- **Comprehensive Parsing**: Recursive descent parser with proper precedence handling
- **Variable Management**: Sophisticated handling of bound/free variables and quantifiers

## 🚀 Quick Start

### Prerequisites

- Java 8 or higher (tested with Java 21)

### Option 1: Download and Run JAR File (Recommended)

**Easiest way to get started!**

1. **Download** the pre-compiled JAR file: [`theorem-prover.jar`](https://github.com/MarkyGitHub/TheoremProver/releases/latest/download/theorem-prover.jar)
2. **Run** directly with Java:
   ```bash
   java -jar theorem-prover.jar
   ```

**That's it!** No compilation needed. The JAR file contains everything required to run the theorem prover.

### Option 2: Compile from Source

```bash
# Compile the project
javac -encoding Windows-1252 -d . -cp . src/inputoutput/*.java
javac -encoding Windows-1252 -d . -cp . src/predicate/common/*.java
javac -encoding Windows-1252 -d . -cp . src/predicate/scanner/*.java
javac -encoding Windows-1252 -d . -cp . src/predicate/parser/*.java src/predicate/resolution/*.java
javac -encoding Windows-1252 -d . -cp . src/propositional/scanner/*.java
javac -encoding Windows-1252 -d . -cp . src/propositional/common/*.java
javac -encoding Windows-1252 -d . -cp . src/propositional/parser/*.java
javac -encoding Windows-1252 -d . -cp . src/propositional/resolution/*.java src/propositional/sequent/*.java
javac -encoding Windows-1252 -d . -cp . src/ProPreTP.java
```

### Running the Theorem Prover

```bash
# Run the main program (if compiled from source)
java ProPreTP

# Run the JAR file (recommended)
java -jar theorem-prover.jar

# Test individual components
echo "P => P." | java propositional.resolution.ResolutionMethod
echo "P(x)." | java predicate.scanner.Scanner
```

## 📝 Symbol Conventions

### Propositional Logic

- **Variables**: `P`, `Q`, `R`, `S`, `T` (capital letters)
- **Connectives**:
  - `!` - NOT (negation)
  - `&` - AND (conjunction)
  - `|` - OR (disjunction)
  - `=>` - IMPLIES (implication)
  - `<=>` - IFF (biconditional)
- **Brackets**: `(` and `)`

### Predicate Logic

- **Variables**: `u`, `v`, `w`, `x`, `y`, `z` (+ numbers: `x1`, `x2`, etc.)
- **Constants**: `a`, `b`, `c`, `d`, `e` (+ numbers: `a1`, `a2`, etc.)
- **Functions**: `f`, `g`, `h`, `i`, `j`, `k`, `l`, `m`, `n`, `o`, `p`, `q`, `r`, `s`, `t` (+ numbers)
- **Predicates**: `F`, `G`, `H`, `I`, `J`, `K`, `L`, `M`, `N`, `O`, `P`, `Q`, `R`, `S`, `T` (+ numbers)
- **Quantifiers**:
  - `A` - Universal quantifier (∀)
  - `E` - Existential quantifier (∃)
- **Connectives**: Same as propositional logic
- **Function/Predicate Arguments**: Enclosed in parentheses

## 🔬 Architecture

### Core Components

1. **Lexical Analysis** (`scanner/`)

   - Tokenizes input strings into logical symbols
   - Handles precedence and symbol recognition

2. **Parsing** (`parser/`)

   - Recursive descent parser for propositional logic
   - Complex stack-based parser for predicate logic
   - Builds abstract syntax trees

3. **Semantic Representation** (`common/`)

   - Abstract `WFExpression` and `Formula` hierarchies
   - Specialized classes for different formula types

4. **Theorem Proving** (`resolution/`)

   - CNF conversion using uniform notation rules
   - Resolution theorem proving algorithm
   - Variable substitution and binding management

5. **User Interface** (`inputoutput/`)
   - Interactive command-line interface
   - Input validation and error handling

### Design Patterns

- **Abstract Factory**: Formula hierarchy creation
- **Strategy**: Different parsing strategies for propositional vs predicate logic
- **Template Method**: Common parsing structure
- **Visitor**: Resolution algorithm traversal

## 📊 Examples

### Running the JAR File

```bash
# Download and run the JAR file
java -jar theorem-prover.jar

# Example session:
# Choose: A (Propositional Logic)
# Enter formula: P => P.
# Result: *** THEOREM PROVEN! ***
```

### Propositional Logic Examples

```bash
# Tautology: P => P
Input: P => P.
Output: *** THEOREM PROVEN! ***

# Contradiction: P & !P
Input: P & !P.
Output: *** NOT A THEOREM ***

# Complex formula: (P => Q) & (Q => R) => (P => R)
Input: (P => Q) & (Q => R) => (P => R).
Output: *** THEOREM PROVEN! ***

# De Morgan's Law
Input: !(P & Q) => (!P | !Q).
Output: *** THEOREM PROVEN! ***
```

### Predicate Logic Examples

```bash
# Universal quantification: ∀x P(x)
Input: Ax P(x).
Output: *** FORMULA VALIDATED ***

# Existential quantification: ∃x P(x)
Input: Ex P(x).
Output: *** FORMULA VALIDATED ***

# Complex formula: ∀x (P(x) => Q(x))
Input: Ax (P(x) => Q(x)).
Output: *** FORMULA VALIDATED ***
```

## 📦 Download

### Ready-to-Run JAR File

**Get started immediately with our pre-compiled JAR file:**

- **Direct Download**: [`theorem-prover.jar`](https://github.com/MarkyGitHub/TheoremProver/releases/latest/download/theorem-prover.jar)
- **GitHub Releases**: [Latest Release](https://github.com/MarkyGitHub/TheoremProver/releases/latest)
- **Size**: ~7KB (lightweight and fast)
- **Requirements**: Java 8 or higher

### Quick Start with JAR

```bash
# 1. Download the JAR file
# 2. Run with Java
java -jar theorem-prover.jar

# That's it! No compilation needed.
```

## 🧪 Testing

The theorem prover has been tested with various logical formulas and demonstrates correct behavior for:

- ✅ Simple tautologies (`P => P`)
- ✅ Complex propositional formulas
- ✅ Basic predicate logic parsing
- ✅ CNF conversion
- ✅ Resolution theorem proving
- ✅ Cross-platform JAR file execution

## 🔧 Technical Details

### Algorithm Complexity

- **Parsing**: O(n) where n = input length
- **CNF Conversion**: O(2^n) worst case (exponential blowup)
- **Resolution**: O(n²) to O(n!) depending on formula structure

### Key Algorithms

- **Operator Precedence Parsing**: For propositional logic
- **Recursive Function Parsing**: For predicate logic functions
- **Uniform Notation Rules**: For CNF conversion
- **Standard Resolution**: For theorem proving

## 📚 Academic Background

This project was originally developed as part of a CC301 Degree Project in 2005 by Mark P Schlichtmann under the supervision of Dr Chris Fox at the Department of Computer Science. It demonstrates fundamental concepts in:

- Automated theorem proving
- Formal logic
- Compiler design principles
- Algorithm implementation

## 🤝 Contributing

This is an educational project showcasing theorem proving concepts. Contributions for improvements, bug fixes, or additional features are welcome!

## 📄 License

MIT License - see [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Original author: Mark P Schlichtmann
- Supervisor: Dr Chris Fox
- Department of Computer Science, 2005
