package mobile_challenge.snake;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends Activity implements View.OnClickListener {
Button play;
    Button setting;
    Button exit;
    Button top;
    Intent intent;
    SharedPreferences sPref;
    Typeface typeFace;
    String SAVED_ORIENTATION="orient";
    boolean orient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        play=(Button)findViewById(R.id.play);
        setting=(Button)findViewById(R.id.setting);
        top=(Button)findViewById(R.id.top);
        exit=(Button)findViewById(R.id.exit);

        play.setOnClickListener(this);
        setting.setOnClickListener(this);
        exit.setOnClickListener(this);
        top.setOnClickListener(this);

        sPref = getSharedPreferences("MySett",MODE_PRIVATE);
        orient=sPref.getBoolean(SAVED_ORIENTATION, false);

        typeFace = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        top.setTypeface(typeFace);
        exit.setTypeface(typeFace);
        play.setTypeface(typeFace);
        setting.setTypeface(typeFace);

        play.setTextSize(15 * getResources().getDisplayMetrics().density);
        exit.setTextSize(15 * getResources().getDisplayMetrics().density);
        top.setTextSize(15 * getResources().getDisplayMetrics().density);
        setting.setTextSize(15 * getResources().getDisplayMetrics().density);

        TextView snake =(TextView)findViewById(R.id.textView3);
        snake.setTypeface(typeFace);
        snake.setTextSize(40 * getResources().getDisplayMetrics().density);

        if(orient)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                intent=new Intent(this,MainActivity.class);
                intent.putExtra(SAVED_ORIENTATION,orient);
                startActivity(intent);
                this.finish();
                break;
            case R.id.setting:
                intent=new Intent(this,Settings.class);
                startActivity(intent);


                break;
            case R.id.exit:
                finish();
                break;
            case R.id.top:


                intent=new Intent(this,TopList.class);
                intent.putExtra(SAVED_ORIENTATION,orient);
                startActivity(intent);

                break;
        }
        }

    @Override
    protected void onResume() {
        super.onResume();
        sPref = getSharedPreferences("MySett",MODE_PRIVATE);
        orient=sPref.getBoolean(SAVED_ORIENTATION, true);
        if(orient)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
