
public class PairingHuffmanNode {


	PairingHuffmanNode nextsibling = null;
	PairingHuffmanNode  leftchild = null;
	PairingHuffmanNode child = null;
	PairingHuffmanNode left = null;
	PairingHuffmanNode right = null;
	int data ;
	String key;
	int dir;
	public  PairingHuffmanNode(PairingHuffmanNode left, PairingHuffmanNode right, int data)
	{
		this.leftchild = left;
		this.nextsibling = right;
		this.data = data;
		
	}
	
	public PairingHuffmanNode( int data,String key)
	{
		this.key = key;
		this.data = data;
		
	}

	public PairingHuffmanNode( int data,String key, PairingHuffmanNode left, PairingHuffmanNode right)
	{
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
		
	}
	public PairingHuffmanNode()
	{
		
	}
}



