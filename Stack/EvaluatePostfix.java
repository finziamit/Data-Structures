package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();

	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');
		double res = 0;
		while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
			if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
				if (tokenizer.sval.equals("quit")) {
					break;
				} else {
					System.err.println(tokenizer);
					System.err.println(myStack);
					System.exit(1);
				}
			} else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
				myStack.push(tokenizer.nval);
			} else {
				try {
					char c = (char) tokenizer.ttype;
					double a = myStack.pop();
					double b = myStack.pop();
					if (c=='+'){
						res = b + a;
					}
					if (c=='-'){
						res = b - a;
					}
					if (c=='*'){
						res = b * a;
					}
					if (c=='/'){
						res = b / a;
					}
					myStack.push(res);
				} catch (Exception e) {
					System.err.println(tokenizer);
					System.err.println(myStack);
					System.exit(1);
				}
			}
		}
		if (myStack.isEmpty()) {
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		} else {
			res = myStack.pop();
			if (!myStack.isEmpty()) {
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
			}
		}
		System.out.println(res);
	}
}
