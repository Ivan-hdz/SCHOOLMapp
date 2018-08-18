package mx.android.schoolapps.schoolmapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import mx.android.schoolapps.schoolmapp.Fragments.CafeteriaFragment;
import mx.android.schoolapps.schoolmapp.Fragments.FLFB;
import mx.android.shcoolapps.schoolmap.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class JavaBridge {
    Fragment frag;
    public JavaBridge(Fragment frg) {
        frag = frg;
    }
    @JavascriptInterface
    public void launchLibrary(){
        frag.getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new FLFB())
                .addToBackStack(null)
                .commit();

    }
    @JavascriptInterface
    public void launchCafe()
    {
        frag.getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new CafeteriaFragment())
                .addToBackStack(null)
                .commit();
    }

    @JavascriptInterface
    public void showToast(String txt)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, txt, duration);
        toast.show();
    }
}