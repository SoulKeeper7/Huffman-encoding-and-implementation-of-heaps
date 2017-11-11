
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import javax.xml.soap.Node;

public class ADSproj 
{
	private static HuffmanNode root = null;
	private static PairingHuffmanNode rootpair = null;
	public static Queue<HuffmanNode> hfq = new LinkedList<HuffmanNode>(); 
		public static Hashtable<String,Integer> Ftable = new Hashtable<String,Integer>();
		public static Hashtable<String,String> Encodedtable = new Hashtable<String,String>();
	public static void main(String[] args) throws IOException
	{int count=0;
	Scanner scanner = null;
		try 
		{
			 scanner = new Scanner(new FileReader("C:\\Users\\agarw\\Downloads\\sample_input_small"
					+ ".txt"));
            String line;
            
            while (scanner.hasNextLine())
            {
            	String x = scanner.nextLine();
                if(Ftable.containsKey(x) == false)
                {
                	count++;
                	Ftable.put(x, 1);
                }
                else if( Ftable.containsKey(x))
                {
                	int x1 = Ftable.get(x);
                	Ftable.replace(x, x1+1);
                }
            }
        } 
		
		
		catch (IOException e)
		{
            e.printStackTrace();
        } 
		finally
		{
                if (scanner != null) 
                {
                    scanner.close();
                }
            
		
        }
		
		///// creation binary heap and huffman
		//Create_Tree_Using_Binry_Heap();


		// create using 4way
		//create_Tree_Using_4Way();
		
	

		
		//create using pairing
		Create_Tree_using_Pairing();
				
		
	}

	private static void create_Tree_Using_4Way() throws IOException, FileNotFoundException
	{
		/////4 way heap creation and huffman
		Heap4way y1 = new Heap4way(0);
		long startTime1 = System.currentTimeMillis();
		//.....your program....
		int count=0;
		for(Map.Entry<String, Integer> x :Ftable.entrySet())
		{
			if (!x.getKey().isEmpty())
			{
				
			y1.insert(new HuffmanNode(x.getValue(),x.getKey()));
			}
			
		}
		// y1.printHeap();
		while(!y1.isEmpty())
		{
			
		HuffmanNode h1 = y1.deleteMin();
		HuffmanNode h2 = null;
		if(!y1.isEmpty())  
		{
			//HuffmanNode x = y.deleteMin();
			 h2= y1.deleteMin();
		}
		else
		{
			HuffmanNode x = h1;
			break;
		}
		HuffmanNode n = new HuffmanNode(h1.val+h2.val);
		
		n.left= h1;
		n.left.dir=0;
		n.right = h2;
		n.right.dir=1;
		root =n;
		if(!y1.isEmpty())
		{
			y1.insert(n);
		}
		}
		
		
			File file = new File("C:\\Users\\agarw\\Downloads\\codetable.txt");
		 	   FileWriter writer = new FileWriter(file,true);
		        
			printPaths(root,writer) 	;
			writer.close();
			Encode();
			InputStream inputStream = new FileInputStream("C:\\Users\\agarw\\Downloads\\sample_large.txt");
			 int byteRead;
			// String s= extract(inputStream);
	            while ((byteRead = inputStream.read()) != -1) 	            	 
	   	          {
	   	          	String s1 = String.format("%1s", Integer.toBinaryString((byteRead+256)%256));
	   	          	String S2 = String.format("%8s",s1).replace(" ","0");	   	          
	   	          }
	   	
			inputStream.close();
		
		
		
		

				long endTime1   = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime1;
		System.out.println(totalTime1+"k");
	}
	 /* Recursive helper function -- given a node, and an array
    containing the path from the root node up to but not 
    including this node, print out all the root-leaf paths.*/
 static void printPathsRecur(HuffmanNode node, int path[], int pathLen,FileWriter writer) throws IOException 
 {
     if (node == null)
         return;

     /* append this node to the path array */
     path[pathLen] = node.dir;
     pathLen++;

     /* it's a leaf, so print the path that led to here  */
     if (node.left == null && node.right == null)
         printArray(path, pathLen,node.key,writer);
     else
     {
         /* otherwise try both subtrees */
         printPathsRecur(node.left, path, pathLen,writer);
         printPathsRecur(node.right, path, pathLen,writer);
     }
 }

 static void printPathsRecur(PairingHuffmanNode node, int path[], int pathLen,FileWriter writer) throws IOException  
 {
     if (node == null)
         return;

     /* append this node to the path array */
     path[pathLen] = node.dir;
     pathLen++;

     /* it's a leaf, so print the path that led to here  */
     if (node.left == null && node.right == null)
         printArray(path, pathLen,node.key,writer);
     else
     {
         /* otherwise try both subtrees */
         printPathsRecur(node.left, path, pathLen,writer);
         printPathsRecur(node.right, path, pathLen,writer);
     }
 }

