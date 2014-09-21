package org.flavio.fiuba.pl0

import static org.flavio.fiuba.pl0.SymbolType.*
import groovy.util.logging.Slf4j
import ch.qos.logback.core.rolling.helper.PeriodicityType

@Slf4j
class Parser {
	
	Scanner scanner;
	boolean error = false;
	
	Parser (Scanner scanner){
		this.scanner = scanner
	}

	void scan() {
		log.debug("Parser START")
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
	
	boolean expect(SymbolType symbolType) {
		if (accept(symbolType)) {
			return true
		}
		error = true
		log.error("Unexpected token:{} @ line {}. {} expected", scanner.symbol.value, scanner.lineNumber(), symbolType)
		return false
	}  
	
	void block() {
		log.debug("BLOCK")
		while(scanner.hasNext() && tokenTypeIn([CONST, VAR, PROCEDURE, PERIOD]) && !error) {
			if (accept(CONST)) {
				cons()
			} else if (accept (VAR)) {
				var();
			} else if (accept (PROCEDURE)) {
				procedure();
			}
		} 
	}
	
	boolean tokenTypeIn(list) {
		return list.contains(scanner.symbol.type);
	}
	
	void procedure() {
		log.debug("PROCEDURE")
		expect(IDENTIFIER)
		expect(SEMICOLON)
		block();
		expect(SEMICOLON)
	}
		
	void cons() {
		log.debug("CONST")
		while (true) {
			expect(IDENTIFIER)
			expect(EQ)
			expect(INTEGER)
			if (!accept(COMMA)) break
		}
		expect(SEMICOLON)
	}
	
	void var() {
		log.debug("VAR")
		while (true) {
			expect(IDENTIFIER)
			if (!accept(COMMA)) break
		}
		expect(SEMICOLON)
	}
	
}
