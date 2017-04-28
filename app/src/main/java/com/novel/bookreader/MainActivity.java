package com.novel.bookreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.novel.bookreader.search.SearchUtil;
import com.novel.bookreader.website.Website;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.click_text)
    TextView mClickText;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.click_text)
    public void onClick(View view){
        Website website = new Website();
        website.searchUrl  = "http://m.yunlaige.com/book/11181.html";
        SearchUtil.search(getApplicationContext(), website);
    }
}
