public class MatrixTestString extends MatrixTest<String>{
    private String s;
    @Override
    public String getParameterInstance() {
        s = Integer.toString((int) ((Math.random() * (1000 - 1)) + 1));
        return s;
    }
}
