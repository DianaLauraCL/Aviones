package mx.fca.aviones;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mx.fca.aviones.viewmodel.SimulacionViewModel;

public class MainActivity extends AppCompatActivity {

    Button btnNext, btnPrevious;
    TextView pasosTextView, colisionesTextView;
    RecyclerView listaAviones;
    AvionAdapter adapter;
    SimulacionViewModel viewModel;

    private static final int GRID_SIZE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAviones = findViewById(R.id.listaAviones);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        pasosTextView = findViewById(R.id.pasosTextView);
        colisionesTextView = findViewById(R.id.colisionesTextView);

        viewModel = new ViewModelProvider(this).get(SimulacionViewModel.class);

        adapter = new AvionAdapter(null, GRID_SIZE);
        listaAviones.setAdapter(adapter);
        listaAviones.setLayoutManager(new GridLayoutManager(this, GRID_SIZE));

        btnNext.setOnClickListener(v -> viewModel.calcularSiguientePaso());
        btnPrevious.setOnClickListener(v -> viewModel.pasoAnterior());

        viewModel.getPlanoActual().observe(this, plano -> {
            if (plano != null) {
                pasosTextView.setText("Pasos: " + plano.noPaso);
                colisionesTextView.setText("Colisiones: " + plano.colisiones.size());
                adapter.setAviones(plano);
            }
        });
    }
}
