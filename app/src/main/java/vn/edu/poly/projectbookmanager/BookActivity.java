package vn.edu.poly.projectbookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.projectbookmanager.adapter.BookAdapter;
import vn.edu.poly.projectbookmanager.model.Book;

public class BookActivity extends AppCompatActivity {
    private List<Book> bookList;
    private RecyclerView rcView;
    private BookAdapter adapter;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        toolBar = findViewById(R.id.toolBar);
        toolBar.setNavigationIcon(R.drawable.back);
        toolBar.setTitle("Book");
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rcView = findViewById(R.id.rcView);
        bookList = new ArrayList<>();
        bookList.add(new Book("","Tên: Cú hích","Thể loại: Kinh tế"));
        bookList.add(new Book("","Tên: Top notch","Thể loại: Ngoại ngữ"));
        bookList.add(new Book("","Tên: Code dạo ký sự","Thể loại: Công nghệ thông tin"));
        bookList.add(new Book("","Tên: Ngẫu hứng vào bếp","Thể loại: Ẩm thực"));
        bookList.add(new Book("","Tên: Suối nguồn tươi trẻ","Thể loại: Sức khỏe"));

        adapter = new BookAdapter(bookList);
        rcView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rcView.setLayoutManager(manager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
