package mx.fca.aviones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvionAdapter extends RecyclerView.Adapter<AvionAdapter.ViewHolder> {

    private List<Avion> aviones = new ArrayList<>();
    private Map<String, Avion> avionesMap = new HashMap<>();
    private Map<Integer, Boolean> avionesColisionados = new HashMap<>();
    private int gridSize;

    public AvionAdapter(List<Avion> aviones, int gridSize) {
        this.gridSize = gridSize;
        if (aviones != null) {
            Plano planoInicial = new Plano(0, new ArrayList<>(aviones), new ArrayList<>());
            setAviones(planoInicial);
        }
    }

    public void setAviones(Plano plano) {
        if (plano == null) return;

        this.aviones = (plano.aviones != null) ? plano.aviones : new ArrayList<>();
        List<Colision> colisiones = (plano.colisiones != null) ? plano.colisiones : new ArrayList<>();

        this.avionesMap.clear();
        for (Avion avion : this.aviones) {
            if (avion.esVisible) {
                String key = avion.x + "," + avion.y;
                this.avionesMap.put(key, avion);
            }
        }

        this.avionesColisionados.clear();
        for (Colision colision : colisiones) {
            for (Avion avionColisionado : colision.avionesInvolucrados) {
                this.avionesColisionados.put(avionColisionado.id, true);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.avion_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int x = position % gridSize;
        int y = position / gridSize;
        String key = x + "," + y;

        Avion avionEnCelda = avionesMap.get(key);

        if (avionEnCelda != null) {
            boolean haColisionado = avionesColisionados.containsKey(avionEnCelda.id);

            if (haColisionado) {
                holder.imgAvion.setImageResource(R.drawable.collision);
            } else {
                switch (avionEnCelda.direccion) {
                    case NORTE:
                        holder.imgAvion.setImageResource(R.drawable.north);
                        break;
                    case ESTE:
                        holder.imgAvion.setImageResource(R.drawable.east);
                        break;
                    case SUR:
                        holder.imgAvion.setImageResource(R.drawable.south);
                        break;
                    case OESTE:
                        holder.imgAvion.setImageResource(R.drawable.west);
                        break;
                }
            }
            holder.imgAvion.setRotation(0);
        } else {
            holder.imgAvion.setImageResource(0);
        }
    }

    @Override
    public int getItemCount() {
        return gridSize * gridSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvion = itemView.findViewById(R.id.imgAvion);
        }
    }
}
