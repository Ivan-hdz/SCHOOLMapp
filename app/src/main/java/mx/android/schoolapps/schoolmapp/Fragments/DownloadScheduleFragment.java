package mx.android.schoolapps.schoolmapp.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import mx.android.shcoolapps.schoolmap.R;

public class DownloadScheduleFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private WebView webView;
    private String boleta;
    private String regEx;

    public DownloadScheduleFragment() {
        // Required empty public constructor
    }

    public static DownloadScheduleFragment newInstance(String param1, String param2) {
        DownloadScheduleFragment fragment = new DownloadScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=  inflater.inflate(R.layout.fragment_download_schedule, container, false);

        AlertDialog.Builder textDialog= new AlertDialog.Builder(getContext());
        textDialog.setTitle("Descargar Horario");

        textDialog.setMessage("¿Estás seguro de querer descargarlo?");

        textDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Elegiste no descargarlo", Toast.LENGTH_SHORT).show();
            }
        });

        textDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getContext(), "Favor de iniciar sesión, esto con el fin de obtener su horario",
                        Toast.LENGTH_LONG).show();

                webView= view.findViewById(R.id.webViewDownloadScheduleFragment);

                webView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return (event.getAction() == MotionEvent.ACTION_MOVE);
                    }
                });

                webView.getSettings().setDomStorageEnabled(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                boleta = "";
                webView.setDownloadListener(new DownloadListener() {
                    public void onDownloadStart(String url, String userAgent,
                                                   String contentDisposition, String mimetype,
                                                   long contentLength) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                webView.setWebViewClient(new WebViewClient()
                {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url)
                    {
                        view.loadUrl(url);
                        return true;
                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onPageFinished(WebView view, String url)
                    {

                        System.out.println("url: "+url);
                        if(!url.contains("PDF"))
                        {
                            String getBoleta = getString(R.string.getBoleta);
                            ValueCallback<String> result = new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String s) {
                                    if(s.contains("20")){
                                        boleta = s.replaceAll("\"","");
                                        regEx = getString(R.string.firstHalfRegExp) + boleta +
                                                getString(R.string.secondHalfRegExp) ;
                                        webView.loadUrl(regEx);
                                    }
                                    else
                                    {
                                        String cssSAES = getString(R.string.css_mainSAES);
                                        webView.evaluateJavascript(cssSAES, null);
                                    }
                                    System.out.println("dentro " + boleta);
                                }
                            };
                            view.evaluateJavascript(getBoleta,result);
                        }
                        else
                        {
                            webView.loadUrl("<html><h1>Su horario se esta descargando..</h1></html>");
                        }
                    }
                });
                webView.loadUrl("https://www.saes.escom.ipn.mx");
            }
        });

        textDialog.show();

        return view;
    }

}
