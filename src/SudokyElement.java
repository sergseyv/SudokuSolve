class SudokyElement {

    private int coordX;
    private int coordY;
    private int value;

    SudokyElement(int coordX, int coordY, int value) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.value = value;
    }

    int getCoordX(){
        return coordX;
    }

    int getCoordY(){
        return coordY;
    }

    int getValue(){
        return value;
    }
}
