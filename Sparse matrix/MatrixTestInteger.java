public class MatrixTestInteger extends MatrixTest<Integer>{


    private int a = 0;
    @Override
    public Integer getParameterInstance() {
        return new Integer(a++);
    }
}
