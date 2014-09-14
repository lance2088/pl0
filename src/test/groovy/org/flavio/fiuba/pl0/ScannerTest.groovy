package org.flavio.fiuba.pl0;

import static org.junit.Assert.*;

import org.junit.Test;

class ScannerTest {

	@Test
	public void testAssign() {
		
		StringReader reader = new StringReader('CONST A:=2;')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.CONST,'CONST')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.ASSIGN,':=')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.INTEGER,'2')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.SEMICOLON,';')
		scanner.next()
		assertEquals(expected, scanner.symbol)
	}
	
	@Test
	public void testErrorWithDouble() {
		
		StringReader reader = new StringReader('CONST A=2.4;')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.CONST,'CONST')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.EQ,'=')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.ERROR,'2.4')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
	}
	
	@Test
	public void testNotEquals() {
		
		StringReader reader = new StringReader('A<>B')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.NE,'<>')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'b')
		scanner.next()
		assertEquals(expected, scanner.symbol)		
	}
	
	@Test
	public void testLE() {
		
		StringReader reader = new StringReader('A<=B')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.LE,'<=')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'b')
		scanner.next()
		assertEquals(expected, scanner.symbol)
	}
	
	@Test
	public void testLT() {
		
		StringReader reader = new StringReader('A<B')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.LT,'<')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'b')
		scanner.next()
		assertEquals(expected, scanner.symbol)
	}
	
	@Test
	public void testGT() {
		
		StringReader reader = new StringReader('A>B')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.GT,'>')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'b')
		scanner.next()
		assertEquals(expected, scanner.symbol)
	}
	
	@Test
	public void testGE() {
		
		StringReader reader = new StringReader('A>=B')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.GE,'>=')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'b')
		scanner.next()
		assertEquals(expected, scanner.symbol)
	}
	
	@Test
	public void testSlash() {
		
		StringReader reader = new StringReader('A/B')
		Scanner scanner = new Scanner(reader)

		def expected = new Symbol(SymbolType.IDENTIFIER,'a')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.SLASH,'/')
		scanner.next()
		assertEquals(expected, scanner.symbol)
		
		expected = new Symbol(SymbolType.IDENTIFIER,'b')
		scanner.next()
		assertEquals(expected, scanner.symbol)
	}
	
}
