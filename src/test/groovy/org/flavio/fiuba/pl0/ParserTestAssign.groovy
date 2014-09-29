package org.flavio.fiuba.pl0;

import static org.junit.Assert.*
import groovy.util.logging.Slf4j;

import org.junit.Test


@Slf4j
class ParserTestAssign {

	Parser parser

	private void setUp(String source) {
		log.info('TESTING: {}', source)
		StringReader reader = new StringReader(source)
		Scanner scanner = new Scanner(reader)
		parser = new Parser(scanner)
		parser.scan()
	}

	
	@Test
	public void testSimpleAssign() {
		setUp("A:=4 .")
		assertFalse(parser.error)
	}
	
	@Test
	public void testPlusMinusAssign() {
		setUp("A:=4+4-3 .")
		assertFalse(parser.error)
	}

	
}