 /* Utility function that prints out an array on a line. */
static  void printArray(int ints[], int len,String key,FileWriter writer) throws IOException 
 {
     int i;
     StringBuilder s = new StringBuilder();
     
     for (i = 1; i < len; i++) 
     {
         s.append(ints[i]);
     }
     Encodedtable.put(key,s.toString());
     writer.write(key+ " ==> " +s.toString()+"\n");
     
 }
public static void Encode() throws IOException
{
	   BufferedReader br = null;	 
	   //File file = new File("C:\\Users\\agarw\\Downloads\\sample_output.txt");
	  // FileWriter writer = new FileWriter(file,true);
        	
   
		try 
		{
			br = new BufferedReader(new FileReader("C:\\Users\\agarw\\Downloads\\sample_input_small"
					+ ".txt"));
        String line;
        OutputStream outputStream = new FileOutputStream("C:\\Users\\agarw\\Downloads\\sample_large.txt",true);
        StringBuilder s = new StringBuilder();
        while ((line = br.readLine()) != null)
        {
            if(Encodedtable.containsKey(line))
            {
         	   s.append(Encodedtable.get(line));
         	   
         	   while(s.length()>=8)
         	   {
         		String sf = s.substring(0, 8) ;          		
         		int x = Integer.parseInt(sf,2) ;         	
         		outputStream.write(x);
         		s.delete(0, 8);
         	   }
            }  
        }
        
        outputStream.close();
		}catch (IOException e)
		{
         e.printStackTrace();
     } 
		finally
		{
         try 
         {
             if (br != null) 
             {
                 br.close();
             }
         }
         catch (IOException ex) {
             ex.printStackTrace();
         }
     }
	

       // FileUtils.writeStringToFile("C:\\Users\\agarw\\Downloads\\sample_output",s.toString())
		

}

	private static void Create_Tree_using_Pairing() throws IOException {
		ParingHeap y2 = new ParingHeap(0);
		long startTime2 = System.currentTimeMillis();
		//.....your program....
		
		for(Map.Entry<String, Integer> x :Ftable.entrySet())
		{
			if(!x.getKey().isEmpty())
				y2.insert(new PairingHuffmanNode(x.getValue(),x.getKey()));
			//y.printHeap();
		}
		
		
			PairingHuffmanNode x = new PairingHuffmanNode();
			while(!y2.isEmpty())
			{
				
				PairingHuffmanNode h1 = y2.deleteMin();
				PairingHuffmanNode h2 = null;
			if(!y2.isEmpty())  
			{
				//HuffmanNode x = y.deleteMin();
				 h2= y2.deleteMin();
			}
			else
			{
				PairingHuffmanNode xf = h1;
				break;
			}
			PairingHuffmanNode n = new PairingHuffmanNode(h1.data+h2.data,"");
			
			n.left= h1;
			n.left.dir=0;
			n.right = h2;
			n.right.dir=1;
			rootpair =n;
			if(!y2.isEmpty())
			{
				y2.insert(n);
			}
			else
			{
			
			
			
				File file = new File("C:\\Users\\agarw\\Downloads\\codetable.txt");
			 	   FileWriter writer = new FileWriter(file,true);
			        
				printPaths(rootpair,writer) 	;
				writer.close();
				Encode();
				InputStream inputStream = new FileInputStream("C:\\Users\\agarw\\Downloads\\sample_small.txt");
			
			}
			}
		long endTime2   = System.currentTimeMillis();
		long totalTime2 = endTime2 - startTime2;
		System.out.println(totalTime2+"k");
	}

	private static void Create_Tree_Using_Binry_Heap() throws IOException {
		Heap y = new Heap(0);
		long startTime = System.currentTimeMillis();
		//.....your program....
		
		for(Map.Entry<String, Integer> x :Ftable.entrySet())
		{
			if(!x.getKey().isEmpty())
			{
			
			y.insert(new HuffmanNode(x.getValue(),x.getKey()));
			}
			//y.printHeap();
		}
		while(!y.isEmpty())
		{
			
		HuffmanNode h1 = y.deleteMin();
		HuffmanNode h2 = null;
		if(!y.isEmpty())  
		{
			//HuffmanNode x = y.deleteMin();
			 h2= y.deleteMin();
		}
		else
		{
			HuffmanNode x = h1;
			break;
		}
		HuffmanNode n = new HuffmanNode(h1.val+h2.val);
		
		n.left= h1;
		n.left.dir=0;
		n.right = h2;
		n.right.dir=1;
		if(!y.isEmpty())
		{
			y.insert(n);
		}
		else
		{
			//printPaths(n) 	;
			File file = new File("C:\\Users\\agarw\\Downloads\\codeTable.txt");
		 	   FileWriter writer = new FileWriter(file,true);
		        
			printPaths(n,writer) 	;
			writer.close();
			Encode();
		
		}
		

		}
		

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime+"k");
	}
	
	public static void printPaths(HuffmanNode node,FileWriter writer) throws IOException 
    {
        int path[] = new int[1000];
        printPathsRecur(node, path, 0,writer);
    }
	public static void printPaths(PairingHuffmanNode node,FileWriter writer) throws IOException 
    {
        int path[] = new int[1000];
        printPathsRecur(node, path, 0,writer);
    }
  
    
}

   


