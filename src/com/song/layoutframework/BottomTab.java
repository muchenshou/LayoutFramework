package com.song.layoutframework;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author song
 *
 */
public class BottomTab extends LinearLayout {
	int mStyle;
	ArrayList<String> mTitles;
	ArrayList<Drawable> mImages;
	public BottomTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		Resources resource = context.getResources();
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.bottom_tab);
		mStyle = a.getInteger(R.styleable.bottom_tab_style, 2);
		TypedValue outValue = new TypedValue();
		a.getValue(R.styleable.bottom_tab_title_list, outValue);

		loadTitleAndImage(outValue.resourceId);

		a.recycle();
		// init windows
		init();
	}

	/**
	 * 
	 */
	private void init() {
		ImageView imageview = new ImageView(getContext());
		final int count = mImages.size();
		for (int i=0; i<count; i++) {
			imageview = new ImageView(getContext());
			imageview.setImageDrawable(mImages.get(i));
			this.addView(imageview);
		}
	}
	
	/**
	 * @param resourceId
	 */
	private void loadTitleAndImage(int resourceId) {
		Resources resource = this.getContext().getResources();
		TypedArray a = resource.obtainTypedArray(resourceId);
		final int count = a.length();
		mImages = new ArrayList<Drawable>();
		for (int i = 0; i < count; i++) {
			TypedValue value = a.peekValue(i);
			Log.i("hello", ""+value.type);
			//mImages.add( resource.getDrawable(value.resourceId));
			mImages.add( resource.getDrawable(R.drawable.ic_launcher));
		}
		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		for (int index = 0; index < getChildCount(); index++) {
			final View child = getChildAt(index);
			// measure
			child.measure(MeasureSpec.AT_MOST, MeasureSpec.AT_MOST);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

}

