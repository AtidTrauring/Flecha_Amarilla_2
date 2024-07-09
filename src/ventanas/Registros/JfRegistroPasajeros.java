package ventanas.Registros;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CEliminaciones;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JfRegistroPasajeros extends javax.swing.JFrame {

    public JfRegistroPasajeros() {
        initComponents();
       // JcmbxTipoPasajeros.setVisible(false);
        //JcmbxTipoPasajeros.setEditable(false);
    }

    private CInserciones queryInserta = new CInserciones();
    private CBusquedas queryBusca = new CBusquedas();
    private CEliminaciones queryElimina = new CEliminaciones();
    private CActualizaciones queryActualiza = new CActualizaciones();
    private ArrayList<String> telefonos = new ArrayList<>();
    private String nombres, apPaterno, apMaterno, telefono;
    private boolean sinTelefono = false;
    private String regexNombres = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?: [a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)?$";
    

    public void asignaValores() {
        nombres = JtxtNombres.getText();
        apPaterno = JtxtApPaterno.getText();
        apMaterno = JtxtApMaterno.getText();
    }

    public void limpiaValores() {
        nombres = null;
        apPaterno = null;
        apMaterno = null;
        telefonos.clear();
        sinTelefono = false;
    }

    public String devuelveCadena(JTextField campo, String regex) {
        String cadena = null;
        cadena = campo.getText();
        if (cadena.isEmpty()) {
            cadena = null;
        } else if (cadena.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?: [a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)?$")) {
            return cadena;
        } else {
            cadena = "NoValido";
        }
        return cadena;
    }

    public boolean validaCampo(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadena(campo, regex);
        if (campoTexto == null) {
            CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
            valida = false;
        } else if (campoTexto.equals("NoValido")) {
            CMensajes.msg_error(mensajeInvalido, "Registro Usuarios");
            valida = false;
        } else {
            valida = true;
        }
        return valida;
    }

    public boolean validaCampos() {
        return     validaCampo(nombres, JtxtNombres, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)")
                && validaCampo(apPaterno, JtxtApPaterno, regexNombres, "Ingrerse un apellido paterno.", "Valores invalidos para apellido paterno")
                && validaCampo(apMaterno, JtxtApMaterno, regexNombres, "Ingrese un apellido materno.", "Valores invalidos para apellido materno");
    }

    public boolean validaCamposConCorreo() {
        return validaCampo(nombres, JtxtNombres, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)")
                || validaCampo(apPaterno, JtxtApPaterno, regexNombres, "Ingrerse un apellido paterno.", "Valores invalidos para apellido paterno")
                || validaCampo(apMaterno, JtxtApMaterno, regexNombres, "Ingrese un apellido materno.", "Valores invalidos para apellido materno");
    }

    public String obtieneTelefono(String mensaje) {
        String telefono = "";
        do {
            telefono = JOptionPane.showInputDialog(null, mensaje, "Numeros de Telefono", JOptionPane.INFORMATION_MESSAGE);
            if (telefono == null) {
                return null;
            } else if (telefono.matches("^[0-9]{10}$")) {
                return telefono;
            } else if (telefono.isEmpty()) {
                CMensajes.msg_advertencia("No puede dejar el campo vacio", "Obtiene Telefono");
                telefono = null;
            } else {
                CMensajes.msg_advertencia("Ingrese un numero de telefono valido", "Obtiene Telefono");
                telefono = null;
            }
        } while (telefono == null);
        return telefono;
    }

    public ArrayList<String> devuelveTelefonos() {
        if (JcmbxTelefonos.getSelectedIndex() == 0) {
            CMensajes.msg("Selecciona una cantidad de telefonos", "Registro de usuarios");
            sinTelefono = true;
        } else {
            sinTelefono = false;
            for (int i = 1; i <= JcmbxTelefonos.getSelectedIndex(); i++) {
                switch (i) {
                    case 1:
                        telefono = obtieneTelefono("Ingrese el primer número de telefono: ");
                        if (telefono == null) {
                            i = JcmbxTelefonos.getSelectedIndex();
                            telefonos.clear();
                        } else {
                            telefonos.add(telefono);
                        }
                        break;
                    case 2:
                        telefono = obtieneTelefono("Ingrese el segundo número de telefono: ");
                        if (telefono == null) {
                            i = JcmbxTelefonos.getSelectedIndex();
                            telefonos.clear();
                        } else {
                            telefonos.add(telefono);
                        }
                        break;
                    case 3:
                        telefono = obtieneTelefono("Ingrese el tercer número de telefono: ");
                        if (telefono == null) {
                            i = JcmbxTelefonos.getSelectedIndex();
                            telefonos.clear();
                        } else {
                            telefonos.add(telefono);
                        }
                        break;
                    case 4:
                        telefono = obtieneTelefono("Ingrese el cuarto número de telefono: ");
                        if (telefono == null) {
                            i = JcmbxTelefonos.getSelectedIndex();
                            telefonos.clear();
                        } else {
                            telefonos.add(telefono);
                        }
                        break;
                    case 5:
                        telefono = obtieneTelefono("Ingrese el quinto número de telefono: ");
                        if (telefono == null) {
                            i = JcmbxTelefonos.getSelectedIndex();
                            telefonos.clear();
                        } else {
                            telefonos.add(telefono);
                        }
                        break;
                }
            }
        }
        return telefonos;
    }

    public int asignaDescuento() {
        int descuento = 0;
        switch ((String) JcmbxTipoPasajeros.getSelectedItem()) {
            case "Adulto":
                descuento = 0;
                break;
            case "Niño":
                descuento = 20;
                break;
            case "Docente":
                descuento = 25;
                break;
            case "Estudiante":
                descuento = 35;
                break;
            case "INAPAM":
                descuento = 30;
                break;
        }
        return descuento;
    }

    public String asignaTipoPasajero() {
        String tipoPasajero = "";
        switch ((String) JcmbxTipoPasajeros.getSelectedItem()) {
            case "Adulto":
                tipoPasajero = "A";
                break;
            case "Niño":
                tipoPasajero = "N";
                break;
            case "Docente":
                tipoPasajero = "D";
                break;
            case "Estudiante":
                tipoPasajero = "E";
                break;
            case "INAPAM":
                tipoPasajero = "AT";
                break;
        }
        return tipoPasajero;
    }

    public void enviarDatos() {
        int id, idTel;
        if (validaCampos()) {
            //telefonos = devuelveTelefonos();
            if (telefonos != null) {
                if (sinTelefono == false) {
                    asignaValores();
                    try {
                        id = queryBusca.obtenIdFinalPersona();
                        System.out.println("Nombre" + nombres + "\nApellido P" + apPaterno + "\nApellido M" + apMaterno);
                        queryInserta.insertaPersona((id + 1), nombres, apPaterno, apMaterno);
                        idTel = queryBusca.obtenIdFinalTelefono();
                        for (int i = 0; i < telefonos.size(); i++) {
                            idTel++;
                            queryInserta.insertaTelefonos(idTel, telefonos.get(i), queryBusca.obtenIdFinalPersona());
                        }
                        queryInserta.insertaPasajeros((queryBusca.obtenIdFinalPasajero() + 1), asignaTipoPasajero(), asignaDescuento(), queryBusca.obtenIdFinalPersona());
                        CMensajes.msg("Usuario Registrado", "Registro Usuarios");
                    } catch (SQLException ex) {
                    } finally {
                        limpiaValores();
                    }
                    this.dispose();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPnlLienzo = new javax.swing.JPanel();
        JlblNombres = new javax.swing.JLabel();
        JlblApellidoPaterno = new javax.swing.JLabel();
        JlblApellidoMaterno = new javax.swing.JLabel();
        JtxtNombres = new javax.swing.JTextField();
        JtxtApPaterno = new javax.swing.JTextField();
        JtxtApMaterno = new javax.swing.JTextField();
        JsNombre = new javax.swing.JSeparator();
        JsApellidoPaterno = new javax.swing.JSeparator();
        JsApellidoMaterno = new javax.swing.JSeparator();
        JbtnEnviar = new javax.swing.JButton();
        JcmbxTipoPasajeros = new javax.swing.JComboBox<>();
        JcmbxTelefonos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pasajeros");
        setResizable(false);

        JPnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JPnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblNombres.setText("Nombre(s)");
        JPnlLienzo.add(JlblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 92, -1));

        JlblApellidoPaterno.setText("Apellido Paterno");
        JPnlLienzo.add(JlblApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 79, 92, -1));

        JlblApellidoMaterno.setText("Apellido Materno");
        JPnlLienzo.add(JlblApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        JtxtNombres.setBorder(null);
        JPnlLienzo.add(JtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 172, -1));

        JtxtApPaterno.setBorder(null);
        JPnlLienzo.add(JtxtApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 172, -1));

        JtxtApMaterno.setBorder(null);
        JtxtApMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtxtApMaternoActionPerformed(evt);
            }
        });
        JPnlLienzo.add(JtxtApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 172, -1));
        JPnlLienzo.add(JsNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, 10));
        JPnlLienzo.add(JsApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 170, 10));
        JPnlLienzo.add(JsApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, 10));

        JbtnEnviar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        JbtnEnviar.setForeground(new java.awt.Color(160, 17, 19));
        JbtnEnviar.setText("Enviar");
        JbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEnviarActionPerformed(evt);
            }
        });
        JPnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 80, -1));

        JcmbxTipoPasajeros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de pasajero", "Adulto", "Niño", "Docente", "Estudiante", "INAPAM" }));
        JPnlLienzo.add(JcmbxTipoPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        JcmbxTelefonos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numeros de Telefono", "1 Telefono", "2 Telefonos", "3 Telefonos", "4 Telefonos", "5 Telefonos" }));
        JcmbxTelefonos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxTelefonosItemStateChanged(evt);
            }
        });
        JcmbxTelefonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxTelefonosActionPerformed(evt);
            }
        });
        JPnlLienzo.add(JcmbxTelefonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEnviarActionPerformed
       if (JcmbxTipoPasajeros.getSelectedIndex() != 0) {
        if (!telefonos.isEmpty()) { // Verifica si telefonos no está vacío
            enviarDatos();
        } else {
            CMensajes.msg_advertencia("Debe ingresar al menos un teléfono", "Registro Usuario");
        }
    } else {
        CMensajes.msg_advertencia("Seleccione un tipo de pasajero", "Registro Usuario");
    }
    }//GEN-LAST:event_JbtnEnviarActionPerformed

    private void JtxtApMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtxtApMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxtApMaternoActionPerformed

    private void JcmbxTelefonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxTelefonosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcmbxTelefonosActionPerformed

    private void JcmbxTelefonosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxTelefonosItemStateChanged
        // TODO add your handling code here:
        telefonos = devuelveTelefonos();
    }//GEN-LAST:event_JcmbxTelefonosItemStateChanged

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
            java.util.logging.Logger.getLogger(JfRegistroPasajeros.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRegistroPasajeros.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRegistroPasajeros.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRegistroPasajeros.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRegistroPasajeros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPnlLienzo;
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JComboBox<String> JcmbxTelefonos;
    private javax.swing.JComboBox<String> JcmbxTipoPasajeros;
    private javax.swing.JLabel JlblApellidoMaterno;
    private javax.swing.JLabel JlblApellidoPaterno;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JSeparator JsApellidoMaterno;
    private javax.swing.JSeparator JsApellidoPaterno;
    private javax.swing.JSeparator JsNombre;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    // End of variables declaration//GEN-END:variables

}
