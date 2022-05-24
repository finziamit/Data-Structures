package il.ac.telhai.ds.trees;

import java.io.IOException;
import java.io.StreamTokenizer;

public class ExpressionTree extends FullBinaryTree<String>{
    public ExpressionTree(String value) {
        super(value);
    }

    public ExpressionTree(String value, BinaryTreeI<String> left, BinaryTreeI<String> right) {
        super(value, left, right);
    }

    /*
     * Read the stream tokenizer until EOF assuming and construct
     * the expression tree corresponding to it.
     * The input contains a prefix expression.
     */

    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
        try{
            char c;
            tokenizer.nextToken();
            c = (char) tokenizer.ttype;
            if (c == '+' || c == '-' || c == '*' || c == '/'){
                return new ExpressionTree(Character.toString(c),createTree(tokenizer),createTree(tokenizer));
            }
            else{
                return new ExpressionTree(String.valueOf((int) tokenizer.nval));
            }
        }
        catch (Exception e){
            throw e;
        }
    }
    /*
     * Returns the infix expression corresponding to the current tree (*)
     */
    public String infix() {
        String str = "";
        if(getLeft() != null){
            str = str.concat("(");
            str = str.concat(((ExpressionTree) getLeft()).infix());
        }
        if(!isNumeric(this.getValue())){
            str = str.concat(" ");
            str = str.concat(this.getValue());
            str = str.concat(" ");
        }
        else{
            str = str.concat(this.getValue());
        }

        if(getRight() != null){
            str = str.concat(((ExpressionTree) getRight()).infix());
            str = str.concat(")");
        }
        return str;
    }
    /*
     * Returns the prefix expression corresponding to the current tree (*)
     */
    public String prefix() {
        return this.preOrder();
    }


    /*
     * Evaluates the expression corresponding to the current tree
     * and returns its value
     */
    public double evaluate() {
        if (!isNumeric(getValue())) {
            if (getValue().equals("*")) {
                return ((ExpressionTree) getLeft()).evaluate() * ((ExpressionTree) getRight()).evaluate();
            }
            if (getValue().equals("/")) {
                return ((ExpressionTree) getLeft()).evaluate() / ((ExpressionTree) getRight()).evaluate();
            }
            if (getValue().equals("+")) {
                return ((ExpressionTree) getLeft()).evaluate() + ((ExpressionTree) getRight()).evaluate();
            }
            if (getValue().equals("-")) {
                return ((ExpressionTree) getLeft()).evaluate() - ((ExpressionTree) getRight()).evaluate();
            }
        }
        return Double.parseDouble(this.getValue());
    }

    private static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
