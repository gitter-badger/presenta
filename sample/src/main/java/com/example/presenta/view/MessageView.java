/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.presenta.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.presenta.R;
import com.example.presenta.screen.MessageScreen;
import io.techery.presenta.mortar.PresenterService;

public class MessageView extends LinearLayout {
  MessageScreen.Presenter presenter;

  private TextView userView;
  private TextView messageView;

  public MessageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setOrientation(VERTICAL);
    presenter = PresenterService.getPresenter(context);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    messageView = (TextView) findViewById(R.id.message);
    userView = (TextView) findViewById(R.id.user);
    userView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        presenter.onUserSelected();
      }
    });
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    presenter.dropView(this);
  }

  public void setUser(String user) {
    userView.setText(user);
  }

  public void setMessage(String message) {
    messageView.setText(message);
  }
}
