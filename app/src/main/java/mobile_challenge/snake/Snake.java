package mobile_challenge.snake;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kolas on 20.03.2016.
 */
public class Snake {
    int size;//довжина
    int x;//позиція по х голови
    int y;//позиція по y голови
    int score;//очки
    ArrayList<Cell> snakecells =new ArrayList<Cell>();//кількість клітинок змії
    int dx;//напрямок руху змії по х: 1-праворуч;-1 -ліворуч
    int dy;//напрямок руху змії по y:1- вгору;-1 - вниз
    int endx;//координата по х останньої клітинка змії;
    int endy;//координата по y останньої клітинка змії;
    public static final int maxlenght=40;//максимальна довжина змії
    Random r =new Random();
    public ArrayList<Cell> getSnake() {
        return snakecells;
    }

    public void setSnake(ArrayList<Cell> snake) {
        this.snakecells = snake;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Snake(int x, int y) {
        this.size = 3;
        this.x = x;
        this.y = y;
        dy=0;
        this.dx = r.nextInt(3)-1;
        if(dx==0){
            while (dy==0)
       this.dy =r.nextInt(3)-1;
        }
        else dy=0;

        int addx=0;
        int addy=0;
switch (dx){
    case 1 : addx=-1; break;
    case -1 : addx=1; break;
    default:addx=0;
}
        switch (dy){
            case 1 : addy=-1; break;
            case -1 : addy=1; break;
            default:addy=0;
        }
        snakecells.add(new Cell(x,y,'Y'));
        for (int i = 1; i <size ; i++) {
            snakecells.add(new Cell(x+addx,y+addy,'@'));

            switch (dx){
                case 1 : addx--; break;
                case -1 : addx++; break;
                default:addx=0;
            }
            switch (dy){
                case 1 : addy--; break;
                case -1 : addy++; break;
                default:addy=0;
            }
        }
        snakecells.get(0).setS('Y');
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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


    void update(){
        move();

        if(dx==1){
            x++;
           snakecells.get(0).setX(x);}
        else if(dx==-1) {x--;snakecells.get(0).setX(x);}
       if(dy==1){
            y++;
            snakecells.get(0).setY( y);}else
        if(dy==-1){y--; snakecells.get(0).setY(y);};

size=snakecells.size();
    }
    void move(){
        endx= snakecells.get(size-1).getX();
        endy= snakecells.get(size-1).getY();
        for (int i = size-1; i >0 ; i--) {
            snakecells.get(i).setX(snakecells.get(i-1).getX());
            snakecells.get(i).setY(snakecells.get(i-1).getY());
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    void eat(){
        score+=5;

        if(size<maxlenght)
        snakecells.add(new Cell(endx,endy,'@'));
        size=snakecells.size();
    }

}
