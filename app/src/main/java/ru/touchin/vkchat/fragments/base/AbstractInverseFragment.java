package ru.touchin.vkchat.fragments.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStage;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.ui.adapters.AbstractProviderAdapter;

import ru.touchin.vkchat.R;

public abstract class AbstractInverseFragment extends AbstractPullToRefreshFragment implements AbsListView.OnScrollListener {

    private static final String LIST_POSITION_EXTRA = "LIST_POSITION_EXTRA";
    private static final String LIST_TOP_MARGIN_EXTRA = "LIST_TOP_MARGIN_EXTRA";

    private int listPosition;
    private int listTopMargin;
    private boolean isListViewStateValid;
    private AbsListView absListView;
    private final Runnable updatePositionAction = new Runnable() {
        public void run() {
            restoreListViewState();
        }
    };

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            setListPosition(getListPosition() + 20);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /**
     * Returns adapter of base ListView so developer should create it with view initialization.
     * It is normal because adapter should only responses for UI but not for logic
     */
    protected Adapter getAdapter() {
        if (absListView == null) {
            return null;
        }

        final ListAdapter baseAdapter = absListView.getAdapter();
        if (baseAdapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter) baseAdapter).getWrappedAdapter();
        }
        return baseAdapter;
    }

    /* Returns last list position of ListView */
    protected int getListPosition() {
        return listPosition;
    }

    protected void setListPosition(final int listPosition) {
        this.listPosition = listPosition;
    }

    /* Resets fragment current state. (e.g. clears cached list position of ListView) */
    protected void resetFragmentState() {
        listPosition = 0;
        listTopMargin = 0;
    }

    /* Restores list view state. Usually after fragment loading */
    protected void restoreListViewState() {
        if (absListView instanceof ListView) {
            if (listPosition == 0) {
                listPosition = absListView.getLastVisiblePosition();
            }
            ((ListView) absListView).setSelectionFromTop(listPosition, listTopMargin);
        } else {
            absListView.setSelection(listPosition);
        }
        isListViewStateValid = true;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        absListView = findViewByType(AbsListView.class, getView());
    }

    @Override
    protected void onFragmentDataLoaded(final AggregationTaskStageState currentTaskStageState) {
        if (!isListViewStateValid) {
            getPostHandler().postDelayed(updatePositionAction, 10);  // currently no idea why doesn't work without delay
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                final int headersCount = absListView instanceof ListView ? ((ListView) absListView).getHeaderViewsCount() : 0;
                final int listItemPosition = position - headersCount;
                if (listItemPosition < 0 || listItemPosition >= getAdapter().getCount()) {
                    return;
                }

                AbstractInverseFragment.this.onItemClick(listItemPosition);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        listPosition = absListView.getLastVisiblePosition();
        final View bottomItem = absListView.getChildAt(absListView.getCount());
        listTopMargin = bottomItem == null ? 0 : bottomItem.getBottom();
        isListViewStateValid = false;
    }

    @Override
    public void onSaveInstanceState(final Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(LIST_POSITION_EXTRA, listPosition);
        savedInstanceState.putInt(LIST_TOP_MARGIN_EXTRA, listTopMargin);
    }

    @Override
    public void onViewStateRestored(final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            listPosition = savedInstanceState.getInt(LIST_POSITION_EXTRA, absListView.getCount());
            listTopMargin = savedInstanceState.getInt(LIST_TOP_MARGIN_EXTRA, 0);
        }
    }

    /* Raises when user tap on ListView item */
    protected void onItemClick(final int position) {
        // Do nothing.
    }

    @Override
    public void onDestroyView() {
        absListView.smoothScrollBy(0, 0); // Stop scrolling animation before calling adapter dispose
        // (otherwise bindView can be called after adapter disposing)
        final Adapter adapter = getAdapter();
        if (adapter instanceof AbstractProviderAdapter) {
            ((AbstractProviderAdapter) adapter).dispose();
        }
        super.onDestroyView();
        absListView = null;
    }

}
