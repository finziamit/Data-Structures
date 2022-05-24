import java.util.Scanner;

public class DiagonalMatrix implements Matrix{
    private int MAX_SIZE = 100;
    private double matrix[];
    private int size;
    private int trans = 1;
    DiagonalMatrix(int size){
        if (size < 1){
            throw new RuntimeException("Size must be bigger then 0");
        }
        this.size = size;
        matrix = new double[2*size -1];
    }
    DiagonalMatrix(){
        matrix = new double[2*MAX_SIZE -1];
        size = MAX_SIZE;
    }
    @Override
    public double get(int i, int j) {
        if (i < 0 || i > size || j < 0 || j > size){
            throw new RuntimeException("illegal index");
        }
        return matrix[(size+(j-i)*trans)-1];
    }

    @Override
    public void set(int i, int j, double x) {
        if (i < 0 || i > size || j < 0 || j > size){
            throw new RuntimeException("illegal index");
        }
        matrix[(size+(j-i)*trans)-1] = x;
    }

    @Override
    public void transpose() {
        trans *= -1;
    }

    @Override
    public Matrix getTranspose() {
        Matrix mat = new DiagonalMatrix(size);
        transpose();
        for (int i=size-1;i>=0;i--){
            mat.set(i,0,get(i,0));
        }
        for (int i=1;i<size;i++){
            mat.set(0,i,get(0,i));
        }
        transpose();
        return mat;
    }
    public String toString(){
        String str = "";
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                str = str.concat(get(i, j) +"\t");
            }
            str = str.concat("\n");
        }
        return str;
    }
    public static void main(String args[]){
        DiagonalMatrix mat = new DiagonalMatrix(5);
        mat.set(0,0,1);
        mat.set(0,1,3);
        mat.set(0,2,4);
        mat.set(0,3,8);
        mat.set(0,4,9);
        mat.set(1,0,2);
        mat.set(1,1,1);
        mat.set(1,2,3);
        mat.set(1,3,4);
        mat.set(1,4,8);
        mat.set(2,0,5);
        mat.set(2,1,2);
        mat.set(2,2,1);
        mat.set(2,3,3);
        mat.set(2,4,4);
        mat.set(3,0,6);
        mat.set(3,1,5);
        mat.set(3,2,2);
        mat.set(3,3,1);
        mat.set(3,4,3);
        mat.set(4,0,7);
        mat.set(4,1,6);
        mat.set(4,2,5);
        mat.set(4,3,2);
        mat.set(4,4,1);
        /*mat.set(0,0,2);
        mat.set(0,1,1);
        mat.set(0,2,3);
        mat.set(0,3,4);
        mat.set(1,0,5);
        mat.set(1,1,2);
        mat.set(1,2,1);
        mat.set(1,3,3);
        mat.set(2,0,6);
        mat.set(2,1,5);
        mat.set(2,2,2);
        mat.set(2,3,1);
        mat.set(3,0,7);
        mat.set(3,1,6);
        mat.set(3,2,5);
        mat.set(3,3,2);*/
        //mat.transpose();
        System.out.print(mat.getTranspose().toString());



    }
}


