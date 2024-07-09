/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas.Registros;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CEliminaciones;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author gelog
 */
public class JfRegistroParadas extends javax.swing.JFrame {

    /**
     * Creates new form JfRegistroParadas
     */
    public JfRegistroParadas() {
        initComponents();
         cargaComboBox(JcmbxRutas1, 1);
        cargaComboBox(JcmbxTeminales, 2);
        
     
    }

    
    
        private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String> terminales = new ArrayList<>();
    private String nombresRuta, terminalParada;
    private boolean sinTelefono = false;
    private String regexNombres = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?: [a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)?$";
    private String regexDuracion = "^[1-9]\\d*\\s+(hora|horas)$";
    private String regexHora = "^(0?[1-9]|1[0-2]):[0-5][0-9]\\s+(a\\. m\\.|p\\. m\\.)$";
    //private String regexPrecio = "^\\d{1,8}(\\.\\d{1,2})?$";
    private String regexPrecio = "^\\d{1,7}(\\.\\d{1,2})?$|^\\d{8}(\\.\\d{1,2})?$";
    private String regexDistancia = "^\\d{1,13}(\\.\\d{1,2})?\\s*km$";
     private DefaultComboBoxModel listas;
       private ArrayList<String> datosListas = new ArrayList<>();
    
    
    
    
    
    
    //asignamos valores al combo box
      public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaComboRutas();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = queryCarga.cargaComboTerminales();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }
        } catch (SQLException e) {
        }
    } 
      
      
      
          public void asignaValores() {
    // Obtener los valores de los campos de texto
    nombresRuta = (String) JcmbxRutas1.getSelectedItem();
    terminalParada= (String)JcmbxTeminales.getSelectedItem();  
}
    
              public void limpiaValores() {
    // Obtener los valores de los campos de texto
    nombresRuta = null;
    terminalParada = null;
    
}
              
    public boolean validaCampoComb(String campoTexto, JComboBox<String> comboBox, String mensajeVacio) {
    boolean valida = true;
    
    campoTexto = (String) comboBox.getSelectedItem(); // Obtener el texto seleccionado del JComboBox
    
    if (campoTexto.equals("Selecciona una opcion")) {
        CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
        valida = false;
    } 
    return valida;
}
    
    
         public boolean validaCampos() {
        return validaCampoComb(nombresRuta, JcmbxRutas1, "Seleccione una ruta")
            && validaCampoComb(terminalParada, JcmbxTeminales, "Selecciones una Parada");
    }
    
         
           public void enviarDatos() {
        int idParada, idRuta,idTerminal;
        //int precio1=Integer.parseInt(precio);
        if (validaCampos()) {
            //telefonos = devuelveTelefonos();
            //if (telefonos != null) {
               // if (sinTelefono == false) {
                    asignaValores();
                    try {
                        idRuta=queryBusca.obtenIdRuta(nombresRuta);
                       idParada= queryBusca.obtenIdFinalParada();
                       idTerminal=queryBusca.obtenIdtTerminal(terminalParada);
                     
                     
                        System.out.println("NombreRuta:" + nombresRuta + "\nterminal:" + terminalParada);
                        //query.insertaPersona((id + 1), nombres, apPaterno, apMaterno);
                        System.out.println("id ruta:" + idRuta);
                        
                        
                       queryInserta.insertaParada(idParada+1, idRuta, idTerminal);
                        
                        
                        CMensajes.msg("Usuario Registrado", "Registro Usuarios");
                    } catch (SQLException ex) {
                    } finally {
                        limpiaValores();
                    }
                    this.dispose();
                //}
            //}
        }
    }
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JlblRuta = new javax.swing.JLabel();
        JcmbxTeminales = new javax.swing.JComboBox<>();
        JlblTerminal = new javax.swing.JLabel();
        JbtnEnviar = new javax.swing.JButton();
        JcmbxRutas1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar paradas");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblRuta.setText("Rutas");
        JpnlLienzo.add(JlblRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        JcmbxTeminales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxTeminales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxTeminalesActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxTeminales, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        JlblTerminal.setText("Terminales");
        JpnlLienzo.add(JlblTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        JbtnEnviar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEnviar.setText("Enviar");
        JbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEnviarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        JcmbxRutas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JpnlLienzo.add(JcmbxRutas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoParadas.jpg"))); // NOI18N
        JpnlLienzo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JpnlLienzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxTeminalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxTeminalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcmbxTeminalesActionPerformed

    private void JbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEnviarActionPerformed
        // TODO add your handling code here:
        try {
           if (JcmbxRutas1.getSelectedIndex() == 0) {
           CMensajes.msg_advertencia("Seleccione una ruta", "Validación de Campos");
        }
        
        if (JcmbxTeminales.getSelectedIndex() == 0) {
            CMensajes.msg_advertencia("Seleccione una terminal", "Validación de Campos");
        }else{
        enviarDatos();
        }
          
        } catch (Exception e) {
           CMensajes.msg_advertencia("Seleccione de nuevo una opcion", "Validación de Campos");
        }
       
    }//GEN-LAST:event_JbtnEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JfRegistroParadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRegistroParadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRegistroParadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRegistroParadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRegistroParadas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JComboBox<String> JcmbxRutas1;
    private javax.swing.JComboBox<String> JcmbxTeminales;
    private javax.swing.JLabel JlblRuta;
    private javax.swing.JLabel JlblTerminal;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
