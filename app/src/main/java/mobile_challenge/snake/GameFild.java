package mobile_challenge.snake;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created  on 19.03.2016.
 */
public class GameFild {
    char[][] charsGameFild;
    String res="";
    Cell cell;//клітинка їжі
    Cell bonus;//клітинка бонуса
    Random r =new Random();
    Snake snake;
    int gameSize=20;
    MainActivity ma;

    public GameFild(MainActivity ma) {
        this.ma=ma;
        snake=new Snake(10,10);
        cell=new Cell(0,0,'$',0,true);
        bonus=new Cell(0,0,'-',0,true);
        charsGameFild=new char[gameSize][gameSize] ;

        for (int i =0;i<gameSize;i++){
            for (int j=0;j<gameSize;j++){
                res=res+" ";
            }
            res+='\n';
        }

    }

    void update(){

         snake.update();

        for (int i = 1; i < snake.snakecells.size(); i++) {
         if(snake.getX()==snake.snakecells.get(i).getX()&snake.getY()==snake.snakecells.get(i).getY()){
             ma.endGame();
            break;
         }
        }

      if(bonus.getEndtime()<System.currentTimeMillis())
          ma.dg.time_update=ma.timeUpdate;


            if(snake.getX()==bonus.getX()&snake.getY()==bonus.getY()) {
               switch (bonus.getS()){
                   case '▲':
                       ma.dg.time_update=50;
                       bonus.setIsEat(true);
                       bonus.setEndBonutime(System.currentTimeMillis()+10000);
                       break;
                   case '▼':
                       ma.dg.time_update+=100;
                       bonus.setIsEat(true);
                       bonus.setEndBonutime(System.currentTimeMillis() + 10000);
                       break;
                   case '#':
                       snake.eat();
                       snake.eat();
                       snake.eat();
                       snake.eat();
                       bonus.setIsEat(true);
                       bonus.setEndBonutime(System.currentTimeMillis() + 10000);
                       break;}
               }

         if(snake.getX()==cell.getX()&snake.getY()==cell.getY()) {
             snake.eat();
             cell.setIsEat(true);
         }

        res="";

        int size =snake.size;
        for (int i =0; i < gameSize;i++){
            for (int j=0;j<gameSize;j++){
               res+=' ';
            }
           if(i!=gameSize-1)
            res+='\n';
        }

        StringBuffer ress=(new StringBuffer(res));

        for (int i = 0; i <size ; i++) {
            if(snake.getY()<0|snake.getY()>=20|snake.getX()<0|snake.getX()>=20){

                ma.endGame();

                break;}
            else
                ress.setCharAt(snake.snakecells.get(i).getY() * (gameSize + 1) + snake.snakecells.get(i).getX(), snake.snakecells.get(i).getS());

        }

            int rX = r.nextInt(gameSize);
            int rY = r.nextInt(gameSize);

                if (cell.getEndtime() < System.currentTimeMillis() || cell.isEat)
                    if (ress.charAt(rY * (gameSize + 1) + rX) == ' ') {
                        cell= new Cell(rX, rY, '$', System.currentTimeMillis() + 10000, false);
                        ress.setCharAt(rY * (gameSize + 1) + rX, '$');

                    }

        rX = r.nextInt(gameSize);
        rY = r.nextInt(gameSize);
       int n=r.nextInt(3);

        if(bonus.isEat || bonus.getEndtime() < System.currentTimeMillis() ) {
            if (ress.charAt(rY * (gameSize + 1) + rX) == ' ' & r.nextInt(1000) < 50)
            switch (n) {
                case 0:
                     {
                        bonus = (new Cell(rX, rY, '▼', System.currentTimeMillis() + 10000, false));
                        ress.setCharAt(rY * (gameSize + 1) + rX, '▼');
                    }
                    break;
                case 1:
                     {
                        bonus = (new Cell(rX, rY, '▲', System.currentTimeMillis() + 10000, false));
                        ress.setCharAt(rY * (gameSize + 1) + rX, '▲');
                    }
                    break;
                case 2:
                    {
                        bonus = (new Cell(rX, rY, '#', System.currentTimeMillis() + 10000, false));
                        ress.setCharAt(rY * (gameSize + 1) + rX, '#');
                    }
                    break;
            }
        }
        ress.setCharAt(cell.getY() * (gameSize + 1) + cell.getX(), cell.getS());
        if(!bonus.isEat)
        ress.setCharAt(bonus.getY() * (gameSize + 1) + bonus.getX(), bonus.getS());

        res=String.valueOf(ress);

            }

}
