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
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author gelog
 */
public class JfRegistroViajaConduce extends javax.swing.JFrame {

    /**
     * Creates new form JfRegistroViaje
     */
    public JfRegistroViajaConduce() {
        initComponents();
          cargaComboBox(JcmbxRutas, 1);
        cargaComboBox(JcmbxAutobuses, 2);
        cargaComboBox(JcmbxConductores, 3);
        cargaComboBox(JcmbxFecha, 4);
    }

      
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String> terminales = new ArrayList<>();
    private String nombresRuta, numAutobus,conductor,fechas;
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
                    datosListas = queryCarga.cargaComboAutobus();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                    
                    case 3:
                    datosListas = queryCarga.cargaComboConductores();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                    
                     case 4:
                    datosListas = queryCarga.cargaComboFechas();
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
    nombresRuta = (String) JcmbxRutas.getSelectedItem();
    numAutobus= (String)JcmbxAutobuses.getSelectedItem();  
    conductor= (String)JcmbxConductores.getSelectedItem();  
    fechas= (String)JcmbxFecha.getSelectedItem();  
}
    
    
             public void limpiaValores() {
    // Obtener los valores de los campos de texto
    nombresRuta = null;
    numAutobus = null;
    conductor = null;
    fechas = null;
    
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
        return validaCampoComb(nombresRuta, JcmbxRutas, "Seleccione una ruta")
            && validaCampoComb(numAutobus, JcmbxAutobuses, "Selecciones una Parada")
            && validaCampoComb(conductor, JcmbxConductores, "Selecciones una Parada")
            && validaCampoComb(fechas, JcmbxFecha, "Selecciones una Parada");
    }
    
      
      
      
      
           public void enviarDatos() {
        int idAutoCondu, idRutaAuto,idRutaCondu,idConductor,idAutobus,idRuta,idFecha;
        //int precio1=Integer.parseInt(precio);
        
           
     
        if (validaCampos()) {
            //telefonos = devuelveTelefonos();
            //if (telefonos != null) {
               // if (sinTelefono == false) {
                    asignaValores();
                     String[] partesFecha = new String[3];
        // Separar la fecha en día, mes y año
        partesFecha = fechas.split("/");
        int dia = Integer.parseInt(partesFecha[0]);
        String mes = partesFecha[1];
        int año =Integer.parseInt(partesFecha[2]);
          
                    try {
                        idAutoCondu=queryBusca.obtenIdFinalAutobusConductor();
                       idRutaAuto= queryBusca.obtenIdFinalRutaAutobus();
                       idRutaCondu=queryBusca.obtenIdFinalRutaConductor();
                     idConductor=queryBusca.obtenIdBuscaConductor(conductor);
                     idAutobus=queryBusca.obtenIdBuscaAutobus(numAutobus);
                     idRuta=queryBusca.obtenIdRuta(nombresRuta);
                     idFecha=queryBusca.obtenIdFecha(dia, mes, año);
                        System.out.println("id de la fecha:"+idFecha);
                        System.out.println("dia:"+partesFecha[0]);
                        System.out.println("mes:"+partesFecha[1]);
                        System.out.println("año:"+partesFecha[2]);
                        //System.out.println(partesFecha.toString());
                          System.out.println("fecha es:"+fechas);
                        System.out.println("NombreRuta:" + nombresRuta + "\neconomico:" + numAutobus);
                        System.out.println("NombreRuta:" + nombresRuta + "\neconomico:" + numAutobus);
                        //query.insertaPersona((id + 1), nombres, apPaterno, apMaterno);
                        System.out.println("id ruta:" + idAutoCondu);
                        
                        System.out.println("idConductor:"+idConductor);
                        System.out.println("idautobus:"+idAutobus);
                      
                         queryInserta.insertaAutobusConductor(idAutoCondu+1, idConductor, idAutobus);
                         queryInserta.insertaRutaConductor(idRutaCondu+1, idRuta, idConductor);
                         queryInserta.insertaRutaAutobus(idRutaAuto+1, idRuta, idAutobus, idFecha);
                        
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

        jLabel1 = new javax.swing.JLabel();
        JpnlLienzo = new javax.swing.JPanel();
        JlblRuta = new javax.swing.JLabel();
        JcmbxRutas = new javax.swing.JComboBox<>();
        JlblAutobuses = new javax.swing.JLabel();
        JcmbxAutobuses = new javax.swing.JComboBox<>();
        JlblFecha = new javax.swing.JLabel();
        JcmbxFecha = new javax.swing.JComboBox<>();
        JbtnEnviar = new javax.swing.JButton();
        JlblConductor = new javax.swing.JLabel();
        JcmbxConductores = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear viajes");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));

        JlblRuta.setText("Rutas");

        JcmbxRutas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));

        JlblAutobuses.setText("Autobuses");

        JcmbxAutobuses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));

        JlblFecha.setText("Fechas");

        JcmbxFecha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));

        JbtnEnviar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEnviar.setText("Enviar");
        JbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEnviarActionPerformed(evt);
            }
        });

        JlblConductor.setText("Conductores");

        JcmbxConductores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoViaje.jpeg"))); // NOI18N

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlblRuta)
                            .addComponent(JcmbxRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JlblAutobuses)
                            .addComponent(JcmbxAutobuses, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JlblConductor))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JcmbxConductores, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JlblFecha))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JcmbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JbtnEnviar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JpnlLienzoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {JcmbxAutobuses, JcmbxConductores, JcmbxFecha, JcmbxRutas});

        JpnlLienzoLayout.setVerticalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addComponent(JlblRuta)
                        .addGap(6, 6, 6)
                        .addComponent(JcmbxRutas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(JlblAutobuses)
                        .addGap(6, 6, 6)
                        .addComponent(JcmbxAutobuses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JlblConductor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JcmbxConductores, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JlblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JcmbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JbtnEnviar)))
                .addGap(14, 14, 14))
        );

        JpnlLienzoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {JcmbxAutobuses, JcmbxConductores, JcmbxFecha, JcmbxRutas});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEnviarActionPerformed
        // TODO add your handling code here:
        try {
                 if (JcmbxRutas.getSelectedIndex() == 0) {
             CMensajes.msg_advertencia("Seleccione una ruta", "Validación de Campos");
            
        }else if(JcmbxAutobuses.getSelectedIndex()==0){
        
        CMensajes.msg_advertencia("Seleccione un numero economico del autobus", "Validación de Campos");
        }else if(JcmbxConductores.getSelectedIndex()==0){
        
        CMensajes.msg_advertencia("Seleccione un conductor", "Validación de Campos");
        
        }else if(JcmbxFecha.getSelectedIndex()==0){
        
        CMensajes.msg_advertencia("Seleccione un conductor", "Validación de Campos");
        }else{
         enviarDatos();
        }
        } catch (Exception e) {
            CMensajes.msg_advertencia("Seleccione los campos vacios", "Validación de Campos");
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
            java.util.logging.Logger.getLogger(JfRegistroViajaConduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRegistroViajaConduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRegistroViajaConduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRegistroViajaConduce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRegistroViajaConduce().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JComboBox<String> JcmbxAutobuses;
    private javax.swing.JComboBox<String> JcmbxConductores;
    private javax.swing.JComboBox<String> JcmbxFecha;
    private javax.swing.JComboBox<String> JcmbxRutas;
    private javax.swing.JLabel JlblAutobuses;
    private javax.swing.JLabel JlblConductor;
    private javax.swing.JLabel JlblFecha;
    private javax.swing.JLabel JlblRuta;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
