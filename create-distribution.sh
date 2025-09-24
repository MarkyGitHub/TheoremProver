#!/bin/bash

# Creating Theorem Prover Distribution Package
# Author: Mark Schlichtmann
# Version: 2.0

echo ""
echo "========================================"
echo "   CREATING DISTRIBUTION PACKAGE"
echo "========================================"
echo ""

# Create distribution directory
mkdir -p distribution/{windows,linux,cross-platform,docs}

echo "Step 1: Compiling core application..."
javac -encoding UTF-8 -d . -cp . src/inputoutput/*.java src/predicate/common/*.java src/predicate/scanner/*.java src/predicate/parser/*.java src/predicate/resolution/*.java src/propositional/scanner/*.java src/propositional/common/*.java src/propositional/parser/*.java src/propositional/resolution/*.java src/propositional/sequent/*.java src/ProPreTP.java

if [ $? -ne 0 ]; then
    echo "ERROR: Core compilation failed"
    exit 1
fi

echo "Step 2: Compiling distribution version..."
javac -encoding UTF-8 -d . -cp . ProPreTP-Distribution.java

if [ $? -ne 0 ]; then
    echo "ERROR: Distribution compilation failed"
    exit 1
fi

echo "Step 3: Creating Windows package..."
cp theorem-prover.bat distribution/windows/
cp ProPreTP_Distribution.class distribution/windows/
cp -r src distribution/windows/
cp *.class distribution/windows/ 2>/dev/null || true
cp README.md distribution/windows/
cp TESTING_GUIDE.md distribution/windows/
cp DEPLOYMENT_GUIDE.md distribution/windows/

echo "Step 4: Creating Linux package..."
cp theorem-prover.sh distribution/linux/
cp ProPreTP_Distribution.class distribution/linux/
cp -r src distribution/linux/
cp *.class distribution/linux/ 2>/dev/null || true
cp README.md distribution/linux/
cp TESTING_GUIDE.md distribution/linux/
cp DEPLOYMENT_GUIDE.md distribution/linux/

echo "Step 5: Creating cross-platform JAR..."
cd distribution/cross-platform
cp -r ../windows/* . 2>/dev/null || true

# Create manifest for JAR
cat > MANIFEST.MF << EOF
Main-Class: ProPreTP_Distribution
Class-Path: .
EOF

# Create JAR file
jar cfm theorem-prover.jar MANIFEST.MF *.class src/* inputoutput/* predicate/* propositional/* 2>/dev/null || true

cd ../..

echo "Step 6: Copying documentation..."
cp README.md distribution/docs/
cp TESTING_GUIDE.md distribution/docs/
cp DEPLOYMENT_GUIDE.md distribution/docs/

echo "Step 7: Creating user instructions..."
cat > distribution/USER_INSTRUCTIONS.md << 'EOF'
# Theorem Prover - User Instructions

## Windows Users
1. Ensure Java 8+ is installed
2. Run: theorem-prover.bat

## Linux Users
1. Install Java: sudo apt-get install openjdk-11-jdk
2. Make executable: chmod +x theorem-prover.sh
3. Run: ./theorem-prover.sh

## Cross-Platform Users
1. Ensure Java 8+ is installed
2. Run: java -jar theorem-prover.jar

## Examples
### Propositional Logic
- P => P.
- (P => Q) & (Q => R) => (P => R).
- !(P & Q) => (!P | !Q).

### Predicate Logic
- Ax P(x).
- Ax (P(x) => Q(x)).
- Ex (P(x) & Q(x)).
EOF

echo ""
echo "========================================"
echo "   DISTRIBUTION PACKAGE CREATED!"
echo "========================================"
echo ""
echo "Package contents:"
echo "  distribution/windows/     - Windows executable and files"
echo "  distribution/linux/       - Linux executable and files"
echo "  distribution/cross-platform/ - Cross-platform JAR file"
echo "  distribution/docs/        - Documentation files"
echo ""
echo "Ready for distribution!"
echo ""
