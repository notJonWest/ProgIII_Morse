package morseConverter;

import gray.adts.binarytree.*;
import gray.adts.stack.LinkedStack;

public class CodeVisitor implements Visitor<String>
{
	LinkedStack<String> morseStack;
	String letter;
	
	public CodeVisitor(String letter)
	{
		this.letter = letter;
		this.morseStack = new LinkedStack<String>();
	}
	
	public String letterCode()
	{
		StringBuffer code = new StringBuffer();
		while (!morseStack.isEmpty())
			code.append(morseStack.pop());
		return code.toString();
	}
	
	@Override
	public void visit(BinaryTreeNode<String> node)
	{
		if (node.element().equalsIgnoreCase(letter))
		{
			morseStack.push(" ");
			while (node.parent() != null)
			{
				BinaryTreeNode<String> parentNode = node.parent();
				if (parentNode.leftChild().equals(node))
					morseStack.push(".");
				else if (parentNode.rightChild().equals(node))
					morseStack.push("-");
				node = parentNode;
			}
		}
	}
}
