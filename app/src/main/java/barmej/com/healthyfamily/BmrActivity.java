package barmej.com.healthyfamily;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import barmej.com.healthyfamily.manager.Active;
import barmej.com.healthyfamily.manager.CalculationsManager;
import barmej.com.healthyfamily.model.Gender;

public class BmrActivity extends BmActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);
        setUIWithUserData();

        ((TextView) findViewById(R.id.kcalTextView)).setText(CalculationsManager.INSTANCE.bmrString(user));
        ((TextView) findViewById(R.id.tiTextView)).setText(CalculationsManager.INSTANCE.tiString(user, Active.SEDENTARY));

        if (user.getGender() == Gender.MALE)
            ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_male));
        else
            ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_female));


        ((RadioGroup) findViewById(R.id.activeRadioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sedentaryRadioButton)
                    ((TextView) findViewById(R.id.tiTextView)).setText(CalculationsManager.INSTANCE.tiString(user, Active.SEDENTARY));
                else if (checkedId == R.id.activeRadioButton)
                    ((TextView) findViewById(R.id.tiTextView)).setText(CalculationsManager.INSTANCE.tiString(user, Active.ACTIVE));
                else if (checkedId == R.id.vigorouslyRadioButton)
                    ((TextView) findViewById(R.id.tiTextView)).setText(CalculationsManager.INSTANCE.tiString(user, Active.Vigorously));

            }
        });
    }
}
