package mx.android.schoolapps.schoolmapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.android.schoolapps.schoolmapp.Adapters.CounselorsNewsPageAdapter;
import mx.android.shcoolapps.schoolmap.R;

public class StudentsCounselorFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public StudentsCounselorFragment() {
    }

    public static StudentsCounselorFragment newInstance(String param1, String param2) {
        StudentsCounselorFragment fragment = new StudentsCounselorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_students_counselor, container, false);

        TabLayout tabLayout= view.findViewById(R.id.tabLayoutCounselors);
        tabLayout.addTab(tabLayout.newTab().setText("INFORMACION"));
        tabLayout.addTab(tabLayout.newTab().setText("NOTICIAS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager= view.findViewById(R.id.viewPagerCounselors);
        CounselorsNewsPageAdapter pagerAdapter= new CounselorsNewsPageAdapter(getFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position= tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
 }
