package org.flavio.fiuba.pl0;

import static org.junit.Assert.*;

import org.junit.Test;

class ScannerTest {

	@Test
	public void test() {
		Scanner scanner = new Scanner('CONST A=2;')
		Symbol expected = new Symbol('CONST', SymbolType.CONST);
		assertEquals(expected, scanner.next()) 
	}

}
