package RBTree;

public class RBTree<Key extends Comparable<Key>, Value> {
    private static final boolean BLACK = false;
    private static final boolean RED = true;

    // 二叉查找树的根结点
    private Node root;

    // 私有类：表示树上结点的数据结构
    private class Node{
        private Key key;              // 键
        private Value val;            // 值
        private Node left, right;     // 指向子树的链接
        private boolean color;        // 结点颜色

        // 结点构造函数
        public Node(Key key, Value val){
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;

            // 默认颜色为红色，因为在2-3树中新加入结点总要去和某结点融合，故将新结点默认颜色设为红色
            this.color = RED;
        }
    }

    // 判断结点x的颜色，主要对空做特殊处理
    private boolean isRed(Node x){
        if (x == null){
            // 对应红黑手性质：每一个叶子结点（最后的空结点）是黑色的
            return BLACK;
        }
        return x.color;
    }


}
