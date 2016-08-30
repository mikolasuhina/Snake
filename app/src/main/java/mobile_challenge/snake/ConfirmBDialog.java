package mobile_challenge.snake;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created  on 23.03.2016.
 */
public class ConfirmBDialog extends DialogFragment {
    EditText name;
    DBHelper dbHelper;
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {



        Dialog dialog = new Dialog(getActivity());

        dialog.setCanceledOnTouchOutside(false);//
        dbHelper = new DBHelper(this.getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        dialog.setContentView(R.layout.gialog);
        name=(EditText)dialog.findViewById(R.id.editText);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                ContentValues cv = new ContentValues();

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String namePl = name.getText().toString();

                cv.put("name", namePl);
                cv.put("score", getActivity().getIntent().getIntExtra("score", 0));
                db.insert("mytable", null, cv);
                dismiss();

            }

        });



        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                dismiss();
            }

        });



        return dialog;

    }

}