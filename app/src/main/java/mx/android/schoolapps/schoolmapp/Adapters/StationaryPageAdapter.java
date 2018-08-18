package mx.android.schoolapps.schoolmapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mx.android.schoolapps.schoolmapp.Fragments.Stationary_FirstFloorFragment;
import mx.android.schoolapps.schoolmapp.Fragments.Stationary_GroundFloorFragment;

/**
 * Created by shiro on 23/03/18.
 */

public class StationaryPageAdapter extends FragmentStatePagerAdapter{

    private int countTabs;

    public StationaryPageAdapter(FragmentManager fm, int countTabs){
        super(fm);
        this.countTabs= countTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new Stationary_GroundFloorFragment();
            case 1:
                return new Stationary_FirstFloorFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
