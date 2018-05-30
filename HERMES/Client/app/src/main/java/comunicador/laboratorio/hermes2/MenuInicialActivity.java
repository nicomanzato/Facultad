package comunicador.laboratorio.hermes2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MenuInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_alumnos);

        ListView listadoAlumno = (ListView) this.findViewById(R.id.listView);
        Button nuevoAlumnoButton = (Button) this.findViewById(R.id.nuevo_alumno_button);
        Button inicializarBDButton = (Button) this.findViewById(R.id.iniciarBDButton);

        ListAdapter adapter = new ArrayAdapter<Alumno>(this, android.R.layout.simple_list_item_1, DaoFactory.getAlumnoDao(getBaseContext()).getAlumnos());

        listadoAlumno.setAdapter(adapter);

        listadoAlumno.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                int idAlumno = ((Alumno) adapterView.getAdapter().getItem(position)).getId();

                Intent intent = new Intent(MenuInicialActivity.this, AlumnoActivity.class);
                intent.putExtra("alumno", adapterView.getAdapter().getItem(position).toString());
                intent.putExtra("idAlumno", idAlumno);
                startActivity(intent);

            }
        });

        nuevoAlumnoButton.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View v) {

                Intent intent = new Intent(MenuInicialActivity.this, NuevoAlumnoActivity.class);
                startActivity(intent);
            }


        });

        inicializarBDButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                DaoFactory.getConfigDao(getBaseContext()).insert("8001","192.168.0.10");

                DaoFactory.getAlumnoDao(getBaseContext()).insertar("Nicolas", "Manzato", "m", "30", "1","1","1","1");
                DaoFactory.getAlumnoDao(getBaseContext()).insertar("Josefina", "Estevez", "f", "40", "1","0","0","1");

                DaoFactory.getPictogramaDao(getBaseContext()).insert("aro", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("asustado_chica", 2);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("bano", 3);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("broches", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("burbujas", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("caballo_blanco", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("caballo_marron", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("caballo_negro", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("cansado_chica", 2);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("casco", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("cepillo", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("chapas", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("contento_chica", 2);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("cubos", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("dolorido_chica", 2);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("enojado_chica", 2);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("escarba_vasos", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("letras", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("limpieza", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("maracas", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("montura", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("pasto", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("pato", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("pelota", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("raqueta_blanca", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("raqueta_dura", 1);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("riendas", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("sed_chica", 3);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("sorprendido_chica", 2);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("tarima", 0);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("triste_chica", 2);
                DaoFactory.getPictogramaDao(getBaseContext()).insert("zanahoria", 1);

                Toast.makeText(getApplicationContext(), "Se han agregado informacion a la base de datos", Toast.LENGTH_LONG).show();

            }

        });

    }
}
