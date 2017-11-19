/*
* Решение судоку методом обхода в глубину (перебор с возвратом)
*/

public class Main {

    public static void main(String[] args) {

        Sudoku sudokuAI = new Sudoku(Constants.sudokuArtoInkala);
        sudokuAI.solve();

        Sudoku sudokuEx1 = new Sudoku(Constants.sudokuExample1);
        sudokuEx1.solve();

        Sudoku sudokuNoSol = new Sudoku(Constants.sudokuNoSolution);
        sudokuNoSol.solve();

        Sudoku sudokuEx2 = new Sudoku(Constants.sudokuExample2);
        sudokuEx2.solve();

    }
}
