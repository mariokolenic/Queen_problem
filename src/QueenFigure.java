public class QueenFigure {
    private int riadok;
    private int stlpec;
    private char image = '♛';

    public QueenFigure(int riadok, int stlpec) {
        this.riadok = riadok;
        this.stlpec = stlpec;
    }

    public char getImage() {
        return image;
    }

    public int getRiadok() {
        return riadok;
    }

    public void setRiadok(int riadok) {
        this.riadok = riadok;
    }

    public int getStlpec() {
        return stlpec;
    }

    public void setStlpec(int stlpec) {
        this.stlpec = stlpec;
    }
}
