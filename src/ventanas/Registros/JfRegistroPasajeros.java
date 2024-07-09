package ventanas.Registros;

import crud.CInserciones;
import crud.CMensajes;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JfRegistroPasajeros extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private final CInserciones queryInserta = new CInserciones();
    private String[] telefonos;
    public ArrayList<String[]> pasajerosInfo = new ArrayList<>();
    private String nombres, apPaterno, apMaterno;
    private String[] datosPasajero;
    private boolean sinTelefono = false;
    private String regexNombres = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?: [a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)?$";
    public int numPasajeros;
    private int contador = 1;

    public JfRegistroPasajeros() {
        initComponents();
    }

    public int asignaPasajeros(int numeroBoletos) {
        return numPasajeros = numeroBoletos;
    }

    public String[] asignaValores() {
        nombres = JtxtNombres.getText();
        apPaterno = JtxtApPaterno.getText();
        apMaterno = JtxtApMaterno.getText();
        return new String[]{nombres, apPaterno, apMaterno, asignaTipoPasajero()};
    }

    public void limpiaValores() {
        nombres = null;
        apPaterno = null;
        apMaterno = null;
        telefonos = null;
        sinTelefono = false;
    }

    public void limpiarCampos() {
        JtxtNombres.setText(null);
        JtxtApPaterno.setText(null);
        JtxtApMaterno.setText(null);
        JcmbxTelefonos.setSelectedIndex(0);
        JcmbxTipoPasajeros.setSelectedIndex(0);
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

    public String[] devuelveTelefonos() {
        String[] telefono = null;
        if (JcmbxTelefonos.getSelectedIndex() == 0) {
            CMensajes.msg("Selecciona una cantidad de telefonos", "Registro de pasajeros");
            sinTelefono = true;
            return null;
        } else {
            telefono = new String[JcmbxTelefonos.getSelectedIndex()];
            sinTelefono = false;
            for (int i = 1; i <= JcmbxTelefonos.getSelectedIndex(); i++) {
                switch (i) {
                    case 1:
                        telefono[i - 1] = obtieneTelefono("Ingrese el número de telefono: ");
                        if (telefono[i - 1] == null) {
                            i = 10;
                            telefono = null;
                        }
                        break;
                    case 2:
                        telefono[i - 1] = obtieneTelefono("Ingrese el segundo número de telefono: ");
                        if (telefono[i - 1] == null) {
                            i = 10;
                            telefono = null;
                        }
                        break;
                    case 3:
                        telefono[i - 1] = obtieneTelefono("Ingrese el tercer número de telefono: ");
                        if (telefono[i - 1] == null) {
                            i = 10;
                            telefono = null;
                        }
                        break;
                    case 4:
                        telefono[i - 1] = obtieneTelefono("Ingrese el cuarto número de telefono: ");
                        if (telefono[i - 1] == null) {
                            i = 10;
                            telefono = null;
                        }
                        break;
                    case 5:
                        telefono[i - 1] = obtieneTelefono("Ingrese el quinto número de telefono: ");
                        if (telefono[i - 1] == null) {
                            i = 10;
                            telefono = null;
                        }
                        break;
                }
            }
        }
        return telefono;
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

    public String[] almcenaDatos() {
        String[] datosUnidos = null;
        if (validaCampos()) {
            telefonos = devuelveTelefonos();
            if (telefonos != null) {
                if (sinTelefono == false) {
                    String[] datosObtenidos = asignaValores();
                    datosUnidos = new String[datosObtenidos.length + telefonos.length];
                    System.arraycopy(datosObtenidos, 0, datosUnidos, 0, datosObtenidos.length);
                    System.arraycopy(telefonos, 0, datosUnidos, datosObtenidos.length, telefonos.length);
                }
            }
        }
        return datosUnidos;
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
        JlblFondo = new javax.swing.JLabel();

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
        JPnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 80, -1));

        JcmbxTipoPasajeros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de pasajero", "Adulto", "Niño", "Docente", "Estudiante", "INAPAM" }));
        JPnlLienzo.add(JcmbxTipoPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 170, -1));

        JcmbxTelefonos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numeros de Telefono", "1 Telefono", "2 Telefonos", "3 Telefonos", "4 Telefonos", "5 Telefonos" }));
        JPnlLienzo.add(JcmbxTelefonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, -1));

        JlblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoUsuariosFondos.jpg"))); // NOI18N
        JPnlLienzo.add(JlblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 220, 250));

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
            if (contador <= numPasajeros) {
                pasajerosInfo.add(almcenaDatos());
                CMensajes.msg("Registre al siguiente pasajero", "Registro pasajero");
                contador++;
                limpiarCampos();
            } else {
                CMensajes.msg("Se guardo la informacion\n de los " + numPasajeros + " pasajeros", "Registro pasajeros");
            }
        } else {
            CMensajes.msg_advertencia("Seleccione un tipo de pasajero", "Registro Usuario");
        }
    }//GEN-LAST:event_JbtnEnviarActionPerformed

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
    private javax.swing.JLabel JlblFondo;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JSeparator JsApellidoMaterno;
    private javax.swing.JSeparator JsApellidoPaterno;
    private javax.swing.JSeparator JsNombre;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    // End of variables declaration//GEN-END:variables

}
