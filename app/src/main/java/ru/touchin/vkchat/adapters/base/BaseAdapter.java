package ru.touchin.vkchat.adapters.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.ui.adapters.AbstractProviderAdapter;

public abstract class BaseAdapter<TItem> extends AbstractProviderAdapter<TItem, RequestPagingProvider<TItem>> {
    private static final int EMPTY_ITEM = 0;
    private static final int ITEM = 1;

    @Override
    public boolean isEnabled(int position) {
        return get(position) != null;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return get(position) == null ? EMPTY_ITEM : ITEM;
    }

    @Override
    public View newView(int position, LayoutInflater inflater, ViewGroup container) {
        if (getItemViewType(position) == EMPTY_ITEM) {
            return new ProgressBar(inflater.getContext());
        } else {
            return inflater.inflate(getResourceId(), container, false);
        }
    }

    @Override
    public void bindView(View view, TItem item, int position) {
        if (getItemViewType(position) == ITEM) {
            fillItem(view, item);
        }
    }

    public abstract int getResourceId();

    public abstract void fillItem(View view, TItem item);
}
