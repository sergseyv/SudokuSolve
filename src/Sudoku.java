import java.util.Stack;


public class Sudoku {

    private int[][] field;            // Рабочее поле судоку - матрица
    private int[][] startfield;       // Хранит стартовое состояние (условие задачи)


    Sudoku ( int[][] taskField ) {

        field = new int[9][9];
        startfield = new int[9][9];

        for ( int x = 0; x < 9; x++) {  //заполняем матрицы - рабочую и стартовую
            for ( int y = 0; y < 9; y++) {
                startfield[x][y] = taskField[x][y];
                field[x][y] = taskField[x][y];
            }
        }
    }


    /* Переопределение вывода судоку*/
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for ( int x = 0; x < 9; x++ ) {
            StringBuilder resStart = new StringBuilder();
            StringBuilder resSolve = new StringBuilder();

            for ( int y = 0; y < 9; y++ ) {

                if ( startfield[x][y] == 0 ) resStart.append("*");
                else resStart.append ( startfield[x][y] );
                resStart.append(" ");

                if ( field[x][y] == 0 ) resSolve.append("*");
                else resSolve.append ( field[x][y] );
                resSolve.append(" ");
            }
            result.append(resStart.toString()).append("     ").append(resSolve.toString()).append("\n");
        }
        return result.toString();
    }


    /*Проверка элемента на уникальность в строке, столбце и квадрате 3х3*/
    private boolean elemIsUnique(int num, int row, int col){

        return  isUniqueInColumn(num, col) &
                isUniqueInRow(num, row) &
                isUniqueInSquare(num, row, col);
    }


    /*Проверка элемента на уникальность в строке*/
    private boolean isUniqueInRow(int num, int row){

        boolean result = true;
        for (int col = 0; col < 9; col++)
            if (num == field[row][col]) {
                result = false;
                break;
            }
        return result;
    }


    /*Проверка элемента на уникальность в столбце*/
    private boolean isUniqueInColumn(int num, int col){

        boolean result = true;
        for (int row = 0; row < 9; row++)
            if (num == field[row][col]) {
                result = false;
                break;
            }
        return result;
    }

    /*Проверка элемента на уникальность в квадрате 3х3*/
    private boolean isUniqueInSquare (int num, int row, int col) {

        int startX, startY;
        boolean result = true;

        if (row < 3) startX = 0;
            else if (row > 5) startX = 6;
                else startX = 3;

        if (col < 3) startY = 0;
            else if (col > 5) startY = 6;
                else startY = 3;

        for (int x = startX; x < startX + 3; x++)
            for (int y = startY; y < startY + 3; y++)

                if ( num == field[x][y] ) {
                    result = false;
                    break;
                }
        return result;
    }

    // одна итерация решения
    private void oneStep(int inputStartX, int inputStartY, int inputStartValue, Stack<SudokyElement> stHypothesis){

        boolean incorrectHypothesis = false;    //признак того, что вычисление пошло по неверной гипотезе
        boolean elemFound;       //признак обнаружения элемента уникального в строке, столбце и квадрате 3х3
        int startValue;
        int StartY = inputStartY;

        for ( int x = inputStartX; (!incorrectHypothesis) & (x < 9); x++ ) {  //перебираем элементы матрицы
            for ( int y = StartY; ( !incorrectHypothesis ) & ( y < 9); y++ ) {

                if ( field[x][y] == 0 ) {           //попался нулевой элемент (т.е. значение не определено)

                    elemFound = false;              //подходящее значение элемента пока не найдено

                    /*если обрабатывается первый элемент текущего шага, то перебор значений
                    продолжается с текущего значения, иначе - начинается с единицы*/
                    if ( (x == inputStartX) & (y == inputStartY) )
                        startValue = inputStartValue;
                    else startValue = 1;

                    /*Поиск элемента, уникального в строке, столбце и квадрате 3х3*/
                    for ( int val = startValue; (!elemFound) & (val <= 9); val++ ) {

                        if ( elemIsUnique(val, x, y) ) {     //нашли того, кто прошел все 3 проверки

                            field[x][y] = val;
                            elemFound = true;
                            stHypothesis.push(new SudokyElement(x, y, val));
                        }
                    }
                    if (!elemFound) incorrectHypothesis = true;
                }
            }
            StartY = 0;
        }
    }


    void solve(){

        Stack<SudokyElement> stHypothesis = new Stack<>();

        oneStep(0,0,1, stHypothesis);

        while ( !stHypothesis.empty() & !isSolved() ) {

            SudokyElement element = stHypothesis.pop();
            int x = element.getCoordX();
            int y = element.getCoordY();
            int k = element.getValue();

            field[x][y] = 0;

            oneStep(x, y,k + 1, stHypothesis);
        }

        if ( isSolved() )
            System.out.println("Sudoku solved!");
        else
            System.out.println("Sudoku has no solution.");

        System.out.println(this);
    }


    private boolean isSolved(){

        boolean sol = true;

        for ( int i = 0; i < 9; i++ ) {
            for ( int j = 0; j < 9; j++ ) {
                if ( field[i][j] == 0 )
                    sol = false;
            }
        }
        return sol;
    }

}
