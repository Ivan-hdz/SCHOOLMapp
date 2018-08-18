package mx.android.schoolapps.schoolmapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mx.android.schoolapps.schoolmapp.Fragments.CounselorsInfoFragment;
import mx.android.schoolapps.schoolmapp.Fragments.CounselorsNewsFragment;

/**
 * Created by Shiro on 05/11/2017.
 */

public class CounselorsNewsPageAdapter extends FragmentStatePagerAdapter {

    private int countTabs;

    public CounselorsNewsPageAdapter(FragmentManager fm, int countTabs) {
        super(fm);
        this.countTabs= countTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new CounselorsInfoFragment();
            case 1:
                return new CounselorsNewsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
