package org.mazn.roundanim;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

public class LetterSpacingTextView extends TextView {

	private CharSequence originalText = "";

	protected String endString;

	public LetterSpacingTextView(Context context) {
		super(context);
	}

	public LetterSpacingTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LetterSpacingTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		originalText = text;
		applySpacing();
	}

	@Override
	public CharSequence getText() {
		return originalText;
	}

	private void applySpacing() {
		if (this == null || this.originalText == null){
			return;
		}
		SpannableString finalText = new SpannableString(originalText.toString());
		finalText.setSpan(new RoundedBackgroundSpan(Color.BLACK), 0, finalText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		super.setText(finalText, BufferType.SPANNABLE);
	}



}