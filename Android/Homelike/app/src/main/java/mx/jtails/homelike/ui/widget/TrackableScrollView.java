package mx.jtails.homelike.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;

public class TrackableScrollView extends ScrollView {

    private ArrayList<Callbacks> mCallbacks = new ArrayList<Callbacks>();

	public TrackableScrollView(Context context) {
		this(context, null);
	}
	
	public TrackableScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public TrackableScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (Callbacks c : mCallbacks) {
            c.onScrollChanged(l - oldl, t - oldt);
        }
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void addCallbacks(Callbacks listener) {
        if (!mCallbacks.contains(listener)) {
            mCallbacks.add(listener);
        }
    }

    public static interface Callbacks {
        public void onScrollChanged(int deltaX, int deltaY);
    }

}
