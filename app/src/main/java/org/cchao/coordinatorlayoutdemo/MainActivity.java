package org.cchao.coordinatorlayoutdemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.cchao.coordinatorlayoutdemo.widget.StickyGridHeadersGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    private StickyGridHeadersGridView gridView;

    private TextView textTitle;

    private CustomScrollView scrollView;

    private ImageView imgExpend;

    private AppBarLayout appBarLayout;

    private TestAdapter testAdapter;

    private List<String> data;

    private List<Integer> headInt;

    private List<String> headStr;

    //1dp高度
    private float oneDP;

    private int scrollY = 0;

    //AppBar的高度
    private int appBarLayoutHeight = 0;

    //GridView当前可见第一个item position
    private int firstVisibleItemNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (StickyGridHeadersGridView) findViewById(R.id.gridview);
        textTitle = (TextView) findViewById(R.id.text_title);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        scrollView = (CustomScrollView) findViewById(R.id.scrollView);
        imgExpend = (ImageView) findViewById(R.id.img_expend);

        //Appbar自动折叠
        imgExpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appBarLayout.setExpanded(false, true);
            }
        });

        oneDP = getResources().getDimension(R.dimen.oneDP);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                scrollY = verticalOffset;
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
                Log.d(TAG, "onScrollStateChanged: " + scrollState + "-->" + firstVisibleItemNow + "-->"
                        + (appBarLayoutHeight - oneDP * 48) + "-->" + (-scrollY));
                if (firstVisibleItemNow == 0 && -scrollY <= (appBarLayoutHeight - oneDP * 48)) {
                    scrollView.setInter(true);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstVisibleItemNow = firstVisibleItem;
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
