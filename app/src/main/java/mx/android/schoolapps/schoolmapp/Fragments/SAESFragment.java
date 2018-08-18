package mx.android.schoolapps.schoolmapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import mx.android.shcoolapps.schoolmap.R;
import mx.android.schoolapps.schoolmapp.Utils;


public class SAESFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SAESFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SAESFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SAESFragment newInstance(String param1, String param2) {
        SAESFragment fragment = new SAESFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_sae, container, false);

        /*final WebView webView= view.findViewById(R.id.wv_captcha);
        WebSettings webSettings= webView.getSettings();
        webView.setWebViewClient(new MyWebViewClient());
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        webView.loadUrl("https://www.saes.escom.ipn.mx/");

        Button btn_getOther = view.findViewById(R.id.btn_captcha);
        btn_getOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.evaluateJavascript("document.location.reload();", null);
            }
        });*/
        Button btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText usr, pass, capt;
                usr = (EditText)view.findViewById(R.id.txt_boleta);
                /*pass = (EditText)view.findViewById(R.id.txt_pass);
                capt = (EditText)view.findViewById(R.id.txt_captcha);
                String js = "document.aspnetForm.elements.ctl00_leftColumn_LoginUser_UserName.value = \""+ usr.getText().toString() +"\";" +
                        "document.aspnetForm.elements.ctl00_leftColumn_LoginUser_Password.value = \""+pass.getText().toString()+"\";" +
                        "document.aspnetForm.elements.ctl00_leftColumn_LoginUser_CaptchaCodeTextBox.value = \""+ capt.getText().toString() +"\";" +
                        "document.aspnetForm.elements.ctl00$leftColumn$LoginUser$LoginButton.click();";
                webView.evaluateJavascript(js, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        webView.loadUrl("https://www.saes.escom.ipn.mx/PDF/Alumnos/Reinscripciones/"+usr.getText().toString()+"-ComprobanteHorario.pdf");

                    }
                });*/
                PDFView pdfV = (PDFView)view.findViewById(R.id.pdfView);
                Utils u = new Utils();
                Utils.RetrievePDFStream pdf = u.new RetrievePDFStream(pdfV);
                pdf.execute("https://www.saes.escom.ipn.mx/PDF/Alumnos/Reinscripciones/"+usr.getText().toString()+"-ComprobanteHorario.pdf");

            }
        });

        // Inflate the layout for this fragment

        return view;
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url) {
            if (url.endsWith(".pdf")) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }
            return false;
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPageFinished(final WebView wv, String url)
        {
            wv.evaluateJavascript("document.getElementById(\"ctl00_leftColumn_LoginNameSession\").innerHTML", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    System.out.println("--------");
                    System.out.println(value);
                    if(value.equals("null") )
                    {
                        String js = "document.getElementById('wrapper').style.display = \"none\";\n" +
                                "document.body.innerHTML += \"<img src='\" + document.getElementById(\"c_default_ctl00_leftcolumn_loginuser_logincaptcha_CaptchaImage\").src + \"' style='width: 90%; height: 90%; display: block' />\";";
                        wv.evaluateJavascript(js, null);

                    }else
                    {
                        wv.loadUrl("javascript:__doPostBack('ctl00$leftColumn$LoginStatusSession$ctl00','')");

                    }
                }
            });

        }
    }

}
