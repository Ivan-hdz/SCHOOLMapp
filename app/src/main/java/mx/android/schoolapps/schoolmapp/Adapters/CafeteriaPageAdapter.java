package mx.android.schoolapps.schoolmapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mx.android.schoolapps.schoolmapp.Fragments.Cafeteria_EntryFragment;
import mx.android.schoolapps.schoolmapp.Fragments.Cafeteria_HallFragment;

/**
 * Created by shiro on 23/03/18.
 */

public class CafeteriaPageAdapter extends FragmentStatePagerAdapter {

    private int countTabs;

    public CafeteriaPageAdapter(FragmentManager fm, int countTabs){
        super(fm);
        this.countTabs= countTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new Cafeteria_HallFragment();
            case 1:
                return new Cafeteria_EntryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
