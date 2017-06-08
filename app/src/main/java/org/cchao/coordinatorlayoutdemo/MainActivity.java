package org.cchao.coordinatorlayoutdemo;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView textTitle;

    private AppBarLayout appBarLayout;

    private TestAdapter testAdapter;

    private List<String> data;

    private float oneDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        textTitle = (TextView) findViewById(R.id.text_title);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        oneDP = getResources().getDimension(R.dimen.oneDP);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < -(int) (oneDP * 48)) {
                    textTitle.setVisibility(View.VISIBLE);
                } else {
                    textTitle.setVisibility(View.GONE);
                }
            }
        });

        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("我是item" + i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        testAdapter = new TestAdapter(data);
        recyclerView.setAdapter(testAdapter);
    }
}
