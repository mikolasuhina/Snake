package mobile_challenge.snake;

/**
 * Created  on 20.03.2016.
 */
public class Cell {
    int x;
    int y;
    char s;
    long timedelay=10000;//час показу на екрані
    long endtime;//кінцева межа часу показу на екрані
    long bonustime;//час дії бонуса
    long endBonutime;//час закінчення дії бонуса

    public long getEndBonutime() {
        return endBonutime;
    }

    public void setEndBonutime(long endBonutime) {
        this.endBonutime = endBonutime;
    }

    public long getBonustime() {
        return bonustime;
    }

    public void setBonustime(long bonustime) {
        this.bonustime = bonustime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    boolean isEat;
    public long getTime() {
        return timedelay;
    }

    public void setTime(long time) {
        this.timedelay = time;
    }

    public Cell(int x, int y,char s) {
        this.x = x;
        this.y = y;
        this.s=s;
    }

    public boolean isEat() {
        return isEat;
    }

    public void setIsEat(boolean isEat) {
        this.isEat = isEat;
    }

    public Cell(int x, int y ,  char s, long endtime, boolean isEat) {
        this.x = x;
        this.isEat = isEat;
        this.endtime = endtime;

        this.s = s;
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

    public void setY(int y) {
        this.y = y;
    }

    public char getS() {
        return s;
    }

    public void setS(char s) {
        this.s = s;
    }
}
