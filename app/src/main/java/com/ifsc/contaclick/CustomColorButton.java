package com.ifsc.contaclick;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.Nullable;

public class CustomColorButton extends Button {

    private float initialX;
    private boolean isTouching = false;
    private GradientDrawable backgroundDrawable;
    private float initialTouchX;
    private float initialButtonX;

    public CustomColorButton(Context context) {
        super(context);
        init();
    }

    public CustomColorButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomColorButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {
        backgroundDrawable = new GradientDrawable();
        backgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        backgroundDrawable.setCornerRadius(1000f); // Bem alto = efeito redondo
        backgroundDrawable.setColor(ColorStateList.valueOf(Color.GRAY));
        setBackground(backgroundDrawable);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialTouchX = event.getRawX();      // onde o dedo tocou
                initialButtonX = getX();              // posição atual do botão
                return true;

            case MotionEvent.ACTION_MOVE:
                float currentTouchX = event.getRawX();
                float deltaX = currentTouchX - initialTouchX;

                // Atualiza a posição do botão
                float newX = initialButtonX + deltaX;
                setX(newX);

                // Normaliza o deslocamento para calcular a cor (limitado a largura do botão)
                float normalized = Math.max(-1f, Math.min(1f, deltaX / getWidth()));
                int red = (int) (255 * (1 - normalized) / 2);
                int green = (int) (255 * (1 + normalized) / 2);

                backgroundDrawable.setColor(ColorStateList.valueOf(Color.rgb(red, green, 0)));
                return true;

            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:
                // Anima de volta à posição original
                ObjectAnimator animator = ObjectAnimator.ofFloat(this, "x", getX(), initialButtonX);
                animator.setDuration(300); // duração em ms
                animator.start();

                // Retorna cor original (cinza)
                backgroundDrawable.setColor(ColorStateList.valueOf(Color.GRAY));
                return true;
        }


        return super.onTouchEvent(event);
    }
}

