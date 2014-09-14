package org.flavio.fiuba.pl0

class Scanner {

	StreamTokenizer tokenizer
	TokenEvaluator evaluator
	Symbol symbol

	Scanner(Reader reader) {
		tokenizer = new StreamTokenizer(reader)
		tokenizer.lowerCaseMode(true)
		int dot = '.' as char	
		int slash = '/' as char
		tokenizer.ordinaryChar(dot)
		tokenizer.ordinaryChar(slash)
		evaluator = new TokenEvaluator()
	}

	Symbol next() {
		def token = tokenizer.nextToken();
		if (token == StreamTokenizer.TT_WORD) {
			symbol = evaluator.evaluate(tokenizer.sval)
		} else if (token == StreamTokenizer.TT_NUMBER) {
			processNumber()
		} else {
			def tokenStr = (token as char) as String
			SymbolType symbolType = evaluator.findSymbolType(tokenStr)
			if(symbolType == SymbolType.COLON) {
				processColon()
			} else if(symbolType == SymbolType.LT) {
				processLT()
			} else if(symbolType == SymbolType.GT) {
				processGT()
			} else {
				symbol = evaluator.evaluate(tokenStr)
			}
		}
	}
	
	private void processColon(){
		def tokenStr = (tokenizer.nextToken() as char) as String
		SymbolType symbolType = evaluator.findSymbolType(tokenStr)
		if(symbolType == SymbolType.EQ) {
			symbol = evaluator.createSymbol(SymbolType.ASSIGN)
		} else {
			symbol = evaluator.createError(SymbolType.COLON.pattern+tokenStr)
			tokenizer.pushBack()
		}	
	}
	
	private void processLT(){
		def tokenStr = (tokenizer.nextToken() as char) as String
		SymbolType symbolType = evaluator.findSymbolType(tokenStr)
		if(symbolType == SymbolType.GT) {
			symbol = evaluator.createSymbol(SymbolType.NE)
		} else if (symbolType == SymbolType.EQ) {
			symbol =  evaluator.createSymbol(SymbolType.LE)
		} else {
			symbol =  evaluator.createSymbol(SymbolType.LT)
			tokenizer.pushBack()
		}
	}
	
	private void processGT(){
		def tokenStr = (tokenizer.nextToken() as char) as String
		SymbolType symbolType = evaluator.findSymbolType(tokenStr)
		if (symbolType == SymbolType.EQ) {
			symbol = evaluator.createSymbol(SymbolType.GE)
		} else {
			symbol = evaluator.createSymbol(SymbolType.GT)
			tokenizer.pushBack()
		}
	}
	
	private void processNumber() {
		Double value = tokenizer.nval
		if(value == (long) value) {
			symbol = evaluator.evaluate((value as long) as String)
		} else {
			symbol = evaluator.createError(value as String)
			tokenizer.pushBack()
		}
	}
	
}
