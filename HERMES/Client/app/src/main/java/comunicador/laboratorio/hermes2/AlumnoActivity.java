package comunicador.laboratorio.hermes2;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.*;
import android.util.*;
import android.media.*;

import android.widget.TextView;

import com.google.gson.*;

public class AlumnoActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_settings:
                intent = new Intent(AlumnoActivity.this, SettingsActivity.class);
                intent.putExtra("alumno", getIntent().getExtras().getString("alumno"));
                intent.putExtra("idAlumno", getIntent().getExtras().getInt("idAlumno"));
                startActivity(intent);
                return true;
            case R.id.action_modo_edicion:
                intent = new Intent(AlumnoActivity.this, TerapeutaActivity.class);
                intent.putExtra("alumno", getIntent().getExtras().getString("alumno"));
                intent.putExtra("idAlumno", getIntent().getExtras().getInt("idAlumno"));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Integer idAlumno = getIntent().getExtras().getInt("idAlumno");
            return PlaceholderFragment.newInstance(position, getScreenWidth(), String.valueOf(idAlumno));
        }

        public int getScreenWidth() {

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;

            return width;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pista";
                case 1:
                    return "Establo";
                case 2:
                    return "Emociones";
                case 3:
                    return "Necesidades";
                case 4:
                    return getIntent().getExtras().getString("alumno");
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private GrillaAdapter adapter;
        private GridView gridView;
        private int anchoColumna;
        private int screenWidth;

        private static final String SCREEN_WIDTH = "screen_width";
        private static final String ID_CATEGORIA = "categoria";
        private static final String ID_ALUMNO = "alumno";


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, int screenWidth, String idAlumno) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(SCREEN_WIDTH, screenWidth);
            args.putInt(ID_CATEGORIA, sectionNumber);
            args.putInt(ID_ALUMNO, Integer.valueOf(idAlumno));
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        private void inicializarGrilla(final GridView gridView, final int cantidadColumnas, int paddingGrilla) { // aca modifique el + 10 porque no permitia que se viera por completo la 3ra columna

            anchoColumna = ((screenWidth - ((cantidadColumnas + 20) * paddingGrilla)) / cantidadColumnas);
            gridView.setNumColumns(cantidadColumnas);
            gridView.setColumnWidth(anchoColumna);
            gridView.setStretchMode(GridView.NO_STRETCH);
            gridView.setPadding(paddingGrilla, paddingGrilla, paddingGrilla, paddingGrilla);
            gridView.setHorizontalSpacing(paddingGrilla);
            gridView.setVerticalSpacing(paddingGrilla);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    Pictograma pictograma = (Pictograma) gridView.getAdapter().getItem(position);

                    MediaPlayer mp = MediaPlayer.create(getActivity(), pictograma.getSonido());
                    mp.start();

                    String categoriaActual = "";

                    switch (getArguments().getInt(ID_CATEGORIA)) {

                        case 0:
                            categoriaActual = "Pista";
                            break;
                        case 1:
                            categoriaActual = "Establo";
                            break;
                        case 2:
                            categoriaActual = "emociones";
                            break;
                        case 3:
                            categoriaActual = "Necesidades";
                            break;
                        case 4:
                            categoriaActual = "Paciente";
                            break;


                    }


                    Notificacion notificacion = new Notificacion(pictograma.getDescripcion(), categoriaActual, DaoFactory.getAlumnoDao(getContext()).getAlumno(getArguments().getInt(ID_ALUMNO)).getNombre());

                    Object[] parametros = {notificacion,DaoFactory.getConfigDao(getContext()).getConfig().getIp(),DaoFactory.getConfigDao(getContext()).getConfig().getPuerto(),getContext()};

                    new Cliente().execute(parametros);




                }
            });

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_alumno, container, false);

            screenWidth = getArguments().getInt(SCREEN_WIDTH);

            gridView = (GridView) rootView.findViewById(R.id.gridView);
            inicializarGrilla(gridView, Constantes.CANTIDAD_COLUMNAS, Constantes.PADDING_GRILLA);

            int tamanoPictogramas = DaoFactory.getAlumnoDao(getContext()).getAlumno(getArguments().getInt(ID_ALUMNO)).getTamanoPictogramas();

            switch (tamanoPictogramas){

                case 20:  anchoColumna = (int)Math.round(anchoColumna * 0.6); break;
                case 30:  anchoColumna = (int)Math.round(anchoColumna * 0.8); break;
                case 40:  anchoColumna = (int)Math.round(anchoColumna * 1); break;

            }

            adapter = new GrillaAdapter(getActivity(), this.cargarPictogramas(getArguments().getInt(ID_CATEGORIA), getArguments().getInt(ID_ALUMNO)), anchoColumna);
            gridView.setAdapter(adapter);


            ImageButton botonSi = (ImageButton) rootView.findViewById(R.id.botonSi);
            botonSi.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.si));

            botonSi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.si);
                    mp.start();

                }
            });

            ImageButton botonNo = (ImageButton) rootView.findViewById(R.id.botonNo);
            botonNo.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.no));

            botonNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.no);
                    mp.start();

                }
            });

            return rootView;
        }

        private List<Pictograma> cargarPictogramas(int categoria, int idAlumno) {

            List<Pictograma> listaPictogramas = new ArrayList<Pictograma>();
            List<Pictograma> listaPictogramasFiltrada = new ArrayList<Pictograma>();

            listaPictogramas.add(new Pictograma("aro"));
            listaPictogramas.add(new Pictograma("asustado_chica"));
            listaPictogramas.add(new Pictograma("bano"));
            listaPictogramas.add(new Pictograma("broches"));
            listaPictogramas.add(new Pictograma("burbujas"));
            listaPictogramas.add(new Pictograma("caballo_blanco"));
            listaPictogramas.add(new Pictograma("caballo_marron"));
            listaPictogramas.add(new Pictograma("caballo_negro"));
            listaPictogramas.add(new Pictograma("cansado_chica"));
            listaPictogramas.add(new Pictograma("casco"));
            listaPictogramas.add(new Pictograma("cepillo"));
            listaPictogramas.add(new Pictograma("chapas"));
            listaPictogramas.add(new Pictograma("contento_chica"));
            listaPictogramas.add(new Pictograma("cubos"));
            listaPictogramas.add(new Pictograma("dolorido_chica"));
            listaPictogramas.add(new Pictograma("enojado_chica"));
            listaPictogramas.add(new Pictograma("escarba_vasos"));
            listaPictogramas.add(new Pictograma("letras"));
            listaPictogramas.add(new Pictograma("limpieza"));
            listaPictogramas.add(new Pictograma("maracas"));
            listaPictogramas.add(new Pictograma("matra"));
            listaPictogramas.add(new Pictograma("montura"));
            listaPictogramas.add(new Pictograma("pasto"));
            listaPictogramas.add(new Pictograma("pato"));
            listaPictogramas.add(new Pictograma("pelota"));
            listaPictogramas.add(new Pictograma("raqueta_blanca"));
            listaPictogramas.add(new Pictograma("raqueta_dura"));
            listaPictogramas.add(new Pictograma("riendas"));
            listaPictogramas.add(new Pictograma("sed_chica"));
            listaPictogramas.add(new Pictograma("sorprendido_chica"));
            listaPictogramas.add(new Pictograma("tarima"));
            listaPictogramas.add(new Pictograma("triste_chica"));
            listaPictogramas.add(new Pictograma("zanahoria"));

            if (categoria==4){
                Cursor cursorAP = DaoFactory.getAlumnoPictogramaDao(getContext()).searchPictogramaAlumno(idAlumno);
                while (cursorAP.moveToNext()) {
                    String idPictograma = cursorAP.getString(cursorAP.getColumnIndex("idPictograma"));
                    Cursor cursorPic = DaoFactory.getPictogramaDao(getContext()).getPictogramaById(Integer.valueOf(idPictograma));
                    if(cursorPic.moveToFirst()){
                        String desc = cursorPic.getString(cursorPic.getColumnIndex("description"));
                        System.out.println(desc);
                        Pictograma pic = new Pictograma(desc);
                        if(listaPictogramasFiltrada.contains(pic)){
                            System.out.println("contiene");
                        } else {
                            listaPictogramasFiltrada.add(pic);
                        }
                    }
                }
            } else {

                boolean categoriaHabilitada = false;

                switch(categoria) {

                    case 0: categoriaHabilitada = (DaoFactory.getAlumnoDao(getContext()).getAlumno(getArguments().getInt(ID_ALUMNO)).getPistaHabilitada() == 1); break;
                    case 1: categoriaHabilitada = (DaoFactory.getAlumnoDao(getContext()).getAlumno(getArguments().getInt(ID_ALUMNO)).getEstabloHabilitada() == 1); break;
                    case 2: categoriaHabilitada = (DaoFactory.getAlumnoDao(getContext()).getAlumno(getArguments().getInt(ID_ALUMNO)).getEmocionesHabilitada() == 1); break;
                    case 3: categoriaHabilitada = (DaoFactory.getAlumnoDao(getContext()).getAlumno(getArguments().getInt(ID_ALUMNO)).getNecesidadHabilitada() == 1); break;
                    case 4: categoriaHabilitada = true; // categoria del alumno, no se puede deshabilitar
                }

                if (categoriaHabilitada) {

                    for (Pictograma pictograma : listaPictogramas) {
                        if (pictograma.getCategoria() == categoria) {
                            listaPictogramasFiltrada.add(pictograma);
                        }
                    }

                }else{

                    //Toast.makeText(getContext(), "La categoria se encuentra deshabilitada", Toast.LENGTH_LONG).show();

                }
            }

            return listaPictogramasFiltrada;

        }

    }
}
