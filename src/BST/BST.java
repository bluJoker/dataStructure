package BST;

public class BST<Key extends Comparable<Key>, Value> {
    // 二叉查找树的根结点
    private Node root;

    // 私有类：表示树上结点的数据结构
    private class Node{
        private Key key;              // 键
        private Value val;            // 值
        private Node left, right;     // 指向子树的链接

        // 结点计数器：以该节点为根的子树的结点总数
        // 作用：简化某些操作的实现，select、rank
        public int N;


        // 结点构造函数
        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }
    private int size(Node x){
        if (x == null){
            return 0;
        }else {
            return x.N;
        }
    }


    /**
     * -----------------------------------------------------------------------------------------------------------------
     * 递归
     * -----------------------------------------------------------------------------------------------------------------
    */
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
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // ***3. 返回最小键
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if (x.left == null){
            return x;
        }
        return min(x.left);
    }

    // 4. 返回最大键
    public Key max(){
        return max(root).key;
    }
    private Node max(Node x){
        if (x.right == null){
            return x;
        }
        return min(x.right);
    }

    // ***5. 向下取整：小于等于key的最大键
    public Key floor(Key key){
        Node x = floor(root, key);
        if (x != null){
            return x.key;
        }else {
            return null;
        }
    }
    private Node floor(Node x, Key key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        }else if (cmp > 0){
            Node t = floor(x.right, key);
            if (t == null){
                return x;
            }else {
                return t;
            }
        }else {
            return x;
        }
    }

    // 6. 向上取整
    public Key ceiling(Key key){
        Node x = ceiling(root, key);
        if (x != null){
            return x.key;
        }else {
            return null;
        }
    }
    private Node ceiling(Node x, Key key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return floor(x.right, key);
        }else if (cmp < 0){
            Node t = floor(x.left, key);
            if (t == null){
                return x;
            }else {
                return t;
            }
        }else {
            return x;
        }
    }

    // ***7. 找到排名为k的键：即树中正好有k个小于它的键
    public Key select(int k){
        Node x = select(root, k);
        if (x == null){
            return null;
        }else {
            return x.key;
        }
    }
    private Node select(Node x, int k){
        if (x == null){
            return null;
        }
        int t = size(x.left);
        if (t == k){
            return x;
        }else if (t < k){
            return select(x.right, k - t - 1);
        }else {
            return select(x.left, k);
        }
    }

    // ***8. 返回给定键的排名
    public int rank(Key key){
        return rank(root, key);
    }
    private int rank(Node x, Key key){
        if (x == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return rank(x.left, key);
        }else if (cmp > 0){
            return rank(x.right, key) + size(x.left) + 1;
        }else {
            return size(x.left) + 1;
        }
    }

    // ***9. 删除最小元素
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if (x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 10. 删除最大元素
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node x){
        if (x.right == null){
            return x.left;
        }
        x.right = deleteMin(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // ***11. 删除键为key的元素
    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node x, Key key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left, key);
        }else if (cmp > 0){
            x.right = delete(x.right, key);
        }else {
            if (x.left == null){
                return x.right;
            }else if (x.right == null){
                return x.left;
            }else {
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // ***12. 中序遍历（有序）
    public void print(Node x){
        if (x == null){
            return;
        }
        print(x.left);
        System.out.println(x.key + " -> " + x.val);
        print(x.right);
    }



    /**
     * -----------------------------------------------------------------------------------------------------------------
     * 迭代
     * -----------------------------------------------------------------------------------------------------------------
     * */

    public static void main(String[] args){
        BST<String, Integer> st = new BST<String, Integer>();
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        st.put("E", 6);
        st.put("X", 7);
        st.put("A", 8);
        st.put("M", 9);
        st.put("P", 10);
        st.put("L", 11);
        st.put("E", 12);
        st.print(st.root);

        System.out.println("st.get(\"M\") = " + st.get("M"));
        System.out.println("st.min() = " + st.min());
        System.out.println("st.max() = " + st.max());
        System.out.println("st.floor(\"H\") = " + st.floor("H"));
        System.out.println("st.floor(\"V\") = " + st.floor("V"));
        System.out.println("st.ceiling(\"O\") = " + st.ceiling("O"));
        System.out.println("st.select(8) = " + st.select(8));
        System.out.println("st.rank(\"R\") = " + st.rank("R"));

        System.out.println("---------deleteMin--------");
        st.deleteMin();
        st.print(st.root);

        System.out.println("---------deleteMax--------");
        st.deleteMax();
        st.print(st.root);

        System.out.println("---------delete P--------");
        st.delete("P");
        st.print(st.root);
    }
}

