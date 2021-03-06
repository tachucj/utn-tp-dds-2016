package ar.edu.utn.frba.dds.services.externo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ar.edu.utn.frba.dds.model.poi.sucursal.banco.SucursalBanco;

public interface ServicioConsultaBanco {

    public List<SucursalBanco> getBancosExternos(String banco, String servicio) throws JsonParseException, JsonMappingException, IOException, UnknownHostException;
}
