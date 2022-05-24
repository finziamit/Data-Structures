package il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack<T>  {
    private DLinkedList<T> lst;
    public DLinkedListStack(){
        lst = new DLinkedList<T>();
    }
    @Override
    public void push(T t) {
        lst.goToEnd();
        lst.insert(t);
    }

    @Override
    public T pop() {
        T temp = lst.remove();
        lst.goToEnd();
        return temp;
    }

    @Override
    public T top() {
        return lst.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return lst.isEmpty();
    }
    @Override
    public String toString(){
        String str = "";
        lst.goToEnd();
        str = str.concat("[");
        T temp = lst.getCursor();
        while(temp != null){
            str = str.concat(temp.toString());
            if (lst.hasPrev()){
                str = str.concat(", ");
            }
            temp = lst.getPrev();
        }
        str = str.concat("]");
        return str;
    }
}