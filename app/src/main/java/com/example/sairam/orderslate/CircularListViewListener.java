package com.example.sairam.orderslate;

public interface CircularListViewListener {
    void onCircularLayoutFinished(CircularListView circularListView,
                                  int firstVisibleItem,
                                  int visibleItemCount,
                                  int totalItemCount);
}
