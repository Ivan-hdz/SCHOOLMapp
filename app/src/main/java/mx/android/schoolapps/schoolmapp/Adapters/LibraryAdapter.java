package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mx.android.shcoolapps.schoolmap.R;
import mx.android.schoolapps.schoolmapp.Models.Book;
import mx.android.schoolapps.schoolmapp.Utils;

/**
 * Created by Shiro on 19/08/2017.
 */

public class LibraryAdapter extends BaseAdapter implements Filterable{

    private Context context;
    private int layout;
    private List<Book> booksList;
    private List<Book> booksListFiltered;

    public LibraryAdapter(Context context, int layout, List<Book> booksList){
        this.context= context;
        this.layout= layout;
        this.booksList= booksList;
        this.booksListFiltered= booksList;
    }

    @Override
    public int getCount() {
        return booksListFiltered.size();
    }

    @Override
    public Book getItem(int position) {
        return booksListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView= LayoutInflater.from(this.context).inflate(this.layout,null);

            vh= new ViewHolder();
            vh.BookName= convertView.findViewById(R.id.textViewAdapterBooksName);
            convertView.setTag(vh);
        }
        else{
            vh= (ViewHolder) convertView.getTag();
        }

        vh.BookName.setText(booksListFiltered.get(position).getBookName());

        return convertView;
    }

    public class ViewHolder{
        private TextView BookName;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString();

                if(charString.isEmpty())
                    booksListFiltered= booksList;
                else{
                    List<Book> filteredList= new ArrayList<>();
                    for(Book item : booksList){

                        if(item.getBookName().toLowerCase().contains(charString.toLowerCase())
                                || item.getBookName().toLowerCase().contains(charSequence))
                            filteredList.add(item);
                    }
                    booksListFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= booksListFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                booksListFiltered= (ArrayList<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
