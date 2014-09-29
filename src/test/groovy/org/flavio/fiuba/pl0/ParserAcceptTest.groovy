package org.flavio.fiuba.pl0;

import static org.junit.Assert.*
import static org.flavio.fiuba.pl0.SymbolType.*
import groovy.util.logging.Slf4j;

import org.junit.Test


@Slf4j
class ParserAcceptTest {

	Parser parser

	private void setUp(String source) {
		log.info('TESTING: {}', source)
		StringReader reader = new StringReader(source)
		Scanner scanner = new Scanner(reader)
		parser = new Parser(scanner)
	}

	
	@Test
	public void testAccept() {
		setUp("A:=4+5-6")
		parser.scanner.next();
		assertTrue(parser.accept(IDENTIFIER))
		assertTrue(parser.accept(ASSIGN))
		assertTrue(parser.accept(INTEGER))
		assertTrue(parser.accept([PLUS, MINUS]))
	}
	
	public void testAcceptWrong() {
		setUp("A:=4+5-6")
		parser.scanner.next();
		assertTrue(parser.accept([IDENTIFIER]))
		assertTrue(parser.accept(ASSIGN))
		assertTrue(parser.accept(INTEGER))
		assertFalse(parser.accept([SLASH]))
	}

	
}
