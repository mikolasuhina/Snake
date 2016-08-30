package mobile_challenge.snake;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends Activity implements View.OnClickListener {
    Button newGAme;
    Button menu;
    Intent intent;
    TextView time;
    TextView score;
    ConfirmBDialog dialog;
    String SAVED_ORIENTATION="orient";
    boolean first =true;
    int min=Integer.MAX_VALUE;
    Typeface typeFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        if(getIntent().getBooleanExtra(SAVED_ORIENTATION, true))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        newGAme=(Button)findViewById(R.id.newGame);
        menu=(Button)findViewById(R.id.menu);
        time=(TextView)findViewById(R.id.time);
        score=(TextView)findViewById(R.id.scorEend);

        time.setText("YOUR TIME -  " + getIntent().getStringExtra("time"));
        score.setText("YOUR SCORE -  " + getIntent().getIntExtra("score", 0));

        newGAme.setOnClickListener(this);
        menu.setOnClickListener(this);

        dialog=new ConfirmBDialog();

        typeFace = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        newGAme.setTypeface(typeFace);
        menu.setTypeface(typeFace);
        time.setTypeface(typeFace);
        score.setTypeface(typeFace);


        menu.setTextSize(20* getResources().getDisplayMetrics().density);
        newGAme.setTextSize(20* getResources().getDisplayMetrics().density);


        
        DBHelper dbHelper; dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null,"score DESC","10");

        if (c.moveToFirst()) {
            int scoreColIndex = c.getColumnIndex("score");

            do {

                    if( c.getInt(scoreColIndex)<min) {
                        min = c.getInt(scoreColIndex);
                        first=false;
                    }
            } while (c.moveToNext());
        } else

            c.close();
        if(first)
            dialog.show(getFragmentManager(),"dialog");



        if(getIntent().getIntExtra("score", 0)>=min)
            dialog.show(getFragmentManager(),"dialog");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        intent=new Intent(this,Menu.class);
        startActivity(intent);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu:

                intent=new Intent(this,Menu.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.newGame:
                intent=new Intent(this,MainActivity.class);
                intent.putExtra(SAVED_ORIENTATION, getIntent().getBooleanExtra(SAVED_ORIENTATION, true));
                startActivity(intent);
                finish();
                break;
        }
    }
}
