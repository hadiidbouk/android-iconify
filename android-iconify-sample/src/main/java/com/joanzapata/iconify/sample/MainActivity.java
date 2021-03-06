package com.joanzapata.iconify.sample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.tabs) TabLayout tabLayout;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.viewPager) ViewPager viewPager;
	@BindView(R.id.SearchEditText) public EditText mSearchEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		// Setup toolbar
		setSupportActionBar(toolbar);

		// Fill view pager
		viewPager.setAdapter(new FontIconsViewPagerAdapter(Font.values(),mSearchEditText));
		viewPager.setOffscreenPageLimit(10);
		tabLayout.setupWithViewPager(viewPager);


	}
}
