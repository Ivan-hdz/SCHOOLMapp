package mx.android.schoolapps.schoolmapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONObject;

import mx.android.shcoolapps.schoolmap.R;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private String accessToken;
    private String coverPhotoURL;
    //private LoginButton loginButton;
    //private Button loginButtonNoFacebook;
    //private ImageView loginImage;
    //private ImageView loginImageAlreadyLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        /*
        loginImage= findViewById(R.id.loginImage);
        //loginButton = findViewById(R.id.login_button);
        loginButtonNoFacebook= findViewById(R.id.login_buttonNoFacebook);
        loginImageAlreadyLogged= findViewById(R.id.loginImageAlreadyLogged);

        loginButtonNoFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken= loginResult.getAccessToken().getToken();
                getEscomPublicPageData();

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Profile profile = Profile.getCurrentProfile();
                        nextActivity(profile);
                        Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                    }
                },1000);
            }

            @Override
            public void onCancel() {
                System.out.println("Canceled...");
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
                System.out.println("There was an Error in Login");
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        try{
            accessToken= AccessToken.getCurrentAccessToken().getToken();

            System.out.println("onResume");
            System.out.println("Current access token: " + accessToken);

            /*
            getEscomPublicPageData();
            //loginButton.setVisibility(View.INVISIBLE);
            loginButtonNoFacebook.setVisibility(View.INVISIBLE);
            loginImage.setVisibility(View.INVISIBLE);
            loginImageAlreadyLogged.setVisibility(View.VISIBLE);
            */
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Profile profile = Profile.getCurrentProfile();
                    nextActivity(profile);
                }
            },1500);
        }catch(Exception e){
            Profile profile = Profile.getCurrentProfile();
            nextActivity(profile);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        System.out.println("onStop");
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        System.out.println("onActivityResult");
        callbackManager.onActivityResult(requestCode, responseCode, intent);
    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
            main.putExtra("coverImageUrl",coverPhotoURL);
            startActivity(main);
        }
    }

    private void getEscomPublicPageData(){
        System.out.println("ACCESS TOKEN= " + accessToken);

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/118118308346763/photos",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        System.out.println(response);
                        try{
                            JSONObject jsonObject= response.getJSONObject();
                            JSONArray dataJsonArray= jsonObject.getJSONArray("data");

                            JSONObject dataJsonObject= dataJsonArray.getJSONObject(0);
                            coverPhotoURL= dataJsonObject.getString("source");

                            System.out.println("CoverPhoto URL= " + coverPhotoURL);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "source");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
