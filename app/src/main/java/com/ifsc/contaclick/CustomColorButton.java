package com.ifsc.contaclick;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.Nullable;

public class CustomColorButton extends Button {

    private float initialX;
    private boolean isTouching = false;

    public CustomColorButton(Context context) {
        super(context);
    }

    public CustomColorButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomColorButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                isTouching = true;
                return true;

            case MotionEvent.ACTION_MOVE:
                if (isTouching) {
                    float currentX = event.getX();
                    float deltaX = currentX - initialX;

                    // Normaliza entre -1 e 1
                    float normalized = Math.max(-1f, Math.min(1f, deltaX / getWidth()));

                    // Transforma em 0-255 para cores
                    int red = (int) (255 * (1 - normalized) / 2);
                    int green = (int) (255 * (1 + normalized) / 2);

                    setBackgroundColor(Color.rgb(red, green, 0));
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isTouching = false;
                return true;
        }

        return super.onTouchEvent(event);
    }
}

