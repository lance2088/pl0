package org.flavio.fiuba.pl0

class TokenEvaluator {

	List<SymbolType> symbols;
	
	TokenEvaluator () {
		symbols = SymbolType.values().sort{a,b -> a.precedence.compareTo(b.precedence)}
	}
	
	SymbolType findSymbolType(String token) {
		return symbols.find{symbol -> symbol.match(token)}
	}
	
	Symbol evaluate(String token) {
		SymbolType type = findSymbolType(token)
		if (type.regex) {
			return new Symbol(type,token)
		}
		return new Symbol(type,type.pattern.toUpperCase())
	}
	
	Symbol createError(String token) {
		return new Symbol(SymbolType.ERROR, token);
	}
	
	Symbol createSymbol(SymbolType symbolType) {
		return new Symbol(symbolType, symbolType.pattern);
	}
	
}
