package il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T> {
    public FullBinaryTree(T value) {
        super(value);
        setLeft(null);
        setRight(null);
    }

    public FullBinaryTree(T value, BinaryTreeI<T> left, BinaryTreeI<T> right) {
        super(value, left, right);
        if (left == null && right != null){
            throw new RuntimeException();
        }
        if (left != null && right == null){
            throw new RuntimeException();
        }

    }
    @Override
    public void setLeft(BinaryTreeI<T> left){
        if (!(left instanceof FullBinaryTree) && left != null){
            throw new RuntimeException();
        }
        if (left != null) {
            if (left.getLeft() == null || left.getRight() == null) {
                if (this.isLeaf()) {
                    throw new RuntimeException();
                }
            }
        }
        if (left != null){
            if (left.getLeft() == null && left.getRight() == null){
                super.setLeft(left);
                return;
            }
        }
        if (getLeft() == null && left == null){
            return;
        }
        throw new RuntimeException();

    }
    @Override
    public void setRight(BinaryTreeI<T> right){
        if (!(right instanceof FullBinaryTree) && right != null){
            throw new RuntimeException();
        }
        if (right != null) {
            if (right.getLeft() == null || right.getRight() == null) {
                if (this.isLeaf()) {
                    throw new RuntimeException();
                }
            }
        }
        if (right != null){
            if (right.getLeft() == null && right.getRight() == null){
                super.setRight(right);
                return;
            }
        }
        if (getRight() == null && right == null){
            return;
        }
        throw new RuntimeException();

    }

    /*@Override
    public void setLeft(BinaryTreeI<T> left) {
        //if (!isFullTree(left)) {
        //    throw new RuntimeException();
        //}
        setLeft(left);
    }*/

    /*public boolean isFullTree(BinaryTreeI<T> tree){
        if(tree == null)
            return true;
        if(tree.getLeft() == null && tree.getRight() == null )
            return true;
        if((tree.getLeft()!=null) && (tree.getRight()!=null))
            return (isFullTree(tree.getLeft()) && isFullTree(tree.getRight()));
        return false;
    }*/
}

