package org.flavio.fiuba.pl0;

import static org.junit.Assert.*
import static org.flavio.fiuba.pl0.SymbolType.*
import groovy.util.logging.Slf4j;

import org.junit.Test


@Slf4j
class ParserTest {

	Parser parser

	private void setUp(String source) {
		log.info('TESTING: {}', source)
		StringReader reader = new StringReader(source)
		Scanner scanner = new Scanner(reader)
		parser = new Parser(scanner)
		parser.scan()
	}

	@Test
	public void testNotPeriod() {
		setUp("A");
		assertTrue(parser.error)
	}
	
	@Test
	public void testBlockNotPeriod() {
		setUp("CONST A=2;");
		assertTrue(parser.error)
	}
	
	@Test
	public void testBlockIncomplete() {
		setUp("CONST A=2");
		assertTrue(parser.error)
	}

	@Test
	public void testPeriod() {
		setUp(".");
		assertFalse(parser.error)
	}

	@Test
	public void testCONST() {
		setUp("CONST A=3;.");
		assertFalse(parser.error)
	}
	
	@Test
	public void testServeralCONST() {
		setUp("CONST A=3,B=2,FLAVIO=5;.");
		assertFalse(parser.error)
	}

	@Test
	public void testBadCONST() {
		setUp("CONST A:=3.")
		assertTrue(parser.error)
	}
	
	@Test
	public void testBadCONST2() {
		setUp("CONST A=something.")
		assertTrue(parser.error)
	}
	
	@Test
	public void testBadCONST3() {
		setUp("CONST A=3;CONST=B=2.")
		assertTrue(parser.error)
	}
	
	@Test
	public void testServeralVAR() {
		setUp("VAR A,FLAVIO;.");
		assertFalse(parser.error)
	}

	@Test
	public void testVAR() {
		setUp("VAR A;.")
		assertFalse(parser.error)
	}
	
	@Test
	public void testBadVAR() {
		setUp("VAR A=something.")
		assertTrue(parser.error)
	}
	

	
}
