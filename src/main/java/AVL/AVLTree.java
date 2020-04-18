package AVL;

import java.util.ArrayList;

public class AVLTree<Key extends Comparable<Key>, Value> {
    // 二叉查找树的根结点
    private Node root;

    // 私有类：表示树上结点的数据结构
    private class Node{
        private Key key;              // 键
        private Value val;            // 值
        private Node left, right;     // 指向子树的链接

        private int height;           // 结点的高度

        // 结点构造函数
        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.height = 1;           // 新添加的元素一定为叶子结点，故其高度为1
        }
    }

    // 判断某棵树是否是二叉搜索树：中序遍历的结果是有序的
    public boolean isBST(){
        ArrayList<Key> keys = new ArrayList<>();
        inorder(root, keys);

        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i-1)) < 0){
                return false;
            }
        }
        return true;
    }

    private void inorder(Node x, ArrayList<Key> list){
        if (x == null){
            return;
        }
        inorder(x.left, list);
        list.add(x.key);
        inorder(x.right, list);
    }

    // 判断该二叉树是否是平衡二叉树: 递归
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node x){
        if (x == null){
            return true;
        }
        if (Math.abs(getBalanceFactor(x)) >1){
            return false;
        }
        return isBalanced(x.left) && isBalanced(x.right);
    }


    // 获得节点的高度，需要该接口的原因为有必要对null做特殊处理
    private int getHeight(Node x){
        return (x == null) ? 0 : x.height;
    }

    // 计算某结点的平衡因子: 左右子树的高度差(left.height - right.height)，可能为负
    private int getBalanceFactor(Node x){
        return (x == null) ? 0 : getHeight(x.left) - getHeight(x.right);
    }


    // ***1. 查找Key=key元素的值
    public Value get(Key key){
        return get(root, key).val;
    }
    private Node get(Node x, Key key){
        if (key == null){
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null){
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return get(x.left, key);
        }else if (cmp > 0){
            return get(x.right, key);
        }else {
            return x;
        }
    }

    // ***2. 插入键值对key-val。如果已存在，仅更新值
    public void put(Key key, Value val){
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val){
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (x == null){
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = put(x.left, key, val);
        }else if (cmp > 0){
            x.right = put(x.right, key, val);
        }else {
            // 元素值相等，仅更新树中已存在元素的值
            x.val = val;
        }

        // 插入结点后，需要更新高度
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        // 计算插入新结点后的平衡因子
        int balanceFactor =  getBalanceFactor(x);

        // 当平衡因子的绝对值 > 1时，该树此时不为平衡二叉树，需要做处理，即重平衡
        if (Math.abs(balanceFactor) > 1){
            System.out.println("x = " + x.key + "  " + x.val + "  " + "unbalanced! 平衡因子 = " + balanceFactor);
        }


        return x;
    }

    public static void main(String[] args){
        AVLTree<Integer, String> st = new AVLTree<>();
        st.put(3, "S");
        st.put(2, "E");
        st.put(1, "A");
        st.put(4, "R");
        st.put(5, "C");
        st.put(6, "H");
        st.put(7, "E");
        st.put(10, "X");
        st.put(9, "A");
        st.put(8, "M");

        System.out.println("isBST : " + st.isBST());
        System.out.println("st.isBalanced() = " + st.isBalanced());
    }
}
