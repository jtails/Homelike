package mx.jtails.homelike.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class FullSizeListView extends ListView {

	public FullSizeListView(Context context) {
		super(context);
	}
	
	public FullSizeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public FullSizeListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
