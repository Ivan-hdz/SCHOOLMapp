package mx.android.schoolapps.schoolmapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import mx.android.schoolapps.schoolmapp.Models.Book;
import mx.android.shcoolapps.schoolmap.R;

public class DetailBookActivity extends AppCompatActivity {

    private ImageView bookCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        Toolbar toolbar= findViewById(R.id.detailBookToolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bookCover= findViewById(R.id.imageViewBookCover);
        TextView bookTitle= findViewById(R.id.textViewBookTitle);
        TextView bookAuthor= findViewById(R.id.textViewBookAuthor);
        TextView bookQuantity= findViewById(R.id.textViewBookQuantity);
        TextView bookDescription=findViewById(R.id.textViewBookDescription);
        TextView bookLanguage = findViewById(R.id.textViewBookLanguage);
        TextView bookPrinting = findViewById(R.id.textViewBookPrinting);
        Bundle extras= getIntent().getExtras();
        Book book= extras.getParcelable("selectedBook");

        toolbar.setTitle(book.getBookName());

        if(book != null){
            setBookCover(book.getBookCoverUrl());
            bookTitle.setText(book.getBookName());
            bookAuthor.setText(book.getAuthor());
            bookQuantity.setText(String.valueOf("Cantidad: " + book.getQuantity()));
            bookDescription.setText("Descr. Física: "+book.getDescription());
            bookPrinting.setText("Pie de Imprenta: "+book.getPrinting());
            bookLanguage.setText("Idioma: "+book.getLanguage());
        }
    }

    private void setBookCover(final String imageUrl){

        Picasso.with(getApplicationContext())
                .load(imageUrl)
                .fit()
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(bookCover, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(getApplicationContext())
                                .load(imageUrl)
                                .fit()
                                .into(bookCover);
                    }
                    @Override
                    public void onError() {
                        Picasso.with(getApplicationContext())
                                .load(imageUrl)
                                .fit()
                                .error(R.drawable.nocover)
                                .into(bookCover);
                    }
                });

        /*
        if(Utils.isNetworkAvailable(getApplicationContext()))
            Picasso.with(getApplicationContext()).load(imageUrl).into(bookCover);
        else
            Picasso.with(getApplicationContext()).load(R.drawable.nocover).into(bookCover);

        */
    }

}
