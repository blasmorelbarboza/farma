/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GENERICOS;

import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DELL
 */
public class ListarReportes {
    private final Conexion conexion;
    
    public ListarReportes() {
        conexion = new Conexion();
    }

    public void generarReportes(String urlJasper, Map parametros) throws JRException {
        try {
            JDialog dialogoReport = new JDialog(new JFrame(), "BMFarma- Reportes...", true);
            dialogoReport.setSize(800, 600);
            dialogoReport.setLocationRelativeTo(null);
            JasperReport report = JasperCompileManager.compileReport(urlJasper);
            JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion.getConexion());
            dialogoReport.getContentPane().add(new JasperViewer(print, true).getContentPane());
            dialogoReport.setVisible(true);
        } catch (JRException ex) {
        }
    }
}
