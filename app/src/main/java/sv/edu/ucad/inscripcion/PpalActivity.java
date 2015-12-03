package sv.edu.ucad.inscripcion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase; //Librerias para conexion
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PpalActivity extends AppCompatActivity {

    public EditText carnet;
    Button boton;
    TextView vinculo;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ppal);

        carnet = (EditText)findViewById(R.id.editusuario);
        final EditText etusu = (EditText)findViewById(R.id.editusuario);
        TextInputLayout til = (TextInputLayout)findViewById(R.id.textlayout1);
        EditText et1 = (EditText) til.findViewById(R.id.editusuario);
        til.setHint(getString(R.string.txtusuario));

        TextInputLayout til2 = (TextInputLayout)findViewById(R.id.textlayout2);
        EditText et2 = (EditText) til2.findViewById(R.id.editpassword);
        til2.setHint(getString(R.string.txtpassword));

        boton = (Button)findViewById(R.id.btnautenticar);
        vinculo = (TextView)findViewById(R.id.newcount);
        vinculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next=new Intent(PpalActivity.this,NuevoUsuario.class);
                startActivity(next);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bienvenido a Inscripcion Online UCAD", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                        (PpalActivity.this, "admonInscripcion.sqlite", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                Cursor fila = bd.rawQuery("SELECT CodCarnet FROM estudiante WHERE CodCarnet =?"+carnet.getText().toString(),null);
                Toast.makeText(getApplicationContext(),"Esta bien",Toast.LENGTH_LONG).show();

                if(fila.moveToFirst()){
                    Intent next = new Intent(PpalActivity.this,layout_inicio_inscripcion.class);
                    Snackbar.make(v,"Bienvenido a Inscripcion Online", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    startActivity(next);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Carnet Invalido",Toast.LENGTH_LONG).show();
                    bd.close();
                }//Fin if
            }//fin onClick
        });//fin del listener de boton
    }//fin del onCreate
}//fin de la clase