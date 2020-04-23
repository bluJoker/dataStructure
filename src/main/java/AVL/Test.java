package AVL;

/**
 * 插入后AVL树:
 *
 *                   4
 *                /     \
 *               2       7
 *              / \     / \
 *             1  3    6   9
 *                    /   / \
 *                   5   8  10
 *
 * */
public class Test {
    public static void main(String[] args){
        AVLTree<Integer, String> st = new AVLTree<>();
        st.insert(3, "S");
        st.insert(2, "E");
        st.insert(1, "A");
        st.insert(4, "R");
        st.insert(5, "C");
        st.insert(6, "H");
        st.insert(7, "E");
        st.insert(10, "X");
        st.insert(9, "A");
        st.insert(8, "M");

        System.out.println("isBST : " + st.isBST());
        System.out.println("st.isBalanced() = " + st.isBalanced());

        st.delete(3);
        System.out.println("isBST3 : " + st.isBST());
        System.out.println("st.isBalanced()3 = " + st.isBalanced());

        st.delete(2);
        System.out.println("isBST2 : " + st.isBST());
        System.out.println("st.isBalanced()2 = " + st.isBalanced());

        st.delete(1);
        System.out.println("isBST1 : " + st.isBST());
        System.out.println("st.isBalanced()1 = " + st.isBalanced());

        st.delete(4);
        System.out.println("isBST4 : " + st.isBST());
        System.out.println("st.isBalanced()4 = " + st.isBalanced());

        st.delete(5);
        System.out.println("isBST5 : " + st.isBST());
        System.out.println("st.isBalanced()5 = " + st.isBalanced());

        st.delete(6);
        System.out.println("isBST6 : " + st.isBST());
        System.out.println("st.isBalanced()6 = " + st.isBalanced());

        st.delete(7);
        System.out.println("isBST7 : " + st.isBST());
        System.out.println("st.isBalanced()7 = " + st.isBalanced());

        st.delete(10);
        System.out.println("isBST10 : " + st.isBST());
        System.out.println("st.isBalanced()10 = " + st.isBalanced());

        st.delete(9);
        System.out.println("isBST9 : " + st.isBST());
        System.out.println("st.isBalanced()9 = " + st.isBalanced());

        st.delete(8);
        System.out.println("isBST8 : " + st.isBST());
        System.out.println("st.isBalanced()8 = " + st.isBalanced());

    }
}
