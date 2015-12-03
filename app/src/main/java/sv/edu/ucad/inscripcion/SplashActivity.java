package sv.edu.ucad.inscripcion;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    public static final int segundos=6;
    public static final int milisegundos=segundos*1000;
    public static final int delay=2;
    public ProgressBar pbprogreso;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_activity);
        pbprogreso=(ProgressBar)findViewById(R.id.progreso);
        pbprogreso.setMax(maximo_progreso());
        empezaranimacion();
    }


    public void empezaranimacion(){
        new CountDownTimer(milisegundos,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                pbprogreso.setProgress(establecer_progreso(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                Intent form= new Intent(SplashActivity.this,PpalActivity.class);
                startActivity(form);
                finish();
            }
        }.start();
    }

    public int establecer_progreso(long miliseconds){
        return (int)((milisegundos-miliseconds)/1000);
    }

    public int maximo_progreso(){
        return segundos-delay;
    }
}