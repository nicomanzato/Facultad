package comunicador.laboratorio.hermes2;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import android.widget.Toast;

public class NuevoAlumnoActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText apellido;

    private RadioButton sexoMasculino;
    private RadioButton sexoFemenino;

    private RadioButton pictogramasPequenio;
    private RadioButton pictogramasMediano;
    private RadioButton pictogramasGrande;

    private CheckBox pistaCheckbox;
    private CheckBox establoCheckbox;
    private CheckBox necesidadesCheckbox;
    private CheckBox emocionesCheckbox;

    private EditText ip;
    private EditText puerto;

    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);

        nombre = (EditText) findViewById(R.id.nombreAlumno);
        apellido = (EditText) findViewById(R.id.apellidoAlumno);

        sexoMasculino = (RadioButton) findViewById(R.id.radioButton_masculino);
        sexoFemenino = (RadioButton) findViewById(R.id.radioButton_femenino);

        pictogramasPequenio = (RadioButton) findViewById(R.id.radioButton_pequenio);
        pictogramasMediano = (RadioButton) findViewById(R.id.radioButton_mediano);
        pictogramasGrande = (RadioButton) findViewById(R.id.radioButton_grande);

        pistaCheckbox = (CheckBox) findViewById(R.id.pistaCheckbox);
        establoCheckbox = (CheckBox) findViewById(R.id.establoCheckbox);
        necesidadesCheckbox = (CheckBox) findViewById(R.id.necesidadesCheckbox);
        emocionesCheckbox = (CheckBox) findViewById(R.id.emocionesCheckbox);

        ip = (EditText) findViewById(R.id.configIP);
        puerto = (EditText) findViewById(R.id.configPuerto);

        boton = (Button) findViewById(R.id.enviarButton);

        boton.setOnClickListener( new Button.OnClickListener() {

            public boolean verificarCampos(){

                if (nombre.getText().toString().isEmpty()) return false;

                if (apellido.getText().toString().isEmpty()) return false;

                if (!sexoFemenino.isChecked() && !sexoMasculino.isChecked()) return false;

                if (!pictogramasPequenio.isChecked() && !pictogramasMediano.isChecked() && !pictogramasGrande.isChecked()) return false;

                if (ip.getText().toString().isEmpty()) return false;

                if (puerto.getText().toString().isEmpty()) return false;

                return true;

            }

            public void onClick(View v) {

                if (!this.verificarCampos()) {

                    Toast.makeText(getApplicationContext(), "Falta completar campos", Toast.LENGTH_LONG).show();

                    return;
                }

                String sexo = "";
                int tamanioPictograma = 20;

                boolean pista = pistaCheckbox.isChecked();
                boolean establo = establoCheckbox.isChecked();
                boolean necesidades = necesidadesCheckbox.isChecked();
                boolean emociones = emocionesCheckbox.isChecked();

                if (sexoMasculino.isChecked()) sexo = "m";

                if (sexoFemenino.isChecked()) sexo = "f";

                if (pictogramasPequenio.isChecked()) tamanioPictograma = 20;

                if (pictogramasMediano.isChecked()) tamanioPictograma = 30;

                if (pictogramasGrande.isChecked()) tamanioPictograma = 40;

                long insertedId = DaoFactory.getAlumnoDao(getBaseContext()).insertar(nombre.getText().toString(),apellido.getText().toString(),sexo, Integer.toString(tamanioPictograma),Boolean.toString(emociones),Boolean.toString(pista),Boolean.toString(necesidades),Boolean.toString(establo));

                DaoFactory.getConfigDao(getBaseContext()).update(puerto.getText().toString(), ip.getText().toString());

                Toast.makeText(getApplicationContext(), "El alumno fue registrado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(NuevoAlumnoActivity.this, AlumnoActivity.class);
                intent.putExtra("alumno", nombre.getText().toString());
                intent.putExtra("idAlumno", insertedId);
                startActivity(intent);

            }
        });

    }
}
