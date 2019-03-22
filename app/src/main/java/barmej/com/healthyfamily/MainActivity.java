package barmej.com.healthyfamily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import barmej.com.healthyfamily.database.HFRoomDatabase;
import barmej.com.healthyfamily.model.Gender;
import barmej.com.healthyfamily.model.User;
import barmej.com.healthyfamily.model.UserDAO;
import barmej.com.healthyfamily.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_USER_ADDED_REQUEST = 123;
    private static final int SELECTED_USER_REQUEST = 124;

    public static final String CURRENT_USER = "CURRENT_USER";

    private User user;
    List<User> users = Collections.emptyList();

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
            updateUIWithUser(user);

        } else if (requestCode == SELECTED_USER_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            user = (User) data.getSerializableExtra(UsersListActivity.SELECTED_USER);
            updateUIWithUser(user);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllUsers();
    }

    private void updateUIWithUser(User user) {
        ((TextView) findViewById(R.id.userNameTextView)).setText(user.getName());
        ImageView imageView = findViewById(R.id.userImageView);

        if (imageView.getVisibility() == View.GONE)
            imageView.setVisibility(View.VISIBLE);

        if (user.getGender() == Gender.MALE)
            imageView.setImageDrawable(getDrawable(R.drawable.ic_male));
        else
            imageView.setImageDrawable(getDrawable(R.drawable.ic_female));
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
        if (users.isEmpty()) {
            Intent intent = new Intent(this, AddUserActivity.class);
            startActivityForResult(intent, NEW_USER_ADDED_REQUEST);
        } else {
            Intent intent = new Intent(this, UsersListActivity.class);
            startActivityForResult(intent, SELECTED_USER_REQUEST);
        }
    }

    private void getAllUsers() {
        final UserDAO userDAO = HFRoomDatabase.Companion.getDatabase(this).userDAO();
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserRepository repository = new UserRepository(userDAO);
                users = repository.getAllUsers();
            }
        }).start();
    }
}
