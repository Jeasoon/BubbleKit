package com.jeasoon.bubblekit.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jeasoon.bubblekit.BubbleKit;
import com.jeasoon.bubblekit.R;
import com.jeasoon.bubblekit.constant.ChatConstant;
import com.jeasoon.bubblekit.notification.NotificationEmitter;
import com.jeasoon.bubblekit.util.ScreenUtil;

public abstract class BaseChatActivity extends AppCompatActivity implements ChatConstant {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BubbleKit.getInstance().init(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BubbleKit.getInstance().destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bubble_chat_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            menu.findItem(R.id.menu_notification_bubble_enable).setChecked(NotificationEmitter.getInstance().isBubbleEnabled());
            menu.findItem(R.id.menu_notification_bubble_service).setChecked(NotificationEmitter.getInstance().isServiceEnabled());
            menu.findItem(R.id.menu_notification_bubble_reply).setChecked(NotificationEmitter.getInstance().isReplyEnabled());
            menu.findItem(R.id.menu_notification_bubble_expand).setChecked(NotificationEmitter.getInstance().isAutoExpandEnabled());
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_notification_orientation_portrait:
                ScreenUtil.setOrientationPortrait(this);
                return true;
            case R.id.menu_notification_orientation_landscape:
                ScreenUtil.setOrientationLandscape(this);
                return true;
            case R.id.menu_notification_screen_normal:
                ScreenUtil.setScreenNormal(this);
                return true;
            case R.id.menu_notification_screen_full:
                ScreenUtil.setScreenFull(this);
                return true;
            case R.id.menu_notification_bubble_enable:
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    NotificationEmitter.getInstance().enableBubble();
                } else {
                    NotificationEmitter.getInstance().disableBubble();
                }
                return true;
            case R.id.menu_notification_bubble_service:
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    NotificationEmitter.getInstance().enableService();
                } else {
                    NotificationEmitter.getInstance().disableService();
                }
                return true;
            case R.id.menu_notification_bubble_reply:
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    NotificationEmitter.getInstance().enableReply();
                } else {
                    NotificationEmitter.getInstance().disableReply();
                }
                return true;
            case R.id.menu_notification_bubble_expand:
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    NotificationEmitter.getInstance().enableAutoExpand();
                } else {
                    NotificationEmitter.getInstance().disableAutoExpand();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
