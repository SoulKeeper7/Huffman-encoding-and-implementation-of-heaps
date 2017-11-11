

public class HuffmanNode 
{
	 HuffmanNode left;
	 HuffmanNode right;
	 int val;
	 int dir;
	 String key;
	 
	 
	 public HuffmanNode(int val)
	 {
		 this.val =val;
	 }
	 
	 public HuffmanNode(int val, String key)
	 {
		 this.val =val;
		 this.key = key;
	 }
	 
	 public HuffmanNode(HuffmanNode left, HuffmanNode right)
	 {
		 this.left = left;
		 this.right = right;
	 }
	 
	 public HuffmanNode(int val, String key,HuffmanNode left, HuffmanNode right)
	 {
		 this.val =val;
		 this.key = key;
		 this.left = left;
		 this.right = right;
	 }
}
