package mobile_challenge.snake;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Settings extends Activity implements View.OnClickListener {
    SharedPreferences sPref;
    String SAVED_INT="int";
    String SAVED_ORIENTATION="orient";
    ToggleButton key;
    ToggleButton orient;
    NumberPicker np;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.anim);
        rotation.setRepeatCount(Animation.INFINITE);

        sPref = getSharedPreferences("MySett",MODE_PRIVATE);

        if(sPref.getBoolean(SAVED_ORIENTATION, true))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        np = (NumberPicker) findViewById(R.id.numberPicker);
        key=(ToggleButton)findViewById(R.id.toggleButton);
        orient=(ToggleButton)findViewById(R.id.or);


        orient.startAnimation(rotation);
        orient.setChecked(sPref.getBoolean(SAVED_ORIENTATION, true));

        back = (Button) findViewById(R.id.sett_back);
        back.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font.ttf"));

        back.setOnClickListener(this);
        key.setOnClickListener(this);
        orient.setOnClickListener(this);

        np.setMaxValue(10);
        np.setMinValue(1);
        np.setEnabled(false);







        np.setValue(sPref.getInt(SAVED_INT, 1));
        np.setWrapSelectorWheel(false);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        key.setChecked(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toggleButton:
                if(key.isChecked()){

                    np.setEnabled(false);

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putInt(SAVED_INT, np.getValue());
                    ed.commit();
                }
                else {
                    np.setEnabled(true);
                }
                break;
            case R.id.or:
                if(orient.isChecked()){

                    SharedPreferences.Editor ed = sPref.edit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    ed.putBoolean(SAVED_ORIENTATION, true);
                    ed.commit();


                }else{

                    SharedPreferences.Editor ed = sPref.edit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    ed.putBoolean(SAVED_ORIENTATION, false);
                    ed.commit();

                }
                break;
            case R.id.sett_back:
                finish();

        }

    }
}
