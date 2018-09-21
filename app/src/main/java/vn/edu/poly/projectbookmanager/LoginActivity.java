package vn.edu.poly.projectbookmanager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.projectbookmanager.database.DatabaseHelper;
import vn.edu.poly.projectbookmanager.model.User;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private Button btnLogin;
    private EditText edtUserName, edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.insertUser(new User("admin", "admin123", "Giang Nam", "0123456789"));
    }

    public void initViews() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void login(View view) {
        Log.e("Login","Login");

        String username = edtUserName.getText().toString().trim();
        String passwrod = edtPassword.getText().toString().trim();

        User user = databaseHelper.getUser(username);
        if (user !=null){
            String originalPassword = user.getPassword();
            if (originalPassword.equals(passwrod)){
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);

            }else {
                Toast.makeText(LoginActivity.this, getString(R.string.notifications_wrong_user_or_passwrod), Toast.LENGTH_SHORT).show();
            }
            //neu user == null, user ko co trong co so du lieu thi thong bao sai usernae or password

        }else {
            Toast.makeText(LoginActivity.this, getString(R.string.notifications_wrong_user_or_passwrod), Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát chương trình?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}

