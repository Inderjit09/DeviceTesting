package macbook.example.devicetesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;

public class TabLayout extends AppCompatActivity
{
    Toolbar toolbar;
    com.google.android.material.tabs.TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3;
    ViewPager viewPager;
    FloatingActionButton floatingActionButton;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);

        floatingActionButton = findViewById(R.id.fab);

        tabLayout = findViewById(R.id.tablayout);
        tabItem1 = findViewById(R.id.tabItem1);
        tabItem2 = findViewById(R.id.tabItem2);
        tabItem3 = findViewById(R.id.tabItem3);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new com.google.android.material.tabs.TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(com.google.android.material.tabs.TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0)
                {
                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLayout.this,R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,R.color.colorPrimary));
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,R.color.colorPrimary));
                    }
                }

                else if(tab.getPosition() == 1)
                {
                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLayout.this,R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,R.color.colorAccent));
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,R.color.colorAccent));
                    }
                }

                else
                {
                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLayout.this,R.color.colorPrimaryDark));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,R.color.colorPrimaryDark));
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,R.color.colorPrimaryDark));
                    }
                }
            }

            @Override
            public void onTabUnselected(com.google.android.material.tabs.TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(com.google.android.material.tabs.TabLayout.Tab tab)
            {

            }
        });
        viewPager.addOnPageChangeListener(new com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }
}
