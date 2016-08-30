package mobile_challenge.snake;

import android.widget.TextView;

/**
 * Created  on 19.03.2016.
 */
public class DrawGame extends Thread {

public   int time_update;
    MainActivity   gameFild;
    boolean gameing=true;
    boolean isPause=true;
    public DrawGame(MainActivity game) {
        gameFild=game;
        this.start();
    }

    public MainActivity getGameFild() {
        return gameFild;
    }

    public void setGameFild(MainActivity gameFild) {
        this.gameFild = gameFild;
    }

    public boolean isGameing() {
        return gameing;
    }

    public void setGameing(boolean gameing) {
        this.gameing = gameing;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setIsPause(boolean isPause) {
        this.isPause = isPause;
    }

    @Override
    public void run() {
        while (gameing){

            try {
                Thread.sleep(time_update);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           this.gameFild.update();
            while (isPause){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
