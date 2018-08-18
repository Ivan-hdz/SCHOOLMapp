package mx.android.schoolapps.schoolmapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mx.android.schoolapps.schoolmapp.Fragments.About_EscomFragment;
import mx.android.schoolapps.schoolmapp.Fragments.About_InfoFragment;

public class AboutEscomPageAdapter extends FragmentStatePagerAdapter {

    private int countTabs;

    public AboutEscomPageAdapter(FragmentManager fm, int countTabs) {
        super(fm);
        this.countTabs= countTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new About_EscomFragment();
            case 1:
                return new About_InfoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
