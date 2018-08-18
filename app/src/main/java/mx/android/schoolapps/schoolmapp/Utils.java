package mx.android.schoolapps.schoolmapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;

import mx.android.schoolapps.schoolmapp.Models.Book;

/**
 * Created by Shiro on 30/10/2017.
 */

public class Utils {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected();
    }

    public static class BookComparator implements Comparator<Book> {
        public int compare(Book a, Book b) {
            return a.getBookName().compareTo(b.getBookName());
        }
    }

    public static class InverseBookComparator implements Comparator<Book>{

        @Override
        public int compare(Book a, Book b) {
            return b.getBookName().compareTo(a.getBookName());
        }
    }

    public class RetrievePDFStream extends AsyncTask<String,Void,InputStream>
    {
        private PDFView pdfView;

        public RetrievePDFStream(){}

        public RetrievePDFStream(PDFView v)
        {
            this.pdfView = v;
        }

        public void setPDFView(PDFView v)
        {
            this.pdfView = v;
        }

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try
            {
                URL url= new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200)
                {
                    inputStream= new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch(IOException e){return null;}
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }

}
