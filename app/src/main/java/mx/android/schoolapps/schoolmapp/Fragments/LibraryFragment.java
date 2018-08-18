package mx.android.schoolapps.schoolmapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mx.android.schoolapps.schoolmapp.Activities.DetailBookActivity;
import mx.android.schoolapps.schoolmapp.Adapters.LibraryAdapter;
import mx.android.schoolapps.schoolmapp.Models.Book;
import mx.android.shcoolapps.schoolmap.R;
import mx.android.schoolapps.schoolmapp.Utils;

public class LibraryFragment extends Fragment {

    private int selectedSortOption;
    private int mParam2;

    private ArrayList<Book> booksList;
    private ListView booksListView;
    private static LibraryAdapter libraryAdapter;

    private static final String KEY_STRING= "SORT";
    private int COMMON_SORT= 0;
    private int INVERSE_SORT= 1;
    private View view;

    public LibraryFragment() {
    }

    public static LibraryFragment newInstance(int sortOptionSelected) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_STRING,sortOptionSelected);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_library, container, false);

        booksList= getBooksList();

        Collections.sort(booksList, new Utils.BookComparator());

        booksListView= view.findViewById(R.id.listViewLibraryFragment);
        booksListView.setVerticalScrollBarEnabled(false);
        libraryAdapter= new LibraryAdapter(getContext(), R.layout.library_adapter_listview, booksList);

        booksListView.setAdapter(libraryAdapter);
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book selectedBook= libraryAdapter.getItem(position);
                Intent intent= new Intent(getContext(), DetailBookActivity.class);

                intent.putExtra("selectedBook",selectedBook);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:

                return false;
            case R.id.classroomSortAtoZ:
                item.setChecked(true);

                Collections.sort(booksList, new Utils.BookComparator());
                libraryAdapter= new LibraryAdapter(getContext(), R.layout.library_adapter_listview, booksList);
                booksListView.setAdapter(libraryAdapter);

                libraryAdapter.notifyDataSetChanged();
                return true;
            case R.id.classroomSortZtoA:
                item.setChecked(true);

                Collections.sort(booksList, new Utils.InverseBookComparator());
                libraryAdapter= new LibraryAdapter(getContext(), R.layout.library_adapter_listview, booksList);
                booksListView.setAdapter(libraryAdapter);

                libraryAdapter.notifyDataSetChanged();
                return true;
        }
        return false;
    }

    public static void loadData(String query){
        libraryAdapter.getFilter().filter(query);
    }

    public ArrayList<Book> getBooksList() {
        return new ArrayList<Book>(){
            {
                add(new Book("Beggining Android application development",
                        "Lee, Wei-Meng.",1,
                        "https://image.slidesharecdn.com/beginningandroidapplicationdevelopmentwei-menglee-120617094530-phpapp01/95/beginning-android-application-development-wei-meng-lee-1-728.jpg?cb=1339926508",
                        "533 p. :  il. ;  24 cm.",
                        "Indianapolis, IN : Wiley Pub., Inc, 2011.",
                        "ENG"));
                add(new Book("Algoritmos a fondo: con implementaciones en C y Java",
                        "Sznajdleder, Pablo Augusto.",7,
                        "https://3.bp.blogspot.com/-o3ZZhaJKlAY/WOAPnrz_3NI/AAAAAAAAaw4/timr0saJsak3ABZwx1gNOPFdcj9StADigCLcB/s1600/Java%2Ba%2Bfondo%2BEstudio%2Bdel%2Blenguaje%2By%2Bdesarrollo%2Bde%2Baplicaciones%252C%2B2da%2BEdici%25C3%25B3n%2B%25E2%2580%2593%2BPablo%2BAugusto%2BSznajdleder-FREELIBROS.jpg",
                        "xxii, 553 p. :  il. ;  24 cm.",
                        "Argentina : Alfaomega, ©2012.",
                        "ESP"));
                add(new Book("Cómo programar en C/C++ y Java",
                        "Deitel, Harvey M.",4,
                        "https://image.slidesharecdn.com/comoprogramarccyjava4taedicin-deiteldeitel-150817222918-lva1-app6892/95/como-programar-c-c-y-java-4ta-edicin-deitel-deitel-1-638.jpg?cb=1439850689",
                        "xxxv, 1113 p. :  il. ;  26 cm. +  1 CD-Rom (4 3/4 plg.).",
                        "México, D.F. : Pearson, ©2004.",
                        "ESP ENG"));
                add(new Book("Cómo programar en C#",
                        "Deitel, Harvey M.",7,
                        "https://i.pinimg.com/originals/e3/16/36/e3163600e79321af0a1bd1ce683971a3.jpg",
                        "xxx, 1166 p. :  il. ;  26 cm. +  1 CD-ROM (4 3/4 plg.).",
                        "México, D.F. : Pearson/Prentice Hall, ©2007.",
                        "ESP ENG"));
                add(new Book("Cómo programar en C++",
                        "Deitel, Harvey M.",10,
                        "https://cdn.gandhi.com.mx/media/catalog/product/i/m/image_1165_1_90547.jpg",
                        "xliv, 927 p. :  il., cuadros, dibujos, tablas ;  24 cm +  1 CD Rom",
                        "México : Prentice-Hall Hispanoamericana , 2003.",
                        "ESP ENG"));
                add(new Book("Cómo programar en Java",
                        "Deitel, Harvey M.",10,
                        "https://image.slidesharecdn.com/comoprogramarjava9naedicion-deitel-150429022148-conversion-gate01/95/como-programar-java-9na-edicion-deitel-1-638.jpg?cb=1430295130",
                        "lii, 1056 p. :  il. ;  24 cm.",
                        "México, D.F. : Prentice-Hall Hispanoamericana, ©1998.",
                        "ESP ENG"));
                add(new Book("Administración de sistemas Linux",
                    "Adelstein,Tom",1,
                    "https://images-eu.ssl-images-amazon.com/images/I/61LLxy8%2BEmL._SL500_AA300_.jpg",
                    "336 p. :  il. ;  22 cm.",
                    "Madrid : Anaya Multimedia, 2007.",
                    "ESP"));
                add(new Book("Windows Server 2003: instalación y configuración avanzada",
                        "Raya Cabrera, José Luis",1,
                        "https://pictures.abebooks.com/isbn/9788478977284-es-300.jpg",
                        "xi, 775 p. :  il. ;  23 cm.",
                        "México : Alfaomega, c2004.",
                        "ESP"));
                add(new Book("Redes de computadoras",
                        "Tanenbaum, Andrew S.",2,
                        "https://cdn.gandhi.com.mx/media/catalog/product/i/m/image_1165_1_92619.jpg",
                        "xxiv, 791 p. :  il. ;  26 cm.",
                        "Naucalpan de Juárez, Estado de México : Pearson Educación, c2012.",
                        "ESP ENG"));
                add(new Book("Redes neuronales: visión computacional y micromecánica",
                        "Baidyk, Tatiana",5,
                        "https://pictures.abebooks.com/isbn/9786072000216-es-300.jpg",
                        "57 p. :  il. ;  21 cm.",
                        "México D.F. : UNAM ; Itaca, 2009.",
                        "ESP"));
                add(new Book("Redes CISCO: guía de estudio para la certificación",
                        "Ariganello, Ernesto",5,
                        "https://www.elsotano.com/cover/792/8/1/358-450/redes-cisco-guia-de-estudio-para-la-certificacion-ccna-routing-y-switching-4-ed/9788499646640.jpg",
                        "xvii, 331 p. :  il. ;  23 cm.",
                        "México : Alfaomega , c2009.",
                        "ESP"));
                add(new Book("Circuitos eléctricos y aplicaciones digitales",
                        "Villaseñor Gómez, Jorge Raúl",2,
                        "http://s3.amazonaws.com/gandhi-images/9786073215152/400/false/image_1165_1_60715.jpg",
                        "xiii, 697 p. :  il. ;  27 cm.",
                        "Naucalpan de Juárez, Estado de México : Pearson, 2013.",
                        "ESP"));
                add(new Book("Circuitos eléctricos",
                        "Dorf, Ricahrd C. y Svodoba, James",5,
                        "https://www.elsolucionario.org/wp-content/archivos/2017/12/circuitos-electricos-dorf-svoboda-8va-edicion.jpg",
                        "xix, 899 p. :  il. ;  25 cm.",
                        "México D.F. : Alfaomega, ©2015.",
                        "ESP"));
                add(new Book("Fundamentos de circuitos eléctricos",
                        "Alexander, Charles K.",2,
                        "https://3.bp.blogspot.com/-ZrF1r7jSPbw/WM1JYinEiUI/AAAAAAAAYY0/qx5IPlGCEjQYpT2htJ3eOooElVqyG1LgQCLcB/s1600/Fundamentos%2Bde%2Bcircuitos%2Bel%25C3%25A9ctricos%252C%2B5ta.%2BEdici%25C3%25B3n%2B%25E2%2580%2593%2BCharles%2BK.%2BAlexander.jpg",
                        "xx, 786 p. :  il. ;  27 cm.",
                        "México, D.F. : McGraw-Hill, ©2013.",
                        "ESP ENG"));
                add(new Book("Diseño de Algoritmos y su Programación en C",
                        "Méndez Girón, Alejandra Margarita.",5,
                        "https://cdn.gandhi.com.mx/media/catalog/product/i/m/image_1165_1_94408.jpg",
                        "xii, 282 p. :  il. ;  23 cm.",
                        "México : Alfaomega, c2013.",
                        "ESP"));
                add(new Book("Análisis y diseño de sistemas",
                        "Kendall, Kenneth E.",3,
                        "https://3.bp.blogspot.com/-dicjVHUrHlg/WO9-1bqADEI/AAAAAAAAb1E/i8MOarvVluUV91qaLiq1ccQ3SxwubqxOACLcB/s1600/An%25C3%25A1lisis%2By%2Bdise%25C3%25B1o%2Bde%2Bsistemas%252C%2B8va%2BEdici%25C3%25B3n%2B-%2BKenneth%2BE.%2BKendall%2By%2BJulie%2BE.%2BKendall-FREELIBROS.jpg",
                        "xxvi, 726 p. :  il. ;  27 cm.",
                        "México : Pearson Educación, 2006.",
                        "ESP"));
                add(new Book("Introducción a la computación y programación con Python: un enfoque multimedia",
                        "Guzdial, Mark.",2,
                        "https://3.bp.blogspot.com/-V2uf9qbh6Kc/WOF7tRCdr5I/AAAAAAAAbHY/fkm1kjmiQeEotgVhB1cVsNKWpQTa_CbzQCLcB/s1600/Introducci%25C3%25B3n%2Ba%2Bla%2Bcomputaci%25C3%25B3n%2By%2Bprogramaci%25C3%25B3n%2Bcon%2BPython%252C%2B3ra%2BEdici%25C3%25B3n%2B-%2BMark%2BJ.%2BGuzdial%2By%2BBarbara%2BEricson-FREELIBROS.jpg",
                        "xxi,424p. :  il. ;  23 cm.",
                        "México : Pearson, 2013.",
                        "ESP ENG"));
                add(new Book("Computer Security",
                        "Gollmann, Dieter",2,
                        "https://images-na.ssl-images-amazon.com/images/I/51JMVhsjd0L._SX404_BO1,204,203,200_.jpg",
                        "xix, 436 pág. :  il. ;  24cm.",
                        "Chichester, West Sussex : Wiley, 2012.",
                        "ENG"));
                add(new Book("Bases de datos",
                        "Reinosa, Enrique José",2,
                        "https://img4.org/images/2017/09/15/b5336058ab46925f6604778b67c25786.jpg",
                        "xix, 361 páginas :  ilustraciones ;  24 cm.",
                        "Buenos Aires ; : Alfaomega, 2012. México, D.F. ",
                        "ESP"));
                add(new Book("Bases de datos",
                        "Ricardo, Catherine M.",2,
                        "https://3.bp.blogspot.com/-g6z0XKJwzWc/WOlVWdkhtPI/AAAAAAAAbbE/ae4L0hV8ShkptcyEgYFtqPFmINT45PZoQCLcB/s1600/Bases%2Bde%2Bdatos%2B-%2BCatherine%2BM.%2BRicardoFREELIBROS.jpg",
                        "xix, 642 p. :  il. ;  27 cm.",
                        "México, D.F. : McGraw-Hills, c2009.",
                        "ESP ENG"));
                add(new Book("Introducción a la teoría de autómatas, lenguajes y computación",
                        "Hopcroft, John E.",2,
                        "https://static.cegal.es/imagenes/marcadas/9788478/978847829088.gif",
                        "xvi, 440 p. :  il. ;  25 cm. ",
                        "Madrid : Pearson : Addison Wesley , c2008.",
                        "ESP ENG"));
                add(new Book("JEE 7 a fondo: diseño y desarrollo de aplicaciones en Java Enterprise",
                        "Sznajdleder, Pablo Augusto.",5,
                        "http://libu.s3.amazonaws.com/img/1066_9789586829885_alfa.jpg",
                        "246 p. :  il. ;  23 cm +  material complementario en la web. ",
                        "Buenos Aires : Alfaomega, ©2015.",
                        "ESP"));
                add(new Book("Big Data: técnicas, herramientas y aplicaciones",
                        "Pérez Marqués, María",5,
                        "https://imagessl9.casadellibro.com/a/l/t0/59/9788494305559.jpg",
                        "xiv, 339 p. :  il. ;  23 cm.",
                        "México, D.F. : Algaomega, 2015.",
                        "ESP"));
                add(new Book("Matemáticas discretas con aplicaciones",
                        "Epp, Susanna S.",2,
                        "https://4.bp.blogspot.com/-QWgyZdNtxYE/WNgNDfp8W7I/AAAAAAAAZ_c/6Nu10ROPd1Y7y0c9RPTjBksajoMtZwcjACLcB/s1600/Matem%25C3%25A1ticas%2Bdiscretas%2Bcon%2Baplicaciones%252C%2B4ta%2BEdici%25C3%25B3n%2B%25E2%2580%2593%2BSusanna%2BS.%2BEpp-FREELIBROS.jpg",
                        "xxii, 820 páginas, I-19 :  ilustraciones ;  27cm.",
                        "México, D.F. : Cengage Learning, 2012.",
                        "ESP ENG"));
                add(new Book("Ecuaciones diferenciales con aplicaciones de modelado",
                        "Zill, Dennis G.",2,
                        "https://image.slidesharecdn.com/zillecuacionesdiferenciales-120911204433-phpapp01/95/zill-ecuaciones-diferenciales-1-728.jpg?cb=1347396502",
                        "xiv, 362 p. :  il. ;  27 cm.",
                        "México, D. F. : Cengage Learning, ©2009.",
                        "ESP ENG"));
                add(new Book("Teoría de la computación: lenguajes formales, autómatas y complejidad",
                        "Brookshear, J. Glenn.",2,
                        "https://luisfernandohurtado.files.wordpress.com/2015/01/lf_c_umg.jpg",
                        "xii, 338 p. :  il. ;  28 cm. ",
                        "Wilmintong, Delawere ; México : Addison Wesley Iberoamericana, ©1993. ",
                        "ESP ENG"));
                add(new Book("Cálculo diferencial e integral",
                        "Granville, William Anthony",6,
                        "https://http2.mlstatic.com/calculo-diferencial-e-integral-granville-D_NQ_NP_824001-MLM20262647676_032015-F.jpg",
                        "xiv, 686 p. :  il. ;  23 cm.",
                        "México, D.F. : Limusa, 1963.",
                        "ESP ENG"));
                add(new Book("Análisis vectorial",
                        "Spiegel, Murray R.",3,
                        "https://libros-gratis.com/wp-content/uploads/2016/09/analisis-vectorial-2da-edicion-murray-r-spiegel-seymour-lipschutz-dennis-spellman.jpeg",
                        "xi, 237 p. :  il. ;  27 cm. ",
                        " México, D.F. : McGraw-Hill, c2011.",
                        "ESP ENG"));
                add(new Book("Matemáticas discretas",
                        "Espinosa Armenta, Ramón",10,
                        "http://www.gandhi.com.mx/media/catalog/product/i/m/image_1165_1_297226_46_503_19_774_26_47.jpg",
                        "xxi, 467 p.  il. ;  24 cm +  material complementario en la web.",
                        "México, D.F. : Alfaomega, c2010.",
                        "ESP"));
                add(new Book("Fundamentos de lógica digital con diseño VHDL",
                        "Brown, Stephen.",10,
                        "https://img.segundamano.mx/medium/67/6787463086.jpg",
                        "xx, 939 p. :  il ;  23 cm +  1 disco óptico laser para computadora (4 3/4 plg.).",
                        "México : McGraw-Hill, c2006",
                        "ESP ENG"));
                add(new Book("VHDL: lenguaje para síntesis y modelado de circuitos",
                        "Brown, Stephen.",14,
                        "https://imgv2-1-f.scribdassets.com/img/document/37379821/original/d398581f59/1507837734",
                        "xiv, 251 p. +  1 Disco optico laser para computadora (4 3/4 plg.)",
                        "México : Alfaomega, 2004. ",
                        "ESP"));
                add(new Book("Estructuras de datos en Java: compatible con Java 2",
                        "Weiss, Mark Allen.",3,
                        "https://i.pinimg.com/originals/91/14/31/9114317e4f4de0fbef7c1b7d14636d7e.jpg",
                        "xxx, 740 p. ; :  il. ;  25 cm.",
                        "Madrid : Pearson, 2000. ",
                        "ESP ENG"));
                add(new Book("Álgebra lineal",
                        "Grossman, Stanley I.",20,
                        "https://cdn.slidesharecdn.com/ss_thumbnails/algebralinealstanleyi-150323000816-conversion-gate01-thumbnail-4.jpg?cb=1427069430",
                        "xxii, 634 p. :  il. ;  23 cm. ",
                        "México, D.F. : McGraw-Hill, ©1996. ",
                        "ESP ENG"));
                add(new Book("Probabilidad y estadística para ingeniería y ciencias",
                        "Walpole, Ronald E.",3,
                        "https://4.bp.blogspot.com/-Kk-dGN1nnOY/WO4jS3UFGvI/AAAAAAAAbuQ/pVhO2qW6ehEs1C8p5dtY2_hHqQ266eeRgCLcB/s1600/Probabilidad%2By%2Bestad%25C3%25ADstica%2Bpara%2Bingenier%25C3%25ADa%2By%2Bciencias%252C%2B9na%2BEdici%25C3%25B3n-FREELIBROS.jpg",
                        "xx, 792 p. :  il. ;  24 cm. ",
                        "Naucalpan de Juárez, Estado de México : Perarson Educación , c2012.",
                        "ESP ENG"));
                add(new Book("Matemáticas avanzadas para ingeniería",
                        "Zill, Dennis G.",2,
                        "https://cdn.gandhi.com.mx/media/catalog/product/i/m/image_1165_1_90688.jpg",
                        "xxxiii, 697 p. :  il. ;  27 cm. ",
                        "México, D.F. : McGraw Hill, c2012. ",
                        "ESP ENG"));
                add(new Book("Matemáticas avanzadas para la ingeniería",
                        "O'Neil, Peter V.",7,
                        "http://2.bp.blogspot.com/-PUqrZp5Gy28/U7pwuJ0HDuI/AAAAAAAAAIE/C1mw2LYWc08/s1600/P%C3%A1ginas+extra%C3%ADdas+sin+t%C3%ADtulo.jpg",
                        "xi, 557 p., :  il., ;  27 cm.",
                        "México, D.F. : Thomson ; Cengage Learning, ©2008. ",
                        "ESP ENG"));
                add(new Book("Procesamiento de bases de datos: fundamentos, diseño e implementación",
                        "Kroenke, David M.",10,
                        "https://3.bp.blogspot.com/-t-qUTsbQU18/V8O5KKCSJnI/AAAAAAAAERU/19yN_jNtTeoY8OEfY3eL80JQ7FuDfCVjACLcB/s1600/ta-pensamiento.jpg",
                        "xv, 671 p. :  il. ; +  1 Disco óptico laser para computadora (4 3/4 plg.).",
                        "México : Pearson, c2003.",
                        "ESP"));
                add(new Book("Sistemas de información para la administración: técnicas e instrumentación",
                        "Bocchino, Wiliam A.",5,
                        "http://biblioteca.hotelescuela.org.ve/wp-content/uploads/2017/05/sistemas-de-informa.jpg",
                        "403 p. ",
                        "México : Trillas, 1990. ",
                        "ESP"));
                add(new Book("Internet and world wide web: how to program",
                        "Deitel, Paul J.",2,
                        "http://www-fp.pearsonhighered.com/assets/hip/images/bigcovers/0273764020.jpg",
                        "921 p. :  il. ;  23 cm. ",
                        "United States of America, Boston : Pearson Education, Inc/, ©2012. ",
                        "ENG"));
                add(new Book("Redes de computadoras: un enfoque descendente",
                        "Kurose, James F.",2,
                        "https://images-na.ssl-images-amazon.com/images/I/61kV4uArUuL.jpg",
                        "xxv, 817 páginas :  ilustraciones, diagramas ;  25 cm. ",
                        "España : Pearson Educación, c2010. ",
                        "ESP ENG"));
                add(new Book("Minería de datos a través de ejemplos",
                        "Pérez Marqués, María",5,
                        "http://libu.s3.amazonaws.com/img/1043_9789586829670_alfa.jpg",
                        "460 pag. :  il. ;  23 cm. +  complementario en la web. ",
                        "México D.f. : Alfaomga, ©2015. ",
                        "ESP"));
                add(new Book("Domine Javascript",
                        "López Quijado, José",2,
                        "http://cdn.gandhi.com.mx/media/catalog/product/i/m/image_1165_1_91768.jpg",
                        "705 p. :  il. ;  23 cm.",
                        "México, D.F. : Alfa-Omega : Ra-Ma, ©2011. ",
                        "ESP"));
                add(new Book("Fundamentos de sistemas de bases de datos",
                        "Elmasri, Ramez",1,
                        "https://2.bp.blogspot.com/-02PL9MBbsPY/WqrhyXd8NSI/AAAAAAAAEeI/Y-nOn5BtLE8FHwHb9KXgYYqP2_Hp7I2CgCLcBGAs/s1600/fundamentos-de-sistemas-de-bases-de-datos-5ta-edicion-ramez-elmasri-lv.jpg",
                        "xxiii, 988 paginas. :  ilustraciones. ;  25 cm. ",
                        "Madrid : Pearson Educación : Addison Wesley, 2007. ",
                        "ESP ENG"));
                add(new Book("Análisis y diseño orientado a objetos con aplicaciones",
                        "Booch, Grady.",5,
                        "https://k19.kn3.net/6135F1072.jpg",
                        "xix, 638 p. :  il. ;  24 cm. ",
                        "Wilmington, Del. : Addison-Wesley Iberoamericana : Díaz de Santos, ©1996. ",
                        "ESP ENG"));
                add(new Book("Introducción al álgebra lineal",
                        "Larson, Roland E.",1,
                        "https://1.bp.blogspot.com/_dhWHn-3u8Kk/S-HQ-QHwKQI/AAAAAAAADPI/Krfu14IQZkY/w800-h800/introduccion_al_algebra_lineal_larson.jpg",
                        "659 p. :  il. ;  23 cm.  ",
                        "México : Limusa-Noriega, ©1994. ",
                        "ESP ENG"));
                add(new Book("Patrones de diseño: elementos de software orientado a objetos reutilizable",
                        "Gamma, Erich",1,
                        "https://www.elsolucionario.org/wp-content/archivos/2018/03/patrones-de-diseno-erich-gamma-1ra-edicion.jpg",
                        "xvi, 364 p. :  il. ;  25 cm.",
                        "Madrid : Addison Wesley, c2003. ",
                        "ESP ENG"));
                add(new Book("UML y patrones: introducción al análisis y diseño orientado a objetos",
                        "Larman, Craig",6,
                        "https://www.dykinson.com/static/img/portadas/9788420534381.jpg",
                        "xxiii, 507 p. :  il. ;  26 cm. ",
                        "México, D.F. : Pearson, ©1999. ",
                        "ESP ENG"));
                add(new Book("Digital design: with RTL design, VHDL and Verilog",
                        "Vahid, Frank",2,
                        "http://www.cs.ucr.edu/~vahid/dd/dd2e_cover_web.jpg",
                        "xvi, 575 p. :  il. ;  25 cm. ",
                        "Hoboken, NJ : Wiley, ©2011. ",
                        "ENG"));
                add(new Book("Java: como programar",
                        "Deitel, Paul J.",3,
                        "https://www.pearsonhighered.com/assets/bigcovers/0/1/3/6/0136123716.jpg",
                        "xxxvi, 1389 páginas :  ilustraciones, diagramas ;  25 cm. +  1 CD-ROM (4 3/4 plg.). ",
                        "México : Pearson Educación , c2008. ",
                        "ESP ENG"));
                add(new Book("Métodos numéricos para ingenieros",
                        "Chapra, Steven C.",2,
                        "https://1.bp.blogspot.com/-JhvYQBHs36M/WPDZk3H2pDI/AAAAAAAAcCA/N3EGeF07izEVr0l-df4ndifav2U-QuuKwCLcB/s1600/M%25C3%25A9todos%2Bnum%25C3%25A9ricos%2Bpara%2Bingenieros%252C%2B5ta%2BEdici%25C3%25B3n%2B%25E2%2580%2593%2BSteven%2BC.%2BChapra%2By%2BRaymond%2BP.%2BCanale-FREELIBROS.jpg",
                        "xxi, 878 p. :  il. ;  23 cm.",
                        "México : Mc Graw Hill, 2011. ",
                        "ESP ENG"));
                add(new Book("Introduction to algorithms",
                        "Cormen, Thomas H.",2,
                        "https://rukminim1.flixcart.com/image/704/704/book/8/4/8/introduction-to-algorithms-original-imadddghtzxgsh4t.jpeg?q=70",
                        "xix, 1292 p. :  il. ;  24 cm. ",
                        "Cambridge, Mass. : MIT Press, ©2009. ",
                        "ENG"));
                add(new Book("Análisis de algoritmos: implementaciones en C y Pascal",
                        "López, Gustavo",5,
                        "https://4.bp.blogspot.com/-rA0pJJb0UsU/WX2GczL8qaI/AAAAAAAAAsU/C9OU_CU2rzkp6W4v9hudDelAEmbAghU2gCLcBGAs/s1600/An%25C3%25A1lisis%2By%2Bdise%25C3%25B1o%2Bde%2Balgoritmos%2B-%2BGustavo%2BL%25C3%25B3pez-FREELIBROS.ORG.jpg",
                        "xxiii, 309 páginas :  ilustraciones ;  24 cm. ",
                        "Buenos Aires, Argentina : Alfaomega , c2009. ",
                        "ESP"));
                add(new Book("Calidad en el desarrollo de software",
                        "Pantaleo, Guillermo",2,
                        "https://libreriapensar.com/3272-atmn_large/calidad-en-el-desarrollo-de-software-guillermo-pantaleo-alfaomega.jpg",
                        "207p. :  il. ;  23 cm.",
                        "Argentina : Alfaomega, 2011. ",
                        "ESP"));
                add(new Book("Ingeniería del software: un enfoque práctico",
                        "Pressman, Roger S.",2,
                        "https://4.bp.blogspot.com/-br73R8-gG0I/WO5dojIkfHI/AAAAAAAAb0c/R3gOFLDFN-A8R_smJfXaNNOUXZR4kWKkwCLcB/s1600/Ingenier%25C3%25ADa%2Bdel%2Bsoftware%2BUn%2Benfoque%2Bpr%25C3%25A1ctico%252C%2B7ma%2BEdici%25C3%25B3n%2B-%2BRoger%2BS.%2BPressman-FREELIBROS.jpg",
                        "xxix, 777 p. :  il. ;  27 cm. ",
                        "México, D.F. : McGraw-Hill Interamericana, ©2010. ",
                        "ESP ENG"));
                add(new Book("Compiladores: principios, técnicas y herramientas",
                        "Aho, Alfred V.",10,
                        "https://1.bp.blogspot.com/-i5EpubIJ47s/UU6OQ8b04eI/AAAAAAAAACY/SeRGErP925A/s1600/compiladores+2da+ed+freelibros.jpg",
                        "xii, 820 p. :  il. ;  23 cm. ",
                        "México, D.F. : Addison Wesley, ©1990. ",
                        "ESP ENG"));
                add(new Book("Compiladores: conceptos fundamentales",
                        "Teufel, Bernard",10,
                        "http://www.etp.com.py/common/getBookImg?attachmentId=97574",
                        " \txi  ; 179 p. ",
                        "Wilmington, Delawere, E.U. : Addison-Wesley Iberoamericana, c1995 ",
                        "ESP"));
            }
        };
    }
}
