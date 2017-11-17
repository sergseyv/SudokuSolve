public class Main {



    public static void main(String[] args) {

        Sudoku sudoku1 = new Sudoku(Constants.sudokuTest001);
        sudoku1.solve();

        for(int[] g1 : Constants.sudokuTest001) {
            for(int g2 : g1) {
                System.out.print(g2+" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println(sudoku1.toString());
    }
}
