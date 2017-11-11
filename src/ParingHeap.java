import java.util.ArrayList;
import java.util.List;

public class ParingHeap 
{
	 private int heapSize;
	    private static  List<PairingHuffmanNode> heap;
	    public static PairingHuffmanNode root = null;
	    
	  //private HuffmanNode root = null;
	 
	    /** Constructor **/    
	    public ParingHeap(int capacity)
	    {
	        heapSize = 0;
	        heap = new   ArrayList<PairingHuffmanNode>(0);
	      //heap.add(new HuffmanNode(-1));
	    }
	    
	    public ParingHeap( )
	    {
	        root = null;
	      }
	    /* Check if heap is empty */
	    public boolean isEmpty() 
	    {
	        return root == null;
	    }
	    /* Make heap logically empty */ 
	    public void makeEmpty( )
	    {
	        root = null;
	    }
	    /* Function to insert data */
	    public  PairingHuffmanNode insert(PairingHuffmanNode x)
	    {
	    	
	        if (root == null)
	            root = x;
	        else
	            root = compareAndLink(root, x);
	        return root;
	    }
	    /* Function compareAndLink */
	    private static PairingHuffmanNode compareAndLink(PairingHuffmanNode first, PairingHuffmanNode second)
	    {
	    	
	    	if (second == null)
	            return first;
	 
	        if (second.data < first.data)
	        {
	            /* Attach first as leftmost child of second */
	            second.child = first.child;
	            first.child = second;
	            first.nextsibling = second.leftchild;
	            if (first.nextsibling != null)
	                first.nextsibling.child = first;
	            second.leftchild = first;
	            return second;
	        }
	        else
	        {
	            /* Attach second as leftmost child of first */
	            second.child = first;
	            first.nextsibling = second.nextsibling;
	            if (first.nextsibling != null)
	                first.nextsibling.child = first;
	            second.nextsibling = first.leftchild;
	            if (second.nextsibling != null)
	                second.nextsibling.child = second;
	            first.leftchild = second;
	            return first;
	        }
	           }
	    
	    public int getmin()
	    {
	    	if( root != null)
	    	return root.data;
	    	return -1;
	    }
	   public  PairingHuffmanNode deleteMin()
	    {
		   if (isEmpty( ) )
	            return null;
		   PairingHuffmanNode x = root;
	        if (root.leftchild == null)
	            root = null;
	        else
	            root = combinesiblings( root.leftchild );
	        x.leftchild= null;
	        return x;
	    }
		   
	    		    	
	private PairingHuffmanNode combinesiblings(PairingHuffmanNode firstSibling)
	{
		//firstSibling.child= null;
		 if( firstSibling.nextsibling == null )
	            return firstSibling;
	        /* Store the subtrees in an array */
	        int numSiblings = 0;
	        List<PairingHuffmanNode> pairarray = new ArrayList<PairingHuffmanNode>();
	        for ( ; firstSibling != null; numSiblings++)
	        {
	           // treeArray = doubleIfFull( treeArray, numSiblings );
	            pairarray.add(firstSibling);
	            /* break links */
	            firstSibling.child.nextsibling = null; 
	            firstSibling.child= null;
	            firstSibling = firstSibling.nextsibling;
	        }
	        //treeArray = doubleIfFull( treeArray, numSiblings );
	        //treeArray[ numSiblings ] = null;
	        /* Combine subtrees two at a time, going left to right */
	       
	        for (  int i = 0; i < pairarray.size(); i += 2)	        	
	        {
	        	if(i<=pairarray.size()-3)	        
	            pairarray.set(i, compareAndLink(pairarray.get(i), pairarray.get(i + 1)));
	        }
	        int j=pairarray.size();
	        if(pairarray.size() %2!=0)
	        {
	        	pairarray.set(pairarray.size()-3, compareAndLink(pairarray.get(pairarray.size()-1), pairarray.get(pairarray.size()-3)));
	        	j=j-3;
	        }
	        else
	        {
	        	j= j-2;
	        }
	        /* Now go right to left, merging last tree with */
	        /* next to last. The result becomes the new last */
	        for ( int  i=j; j > 0; j -= 2)
	            pairarray.set(j-2,compareAndLink(pairarray.get(j-2), pairarray.get(j)));
	        return pairarray.get(0);


		//return null;
	}

				  
	    

}