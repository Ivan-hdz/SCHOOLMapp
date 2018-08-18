package mx.android.schoolapps.schoolmapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shiro on 04/10/2017.
 */

public class Book implements Parcelable {

    private String name;
    private String author;
    private int quantity;
    private String coverUrl;
    private String description;
    private String printing;
    private String language;

    public Book(){
    }

    public Book(String name, String author, int quantity, String coverUrl, String description,String printing,String language){
        this.name= name;
        this.author= author;
        this.quantity= quantity;
        this.coverUrl= coverUrl;
        this.description=description;
        this.printing=printing;
        this.language=language;
    }

    public String getBookName(){ return name;}

    public String getBookCoverUrl() {
        return coverUrl;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {return description;}

    public String getLanguage() {return language;}

    public String getPrinting() {return printing;}

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        quantity = in.readInt();
        coverUrl = in.readString();
        description=in.readString();
        printing=in.readString();
        language=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
        dest.writeInt(quantity);
        dest.writeString(coverUrl);
        dest.writeString(description);
        dest.writeString(printing);
        dest.writeString(language);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
