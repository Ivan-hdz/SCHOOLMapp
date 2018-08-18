package mx.android.schoolapps.schoolmapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import mx.android.shcoolapps.schoolmap.R;
import mx.android.schoolapps.schoolmapp.Utils;

import static com.facebook.HttpMethod.GET;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

public class CounselorsNewsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SwipeRefreshLayout swipeRefreshNews;
    private String counselorsNewsUrl= "https://www.facebook.com/consejeros.escom/?fref=ts";

    public CounselorsNewsFragment() {
        // Required empty public constructor
    }

    public static CounselorsNewsFragment newInstance(String param1, String param2) {
        CounselorsNewsFragment fragment = new CounselorsNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_counselors_news, container, false);

        swipeRefreshNews= view.findViewById(R.id.swipeRefreshCounselorsNews);
        final WebView webView= view.findViewById(R.id.webViewCounselorsPage);

        WebSettings webSettings= webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(counselorsNewsUrl);

        swipeRefreshNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.loadUrl(counselorsNewsUrl);
            }
        });

        return view;
    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {

            swipeRefreshNews.setRefreshing(false);
            counselorsNewsUrl = url;
            super.onPageFinished(view, url);
        }
    }

}
