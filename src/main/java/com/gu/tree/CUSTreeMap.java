package com.gu.tree;


import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
public class CUSTreeMap<K extends Comparable<K>,V>  implements Cloneable, java.io.Serializable{
    public CusNode<K, V> getRoot() {
        return root;
    }

    //定义全局根节点
    private transient CusNode<K,V> root;
    /* 定义比较器 */
//    private final Comparator<? super K> comparator;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CUSTreeMap<Integer, String> treeMap = new CUSTreeMap<>();
        while (true){
            String line = sc.nextLine();
            if (line.equals("")) break;
            treeMap.put(new Integer(line),line);
            new PrintTree().tests(treeMap);
        }

    }

    public V put(K key, V value) {
        /* 当key是null是返回异常 */
        if (key == null){
            throw new NullPointerException();
        }
        /* 获取根节点 */
        CusNode<K,V> r = root;
        if (root == null){  // 根节点为空时,直接添加即可
            root = new CusNode<K,V>(key,value,null);
            return null;
        }
        int compareResult; //判断插入位置  <0 左  大于0  右
        CusNode<K,V> parent; //插入的父节点
        do {
            parent = r;  //循环 找出插入的父节点
            compareResult = key.compareTo(r.key);  //比较key值判断插入位置
            if (compareResult<0){
                r = r.left;  //循环找出插入位置
            }else if (compareResult>0){
                r = r.right; //循环找出插入位置
            }else{
                r.value = value; //相等则改变值并返回
                return null;
            }
        }while (r!=null);   //当插入节点为null时  返回找到插入的父节点
        CusNode<K, V> cNode = new CusNode<>(key, value, parent);
        if (compareResult<0){
            parent.left = cNode;  //插入节点
        }else{
            parent.right = cNode;  //插入节点
        }

        /*  插入节点后调整位置 旋转加变色 */
        fixAfterPut(cNode);
        return null;
    }

    private void fixAfterPut(CusNode<K,V> cNode) {
        cNode.color = RED;
        while (cNode != null && cNode != root && cNode.parent.color == RED) {
            //判断父节点是爷爷节点的右孩子
            if (getparent(cNode)==getright(getparent(getparent(cNode)))){
                CusNode<K, V> getleft = getleft(getparent(getparent(cNode)));//叔叔节点
                if (colorOf(getleft) == RED) {
                    setColor(getparent(cNode), BLACK);
                    setColor(getleft, BLACK);
                    setColor(getparent(getparent(cNode)), RED);
                    cNode = getparent(getparent(cNode));
                }
                else {
                    if (cNode == getleft(getparent(cNode))) {
//                        cNode = parentOf(cNode);
//                        rotateRight(cNode);
                    }
                    setColor(getparent(cNode), BLACK);
                    setColor(getparent(getparent(cNode)), RED);
                    leftrotate(getparent(getparent(cNode)));
                }
            }
        }
        root.color = BLACK;
    }
    private void leftrotate(CusNode<K,V> cNode) {
        if (cNode!=null){
            CusNode<K,V> r = cNode.right;
            cNode.right = r.left;
            if (r.left!=null){
                r.left.parent =cNode;
            }
            r.parent = cNode.parent ;
            if (cNode.parent == null){
                root = r;
            }else if(cNode.parent.right == cNode){
                cNode.parent.right = r;
            }else {
                cNode.parent.left = r;
            }
            r.left = cNode;
            cNode.parent = r;
            r.parent = cNode.parent;
        }
    }


    private  void setColor(CusNode<K,V> cNode, boolean c) {
        if (cNode != null)
            cNode.color = c;
    }
    private boolean colorOf(CusNode<K,V> cNode) {
        return (cNode == null ? BLACK : cNode.color);
    }
    private CusNode<K,V> getparent(CusNode<K,V> cNode) {
        return (cNode == null) ? null: cNode.parent;
    }
    private CusNode<K,V> getleft(CusNode<K,V> cNode) {
        return (cNode == null) ? null: cNode.left;
    }
    private CusNode<K,V> getright(CusNode<K,V> cNode) {
        return (cNode == null) ? null: cNode.right;
    }


    private static final boolean RED   = false;
    private static final boolean BLACK = true;
    //定义实体
    static final class CusNode<K extends Comparable<K>,V>{
        K key;
        V value;
        CusNode<K,V> left;
        CusNode<K,V> right;
        CusNode<K,V> parent;
        Boolean color = BLACK;

        CusNode(K key, V value, CusNode<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;

            return valEquals(key,e.getKey()) && valEquals(value,e.getValue());
        }
        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }
    static final boolean valEquals(Object o1, Object o2) {
        return (o1==null ? o2==null : o1.equals(o2));
    }
}
