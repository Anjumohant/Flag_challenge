// Generated by view binder compiler. Do not edit!
package com.example.flags_challenge.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.flags_challenge.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContentSplashScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView appName;

  @NonNull
  public final ImageView ivSplashScreen;

  private ContentSplashScreenBinding(@NonNull ConstraintLayout rootView, @NonNull TextView appName,
      @NonNull ImageView ivSplashScreen) {
    this.rootView = rootView;
    this.appName = appName;
    this.ivSplashScreen = ivSplashScreen;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentSplashScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentSplashScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_splash_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentSplashScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appName;
      TextView appName = ViewBindings.findChildViewById(rootView, id);
      if (appName == null) {
        break missingId;
      }

      id = R.id.iv_splash_screen;
      ImageView ivSplashScreen = ViewBindings.findChildViewById(rootView, id);
      if (ivSplashScreen == null) {
        break missingId;
      }

      return new ContentSplashScreenBinding((ConstraintLayout) rootView, appName, ivSplashScreen);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
