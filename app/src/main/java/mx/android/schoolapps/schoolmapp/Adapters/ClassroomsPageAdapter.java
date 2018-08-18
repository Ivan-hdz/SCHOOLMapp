package mx.android.schoolapps.schoolmapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mx.android.schoolapps.schoolmapp.Fragments.AllClassroomsFragment;
import mx.android.schoolapps.schoolmapp.Fragments.EmptyClassroomsFragment;

/**
 * Created by Shiro on 21/11/2017.
 */

public class ClassroomsPageAdapter extends FragmentStatePagerAdapter{

    private int countTabs;

    public ClassroomsPageAdapter(FragmentManager fm,int countTabs) {
        super(fm);
        this.countTabs= countTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AllClassroomsFragment();
            case 1:
                return new EmptyClassroomsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
