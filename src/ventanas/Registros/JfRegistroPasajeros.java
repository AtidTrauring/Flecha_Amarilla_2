package ventanas.Registros;

import crud.CInserciones;
import crud.CMensajes;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JfRegistroPasajeros extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultComboBoxModel modeloCombo;
    private final CInserciones queryInserta = new CInserciones();
    private String[] telefonos;
    public ArrayList<String[]> pasajerosInfo = new ArrayList<>();
    public ArrayList<String[]> asientosInfo = new ArrayList<>();
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

    public void asignaAsientos(ArrayList<String[]> asientos) throws SQLException {
        asientosInfo = asientos;
    }

    public void cargaAsientos() {
        remueveAsientos();
        for (String[] asiento : asientosInfo) {
            JcmbxAsientos.addItem(asiento[1]);
        }
    }

    public void remueveAsientos() {
        while (JcmbxAsientos.getItemCount() > 1) {
            JcmbxAsientos.removeItemAt(1);
        }
    }

    public String[] asignaValores() {
        String[] datosPasajero = new String[5];
        nombres = JtxtNombres.getText();
        apPaterno = JtxtApPaterno.getText();
        apMaterno = JtxtApMaterno.getText();
        switch ((String) JcmbxTipoPasajeros.getSelectedItem()) {
            case "Adulto":
                datosPasajero = new String[]{nombres, apPaterno, apMaterno, "A", "0"};
                break;
            case "Niño":
                datosPasajero = new String[]{nombres, apPaterno, apMaterno, "N", "20"};
                break;
            case "Docente":
                datosPasajero = new String[]{nombres, apPaterno, apMaterno, "D", "25"};
                break;
            case "Estudiante":
                datosPasajero = new String[]{nombres, apPaterno, apMaterno, "E", "35"};
                break;
            case "INAPAM":
                datosPasajero = new String[]{nombres, apPaterno, apMaterno, "AT", "30"};
                break;
        }
        return datosPasajero;
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
        JcmbxAsientos.setSelectedIndex(0);
        JrbComercial.setSelected(true);
        JrbPP.setSelected(false);
        cargaAsientos();
    }

    public String devuelveCadena(JTextField campo, String regex) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            return null;
        } else if (!texto.matches(regex)) {
            return "NoValido";
        } else {
            return texto;
        }
    }

    public boolean validaCampo(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        campoTexto = devuelveCadena(campo, regex);

        if (campoTexto == null) {
            CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
            return false;
        } else if (campoTexto.equals("NoValido")) {
            CMensajes.msg_error(mensajeInvalido, "Registro Usuarios");
            return false;
        } else {
            return true;
        }
    }

    public boolean validaCampos() {
        return validaCampo(nombres, JtxtNombres, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)")
                && validaCampo(apPaterno, JtxtApPaterno, regexNombres, "Ingrese un apellido paterno.", "Valores invalidos para apellido paterno")
                && validaCampo(apMaterno, JtxtApMaterno, regexNombres, "Ingrese un apellido materno.", "Valores invalidos para apellido materno");
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
            } else {
                CMensajes.msg_advertencia("Ingrese un numero de telefono valido", "Obtiene Telefono");
            }
        } while (telefono == null || !telefono.matches("^[0-9]{10}$"));
        return telefono;
    }

    public String[] devuelveTelefonos() {
        String[] telefono = null;
        if (JcmbxTelefonos.getSelectedIndex() == 0) {
            CMensajes.msg("Selecciona una cantidad de telefonos", "Registro de pasajeros");
            sinTelefono = true;
            return null;
        } else {
            int cantidad = JcmbxTelefonos.getSelectedIndex();
            telefono = new String[cantidad];
            sinTelefono = false;

            for (int i = 0; i < cantidad; i++) {
                int j = i;
                String mensaje = "Ingrese el número de telefono: ";
                if (i > 0) {
                    mensaje = "Ingrese el " + (j + 1) + "º número de telefono: ";
                }

                telefono[i] = obtieneTelefono(mensaje);
                if (telefono[i] == null) {
                    sinTelefono = true;
                    return null;
                }
            }
        }
        return telefono;
    }

    public String[] asientoBoleto() {
        String asiento = "";
        String opcionA = JcmbxAsientos.getSelectedItem().toString();
        String opcionBoleto = "";
        if (JrbComercial.isSelected()) {
            opcionBoleto = "C";
        } else {
            opcionBoleto = "PP";
        }

        for (int i = 0; i < asientosInfo.size(); i++) {
            String[] asientoEscogido = asientosInfo.get(i);
            if (asientoEscogido[1].equals(opcionA)) {
                asiento = asientoEscogido[0];
                asientosInfo.remove(i);
                break;
            }
        }
        return new String[]{asiento, opcionBoleto};
    }

    public String[] almcenaDatos() {
        String[] datosUnidos = null;
        if (validaCampos()) {
            telefonos = devuelveTelefonos();
            if (telefonos != null) {
                if (sinTelefono == false) {
                    String[] datosObtenidos = asignaValores(); // Tiene 5 campos
                    String[] prueba = asientoBoleto(); // Tiene 2 Campos
                    datosUnidos = new String[datosObtenidos.length + prueba.length + telefonos.length];
                    System.arraycopy(datosObtenidos, 0, datosUnidos, 0, datosObtenidos.length);
                    System.arraycopy(prueba, 0, datosUnidos, datosObtenidos.length, prueba.length);
                    System.arraycopy(telefonos, 0, datosUnidos, datosObtenidos.length + prueba.length, telefonos.length);
                    System.out.println(Arrays.toString(datosUnidos));
                    limpiaValores();
                }
            }
        }
        return datosUnidos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGTiposBoletos = new javax.swing.ButtonGroup();
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
        JcmbxAsientos = new javax.swing.JComboBox<>();
        JlblFondo = new javax.swing.JLabel();
        JrbPP = new javax.swing.JRadioButton();
        JrbComercial = new javax.swing.JRadioButton();

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
        JPnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 80, -1));

        JcmbxTipoPasajeros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de pasajero", "Adulto", "Niño", "Docente", "Estudiante", "INAPAM" }));
        JPnlLienzo.add(JcmbxTipoPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 170, -1));

        JcmbxTelefonos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numeros de Telefono", "1 Telefono", "2 Telefonos", "3 Telefonos", "4 Telefonos", "5 Telefonos" }));
        JPnlLienzo.add(JcmbxTelefonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, -1));

        JcmbxAsientos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asientos" }));
        JPnlLienzo.add(JcmbxAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 110, -1));

        JlblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoUsuariosFondos.jpg"))); // NOI18N
        JPnlLienzo.add(JlblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 220, 250));

        JrbPP.setBackground(new java.awt.Color(255, 255, 255));
        BGTiposBoletos.add(JrbPP);
        JrbPP.setText("Primer Plus");
        JPnlLienzo.add(JrbPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        JrbComercial.setBackground(new java.awt.Color(255, 255, 255));
        BGTiposBoletos.add(JrbComercial);
        JrbComercial.setSelected(true);
        JrbComercial.setText("Comercial");
        JPnlLienzo.add(JrbComercial, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
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
            if (JcmbxAsientos.getSelectedIndex() != 0) {
                if (contador <= numPasajeros) {
                    String[] pasajero = almcenaDatos();
                    if (pasajero == null) {
                    } else {
                        pasajerosInfo.add(pasajero);
                        CMensajes.msg("Registre al siguiente pasajero", "Registro pasajero");
                        contador++;
                        limpiarCampos();
                        if (contador > numPasajeros) {
                            CMensajes.msg("Se guardo la informacion\n de los " + numPasajeros + " pasajeros", "Registro pasajeros");
                            this.dispose();
                        }
                    }
                }
            } else {
                CMensajes.msg_advertencia("Seleccione un asiento", "Registro Usuario");
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
    private javax.swing.ButtonGroup BGTiposBoletos;
    private javax.swing.JPanel JPnlLienzo;
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JComboBox<String> JcmbxAsientos;
    private javax.swing.JComboBox<String> JcmbxTelefonos;
    private javax.swing.JComboBox<String> JcmbxTipoPasajeros;
    private javax.swing.JLabel JlblApellidoMaterno;
    private javax.swing.JLabel JlblApellidoPaterno;
    private javax.swing.JLabel JlblFondo;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JRadioButton JrbComercial;
    private javax.swing.JRadioButton JrbPP;
    private javax.swing.JSeparator JsApellidoMaterno;
    private javax.swing.JSeparator JsApellidoPaterno;
    private javax.swing.JSeparator JsNombre;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    // End of variables declaration//GEN-END:variables

}
