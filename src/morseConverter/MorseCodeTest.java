package morseConverter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MorseCodeTest
{
	@Test
	public void testFileRead()
	{
		//File reading works regardless of file order. I have the file sorted alphabetically to prove this.
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure the file is properly read in and nodes are in proper order.", tree.traverseTree(Traverse.LEVEL), "etianmsurwdkgohvflpjbxcyzq");
	}
	
	@Test
	public void testEncodeStandard()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encode properly encodes.",
				tree.encode("The quick brown fox jumps over the lazy dog"),
				"- .... .  --.- ..- .. -.-. -.-  -... .-. --- .-- "
				+ "-.  ..-. --- -..-  .--- ..- -- .--. ...  --- "
				+ "...- . .-.  - .... .  .-.. .- --.. -.--  -.. --- "
				+ "--.");
	}
	
	@Test
	public void testEncodeUpperCase()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encoding works with capitals",
				tree.encode("AT"), ".- -");
	}
	
	@Test
	public void testEncodeLowerCase()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encoding works with lowercase letters",
				tree.encode("at"), ".- -");
	}
	
	@Test
	public void testEncodeUnknownCharacters()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encoding ignores non-letters",
				tree.encode("b@#$a"), "-... .-");
	}
	
	@Test
	public void testEncodeOnlyUnknownCharacters()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encoding works with only non-letters",
				tree.encode("@#$"), "");
	}
	
	@Test
	public void testEncodeEmpty()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encoding does not fail when inputted with an empty string",
				tree.encode(""), "");
	}

	@Test
	public void testEncodeOnlySpace()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encoding does gives back nothing when only given spaces",
				tree.encode(" "), "");
	}
	
	public void testEncodeMorse()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure encoding treats morse code symbols the same as any other non-letter",
				tree.encode(".-"), "");
	}
	
	@Test
	public void testDecodeStandard()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure decoding decodes morse code into letters properly.",
				tree.decode("- .... .  --.- ..- .. -.-. -.-  -... .-. --- .-- "
						+ "-.  ..-. --- -..-  .--- ..- -- .--. ...  --- "
						+ "...- . .-.  - .... .  .-.. .- --.. -.--  -.. --- "
						+ "--."), "the quick brown fox jumps over the lazy dog");
	}
	
	@Test
	public void testDecodeNonMorse()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure decoding ignores any non morse code characters.",
				tree.decode("abc.-"), "a");
	}
	
	@Test
	public void testDecodeOnlyNonMorse()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure decoding does not fail when given only non morse characters.",
				tree.decode("abc"), "");
	}
	
	@Test
	public void testDecodeEmpty()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure decoding does not fail when given an empty string",
				tree.decode(""), "");
	}

	@Test
	public void testDecodeOnlySpace()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure decoding does gives back nothing when only given spaces",
				tree.decode(" "), "");
	}
	
	@Test
	public void testPreserveSpacing()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure the spacing is preserved when encoding and decoding.",
				tree.decode(tree.encode("This sentence has many words")), "this sentence has many words");
	}
	
	@Test
	public void testInvalidMorse()
	{
		MorseCodeTree tree = new MorseCodeTree();
		assertEquals("Make sure invalid morse code is ignored",
				tree.decode("..--- not code -"), "t");
	}
}
