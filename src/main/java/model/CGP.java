package model;

import java.util.ArrayList;

import org.joda.time.DateTime;

import util.time.DateTimeProvider;

public class CGP extends PuntoDeInteres {

    private ArrayList<ServicioCGP> servicios;
    private Comuna comuna;

	public CGP (DateTimeProvider dateTimeProviderImpl){
	    this.dateTimeProvider = dateTimeProviderImpl;
	}
    
    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(final Comuna comuna) {
        this.comuna = comuna;
    }

    public ArrayList<ServicioCGP> getServicios() {
        return servicios;
    }

    public void setServicios(final ArrayList<ServicioCGP> servicios) {
        this.servicios = servicios;
    }

    public void agregarServicio(final ServicioCGP servicio) {
        this.servicios.add(servicio);
    }

    @Override
    protected boolean estaDisponible() {
        DateTime fechaHoraActual = this.dateTimeProvider.getDateTime();
        for (ServicioCGP servicio : servicios) {
            if (servicio.getHorarios().atiende(fechaHoraActual)) {
                return true;
            }
        }
        return false;
    }

    protected boolean estaDisponible(String nombreServicioCGP) {
        DateTime fechaHoraActual = this.dateTimeProvider.getDateTime();
        for (ServicioCGP servicio : servicios) {
            if (servicio.getNombre().toLowerCase() == nombreServicioCGP.toLowerCase()) {
                return servicio.getHorarios().atiende(fechaHoraActual);
            }
        }
        return false;
    }

    @Override
    protected boolean tienePalabra(final String palabra) {
        for (ServicioCGP servicio : servicios) {
            if (servicio.getNombre().toLowerCase().contains(palabra.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean esCercano(final Geolocalizacion geolocalizacion) {
        return this.getComuna().incluyeGeolocalizacion(geolocalizacion);
    }

}
