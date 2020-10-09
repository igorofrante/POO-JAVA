package algoritmos;



public class TestarAVL {
	  public static void main(String[] args) throws Exception {
	    // let's contrast and compare
	    BST T = new BST();                                           // an empty BST
	    AVL A = new AVL();                                           // an empty AVL

	    int n = 12;
	    int[] arr = new int[]{15, 32, 100, 6, 23, 4, 7, 71, 5, 50, 3, 1};
	    for (int i = 0; i < n; i++) {
	      T.insert(arr[i]);
	      A.insert(arr[i]);
	    }

	    // Example of Java polymorphism: method getHeight() returns different value
	    System.out.println(T.getHeight());                         // 4, taller tree
	    System.out.println(A.getHeight());                        // 3, shorter tree

	    // Another Java polymorphism: method inorder() returns similar value
	    T.inorder();                      // The BST: 1 3 4 5 6 7 15 23 32 50 71 100
	    A.inorder();                      // The AVL: 1 3 4 5 6 7 15 23 32 50 71 100

	    System.out.println("---");
	    System.out.println(A.search(71));                               // found, 71
	    System.out.println(A.search(7));                                 // found, 7
	    System.out.println(A.search(22));                           // not found, -1

	    System.out.println(A.findMin());                                        // 1
	    System.out.println(A.findMax());                                      // 100

	    System.out.println("---");
	    System.out.println(A.successor(1));                                     // 3
	    System.out.println(A.successor(3));                                     // 4
	    System.out.println(A.successor(4));                                     // 5
	    System.out.println(A.successor(5));                                     // 6
	    System.out.println(A.successor(6));                                     // 7
	    System.out.println(A.successor(7));                                    // 15
	    System.out.println(A.successor(15));                                   // 23
	    System.out.println(A.successor(23));                                   // 32
	    System.out.println(A.successor(32));                                   // 50
	    System.out.println(A.successor(50));                                   // 71
	    System.out.println(A.successor(71));                                  // 100
	    System.out.println(A.successor(100));                                  // -1

	    System.out.println("---");
	    System.out.println(A.predecessor(1));                                  // -1
	    System.out.println(A.predecessor(3));                                   // 1
	    System.out.println(A.predecessor(4));                                   // 3
	    System.out.println(A.predecessor(5));                                   // 4
	    System.out.println(A.predecessor(6));                                   // 5
	    System.out.println(A.predecessor(7));                                   // 6
	    System.out.println(A.predecessor(15));                                  // 7
	    System.out.println(A.predecessor(23));                                 // 15
	    System.out.println(A.predecessor(32));                                 // 23
	    System.out.println(A.predecessor(50));                                 // 32
	    System.out.println(A.predecessor(71));                                 // 50
	    System.out.println(A.predecessor(100));                                // 71

	    // deletion demo
	    System.out.println("---");
	    System.out.print("Current BST/AVL:");
	    A.inorder();

	    int[] deletionorder = new int[]{23, 100, 32, 71, 50, 7, 5, 1, 3, 6, 15, 4};
	    for (int i = 0; i < n; i++) {
	      System.out.println("Deleting: " + deletionorder[i]);

	      A.delete(deletionorder[i]);
	      System.out.print("AVL, height: " + A.getHeight() + ", inorder traversal: ");
	      A.inorder();

	      T.delete(deletionorder[i]);
	      System.out.print("BST, height: " + T.getHeight() + ", inorder traversal: "); // equal or taller than A.getHeight()
	      T.inorder(); // should be the same as A.inorder()
	    }
	  }
}
