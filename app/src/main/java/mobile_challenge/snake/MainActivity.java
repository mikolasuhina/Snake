package mobile_challenge.snake;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Handler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnTouchListener {

    String SAVED_INT="int";
    TextView Text;
    TextView score;
    DrawGame dg;
    GameFild game;
    Button pause;
    Typeface typeFace;
    Intent intent;
    Chronometr chronometr;
    SharedPreferences sPref;
    String time;
    int timeUpdate;

    float x1, x2, y1, y2, dx, dy;
    String SAVED_ORIENTATION="orient";
    Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        dg.setGameing(false);
        intent=new Intent(this,Menu.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout gameL =new FrameLayout(this);
        gameL.setOnTouchListener(this);

        LayoutInflater layoutInflater = getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.activity_main, null);
        LinearLayout linearLayout =(LinearLayout)view.findViewById(R.id.line1);
        Text=(TextView)view.findViewById(R.id.textfild);
        score=(TextView)view.findViewById(R.id.score);
        pause=(Button)view.findViewById(R.id.button);
        chronometr=new Chronometr(this);
        chronometr.setVisibility(View.VISIBLE);
        sPref = getSharedPreferences("MySett",MODE_PRIVATE);

        if(getIntent().getBooleanExtra(SAVED_ORIENTATION, true)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Text.setTextSize(10 * getResources().getDisplayMetrics().density);}//розмір шрифта залежно від екрану}
        else{ Text.setTextSize(15 * getResources().getDisplayMetrics().density);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);}
        linearLayout.addView(chronometr);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dg.isPause) {
                    start();
                } else {
                    pause();
                }
            }
        });
        game=new GameFild(this);


        Text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Anonymous.ttf"));//однакова ширина символів



        dg=new DrawGame(this);
        gameL.addView(view);

        timeUpdate= 200-15*sPref.getInt(SAVED_INT,1);

        dg.time_update=timeUpdate;

        typeFace = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        setContentView(gameL);
        pause.setTypeface(typeFace);
        score.setTypeface(typeFace);
        chronometr.setTypeface(typeFace);
        pause.setTextSize(15 * getResources().getDisplayMetrics().density);
        score.setTextSize(15 * getResources().getDisplayMetrics().density);
        chronometr.setTextSize(17 * getResources().getDisplayMetrics().density);

    }
        void  update(){

        game.update();

        handler.post(new Runnable() {
            public void run() {
                Text.setText(game.res);
                score.setText("SCORE: "+String.valueOf(game.snake.getScore()));
            }


        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case(MotionEvent.ACTION_DOWN):
                x1 = event.getX();
                y1 = event.getY();
                break;
            case(MotionEvent.ACTION_UP): {
                x2 = event.getX();
                y2 = event.getY();
                dx = x2-x1;
                dy = y2-y1;


                if(Math.abs(dx) > Math.abs(dy)) {
                    if(dx>0) {
                        if(game.snake.getDx()!=-1){
                        game.snake.setDx(1);
                        game.snake.setDy(0);}
                    }
                    else {
                        if(game.snake.getDx()!=1){
                        game.snake.setDx(-1);
                        game.snake.setDy(0);}
                    }
                } else {

                    if(dy>0){
                        if(game.snake.getDy()!=-1){
                            game.snake.setDy(1);

                        game.snake.setDx(0);
                        }
                    }
                    else {
                        if(game.snake.getDy()!=1){
                        game.snake.setDy(-1);
                        game.snake.setDx(0);
                        }
                    }
                }
            }
        }

        return true;
    }
    void endGame(){
        dg.setGameing(false);
        chronometr.stop();
        time=chronometr.getMin()+" : "+chronometr.getSec()+" : "+chronometr.getMil();
        intent=new Intent(this,EndGame.class);
        intent.putExtra("time",time);
        intent.putExtra(SAVED_ORIENTATION, getIntent().getBooleanExtra(SAVED_ORIENTATION, true));
        intent.putExtra("score",game.snake.getScore());
        startActivity(intent);
        finish();

    }
    void start(){
        dg.setIsPause(false);
        chronometr.start();
        pause.setText("PAUSE");
        game.cell.setEndtime(System.currentTimeMillis() + game.cell.getTime());
        game.bonus.setEndBonutime(System.currentTimeMillis() + game.bonus.getBonustime());
        game.bonus.setEndtime(System.currentTimeMillis() + game.bonus.getTime());


        ;
    }
    void pause(){
        dg.setIsPause(true);
        chronometr.stop();
        game.cell.setTime(game.cell.getEndtime() - System.currentTimeMillis());
        game.bonus.setBonustime(game.bonus.getEndBonutime() - System.currentTimeMillis());

        game.bonus.setTime(game.bonus.getEndtime() - System.currentTimeMillis());

        pause.setText("PLAY");
    }
}
