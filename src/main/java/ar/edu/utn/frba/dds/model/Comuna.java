package ar.edu.utn.frba.dds.model;

import java.awt.Polygon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "superficie" })
public class Comuna {

    private int numeroComuna;
    private Polygon superficie;

    public int getNumeroComuna() {
        return numeroComuna;
    }

    public void setNumeroComuna(int numeroComuna) {
        this.numeroComuna = numeroComuna;
    }

    public void setSuperficie(Polygon superficie) {
        this.superficie = superficie;
    }

    public Polygon getSuperficie() {
        return superficie;
    }

    public boolean incluyeGeolocalizacion(Geolocalizacion geolocalizacion) {
        return superficie.contains(geolocalizacion.getLatitud(), geolocalizacion.getLongitud());
    }
}