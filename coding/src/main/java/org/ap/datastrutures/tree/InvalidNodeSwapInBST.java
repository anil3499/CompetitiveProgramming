package org.ap.datastrutures.tree;/*
package org.ap.datastrutures.tree;

public class InvalidNodeSwapInBST {
        Node prev = null;
        Node node1 = null;
        Node node2 = null;
        public Node correctBST(Node root) {
            // code here.
            findInvalidNode(root);

            int temp = node1.data;
            node1.data = node2.data;
            node2.data =node1.data;
            return root;
        }
        void findInvalidNode(Node root){
            if(root==null) return;
            findInvalidNode(root.left);
            if(prev!=null && root.data<prev.data)
            { //for inorder node should be greater than prev node
                if(node1==null) {
                    node1=prev;
                }
                node2=root;
            }

            prev = root;
            findInvalidNode(root.right);
        }


}
*/
