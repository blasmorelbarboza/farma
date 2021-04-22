/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GENERICOS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class ConstructorCombo {
    
    private ArrayList<registrosTabla> datos;
    
    
    public ConstructorCombo(ResultSet cursor) throws MIError{
        /**Carga el Combo Recibe ResultSet Con la Sentencia
         */
        try {
            datos = new ArrayList<>();
            datos.add(new registrosTabla(0, "<No Seleccionado>"));
            while (cursor.next()) {
                datos.add(new registrosTabla(cursor.getInt(1), cursor.getString(2)));
            }
        } catch (SQLException ex) {
            throw new MIError("Ocurrio un error durante la lectura de datos para el combo..", ex);            
        }
    }

    public ArrayList<registrosTabla> getRegistrosCombo() {
        return datos;
    }

    
    public Integer getCodigoActual(int indice) {
     /**
     * getCodigoActual Recupera actual del Combo
     */
        if (indice != -1) {
            return datos.get(indice).getCodCombo();
        }
        return 0;
    }

    public class registrosTabla {

        private final Integer codCombo;
        private final String desCombo;

        public registrosTabla(Integer codCombo, String desCombo) {
            this.codCombo = codCombo;
            this.desCombo = desCombo;
        }

        public Integer getCodCombo() {
            return codCombo;
        }

        public String getDesCombo() {
            return desCombo;
        }
    }

}
