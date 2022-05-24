package il.ac.telhai.ds.hash;
import il.ac.telhai.ds.linkedlist.DLinkedList;

public class HashTable<V> {
    public static final int DEF_MAX_HASH_SIZE = 10;
    private DLinkedList<V>[] table;
    private int numberOfElement;
    private int size;
    /**
     * c'tor
     * construct a hash-table of max-size "DEF_MAX_HASH_SIZE".
     */
    @SuppressWarnings({"unchecked"})
    public HashTable() {
        table = new DLinkedList[DEF_MAX_HASH_SIZE];
        for (int i=0; i<DEF_MAX_HASH_SIZE; i++){
            this.table[i] = new DLinkedList<V>();
        }
        this.numberOfElement = 0;
        this.size = DEF_MAX_HASH_SIZE;
    }

    /**
     * construct a hash-table of size 'hashSize'.
     * @param hashSize, the size of the constructed hash-table.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public HashTable (int hashSize) {
        table = new DLinkedList[hashSize];
        for (int i=0; i<hashSize; i++){
            this.table[i] = new DLinkedList<V>();
        }
        this.numberOfElement = 0;
        this.size = hashSize;
    }

    /**
     * @param val
     * @return true if the hash-table contains val, otherwise return false
     */
    public boolean contains(V val) {
        if (isEmpty())
            return false;
        int index = func(val);
        if (this.table[index].isEmpty())
            return false;
        this.table[index].goToBeginning();
        while (this.table[index].hasNext()){
            if (this.table[index].getCursor().equals(val)){
                return true;
            }
            this.table[index].getNext();
        }
        if (this.table[index]!= null){
            return this.table[index].getCursor().equals(val);
        }
        return false;
    }

    /**
     * Add val to the hash-table.
     *
     * @param val
     * @return If the val has already exist in the the hash-table, it will not be
     *         added again. Return true if the val was added successfully. Otherwise
     *         return false.
     */
    public boolean add(V val) {
        if (this.contains(val)){
            return false;
        }
        int index = func(val);
        this.table[index].goToEnd();
        this.table[index].insert(val);
        this.numberOfElement++;
        return true;
    }

    /**
     * Remove val from the hash-table.
     *
     * @param val
     * @return Return true if the val was removed successfully. Otherwise return
     *         false.
     */
    public boolean remove(V val) {
        if (!this.contains(val)){
            return false;
        }
        int index = func(val);
        this.table[index].remove(val);
        this.numberOfElement--;
        return true;
    }

    /**
     * clear al the data in the hash-table
     */
    public void clear() {
        this.numberOfElement = 0;
        for (int i=0; i<this.size; i++){
            this.table[i].clear();
        }
    }

    /**
     * @return true if the hash-table is empty, otherwise return false.
     */
    public boolean isEmpty() {
        return this.numberOfElement == 0;
    }

    private int func(V val){
        return val.hashCode() % this.size;
    }
}