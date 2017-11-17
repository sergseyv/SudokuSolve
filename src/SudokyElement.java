/**
 * Created by Serg on 12.10.2017.
 */
public class SudokyElement {
    private int coordX;
    private int coordY;
    private int value;

    public SudokyElement(int coordX, int coordY, int value) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.value = value;
    }

    public int getCoordX(){
        return coordX;
    }

    public int getCoordY(){
        return coordY;
    }

    public int getValue(){
        return value;
    }
}
