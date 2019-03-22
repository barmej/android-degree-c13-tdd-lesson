package barmej.com.healthyfamily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import barmej.com.healthyfamily.adapter.UsersAdapter;
import barmej.com.healthyfamily.database.HFRoomDatabase;
import barmej.com.healthyfamily.model.User;
import barmej.com.healthyfamily.model.UserDAO;
import barmej.com.healthyfamily.repository.UserRepository;

public class UsersListActivity extends AppCompatActivity implements OnItemClickListener {

    private static final int NEW_USER_ADDED_LIST_REQUEST = 223;
    public static final String SELECTED_USER = "SELECTED_USER";

    private List<User> users;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);


        RecyclerView recyclerView = findViewById(R.id.usersRecyclerView);
        usersAdapter = new UsersAdapter(this);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllUsers();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_USER_ADDED_LIST_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            getAllUsers();
        }
    }

    private void getAllUsers() {
        final UserDAO userDAO = HFRoomDatabase.Companion.getDatabase(this).userDAO();

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserRepository repository = new UserRepository(userDAO);
                users = repository.getAllUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        usersAdapter.setUsersList(users);
                    }
                });
            }
        }).start();
    }

    public void addUserOnClick(View view) {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivityForResult(intent, NEW_USER_ADDED_LIST_REQUEST);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        intent.putExtra(SELECTED_USER, users.get(position));
        setResult(RESULT_OK, intent);
        finish();
    }
}
