package com.example.sairam.orderslate;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class NotificationActivity extends Activity {

    private CircularListView mCircularListView;
    private boolean mIsAdapterDirty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mCircularListView = (CircularListView) findViewById(R.id.circularListView);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 10; i++) {
            listAdapter.add(String.format("Item %02d", i));
        }

        Display display = getWindowManager().getDefaultDisplay();
        mCircularListView.setRadius(Math.min(300, display.getWidth() / 2));
        mCircularListView.setAdapter(listAdapter);
        mCircularListView.scrollFirstItemToCenter();

        mCircularListView.setCircularListViewListener(new CircularListViewListener() {
            @Override
            public void onCircularLayoutFinished(CircularListView circularListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                refreshCircular();
            }
        });
    }

    void refreshCircular() {
        if (mIsAdapterDirty) {
            mCircularListView.scrollFirstItemToCenter();
            mIsAdapterDirty = false;
        }

        TextView centerView = (TextView) mCircularListView.getCentralChild();

        if (centerView != null) {
            centerView.setTextColor(getResources().getColor(R.color.center_text));
        }
        for (int i = 0; i < mCircularListView.getChildCount(); i++) {
            TextView view = (TextView) mCircularListView.getChildAt(i);
            if (view != null && view != centerView) {
                view.setTextColor(getResources().getColor(R.color.default_text));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.align_right:
                mCircularListView.setCircularListViewContentAlignment(CircularListViewContentAlignment.Right);
                return true;
            case R.id.circler:
                mCircularListView.setCircularListViewContentAlignment(CircularListViewContentAlignment.None);
                return true;
            case R.id.align_left:
                mCircularListView.setCircularListViewContentAlignment(CircularListViewContentAlignment.Left);
                return true;
            default:
                return false;
        }
    }
}
