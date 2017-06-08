package org.cchao.coordinatorlayoutdemo;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import org.cchao.coordinatorlayoutdemo.widget.StickyGridHeadersGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    private StickyGridHeadersGridView gridView;

    private TextView textTitle;

    private CustomScrollView scrollView;

    private AppBarLayout appBarLayout;

    private TestAdapter testAdapter;

    private List<String> data;

    private List<Integer> headInt;

    private List<String> headStr;

    private float oneDP;

    private int appBarLayoutHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (StickyGridHeadersGridView) findViewById(R.id.gridview);
        textTitle = (TextView) findViewById(R.id.text_title);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        scrollView = (CustomScrollView) findViewById(R.id.scrollView);


        oneDP = getResources().getDimension(R.dimen.oneDP);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (0 == appBarLayoutHeight) {
                    appBarLayoutHeight = appBarLayout.getHeight();
                }
                if (verticalOffset < -(int) (oneDP * 48)) {
                    textTitle.setVisibility(View.VISIBLE);
                } else {
                    textTitle.setVisibility(View.GONE);
                }
                if (verticalOffset == 0) {
                    //展开状态
                    scrollView.setInter(true);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //折叠状态
                    scrollView.setInter(false);
                } else {
                    //中间状态
                    scrollView.setInter(true);
                }
            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, "onScroll: " + firstVisibleItem + "->" + visibleItemCount + "->" + totalItemCount);
                if (0 == firstVisibleItem) {
                    scrollView.setInter(true);
                } else {
                    scrollView.setInter(false);
                }
            }
        });

        data = new ArrayList<>();
        headInt = new ArrayList<>();
        headStr = new ArrayList<>();
        for (int i = 0; i < 90; i++) {
            data.add("我是item" + i);
        }
        headInt.add(10);
        headInt.add(10);
        headInt.add(70);
        headStr.add("第一个head");
        headStr.add("第二个head");
        headStr.add("第三个head");
        testAdapter = new TestAdapter(data, headInt, headStr);
        gridView.setAdapter(testAdapter);
    }
}
