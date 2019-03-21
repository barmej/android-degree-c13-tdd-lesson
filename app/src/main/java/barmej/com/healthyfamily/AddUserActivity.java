package barmej.com.healthyfamily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import barmej.com.healthyfamily.model.Gender;
import barmej.com.healthyfamily.model.User;

public class AddUserActivity extends AppCompatActivity {

    public static final String NEW_USER = "NEW_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        ((RadioGroup) findViewById(R.id.genderRadioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.maleRadioButton)
                    ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_male));

                else if (checkedId == R.id.femaleRadioButton)
                    ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_female));

            }
        });
    }

    private void saveUserTODB() {
//        TODO Background task
    }

    public void addUserOnClick(View view) {
        boolean allRequiredField = true;

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText weightEditText = findViewById(R.id.weightEditText);
        EditText heightEditText = findViewById(R.id.heightEditText);
        EditText ageEditText = findViewById(R.id.ageEditText);

        RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup);
        int radioButtonId = genderRadioGroup.getCheckedRadioButtonId();
        Gender gender = (radioButtonId == R.id.femaleRadioButton ? Gender.FEMALE : Gender.MALE);

        if (TextUtils.isEmpty(nameEditText.getText())) {
            nameEditText.setError(getString(R.string.fieldIsRequired));
            allRequiredField = false;
        } else if (TextUtils.isEmpty(weightEditText.getText())) {
            weightEditText.setError(getString(R.string.fieldIsRequired));
            allRequiredField = false;

        } else if (TextUtils.isEmpty(heightEditText.getText())) {
            heightEditText.setError(getString(R.string.fieldIsRequired));
            allRequiredField = false;

        } else if (TextUtils.isEmpty(ageEditText.getText())) {
            ageEditText.setError(getString(R.string.fieldIsRequired));
            allRequiredField = false;

        } else if (radioButtonId == -1) {
            allRequiredField = false;
        }

        if (allRequiredField) {
            User user = new User(nameEditText.getText().toString(), Double.parseDouble(weightEditText.getText().toString()), Double.parseDouble(heightEditText.getText().toString()), Integer.parseInt(ageEditText.getText().toString()), gender);
            saveUserTODB();

            Intent intent = new Intent();
            intent.putExtra(NEW_USER, user);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
