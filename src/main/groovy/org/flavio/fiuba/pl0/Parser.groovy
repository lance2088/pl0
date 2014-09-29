package org.flavio.fiuba.pl0

import static org.flavio.fiuba.pl0.SymbolType.*
import groovy.transform.TypeChecked;
import groovy.util.logging.Slf4j
import ch.qos.logback.core.rolling.helper.PeriodicityType

@Slf4j
@TypeChecked
class Parser {

	Scanner scanner;
	boolean error = false;

	Parser (Scanner scanner){
		this.scanner = scanner
	}

	void scan() {
		log.debug('PROGRAM')
		scanner.next()
		block()
		expect(PERIOD)
	}

	boolean accept(SymbolType symbolType){
		if (scanner.symbol.type == symbolType){
			scanner.next();
			return true;
		}
		return false;
	}
	
	boolean accept(List<SymbolType> symbols){
		return symbols.contains(scanner.symbol.type);
	}

	boolean expect(SymbolType symbolType) {
		if (accept(symbolType)) {
			return true
		}
		error = true
		log.error("Unexpected token:{} @ line {}. {} expected", scanner.symbol.value, scanner.lineNumber(), symbolType)
		return false
	}

	void block() {
		log.debug('BLOCK')
		if (accept(CONST)) {
			cons()
		}
		if (accept (VAR)) {
			var()
		}
		while(accept (PROCEDURE)) {
			procedure()
		}
		preposition()
	}

	
	void preposition() {
		if (accept(IDENTIFIER)) {
			log.debug('ASSIGN')
			expect(ASSIGN)
			expression()
		}
		if (accept(CALL)) {
			expect(IDENTIFIER)
		}
	}
	
	void expression() {
		log.debug('EXPRESSION')
		accept([PLUS,MINUS])
		expect(INTEGER)
		while(accept([PLUS,MINUS])) {
			expect(INTEGER)
		}
	}

	void procedure() {
		log.debug('PROCEDURE')
		expect(IDENTIFIER)
		expect(SEMICOLON)
		block();
		expect(SEMICOLON)
	}

	void cons() {
		log.debug('CONST')
		while (true) {
			expect(IDENTIFIER)
			expect(EQ)
			expect(INTEGER)
			if (!accept(COMMA)) break
		}
		expect(SEMICOLON)
	}

	void var() {
		log.debug('VAR')
		while (true) {
			expect(IDENTIFIER)
			if (!accept(COMMA)) break
		}
		expect(SEMICOLON)
	}
}
