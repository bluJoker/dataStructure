package HashTable;

import java.util.TreeMap;

// 哈希表的key不需要比较，所以不extends Comparable接口
public class HashTable<Key, Value> {
    private TreeMap<Key, Value>[] hashtable;
    private int M;
    private int size;

    public HashTable(int M) {
        this.M = M;
        this.hashtable = new TreeMap[M];
        this.size = 0;
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable(){
        this(97);
    }

    private int hash(Key k){
        int hashh = k.hashCode();
        return (hashh & 0x7fffffff) % M;
    }


    public void add(Key key, Value value){
        int hashcode = hash(key);
        TreeMap<Key, Value> map = hashtable[hashcode];
        if (!map.containsKey(key)){
            size++;
        }
        map.put(key, value);
    }

    public Value remove(Key key){
        int hashcode = hash(key);
        TreeMap<Key, Value> map = hashtable[hashcode];
        Value ret = null;
        if (map.containsKey(key)){
            ret = map.remove(key);
            size--;
        }
        return ret;
    }

    public Value get(Key key){
        int hashcode = hash(key);
        return hashtable[hashcode].get(key);
    }
}
