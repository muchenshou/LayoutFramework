package com.song.layoutframework;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
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
		Button imageview = null;
		final int count = mImages.size();
		for (int i = 0; i < count; i++) {
			imageview = new Button(getContext());
			imageview.setFocusable(true);
			imageview.setFocusableInTouchMode(true);
			imageview.setBackgroundDrawable(mImages.get(i));
			Log.i("hello", "" + (1f / (float) count));
			imageview.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
					1f / (float) count));
			this.addView(imageview);
		}
	}

	public static final int[] STATE_PRESS_FOCUSE = {
			android.R.attr.state_pressed, android.R.attr.state_focused };
	public static final int[] STATE_FOCUSE = { android.R.attr.state_focused };
	public static final int[] STATE_PRESS = { android.R.attr.state_pressed };
	public static final int[] STATE_NONPRESS = { -android.R.attr.state_pressed };

	Drawable getDrawable() {
		Drawable press = getContext().getResources().getDrawable(
				R.drawable.more_book_button);
		Drawable unpress = getContext().getResources().getDrawable(
				R.drawable.more_book_button_f);
		Drawable focus = getContext().getResources().getDrawable(
				R.drawable.ic_launcher);
		StateListDrawable drawable = new StateListDrawable();

		Drawable[] draws = new Drawable[2];
		draws[0] = unpress;
		draws[1] = focus;
		LayerDrawable layer = new LayerDrawable(draws);
		drawable.addState(STATE_FOCUSE, layer);

		drawable.addState(STATE_PRESS, press);
		drawable.addState(STATE_NONPRESS, unpress);
		return drawable;
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
			// mImages.add( resource.getDrawable(value.resourceId));
			mImages.add(getDrawable());
		}
		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

}
