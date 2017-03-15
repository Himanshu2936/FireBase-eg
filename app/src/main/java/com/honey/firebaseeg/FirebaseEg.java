package com.honey.firebaseeg;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by honey on 1/21/2017.
 */

public class FirebaseEg  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
