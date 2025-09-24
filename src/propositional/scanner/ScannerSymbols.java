package propositional.scanner;

public enum ScannerSymbols {
    LEFT_BRACKET('('), RIGHT_BRACKET(')'), OR('|'), NOT('!'), AND('&'), IFF_1('<'), EQUALS('='), DOT('.');
    private char symbol;

    ScannerSymbols(char aSymbol) {
        this.symbol = aSymbol;
    }

    public final char getSymbol() {
        return this.symbol;
    }

    public ScannerSymbols setSymbol(char aSymbol) {
        this.symbol = aSymbol;
        return this;
    }
}
