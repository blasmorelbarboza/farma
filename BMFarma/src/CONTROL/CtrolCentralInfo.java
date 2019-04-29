/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import FORMULARIOS.CentralInformes;
import GENERICOS.ListarReportes;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author DELL
 */
public class CtrolCentralInfo {
    private CentralInformes ctrolInf;
    private final ListarReportes reportes;
    private String urlJasper;
    private final HashMap<String, Object> parametros;
    
    public enum listaReporte{
        cargos,
        nacionalidad,
        ciudad,
        sucursal,
        funcionarios
    };
    
    private listaReporte opcion;
    
    public CtrolCentralInfo(CentralInformes c ) {
        ctrolInf=c;
        reportes=new ListarReportes();
        parametros=new HashMap<>();
    }
    
    public void seleccionReporteAimprimir(listaReporte opcionRec){
        this.opcion=opcionRec;
    }
    
    public void imprimirReportes(){
        try {
            switch(opcion){
                case cargos:
                    urlJasper="src\\Reportes\\nacionaliti.jrxml";
                break;
                case ciudad:
                    urlJasper="src\\Reportes\\nacionaliti.jrxml";
                    break;
                case nacionalidad: 
                    urlJasper="src\\Reportes\\nacionaliti.jrxml";
                    break;
                case sucursal: 
                    urlJasper="src\\Reportes\\nacionaliti.jrxml";
                    break;
                case funcionarios: 
                    parametros.put("nro_ci", Integer.parseInt(ctrolInf.txtParametro.getText().trim()));
                   
                    urlJasper="src\\Reportes\\nacionaliti.jrxml";
                    break;
              
            }
            reportes.generarReportes(urlJasper, parametros);
        } catch (JRException ex) {
            Logger.getLogger(CtrolCentralInfo.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
