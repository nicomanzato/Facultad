package comunicador.laboratorio.hermes2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

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
    private Button botonEliminarAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Alumno alumnoActual = DaoFactory.getAlumnoDao(getBaseContext()).getAlumno(getIntent().getExtras().getInt("idAlumno"));
        Config config = DaoFactory.getConfigDao(getBaseContext()).getConfig();

        nombre = (EditText) findViewById(R.id.SettingsNombreAlumno);
        apellido = (EditText) findViewById(R.id.SettingsApellidoAlumno);

        sexoMasculino = (RadioButton) findViewById(R.id.SettingsRadioButton_masculino);
        sexoFemenino = (RadioButton) findViewById(R.id.SettingsRadioButton_femenino);

        pictogramasPequenio = (RadioButton) findViewById(R.id.SettingsRadioButton_pequenio);
        pictogramasMediano = (RadioButton) findViewById(R.id.SettingsRadioButton_mediano);
        pictogramasGrande = (RadioButton) findViewById(R.id.SettingsRadioButton_grande);

        pistaCheckbox = (CheckBox) findViewById(R.id.SettingsPistaCheckbox);
        establoCheckbox = (CheckBox) findViewById(R.id.SettingsEstabloCheckbox);
        necesidadesCheckbox = (CheckBox) findViewById(R.id.SettingsNecesidadesCheckbox);
        emocionesCheckbox = (CheckBox) findViewById(R.id.SettingsEmocionesCheckbox);

        ip = (EditText) findViewById(R.id.SettingsConfigIP);
        puerto = (EditText) findViewById(R.id.SettingsConfigPuerto);

        boton = (Button) findViewById(R.id.SettingsEnviarButton);
        botonEliminarAlumno = (Button) findViewById(R.id.SettingsEliminarAlumno);


        nombre.setText(alumnoActual.getNombre());
        apellido.setText(alumnoActual.getApellido());

        sexoMasculino.setChecked(alumnoActual.getSexo().equals("m"));
        sexoFemenino.setChecked(alumnoActual.getSexo().equals("f"));

        switch(alumnoActual.getTamanoPictogramas()){

            case 20: pictogramasPequenio.setChecked(true); break;
            case 30: pictogramasMediano.setChecked(true); break;
            case 40: pictogramasGrande.setChecked(true); break;

        }

        pistaCheckbox.setChecked(alumnoActual.getPistaHabilitada() == 1);
        establoCheckbox.setChecked(alumnoActual.getEstabloHabilitada() == 1);
        necesidadesCheckbox.setChecked(alumnoActual.getNecesidadHabilitada() == 1);
        emocionesCheckbox.setChecked(alumnoActual.getEmocionesHabilitada() == 1);

        ip.setText(config.getIp());
        puerto.setText(Integer.toString(config.getPuerto()));

        boton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                if (!this.verificarCampos()) {

                    Toast.makeText(getApplicationContext(), "Falta completar campos", Toast.LENGTH_LONG).show();

                    return;
                }

                String sexo = "";
                int tamanioPictograma = 20;

                int pista = 0;
                int establo = 0;
                int necesidades = 0;
                int emociones = 0;

                if (pistaCheckbox.isChecked()) pista = 1;
                if (establoCheckbox.isChecked()) establo = 1;
                if (necesidadesCheckbox.isChecked()) necesidades = 1;
                if (emocionesCheckbox.isChecked()) emociones = 1;

                if (sexoMasculino.isChecked()) sexo = "m";

                if (sexoFemenino.isChecked()) sexo = "f";

                if (pictogramasPequenio.isChecked()) tamanioPictograma = 20;

                if (pictogramasMediano.isChecked()) tamanioPictograma = 30;

                if (pictogramasGrande.isChecked()) tamanioPictograma = 40;

                Alumno alumnoActualizado = new Alumno(nombre.getText().toString(), apellido.getText().toString(), sexo, tamanioPictograma, emociones, pista, necesidades, establo);
                alumnoActualizado.setId(getIntent().getExtras().getInt("idAlumno"));

                String ipActualizada = ip.getText().toString();
                String puertoActualizado = puerto.getText().toString();

                DaoFactory.getAlumnoDao(getBaseContext()).updateAlumno(alumnoActualizado);
                DaoFactory.getConfigDao(getBaseContext()).update(puertoActualizado, ipActualizada);

                Toast.makeText(getApplicationContext(), "Se han actualizado los ajustes", Toast.LENGTH_LONG).show();


            }

            public boolean verificarCampos() {

                if (nombre.getText().toString().isEmpty()) return false;

                if (apellido.getText().toString().isEmpty()) return false;

                if (!sexoFemenino.isChecked() && !sexoMasculino.isChecked()) return false;

                if (!pictogramasPequenio.isChecked() && !pictogramasMediano.isChecked() && !pictogramasGrande.isChecked())
                    return false;

                if (ip.getText().toString().isEmpty()) return false;

                if (puerto.getText().toString().isEmpty()) return false;

                return true;

            }

        });

        botonEliminarAlumno.setOnClickListener( new Button.OnClickListener(){


            public void onClick(View v){

                DaoFactory.getAlumnoDao(getBaseContext()).deleteAlumno(getIntent().getExtras().getInt("idAlumno"));
                Toast.makeText(getApplicationContext(), "El alumno fue eliminado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SettingsActivity.this, MenuInicialActivity.class);
                startActivity(intent);

            }

        });

    }
}
