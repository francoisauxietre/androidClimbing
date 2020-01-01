package frox.world.com.controller.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import frox.world.com.R;


//https://firebase.google.com/docs/auth/android/facebook-login
//https://firebase.google.com/docs/auth/android/apple
//https://firebase.google.com/docs/auth/android/google-signin?utm_source=studio
//https://firebase.google.com/docs/auth/android/twitter-login



public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
}
/*
    // reuqest Id token : AIzaSyBJYYlRyXUKRZkgQQDTtGSjeehXElfzImU Api key google
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //commum a google et facebok
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("LOGIN_ACTIVITY_onActivityResult", "Google sign in failed", e);
                // ...
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("LOGIN_ACTIVITY_firebaseAuthWithGoogle", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOGIN_ACTIVITY_firebaseAuthWithGoogle", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOGIN_ACTIVITY_firebaseAuthWithGoogle", "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


//    FirebaseAuth.getInstance().signOut();

Initialize Facebook Login button
    mCallbackManager = CallbackManager.Factory.create();
    LoginButton loginButton = findViewById(R.id.buttonFacebookLogin);
loginButton.setReadPermissions("email", "public_profile");
loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d(TAG, "facebook:onSuccess:" + loginResult);
            handleFacebookAccessToken(loginResult.getAccessToken());
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "facebook:onCancel");
            // ...
        }

        @Override
        public void onError(FacebookException error) {
            Log.d(TAG, "facebook:onError", error);
            // ...
        }
    });
    // ...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
*/