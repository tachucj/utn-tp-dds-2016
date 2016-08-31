package ar.edu.utn.frba.dds.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frba.dds.model.PuntoDeInteres;
import ar.edu.utn.frba.dds.services.AppService;
import ar.edu.utn.frba.dds.services.CGPService;
import ar.edu.utn.frba.dds.services.TerminalInteractivaService;

@RestController
public class POIController {

    @Autowired
    CGPService cgpService;

    @Autowired //TODO Evaluar si permanece
    TerminalInteractivaService terminalInteractivaService;
    
    @Autowired
    AppService appService;

    @RequestMapping(value = { "/pois" }, method = RequestMethod.GET)
    public @ResponseBody List<PuntoDeInteres> getAllPois() {
        return appService.getPois();
    }

    @RequestMapping(value = { "/pois/{idTerminal}/{textoBusqueda}" }, method = RequestMethod.GET)
    public @ResponseBody List<PuntoDeInteres> buscarPoi(@PathVariable("textoBusqueda") String textoBusqueda, @PathVariable("idTerminal") int idTerminal)
            throws Exception {
        try {
            return appService.getPois(textoBusqueda, new DateTime(),idTerminal);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error interno al obtener los pois");
        }
    }

    @RequestMapping(value = { "/poi/{idPoi}" }, method = RequestMethod.GET)
    public @ResponseBody PuntoDeInteres poi(@PathVariable("idPoi") int idPoi) {
        return appService.poi(idPoi);
    }

    @RequestMapping(value = { "/poi/{idPoi}/{idTerminal}/cercano" }, method = RequestMethod.GET)
    public @ResponseBody boolean esCercano(@PathVariable("idPoi") int idPoi, @PathVariable("idTerminal") int idTerminal) {
        return appService.esCercano(idPoi,idTerminal);
    }

    @RequestMapping(value = { "/poi/{idPoi}/disponible" }, method = RequestMethod.GET)
    public @ResponseBody boolean estaDisponible(@PathVariable("idPoi") int idPoi) {
        return appService.estaDisponible(idPoi);
    }

    @RequestMapping(value = { "/reporte" }, method = RequestMethod.GET)
    public @ResponseBody Map<String, Long> generarReporte() {
        return appService.generarReporteBusquedasPorFecha();
    }
    
    @RequestMapping(value = { "/reporteTerminal" }, method = RequestMethod.GET)
    public @ResponseBody Map<Integer, Long> generarReportePorTerminal() {
        return appService.generarReporteBusquedasPorTerminal();
    }
    
    @RequestMapping(value = { "/reporte/{idTerminal}" }, method = RequestMethod.GET)
    public @ResponseBody Map<String, Long> generarReporteDeTerminal(@PathVariable("idTerminal") int idTerminal) {
        return appService.generarReporteBusquedasDeTerminal(idTerminal);
    }
}