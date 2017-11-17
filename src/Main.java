public class Main {



    public static void main(String[] args) {

        Sudoku sudoku1 = new Sudoku(Constants.sudokuArtoInkala);

        System.out.println(sudoku1);

        sudoku1.solve();

        System.out.println();

        System.out.println(sudoku1);
    }
}
