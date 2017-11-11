

	import java.util.ArrayList;

	import java.util.List;
	import java.util.NoSuchElementException;

	public class Heap4way
	{
	    private static final int d = 4
	    		;
	    private int heapSize;
	    private static  List<HuffmanNode> heap;
	 
	    /** Constructor **/    
	    public Heap4way(int capacity)
	    {
	        heapSize = 3;
	        heap = new ArrayList<HuffmanNode>(0);
	        heap.add(new HuffmanNode(-1));
	        heap.add(new HuffmanNode(-1));
	        heap.add(new HuffmanNode(-1));
	        
	        
	        
	    }
	 
	    /** Function to check if heap is empty **/
	    public boolean isEmpty( )
	    {
	        return heapSize == 3;
	    }
	 
	    /** Check if heap is full **/
	    public boolean isFull( )
	    {
	        return heapSize == heap.size();
	    }
	 
	    /** Clear heap */
	    public void makeEmpty( )
	    {
	        heapSize = 3;
	    }
	 
	    /** Function to  get index parent of i **/
	    private int parent(int i) 
	    {
	        return (int) (i/d)+2;
	    }
	 
	    /** Function to get index of k th child of i **/
	    private int kthChild(int i, int k) 
	    {
	        return d * i + k-9;
	    }
	 
	    /** Function to insert element */
	    public void insert(HuffmanNode x)
	    {
	       // if (isFull( ) )
	         //   throw new NoSuchElementException("Overflow Exception");
	        /** Percolate up **/
	    	heapSize++;
	        heap.add(x);
	        heapifyUp(heapSize - 1);
	    }
	 
	    /** Function to find least element **/
	    public int getMin( )
	    {
	        if (isEmpty() )
	            throw new NoSuchElementException("Underflow Exception");           
	        return heap.get(3).val;
	    }
	 
	    /** Function to delete min element **/
	    public HuffmanNode deleteMin()
	    {
	    	HuffmanNode keyItem = heap.get(3);
	        delete(3);
	        return keyItem;
	    }
	 
	    /** Function to delete element at an index **/
	    public HuffmanNode delete(int ind)
	    {
	        if (isEmpty() )
	            throw new NoSuchElementException("Underflow Exception");
	        
	        HuffmanNode keyItem = heap.get(ind);
	       // heap.remove(ind);
	      
	        heap.set(3,heap.get(heapSize - 1));
	        heap.remove(heapSize - 1);
	        heapSize--;
	        if(!heap.isEmpty())
	        heapifyDown(ind);  
	        
	        return keyItem;
	    }
	 
	    /** Function heapifyUp  **/
	    private void heapifyUp(int lastindex)
	    {
	    	if(lastindex <=3)
	    	{
	    		return;
	    	}
	    	HuffmanNode tmp = heap.get(lastindex);    
	        while (lastindex > 3 && tmp.val < (heap.get(parent(lastindex)).val))
	        {
	            heap.set(lastindex,heap.get(parent(lastindex)));
	            lastindex = parent(lastindex);
	        }                   
	        heap.set(lastindex,tmp);
	    }
	 
	    /** Function heapifyDown **/
	    private void heapifyDown(int ind)
	    {
	  
	        int child;
	        if(heapSize <=4)
	        {
	        	return;
	        }
	        HuffmanNode tmp = heap.get(ind) ;
	        
	        	        	
	        while (kthChild(ind, 1) < heapSize)
	        {
	            child = minChild(ind);
	            if ((heap.get(child).val <= tmp.val))
	                heap.set(ind,heap.get(child));
	            else
	            	break;
	            
	            ind = child;
	        }
	        heap.set(ind,tmp);
	    }
	 
	    /** Function to get smallest child **/
	    private int minChild(int ind) 
	    {
	        int bestChild = kthChild(ind, 1);
	        int k = 2;
	        int pos = kthChild(ind, k);
	        
	        while ((k <= d) && (pos <heapSize) ) 
	        {
	            if ((heap.get(pos).val) < (heap.get(bestChild).val))
	                bestChild = pos;
	            
	            pos = kthChild(ind, ++k);
	        }    
	        return bestChild;
	    }
	 
	    /** Function to print heap **/
	    public void printHeap()
	    {
	       // System.out.print("\nHeap = ");
	       // for (long i = 0; i < heapSize; i++)
	            //System.out.print(heap(int) i] +" ");
	        //System.out.println("done");
	    }  
		}





