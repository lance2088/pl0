package org.flavio.fiuba.pl0;

import static org.junit.Assert.*;

import javax.xml.ws.ServiceMode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class TokenEvaluatorTest {
	
	TokenEvaluator evaluator;
	
	@Before
	void before() {
		evaluator = new TokenEvaluator();
	}
	
	@Test
	public void testEvaluate() {
	
		assertTrue(SymbolType.CONST == evaluator.findSymbolType('const'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType('const'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType('<'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType('>'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType('<='))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType('var'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType('end'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType('begin'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType(','))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType(':'))
		assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType(';'))
		assertTrue(SymbolType.ERROR == evaluator.findSymbolType('co444'))
		assertTrue(SymbolType.ERROR == evaluator.findSymbolType('=>'))
		assertTrue(SymbolType.ERROR == evaluator.findSymbolType('44a'))
		assertTrue(SymbolType.ERROR == evaluator.findSymbolType('a$'))
		assertTrue(SymbolType.ERROR == evaluator.findSymbolType('!a'))
	}
	
	@Test
	public void testSymbol() {		
		assertEquals(new Symbol(SymbolType.CONST,"CONST"),evaluator.evaluate("const"));
		assertEquals(new Symbol(SymbolType.IDENTIFIER,"anIdentifier"),evaluator.evaluate("anIdentifier"));
		assertEquals(new Symbol(SymbolType.COMMA,","),evaluator.evaluate(","));
		assertEquals(new Symbol(SymbolType.INTEGER,"152"),evaluator.evaluate("152"));		
	}
	

}
