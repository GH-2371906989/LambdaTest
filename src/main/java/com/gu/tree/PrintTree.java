package com.gu.tree;

import com.alibaba.fastjson2.JSON;

import java.util.*;

public class PrintTree {
    static Map<CUSTreeMap.CusNode,Integer> map =new HashMap<>();


    public static void main(String[] args) {
        CUSTreeMap<Integer, String> treeMap = new CUSTreeMap<>();
        treeMap.put(1,"第1");
        treeMap.put(3,"第3");
        treeMap.put(2,"第2");
        treeMap.put(4,"第4");
        treeMap.put(5,"第5");
        tests(treeMap);
    }

     static void tests(CUSTreeMap<Integer, String> treeMap) {
        PrintTree printTree = new PrintTree();
        List<List<CUSTreeMap.CusNode>> lists = printTree.levelOrder(treeMap.getRoot());
        System.out.println(JSON.toJSONString(lists));
        List<CUSTreeMap.CusNode> mid = new ArrayList<>();
        Map<CUSTreeMap.CusNode,Integer> map =new HashMap<>();
        printTree.midOrder(treeMap.getRoot(),mid);
        for (int i = 0; i < mid.size(); i++) {
            map.put(mid.get(i),i);
        }

        for (int i = 0; i < lists.size(); i++) {
            List<CUSTreeMap.CusNode> list = lists.get(i);
            String str = "";
            for (int j = 0; j < list.size(); j++) {
                CUSTreeMap.CusNode cusNode = list.get(j);
                int wz = map.get(cusNode);
                int lwz = 0;
                if (j>0){
                    lwz = map.get(list.get(j-1));
                }
                String empty = printTree.getEmpty((lwz==0?wz:wz-lwz) * 2);
                String data = "";
                if(!cusNode.color)
                    data+= empty +"\033[1;95m"+cusNode.key+"\033[0m";//打印红色
                else
                    data += empty+cusNode.key;
                System.out.print(data);
                if (j==list.size()-1){
                    System.out.println();
                    System.out.println(str==null?"":str);
                }
            }
        }

    }

    String  getEmpty(int x){
        String empty ="";
        for (int i = 0; i < x; i++) {
            empty+="\t";
        }
        return empty;
    }

    public List<List<CUSTreeMap.CusNode>> levelOrder(CUSTreeMap.CusNode root) {
        List<List<CUSTreeMap.CusNode>> res = new ArrayList<>();
        Queue<CUSTreeMap.CusNode> queue = new ArrayDeque<>();
        if (root == null) {
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            List<CUSTreeMap.CusNode> list = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                CUSTreeMap.CusNode node = queue.poll();
                list.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
            res.add(list);
        }
        return res;
    }

    /* 中序遍历 */
    public   void midOrder(CUSTreeMap.CusNode<Integer, String> root, List<CUSTreeMap.CusNode> mid) {
        if(root == null)return;
        midOrder(root.left,mid);
        mid.add(root);
        midOrder(root.right,mid);
    }



}
