package org.flavio.fiuba.pl0

import groovy.transform.Immutable;
import groovy.transform.ToString;


@Immutable
@ToString(includePackage = false, includeNames = true)
class Symbol {

	SymbolType type;
	String value;
	
}
