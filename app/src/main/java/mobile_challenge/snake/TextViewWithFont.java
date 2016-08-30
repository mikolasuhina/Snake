package mobile_challenge.snake;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created  on 26.03.2016.
 */
public class TextViewWithFont extends TextView {
    Typeface typeFace;
    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
        setTextSize(15 * getResources().getDisplayMetrics().density);
        this.setTypeface(typeFace);
    }

    public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
       setTextSize(15 * getResources().getDisplayMetrics().density);
        this.setTypeface(typeFace);
    }

    public TextViewWithFont(Context context) {
        super(context);
        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
        setTextSize(15 * getResources().getDisplayMetrics().density);

        this.setTypeface(typeFace);
    }

}