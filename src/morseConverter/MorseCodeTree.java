package morseConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gray.adts.binarytree.*;

public class MorseCodeTree
{
	BinaryTree<String> morseLettersTree;
	
	public MorseCodeTree()
	{
		this("./data/conversions.dat");
	}
	public MorseCodeTree(String filename)
	{
		morseLettersTree = new LinkedBinaryTree<String>();
		morseLettersTree.makeRoot("");
		File codeFile = new File(filename);
		Scanner inFile;
		try
		{
			inFile = new Scanner(codeFile);
			while (inFile.hasNextLine())
			{
				String line = inFile.nextLine();
				String letter = line.substring(0, line.indexOf(" "));
				String morse = line.substring(line.indexOf(" ") + 1);
				BinaryTreeNode<String> currentNode = morseLettersTree.root();
				for (int i = 0; i < morse.length(); i++)
				{
					String el = null;
					if (i == morse.length() - 1)
						el = letter;
					if (morse.charAt(i) == '.')
					{
						if (currentNode.leftChild() != null)
						{
							currentNode = currentNode.leftChild();
							if (currentNode.element() == null)
								currentNode.setElement(el);
						}
						else
						{
							currentNode = morseLettersTree.makeLeftChild(currentNode, el);
						}
					}
					else if (morse.charAt(i) == '-')
					{
						if (currentNode.rightChild() != null)
						{
							currentNode = currentNode.rightChild();
							if (currentNode.element() == null)
								currentNode.setElement(el);
						}
						else
						{
							currentNode = morseLettersTree.makeRightChild(currentNode, el);
						}
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Could not find file \"" + filename + "\"- Exiting program.");
			System.exit(-1);
		}
		
	}
	
	public String traverseTree(Traverse method)
	{
		Visitor<String> strVisitor = new StringifyVisitor();
		switch (method)
		{
			case PREORDER:
				morseLettersTree.preOrderTraversal(strVisitor);
				break;
			case POSTORDER:
				morseLettersTree.postOrderTraversal(strVisitor);
				break;
			case INORDER:
				morseLettersTree.inOrderTraversal(strVisitor);
				break;
			case LEVEL:
				morseLettersTree.levelOrderTraversal(strVisitor);
				break;
		}
		
		return strVisitor.toString();
	}
	
	public String encode(String word)
	{
		StringBuffer code = new StringBuffer("");
		for (String c: word.toLowerCase().split(""))
		{
			if (morseLettersTree.contains(c))
			{
				CodeVisitor codeVisitor = new CodeVisitor(c);
				morseLettersTree.postOrderTraversal(codeVisitor);
				code.append(codeVisitor.letterCode());
			}
			else if (c.equals(" "))
				code.append(" ");
		}
		
		return code.toString().trim();
	}
	
	public String decode(String code)
	{
		StringBuffer word = new StringBuffer();
		for (String w: code.split("  ")) //Every word
		{
			for (String l: w.split(" ")) //Every letter
			{
				BinaryTreeNode<String> letterNode = morseLettersTree.root();
				for (String s: l.split("")) //Every morse symbol
				{
					if (letterNode != null)
					{
						if (s.equals("."))
							letterNode = letterNode.leftChild();
						else if (s.equals("-"))
							letterNode = letterNode.rightChild();
					}
				}
				if (letterNode != null)
					word.append(letterNode.element());
			}
			word.append(" ");
		}
		return word.toString().trim();
	}
}
