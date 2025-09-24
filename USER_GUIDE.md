# 👥 Theorem Prover User Guide

A comprehensive guide for end-users to get started with the theorem prover.

## 📋 Table of Contents

- [Quick Start](#-quick-start)
- [Installation](#-installation)
- [Basic Usage](#-basic-usage)
- [Propositional Logic](#-propositional-logic)
- [Predicate Logic](#-predicate-logic)
- [Examples](#-examples)
- [Troubleshooting](#-troubleshooting)
- [FAQ](#-faq)

## 🚀 Quick Start

### What is the Theorem Prover?

The Theorem Prover is a tool that can automatically verify whether logical formulas are valid theorems. It supports both propositional logic and predicate logic.

### Prerequisites

- **Java 8 or higher** must be installed on your system
- Basic understanding of logical notation

### Fastest Way to Start

1. **Download** `theorem-prover.jar` (~7KB) from GitHub releases
2. **Run** with Java: `java -jar theorem-prover.jar`
3. **Follow** the on-screen instructions

## 💻 Installation

### 🎯 **RECOMMENDED: All Systems (Cross-Platform)**

1. **Download** `theorem-prover.jar` (~7KB) from GitHub releases
2. **Ensure Java 8+** is installed on your system
3. **Run**: `java -jar theorem-prover.jar`
4. **That's it!** Works on Windows, Linux, macOS, and any Java system

## 🎯 Basic Usage

### Starting the Program

```
========================================
   THEOREM PROVER - DISTRIBUTION VERSION
========================================

WELCOME! This program can prove theorems in:
  * Propositional Logic
  * Predicate Logic

Type '0' at any time to exit.
========================================

Choose the type of logic to work with:
  A) Propositional Logic
  B) Predicate Logic
  H) Show help and syntax guide
  0) Exit the program

Enter your choice (A/B/H/0):
```

### Navigation

- **A** - Work with propositional logic
- **B** - Work with predicate logic
- **H** - Show help and examples
- **0** - Exit the program

### Formula Input

- Always end formulas with a period (`.`)
- Use parentheses to clarify precedence
- Check syntax carefully before submitting

## 🔗 Propositional Logic

### Symbols and Syntax

| Symbol        | Meaning             | Example    |
| ------------- | ------------------- | ---------- |
| `P`, `Q`, `R` | Variables           | `P`        |
| `!`           | NOT (negation)      | `!P`       |
| `&`           | AND (conjunction)   | `P & Q`    |
| `\|`          | OR (disjunction)    | `P \| Q`   |
| `=>`          | IMPLIES             | `P => Q`   |
| `<=>`         | IFF (biconditional) | `P <=> Q`  |
| `(`, `)`      | Parentheses         | `(P => Q)` |
| `.`           | End of formula      | `P => P.`  |

### Operator Precedence (from highest to lowest)

1. `!` (NOT)
2. `&` (AND)
3. `\|` (OR)
4. `=>` (IMPLIES)
5. `<=>` (IFF)

### Examples

```
Simple tautology: P => P.
Law of non-contradiction: !(P & !P).
Transitivity: (P => Q) & (Q => R) => (P => R).
Modus ponens: P & (P => Q) => Q.
De Morgan's law: !(P & Q) => (!P | !Q).
```

## 🔍 Predicate Logic

### Symbols and Syntax

| Symbol        | Meaning                      | Example    |
| ------------- | ---------------------------- | ---------- |
| `x`, `y`, `z` | Variables                    | `x`        |
| `a`, `b`, `c` | Constants                    | `a`        |
| `P`, `Q`, `R` | Predicates                   | `P(x)`     |
| `A`           | Universal quantifier (∀)     | `Ax P(x)`  |
| `E`           | Existential quantifier (∃)   | `Ex P(x)`  |
| `(`, `)`      | Function/predicate arguments | `P(x, y)`  |
| `.`           | End of formula               | `Ax P(x).` |

### Quantifier Rules

- **Universal (A)**: "For all x, P(x)" - `Ax P(x)`
- **Existential (E)**: "There exists x such that P(x)" - `Ex P(x)`

### Examples

```
Universal quantification: Ax P(x).
Existential quantification: Ex P(x).
Implication with quantifiers: Ax (P(x) => Q(x)).
Complex formula: Ax (P(x) => Q(x)) & P(a) => Q(a).
```

## 📚 Examples

### Propositional Logic Examples

#### Example 1: Simple Tautology

```
Input: P => P.
Output: *** THEOREM PROVEN! ***
        The formula is a valid theorem.
```

#### Example 2: Transitivity Law

```
Input: (P => Q) & (Q => R) => (P => R).
Output: *** THEOREM PROVEN! ***
        The formula is a valid theorem.
```

#### Example 3: Non-Theorem

```
Input: P => Q.
Output: *** NOT A THEOREM ***
        The formula is not a theorem.
```

#### Example 4: De Morgan's Law

```
Input: !(P & Q) => (!P | !Q).
Output: *** THEOREM PROVEN! ***
        The formula is a valid theorem.
```

### Predicate Logic Examples

#### Example 1: Universal Quantification

```
Input: Ax P(x).
Output: *** FORMULA VALIDATED ***
        The formula syntax is correct.
```

#### Example 2: Existential Quantification

```
Input: Ex P(x).
Output: *** FORMULA VALIDATED ***
        The formula syntax is correct.
```

#### Example 3: Complex Predicate Formula

```
Input: Ax (P(x) => Q(x)) & P(a) => Q(a).
Output: *** FORMULA VALIDATED ***
        The formula syntax is correct.
```

## 🛠️ Troubleshooting

### Common Issues

#### "Java is not installed"

**Problem**: Program won't start, Java error message
**Solution**:

- Windows: Download from [Oracle](https://www.oracle.com/java/technologies/downloads/)
- Linux: `sudo apt-get install openjdk-11-jdk`
- macOS: `brew install openjdk@11`

#### "Compilation failed"

**Problem**: Error when running batch/script files
**Solution**:

- Ensure Java 8+ is installed
- Check file permissions (Linux/macOS)
- Verify all files are present

#### "Parsing error"

**Problem**: Formula input causes parsing error
**Solution**:

- Check syntax carefully
- Ensure formula ends with period (`.`)
- Use proper parentheses
- Verify symbol usage

#### "Invalid input"

**Problem**: Program doesn't recognize menu choice
**Solution**:

- Use exact letters: A, B, H, 0
- Case doesn't matter (A or a works)
- No spaces or extra characters

### Error Messages

#### `*** PARSING ERROR ***`

- **Cause**: Invalid formula syntax
- **Fix**: Check syntax rules, ensure proper parentheses

#### `*** SYNTAX ERROR ***`

- **Cause**: Predicate logic syntax incorrect
- **Fix**: Verify quantifier usage and variable names

#### `*** ERROR ***`

- **Cause**: General processing error
- **Fix**: Check formula complexity, try simpler formula

## ❓ FAQ

### Q: What is a theorem?

**A**: A theorem is a logical statement that is always true, regardless of the truth values of its variables. For example, `P => P` is always true.

### Q: Why does my formula show "NOT A THEOREM"?

**A**: Not all logical statements are theorems. Only statements that are always true (tautologies) are theorems. For example, `P => Q` is not always true.

### Q: Can I use lowercase variables in propositional logic?

**A**: No, propositional logic uses uppercase variables (P, Q, R, etc.). Lowercase is reserved for predicate logic variables.

### Q: What's the difference between propositional and predicate logic?

**A**:

- **Propositional logic**: Works with simple statements (P, Q, R)
- **Predicate logic**: Works with statements about objects (P(x), Q(x,y))

### Q: Why does predicate logic only show "FORMULA VALIDATED"?

**A**: Full theorem proving for predicate logic is complex and currently in development. The program validates syntax but doesn't prove theorems yet.

### Q: Can I save my formulas?

**A**: Currently, the program doesn't have save functionality. You can copy formulas from the output for later use.

### Q: What's the maximum formula complexity?

**A**: The program can handle moderately complex formulas. Very large formulas may cause performance issues or timeouts.

### Q: Is there a web version?

**A**: Currently, the program runs locally. A web version is planned for future releases.

### Q: Can I contribute or report bugs?

**A**: Yes! Visit the GitHub repository to report issues or contribute improvements.

## 🎯 Tips for Success

### Writing Good Formulas

1. **Start simple**: Begin with basic formulas like `P => P`
2. **Use parentheses**: Clarify precedence with `(P => Q) & (Q => R)`
3. **Check syntax**: Always end with period (`.`)
4. **Test incrementally**: Build complex formulas step by step

### Understanding Results

1. **THEOREM PROVEN**: Formula is always true
2. **NOT A THEOREM**: Formula is not always true
3. **FORMULA VALIDATED**: Syntax is correct (predicate logic)
4. **PARSING ERROR**: Syntax is incorrect

### Learning Logic

1. **Study examples**: Try the provided examples first
2. **Practice basics**: Master simple formulas before complex ones
3. **Read help**: Use the built-in help (option H)
4. **Experiment**: Try different combinations and see results

---

## 🎉 Getting Started

1. **Install** the theorem prover for your system
2. **Run** the program
3. **Try** the examples in this guide
4. **Experiment** with your own formulas
5. **Learn** from the results

**Happy theorem proving!** 🚀

For more advanced usage, see the [Testing Guide](TESTING_GUIDE.md) and [Deployment Guide](DEPLOYMENT_GUIDE.md).
