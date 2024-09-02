// Generated by view binder compiler. Do not edit!
package com.example.trackmyride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.trackmyride.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStartBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView appLogo;

  @NonNull
  public final ImageView background;

  @NonNull
  public final Button conductorButton;

  @NonNull
  public final View decorativeElement;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView subtitleText;

  @NonNull
  public final TextView titleText;

  @NonNull
  public final Button userButton;

  private ActivityStartBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView appLogo,
      @NonNull ImageView background, @NonNull Button conductorButton,
      @NonNull View decorativeElement, @NonNull ConstraintLayout main,
      @NonNull TextView subtitleText, @NonNull TextView titleText, @NonNull Button userButton) {
    this.rootView = rootView;
    this.appLogo = appLogo;
    this.background = background;
    this.conductorButton = conductorButton;
    this.decorativeElement = decorativeElement;
    this.main = main;
    this.subtitleText = subtitleText;
    this.titleText = titleText;
    this.userButton = userButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_start, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appLogo;
      ImageView appLogo = ViewBindings.findChildViewById(rootView, id);
      if (appLogo == null) {
        break missingId;
      }

      id = R.id.background;
      ImageView background = ViewBindings.findChildViewById(rootView, id);
      if (background == null) {
        break missingId;
      }

      id = R.id.conductorButton;
      Button conductorButton = ViewBindings.findChildViewById(rootView, id);
      if (conductorButton == null) {
        break missingId;
      }

      id = R.id.decorativeElement;
      View decorativeElement = ViewBindings.findChildViewById(rootView, id);
      if (decorativeElement == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.subtitleText;
      TextView subtitleText = ViewBindings.findChildViewById(rootView, id);
      if (subtitleText == null) {
        break missingId;
      }

      id = R.id.titleText;
      TextView titleText = ViewBindings.findChildViewById(rootView, id);
      if (titleText == null) {
        break missingId;
      }

      id = R.id.userButton;
      Button userButton = ViewBindings.findChildViewById(rootView, id);
      if (userButton == null) {
        break missingId;
      }

      return new ActivityStartBinding((ConstraintLayout) rootView, appLogo, background,
          conductorButton, decorativeElement, main, subtitleText, titleText, userButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}