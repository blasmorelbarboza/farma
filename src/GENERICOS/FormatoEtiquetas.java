package Genericos;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FormatoEtiquetas {

    public static enum estiloJLabel {
        Titulo01, TituloPrincipal, SubTitulo01, Normal01
    };

    public FormatoEtiquetas() {
    }

    
    public void AsignarFormatoEtiquetas(HashMap<JLabel, estiloJLabel> grupoEtiquetas) {
        for (Map.Entry<JLabel, estiloJLabel> entry : grupoEtiquetas.entrySet()) {
            JLabel key = entry.getKey();
            estiloJLabel value = entry.getValue();
            switch (value) {
                case Titulo01:
                    key.setFont(new Font("Arial", 1, 18)); // NOI18N
                    key.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case SubTitulo01:
                    key.setFont(new Font("Arial", 0, 16)); // NOI18N
                    key.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case Normal01:
                    key.setFont(new Font("Arial Narrow", 0, 14)); // NOI18N
                    key.setHorizontalAlignment(SwingConstants.CENTER);
                    key.setForeground(new java.awt.Color(0, 0, 0));
                    break;
                case TituloPrincipal:
                    key.setBackground(new java.awt.Color(255, 255, 255));
                    key.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
                    key.setForeground(new java.awt.Color(255, 255, 255));
                    key.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
            }

        }
    }

}
