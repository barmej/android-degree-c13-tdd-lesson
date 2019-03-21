package barmej.com.healthyfamily;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import barmej.com.healthyfamily.manager.CalculationsManager;
import barmej.com.healthyfamily.model.Gender;

public class BmiActivity extends BmActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        setUIWithUserData();

        if (user.getGender() == Gender.MALE)
            ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_male));
        else
            ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_female));

        ((TextView) findViewById(R.id.bmiRateTextView)).setText(CalculationsManager.INSTANCE.bmiString(user));
    }
}
