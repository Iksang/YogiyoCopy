package kr.co.tjeit.yogiyocopy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.co.tjeit.yogiyocopy.fragment.MyProfileFragment;
import kr.co.tjeit.yogiyocopy.fragment.OrderListFragment3;
import kr.co.tjeit.yogiyocopy.fragment.SeeMoreFragment;
import kr.co.tjeit.yogiyocopy.fragment.StoreListFragment;

public class Main2Activity extends AppCompatActivity {

    private ViewPager myViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myViewPager = (ViewPager)findViewById(R.id.myViewPager);
        myViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    }

    class MainPagerAdapter extends FragmentStatePagerAdapter{


        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new StoreListFragment();
            }
            else if(position == 1){
                return new OrderListFragment3();

            }
            else if(position == 2){
                return new SeeMoreFragment();
            }
            else{
                return new MyProfileFragment();
            }
        }


    }
}
