import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

public class Heap 
{
    private static final int d = 2
    		;
    private int heapSize;
    private static  List<HuffmanNode> heap;
 
    /** Constructor **/    
    public Heap(int capacity)
    {
        heapSize = 0;
        heap = new ArrayList<HuffmanNode>(0);
       // heap.add(new HuffmanNode(-1));
        
        
        
    }
 
    /** Function to check if heap is empty **/
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }
 
    /** Check if heap is full **/
    public boolean isFull( )
    {
        return heapSize == heap.size();
    }
 
    /** Clear heap */
    public void makeEmpty( )
    {
        heapSize = 1;
    }
 
    /** Function to  get index parent of i **/
    private int parent(int i) 
    {
        return (int) Math.ceil((i-1)/d);
    }
 
    /** Function to get index of k th child of i **/
    private int kthChild(int i, int k) 
    {
        return d * i + k;
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
    public int findMin( )
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");           
        return heap.get(0).val;
    }
 
    /** Function to delete min element **/
    public HuffmanNode deleteMin()
    {
    	HuffmanNode keyItem = heap.get(0);
        delete(0);
        return keyItem;
    }
 
    /** Function to delete element at an index **/
    public HuffmanNode delete(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        
        HuffmanNode keyItem = heap.get(ind);
       // heap.remove(ind);
      
        heap.set(0,heap.get(heapSize - 1));
        heap.remove(heapSize - 1);
        heapSize--;
        if(!heap.isEmpty())
        heapifyDown(ind);  
        
        return keyItem;
    }
 
    /** Function heapifyUp  **/
    private void heapifyUp(int childInd)
    {
    	if(childInd ==0)
    	{
    		return;
    	}
    	HuffmanNode tmp = heap.get(childInd);    
        while (childInd > 0 && tmp.val < (heap.get(parent(childInd)).val))
        {
            heap.set(childInd,heap.get(parent(childInd)));
            childInd = parent(childInd);
        }                   
        heap.set(childInd,tmp);
    }
 
    /** Function heapifyDown **/
    private void heapifyDown(int ind)
    {
  
        int child;
        
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
        while ((k <= d) && (pos <heapSize)) 
        {
            if ((heap.get(pos).val) < (heap.get(bestChild).val))
                bestChild = pos;
            pos = kthChild(ind, k++);
        }    
        return bestChild;
    }
 
   
	}


