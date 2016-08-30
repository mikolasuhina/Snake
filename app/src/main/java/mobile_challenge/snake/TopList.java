package mobile_challenge.snake;

import android.app.Activity;
import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TopList extends Activity implements View.OnClickListener {
    String SAVED_ORIENTATION="orient";
    DBHelper dbHelper;
    ListView lvMain;
    Button clear;
    Button back;
    ArrayList<String> name = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.puls);
        setContentView(R.layout.activity_top_list);

        if (getIntent().getBooleanExtra(SAVED_ORIENTATION, true))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbHelper = new DBHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, "score DESC", "10");
        if (c.moveToFirst()) {

            int nameColIndex = c.getColumnIndex("name");
            int scoreColIndex = c.getColumnIndex("score");

            do {
         if(!c.getString(nameColIndex).equals(""))
                name.add(c.getString(nameColIndex) + '\n' + " Score = " + c.getInt(scoreColIndex));
                else
         name.add("Unknown"+ '\n' + " Score = " + c.getInt(scoreColIndex));

            } while (c.moveToNext());
        } else

            c.close();

        lvMain = (ListView) findViewById(R.id.lvMain);

        clear=(Button)findViewById(R.id.clear);
        clear.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font.ttf"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listrec, name);

        lvMain.setAdapter(adapter);
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);
         back = (Button)findViewById(R.id.top_back);
        back.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font.ttf"));
        back.setOnClickListener(this);

        lvMain.startAnimation(pulse);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.clear:
              SQLiteDatabase ndb = dbHelper.getWritableDatabase();

              ndb.delete("mytable", null, null);
              name.clear();

              ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.listrec, name);

              lvMain.setAdapter(adapter);
              break;
          case R.id.top_back:
              finish();
      }
    }
}
