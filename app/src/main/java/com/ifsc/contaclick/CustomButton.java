package com.ifsc.contaclick;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomButton extends androidx.appcompat.widget.AppCompatButton {
    float toqueInicialX;
    float buttonInicialX;
    public CustomButton(@NonNull Context context) {
        super(context);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Coordenadas" ,
                "  x:"+ Float.toString(event.getRawX())+
                "  y:"+Float.toString(event.getRawY()));

        int acao=event.getAction();
        switch (event.getAction()){

            case (MotionEvent.ACTION_DOWN):
                toqueInicialX = event.getRawX();
                buttonInicialX=this.getX();

                return true;
            case (MotionEvent.ACTION_MOVE):
                float delta=event.getRawX()- toqueInicialX;
                //Define a nova posição somando a diferença absoluta entre X1-X0
                this.setX(delta+buttonInicialX);

                // Normaliza o deslocamento para calcular a cor (limitado a largura do botão)
                float normalized = Math.max(-1f, Math.min(1f, delta / getWidth()));
                int red = (int) (255 * (1 - normalized) / 2);
                int green = (int) (255 * (1 + normalized) / 2);
                this.setBackgroundColor(Color.rgb(red,green,0));
                return true;

            case (MotionEvent.ACTION_UP):

                this.setBackgroundColor(Color.GRAY);

                ObjectAnimator animator =  ObjectAnimator.ofFloat(this,"x",this.getX(),buttonInicialX);
                animator.setDuration(500);
                animator.start();
                return true;

        }



        return true;
    }
}
