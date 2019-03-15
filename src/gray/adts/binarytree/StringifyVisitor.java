package gray.adts.binarytree;

public class StringifyVisitor implements Visitor<String>
{
	private StringBuffer nodes;
	public StringifyVisitor()
	{
		nodes = new StringBuffer("");
	}
	
	@Override
	public String toString()
	{
		return nodes.toString();
	}
	
	@Override
	public void visit(BinaryTreeNode<String> node)
	{
		nodes.append(node.element().toString());
	}

}
