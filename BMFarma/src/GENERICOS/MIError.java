package GENERICOS;

import javax.swing.JOptionPane;

public class MIError extends Throwable {

    public MIError(String msm, Throwable causa) {
        super(msm, causa); /// Super obliga a que se ejecute msm, causa Throwable // mostrar el contenido del objeto
    }

    public MIError(String msm, Throwable causa, Integer nivel) {
        super(msm, causa); /// Super obliga a que se ejecute msm, causa Throwable
       /* System.err.println(msm);
        System.err.println(causa);  /// en caso de no usuar el super*/ 
        switch (nivel) {
            case 1:
                /* crea archivo plano con el error*/
                JOptionPane.showConfirmDialog(null,
                        "crea archivo plano con el error");
                break;
            case 2:
                /*crea archivo plano con el error y lo almacena en BD*/
                JOptionPane.showConfirmDialog(null,
                        "crea archivo plano con el error y lo almacena en BD");
                break;
            case 3:
                /*crea archivo plano con el error y envie un correo*/
                JOptionPane.showConfirmDialog(null,
                        "crea archivo plano con el error y envie un correo");

        }
    }

}
