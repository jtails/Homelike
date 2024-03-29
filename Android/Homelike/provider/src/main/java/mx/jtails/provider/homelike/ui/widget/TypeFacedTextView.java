package mx.jtails.provider.homelike.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import mx.jtails.provider.homelike.R;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public class TypeFacedTextView extends TextView {

    public TypeFacedTextView(Context context) {
        this(context, null);
    }

    public TypeFacedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TypeFacedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(this.isInEditMode()) {
            return;
        }

        this.customizeTypography(context, attrs);
    }

    private void customizeTypography(Context context, AttributeSet attrs){
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.TypeFacedTextView);
        String fontName = styledAttrs.getString(R.styleable.TypeFacedTextView_typeface);
        styledAttrs.recycle();

        if(fontName != null){
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            setTypeface(typeface);
        }
    }

}
