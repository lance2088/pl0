package org.flavio.fiuba.pl0

import groovy.transform.Canonical;

@Canonical
class Scanner {

	String source;
	
	Symbol next() {
		return new Symbol("CONST", SymbolType.CONST);
	}
	
}
