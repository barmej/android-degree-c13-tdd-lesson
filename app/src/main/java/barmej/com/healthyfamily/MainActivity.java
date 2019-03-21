package barmej.com.healthyfamily;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import barmej.com.healthyfamily.model.Gender;
import barmej.com.healthyfamily.model.User;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_USER_ADDED_REQUEST = 123;
    public static final String CURRENT_USER = "CURRENT_USER";

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_USER_ADDED_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            user = (User) data.getSerializableExtra(AddUserActivity.NEW_USER);
            ((TextView) findViewById(R.id.userNameTextView)).setText(user.getName());

            findViewById(R.id.userImageView).setVisibility(View.VISIBLE);

            if (user.getGender() == Gender.MALE)
                ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_male));
            else
                ((ImageView) findViewById(R.id.userImageView)).setImageDrawable(getDrawable(R.drawable.ic_female));

        }
    }

    public void bmrOnClick(View view) {
        openActivityWithClass(BmrActivity.class, view);
    }

    public void bmiOnClick(View view) {
        openActivityWithClass(BmiActivity.class, view);
    }

    private void openActivityWithClass(Class<?> cls, View view) {
        if (user != null) {
            Intent intent = new Intent(this, cls);
            intent.putExtra(CURRENT_USER, user);
            startActivity(intent);
        } else {
            Snackbar.make(view, R.string.chooseUser, Snackbar.LENGTH_LONG).show();
        }
    }

    public void userOnClick(View view) {
        Intent intent;
//        TODO firstUser Logic
        boolean firstUser = true;
        if (firstUser) {
            intent = new Intent(this, AddUserActivity.class);
            startActivityForResult(intent, NEW_USER_ADDED_REQUEST);
        } else {
            intent = new Intent(this, UsersListActivity.class);
            startActivity(intent);
        }
    }
}
