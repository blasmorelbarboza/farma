package Genericos;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FormatoTextos {

    public static enum formatoTexto {

        Titulo01, SubTitulo01, Normal01
    };

    private ArrayList<JTextField> arrayTextos;

    public void asignarFormatoTexto(HashMap<JTextField, asignarPropiedades> grupoCuadroTexto, formatoTexto formato) {
        arrayTextos = new ArrayList<>();
        for (Map.Entry<JTextField, asignarPropiedades> entry : grupoCuadroTexto.entrySet()) {
            JTextField key = entry.getKey();
            asignarPropiedades value = entry.getValue();
            arrayTextos.add(key);
            key.setDocument(new PropiedadesTextos(key, value.getEsCaracter(), value.getValorMaxDigito()));
 
            switch (formato) {
                case Titulo01:
                    key.setFont(new Font("Arial", 1, 18)); // NOI18N
                    key.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case SubTitulo01:
                    key.setFont(new Font("Arial", 1, 15)); // NOI18N
                    key.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case Normal01:
                    key.setFont(new Font("Arial", 1, 12)); // NOI18N
                    key.setHorizontalAlignment(SwingConstants.LEFT);
                    key.setSize(120, 30);
                    break;
            }
        }
    }

    public static class asignarPropiedades {

        private final Integer valorMaxDigito;
        private final Boolean esCaracter;

        public asignarPropiedades(Integer valorMaxDigito, Boolean esCaracter) {
            this.valorMaxDigito = valorMaxDigito;
            this.esCaracter = esCaracter;
        }

        public Integer getValorMaxDigito() {
            return valorMaxDigito;
        }

        public Boolean getEsCaracter() {
            return esCaracter;
        }

    }

}
