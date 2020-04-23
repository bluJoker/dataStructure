package HashTable;

public class Test {
    public static void main(String[] args){

        HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
        hashTable.add("wangw1", 1);
        hashTable.add("wangw2", 2);
        hashTable.add("wangw3", 3);
        hashTable.add("wangw2", 10);
        hashTable.add("wangw4", 4);
        hashTable.add("wangw5", 5);
        hashTable.add("wangw6", 6);
        hashTable.add("wangw7", 7);

    }
}
