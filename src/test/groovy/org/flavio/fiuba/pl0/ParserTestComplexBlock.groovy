package org.flavio.fiuba.pl0;

import static org.junit.Assert.*
import groovy.util.logging.Slf4j;

import org.junit.Test


@Slf4j
class ParserTestComplexBlock {

	Parser parser

	private void setUp(String source) {
		log.info('TESTING: {}', source)
		StringReader reader = new StringReader(source)
		Scanner scanner = new Scanner(reader)
		parser = new Parser(scanner)
		parser.scan()
	}

	@Test
	public void testBlock() {
		setUp("VAR x; CONST a=4;.")
		assertFalse(parser.error)
	}
	
	
	@Test
	public void testBadBlock() {
		setUp("VAR x; CONST a=4.")
		assertTrue(parser.error)
	}
	
}
