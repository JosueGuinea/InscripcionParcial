package sv.edu.ucad.inscripcion;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoUsuario extends AppCompatActivity implements TextWatcher{

    protected Button btnguardar;
    EditText carnet;
    EditText nombres;
    EditText apellidos;
    EditText fechanacimiento;
    EditText pass;
    EditText confpass;

    String scarnet,snombres, sapellidos,sdia,sanio,spass,sconfpass;
    String vacio = new String("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_newuser_activity);

        btnguardar = (Button)findViewById(R.id.guardarnuevousuario);

        carnet = (EditText)findViewById(R.id.newcarnet);
        TextInputLayout til1 = (TextInputLayout)findViewById(R.id.textlayout1);
        EditText et1 = (EditText) til1.findViewById(R.id.newcarnet);
        til1.setHint(getString(R.string.carnet));
        carnet.addTextChangedListener(this);

        nombres = (EditText)findViewById(R.id.newnombres);
        TextInputLayout til2 = (TextInputLayout)findViewById(R.id.textlayout2);
        EditText et2 = (EditText) til2.findViewById(R.id.newnombres);
        til2.setHint(getString(R.string.nombres));
        nombres.addTextChangedListener(this);

        apellidos = (EditText)findViewById(R.id.newapellidos);
        TextInputLayout til3 = (TextInputLayout)findViewById(R.id.textlayout3);
        EditText et3 = (EditText) til3.findViewById(R.id.newapellidos);
        til3.setHint(getString(R.string.apellidos));
        apellidos.addTextChangedListener(this);

        pass = (EditText)findViewById(R.id.password);
        TextInputLayout til4 = (TextInputLayout)findViewById(R.id.textlayout4);
        EditText et4 = (EditText) til4.findViewById(R.id.password);
        til4.setHint(getString(R.string.newpassword));
        pass.addTextChangedListener(this);

        confpass = (EditText)findViewById(R.id.confirmpassword);
        TextInputLayout til5 = (TextInputLayout)findViewById(R.id.textlayout5);
        EditText et5 = (EditText) til5.findViewById(R.id.confirmpassword);
        til5.setHint(getString(R.string.confirmpassword));
        confpass.addTextChangedListener(this);

        fechanacimiento = (EditText)findViewById(R.id.nacimiento);
        TextInputLayout til6 = (TextInputLayout)findViewById(R.id.textlayout6);
        EditText et6 = (EditText) til6.findViewById(R.id.nacimiento);
        til6.setHint(getString(R.string.fecha_nacimiento));
        fechanacimiento.addTextChangedListener(this);

        scarnet = carnet.getText().toString();
                carnet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            if (carnet.length() < 3) {
                                carnet.setError("La longitud del carnet no es correcta");
                            }
                        }
                    }
                });//fin del onFocusListener carnet

        snombres = nombres.getText().toString();
                nombres.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            if (nombres.length() < 3) {
                                nombres.setError("Escriba sus nombres");
                                }
                            }
                    }
                });//fin de nombres

        sapellidos = apellidos.getText().toString();
                apellidos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            if (apellidos.length() < 3) {
                                apellidos.setError("Verifique su apellido");
                                }
                            }
                    }
                });//fin de apellidos

        spass = pass.getText().toString();
        sconfpass = confpass.getText().toString();
                confpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            if (pass.length() < 5){
                                pass.setError("Contraseña muy corta");
                            }else if(!(pass.getText().toString().equals(confpass.getText().toString())||
                                    pass.getText().toString().equals(confpass.getText().toString()))){
                                confpass.setError("Contraseña no son iguales");
                            }
                        }
                    }
        });//fin del pass



        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(carnet.getText().toString().equals(vacio)||
                        nombres.getText().toString().equals(vacio)||
                        apellidos.getText().toString().equals(vacio))){
                    if(pass.getText().toString().equals(confpass.getText().toString())
                            && pass.getText().toString().length()>=5){
                    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(NuevoUsuario.this, "admonInscripcion.sqlite", null, 1);
                    SQLiteDatabase bd = admin.getWritableDatabase();

                    ContentValues registro = new ContentValues();  //es una clase para guardar datos

                    registro.put("NombreEstudiante", nombres.getText().toString());
                    registro.put("ApellidosEstudiante", apellidos.getText().toString());
                    registro.put("FechaNacimiento", fechanacimiento.getText().toString());
                    registro.put("CodCarnet", carnet.getText().toString());
                    registro.put("Genero", "Masculino");

                    //Realizando insercion de datos por metodo insert
                    bd.insert("estudiante", null, registro);
                    //Cerrando base de datos
                    bd.close();
                    carnet.setText("");
                    nombres.setText("");
                    apellidos.setText("");
                    pass.setText("");
                    confpass.setText("");
                    fechanacimiento.setText("");

                    Toast.makeText(getApplicationContext(), "Datos cargados correctamente", Toast.LENGTH_SHORT).show();
                    Intent next = new Intent(NuevoUsuario.this, PpalActivity.class);
                    startActivity(next);
                    finish();
                }else {
                        Toast.makeText(getApplicationContext(), "Contraseñas no coinciden o muy corta", Toast.LENGTH_SHORT).show();
                      }
                }else{
                    Toast.makeText(getApplicationContext(), "Existen campos vacios", Toast.LENGTH_SHORT).show();
                }
            }//fin del onClick
        });//fin del onClickListener
    }//fin del onCreate

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
    @Override
    public void afterTextChanged(Editable s) {}//fin afterTextChanged

}//fin de las clase/