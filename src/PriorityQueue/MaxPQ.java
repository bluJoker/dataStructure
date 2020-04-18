package PriorityQueue;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            // 找出孩子中较大者
            if (j < N && less(j, j+1)){
                j++;
            }
            if (!less(k, j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * 插入元素:
     * 将新元素加到数组末尾，增加堆的大小并让这个新元素上浮到合适的位置
     * */
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    /**
     * 删除最大元素:
     * 从数组顶端删去最大的元素并将数组的最后一个元素放到顶端，减小堆的大小并让这个元素下沉到合适的位置
     * */
    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }
}
