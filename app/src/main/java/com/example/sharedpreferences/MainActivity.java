package com.example.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText edtName,edtAddress,edtPhone,edtEmail;
    RadioGroup rdgFavouritePartOfDay;
    RadioButton rdMorning,rdAfternoon,rdEvening,rdNight;

    public static final String MYPREFS="mySharedPreferences";

    private String favouritePartOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName=findViewById(R.id.edtName);
        edtAddress=findViewById(R.id.edtAddress);
        edtPhone=findViewById(R.id.edtPhone);
        edtEmail=findViewById(R.id.edtEmail);

        rdgFavouritePartOfDay=findViewById(R.id.rdgFavouritePartOfDay);

        rdMorning=findViewById(R.id.rdMorning);
        rdAfternoon=findViewById(R.id.rdAfternoon);
        rdEvening=findViewById(R.id.rdEvening);
        rdNight=findViewById(R.id.rdNight);

        this.loadPreferences();

    }

    private void loadPreferences() {
        int mode= Activity.MODE_PRIVATE;
        android.content.SharedPreferences mySharedPreferences=getSharedPreferences(MYPREFS,mode);
        edtName.setText(mySharedPreferences.getString("name",""));
        edtAddress.setText(mySharedPreferences.getString("address",""));
        edtPhone.setText(mySharedPreferences.getString("phone",""));
        edtEmail.setText(mySharedPreferences.getString("email",""));

        favouritePartOfDay=mySharedPreferences.getString("favouritePartOfDay","m");
        loadRadioButtonPreferences();
    }
    public void loadRadioButtonPreferences(){
        if (favouritePartOfDay.equals("m")){
            rdgFavouritePartOfDay.check(R.id.rdMorning);
        } else if (favouritePartOfDay.equals("a")){
            rdgFavouritePartOfDay.check(R.id.rdAfternoon);
        }else if (favouritePartOfDay.equals("e")){
            rdgFavouritePartOfDay.check(R.id.rdEvening);
        }else if (favouritePartOfDay.equals("n")){
            rdgFavouritePartOfDay.check(R.id.rdNight);
        }else {
            rdgFavouritePartOfDay.check(R.id.rdMorning);
        }
    }
    public void onClick(View view){
        if (rdMorning.isChecked()){
            favouritePartOfDay="m";
        }else if (rdAfternoon.isChecked()){
            favouritePartOfDay="a";
        }else if (rdEvening.isChecked()){
            favouritePartOfDay="e";
        }else if (rdNight.isChecked()){
            favouritePartOfDay="n";
        }else {
            favouritePartOfDay="";
        }

    }
    protected void savePreferences(){
        int mode= Activity.MODE_PRIVATE;
        android.content.SharedPreferences mySharedPreferences=getSharedPreferences(MYPREFS,mode);
        android.content.SharedPreferences.Editor editor=mySharedPreferences.edit();
        editor.putString("name",edtName.getText().toString());
        editor.putString("address",edtAddress.getText().toString());
        editor.putString("phone",edtPhone.getText().toString());
        editor.putString("email",edtEmail.getText().toString());
        editor.putString("favouritePartOfDay",favouritePartOfDay);
        editor.commit();

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.savePreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shared_preferences_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }
}
