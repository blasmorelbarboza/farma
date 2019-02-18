package CONTROL;

import FORMULARIOS.Presupuesto;
import GENERICOS.LimpiarTextosPanel;
import GENERICOS.MIError;
import javax.swing.JDialog;

public class PresupuestoCtrol {
        public enum paneles {
        cabecera, detalle, detalleTotal, detalleBtn, botonera, todos,
    };
    
    private Presupuesto PresupuestoInt;
    private LimpiarTextosPanel limpiarPanel;
    private String MiMsM;
     public PresupuestoCtrol(JDialog dialogoPadre) {
         PresupuestoInt = (Presupuesto) dialogoPadre;
//        utiles = new Utiles();
//        formatoTxt = new FormatoTextos();
//        textoVacio = new ValidarVacios();
//        limpiarPanel = new LimpiarTextosPanel();
//        habilitarPanel = new HabilitarTextosPanel();

    }
     
         public void limpiarTexto(paneles p) throws MIError {
        try {
            switch (p) {

                case cabecera:
                    limpiarPanel.limpiarCampos(PresupuestoInt.pnlCabecera);
                    break;
                case detalleTotal:
                    limpiarPanel.limpiarCampos(PresupuestoInt.pnlTotalDetalle);
                    break;

                case todos:
                    limpiarPanel.limpiarCampos(PresupuestoInt.pnlCabecera);
                    limpiarPanel.limpiarCampos(PresupuestoInt.pnlDetalle);
                    limpiarPanel.limpiarCampos(PresupuestoInt.pnlDetalle);
                    break;

            }
        } catch (Exception ex) {
            MiMsM = "Ocurrio un error en el metodo  limpiarTexto ";
            throw new MIError(MiMsM, ex);
        }
    }
}
