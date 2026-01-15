package mx.fca.aviones.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import mx.fca.aviones.Plano;
import mx.fca.aviones.Planificador;

public class SimulacionViewModel extends ViewModel {

    private final List<Plano> historialDePasos = new ArrayList<>();
    private int indicePasoActual = -1;

    private final androidx.lifecycle.MutableLiveData<Plano> planoActual = new androidx.lifecycle.MutableLiveData<>();

    public SimulacionViewModel() {
        // Estado inicial
        Plano planoInicial = Planificador.crearRutaInicial();
        historialDePasos.add(planoInicial);
        indicePasoActual = 0;
        planoActual.setValue(planoInicial);
    }

    public androidx.lifecycle.LiveData<Plano> getPlanoActual() {
        return planoActual;
    }

    public void calcularSiguientePaso() {
        if (indicePasoActual < historialDePasos.size() - 1) {
            indicePasoActual++;
            planoActual.setValue(historialDePasos.get(indicePasoActual));
            return;
        }
        Plano planoAnterior = historialDePasos.get(indicePasoActual);
        Plano nuevoPlano = planoAnterior.next();
        historialDePasos.add(nuevoPlano);
        indicePasoActual++;
        planoActual.setValue(nuevoPlano);
    }

    public void pasoAnterior() {
        if (indicePasoActual > 0) {
            indicePasoActual--;
            planoActual.setValue(historialDePasos.get(indicePasoActual));
        }
    }
}
