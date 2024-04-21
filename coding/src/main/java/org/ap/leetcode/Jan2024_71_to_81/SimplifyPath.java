package org.ap.leetcode.Jan2024_71_to_81;

import java.util.*;

class ListNode {
    String val;
    ListNode prev=null;
    ListNode next=null;
}
public class SimplifyPath {


    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty())
                stack.pop();
            else if (!skip.contains(dir))
                stack.push(dir);
        }
        String res = "";
        for (String dir : stack)
            res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }


    public String simplifyPath1(String path) {
        String[] strs = path.split("/");

        ListNode root = null,prev=null,curr = null;
        for(int i=0; i<strs.length;i++){
            String str = strs[i];
            System.out.println(str);
            if(curr==null) {
                root=null;
            }

            if(str.equals("")){
                continue;
            }else if(str.equals(".")){
                continue;
            } else if(str.equals("..")){
                //System.out.println(root.val +" vsr");
                if(curr==null) {
                    root=null;
                } else {
                    curr = curr.prev;
                    if(curr==null) {
                        root=null;
                        continue;
                    } else {
                        //if(curr!=null)
                        curr.next = null;
                    }
                }
            } else {
                if(root==null){
                    root = new ListNode();
                    root.val = str;
                    curr=root;
                }else {
                    ListNode node = new ListNode();
                    node.val=str;
                    node.prev = curr;

                    curr.next=node;
                    curr = node;
                }
            }
        }
        curr = root;
        String result ="";
        while(curr!=null) {
            System.out.println(curr.val + " awrviheir");
            result=result + "/" + curr.val;
            curr=curr.next;
        }
        if(result.equals("")) return "/";
        return result;
    }
}
