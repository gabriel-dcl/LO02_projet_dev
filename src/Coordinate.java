public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Coordinate obj) {
       if(this.x == obj.getX() && this.y == obj.getY())
           return true;
       return false;
    }

    public void setY(int y) {
        this.y = y;
    }
}
