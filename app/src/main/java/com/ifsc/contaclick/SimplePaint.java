package com.ifsc.contaclick;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SimplePaint extends View {
    float x0,y0;
    ListaCamadas listaCamadas;
    public SimplePaint(Context context) {
        super(context);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public  void init(){
        listaCamadas = new ListaCamadas();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (Camada c: listaCamadas.camadas) {
            canvas.drawPath(c.path, c.paint);

        }
        for (int i = 0; i< listaCamadas.camadas.size(); i++){
            canvas.drawPath(listaCamadas.camadaAtual.path, listaCamadas.camadaAtual.paint);

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x0=event.getX();
                y0=event.getY();
                listaCamadas.camadaAtual.path.moveTo(x0,y0);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                listaCamadas.camadaAtual.path.lineTo(event.getX(),event.getY());
                this.invalidate();
                return true;
        }
        return true;
    }

    public void clearDraw(){
        listaCamadas.limpaCamadas();
        invalidate();
    }
    public void changeColor(int color){
        listaCamadas.addCamada();
        listaCamadas.camadaAtual.paint.setColor(color);
        listaCamadas.camadaAtual.paint.setColor(color);
    }
}
