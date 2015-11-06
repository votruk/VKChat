package ru.touchin.vkchat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.zuzuk.providers.base.AbstractItemsProvider;
import org.zuzuk.ui.adapters.AbstractPagerAdapterWithProgressBar;


public abstract class AbstractAdapterWithDetection<TItem, TProvider extends AbstractItemsProvider<TItem>>
        extends AbstractPagerAdapterWithProgressBar<TItem, TProvider> {

    private static final int EMPTY_ITEM = 0;
    private int currentListCount;

    @Override
    public int getItemViewType(final int position) {
        return get(position) == null ? EMPTY_ITEM : getItemRealViewType(position);
    }

    @Override
    public View newView(final int position, final LayoutInflater inflater, final ViewGroup container) {
        if (getItemViewType(position) == EMPTY_ITEM) {
            currentListCount = getCount();
            return new ProgressBar(container.getContext());
        } else {
            if (position == (getCount() - currentListCount)) {
                System.out.println("STOP ME!!!");
            }
            return newRealView(position, inflater, container);
        }
    }

}
