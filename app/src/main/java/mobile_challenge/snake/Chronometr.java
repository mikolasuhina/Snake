package mobile_challenge.snake;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 23 on 22.10.2015.
 */
public class Chronometr extends TextView {
    DisplayMetrics displaymetrics = getResources().getDisplayMetrics();

    String mil="000";
    String se="00";
    String mi="00";
    long sec = 0;
    long min = 0;
    long mill = 0;
    boolean stop=true;
    private Timer timer;
    private Context ctx;
    private long startTime;
    private long delayTime=0;
    private long intervalTime=10;
    long t=0;//для паузи

long getSecond(){
    return sec;
}

String getMin(){
        return mi;}
    String getMil(){
        return mil;
}
   String getSec(){
        return se;}
    private void runThread() {
        ((Activity) ctx).runOnUiThread(new MyRunner());
    }

    public void stop() {
        stop=false;

        timer.cancel();

        t=mill+sec*1000+min*60000;;

    }
/*
Запускаю матод за допомогою таймера
 */
    public void start() {
     stop=true;
        timer = new Timer();
        startTime = System.currentTimeMillis();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                runThread();

            }
        }, delayTime, intervalTime);

    }


    public class MyRunner implements Runnable {

        @Override
        public void run() {


            long elapsed = System.currentTimeMillis() - startTime+t;

           if (elapsed >= 1000) {
               min=elapsed/60000;
                sec = elapsed / 1000;
                if(sec>=60)sec=sec%60;
                mill = elapsed % 1000;
           }


            else
                mill = elapsed;

            if(sec>=60){

            }
            mil=Long.toString(mill);
            se=Long.toString(sec);
            mi=Long.toString(min);

//щоб було **:**:***
                if(mil.length()==1)
                    mil="00"+mil;
            if(mil.length()==2)
                mil="0"+mil;
            if(se.length()==1)
               se="0"+se;
            if(mi.length()==1)
                mi="0"+mi;
            if(stop==true)
            Chronometr.this.setText(mi+":"+se + ":" + mil);
        }

    }
    /*
    скидка значань таймера
     */
public void nul(){
    t=0;
    min=0;
     sec=0;
    mill=0;
}
/*
Конструктор
 */
    public Chronometr(Context context) {
        super(context);
        this.ctx = context;

        setText("00:00:000");


    }




}
