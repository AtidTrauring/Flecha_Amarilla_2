package ventanas.Registros;

import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JfRegistroCompra extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultComboBoxModel listas;
    private final CCargaCombos queryCarga = new CCargaCombos();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CInserciones queryInserta = new CInserciones();
    private ArrayList<String[]> datosPasajeros = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();
    private String nombres, apPaterno, apMaterno, correo;
    private String regexNombres = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?: [a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)?$", regexCorreo = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public JfRegistroCompra() {
        initComponents();
        JcmbxAnios.setVisible(false);
        JcmbxMeses.setVisible(false);
        JcmbxTipoTarjeta.setVisible(false);

        JtxtNumCuenta.setVisible(false);
        JtxtNumCuenta.setEditable(false);
        JlblNumCuenta.setVisible(false);
        JspNumCuenta.setVisible(false);

        JpwsCvv.setVisible(false);
        JpwsCvv.setEditable(false);
        JlblCvv.setVisible(false);
        JspCvv.setVisible(false);

        JtxtCorreo.setVisible(false);
        JtxtCorreo.setEditable(false);
        JlblCorreo.setVisible(false);
        JspCorreo.setVisible(false);
    }

    public ArrayList<String[]> capturaPasajero3s(ArrayList<String[]> pasajerosInfo) {
        return datosPasajeros = pasajerosInfo;
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    ArrayList<String[]> Anios = queryCarga.cargaComboAnios();
                    for (String[] Anio : Anios) {
                        listas.addElement(Anio[1]);
                    }
                    Anios.clear();
                    break;
                case 2:
                    ArrayList<String[]> Meses = queryCarga.cargaComboMeses();
                    for (String[] Mes : Meses) {
                        listas.addElement(Mes[1]);
                    }
                    Meses.clear();
                    break;
                case 3:
                    datosListas = queryCarga.cargaComboTTarjetas();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }

        } catch (SQLException e) {
            CMensajes.msg_error("Ocurrio un error al cargar la lista", "Metodo de pago");
        }
    }

    public String[] asignaValores() {
        nombres = JtxtNombres.getText();
        apPaterno = JtxtApPaterno.getText();
        apMaterno = JtxtApMaterno.getText();
        return new String[]{nombres, apPaterno, apMaterno};
    }

    public String[] asignaValoresConCorreo() {
        nombres = JtxtNombres.getText();
        apPaterno = JtxtApPaterno.getText();
        apMaterno = JtxtApMaterno.getText();
        correo = JtxtCorreo.getText();
        return new String[]{nombres, apPaterno, apMaterno, correo};
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

    public boolean validaCamposSinCorreo() {
        return validaCampo(nombres, JtxtNombres, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)")
                || validaCampo(apPaterno, JtxtApPaterno, regexNombres, "Ingrerse un apellido paterno.", "Valores invalidos para apellido paterno")
                || validaCampo(apMaterno, JtxtApMaterno, regexNombres, "Ingrese un apellido materno.", "Valores invalidos para apellido materno");
    }

    public boolean validaCamposConCorreo() {
        return validaCampo(nombres, JtxtNombres, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)")
                || validaCampo(apPaterno, JtxtApPaterno, regexNombres, "Ingrerse un apellido paterno.", "Valores invalidos para apellido paterno")
                || validaCampo(apMaterno, JtxtApMaterno, regexNombres, "Ingrese un apellido materno.", "Valores invalidos para apellido materno")
                || validaCampo(correo, JtxtCorreo, regexCorreo, "Ingrese un correo", "Valores invalidos para correo");
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

    public void enviarDatosCliente() {
        if (JrbLinea.isSelected()) {
            if (validaCamposConCorreo()) {
                String[] valoresObtenidos = asignaValores();
                try {
                    for (String[] datosPasajero : datosPasajeros) {
                        if (queryInserta.insertaPersona((queryBusca.obtenIdFinalPersona() + 1), nombres, apPaterno, apMaterno)) {
                            // Mensaje "Se logro"
                            for (int i = 4; i <= datosPasajero.length; i++) {
                                // Inserta los telefonos necesarios
                                if (queryInserta.insertaTelefonos((queryBusca.obtenIdFinalTelefono() + 1), datosPasajero[i], ERROR)) {

                                }

                            }

                            /* Añadimos datos al modelo de la tabla Hacemos la seleccion con respecto al tipo de pasajero que sea /*
                            N -> Niño
                            A -> Adulto
                            D -> Docente
                            E -> Estudiante
                            AT -> INAPAM
                             */
                            switch (datosPasajero[3]) {
                                case "N":
                                    queryInserta.insertaPasajeros((queryBusca.obtenIdFinalPasajero() + 1), "N", 20, queryBusca.obtenIdFinalPersona());
                                    break;
                                case "A":
                                    queryInserta.insertaPasajeros((queryBusca.obtenIdFinalPasajero() + 1), "A", 0, queryBusca.obtenIdFinalPersona());
                                    break;
                                case "D":
                                    queryInserta.insertaPasajeros((queryBusca.obtenIdFinalPasajero() + 1), "D", 25, queryBusca.obtenIdFinalPersona());
                                    break;
                                case "E":
                                    queryInserta.insertaPasajeros((queryBusca.obtenIdFinalPasajero() + 1), "E", 35, queryBusca.obtenIdFinalPersona());
                                    break;
                                case "AT":
                                    queryInserta.insertaPasajeros((queryBusca.obtenIdFinalPasajero() + 1), "AT", 30, queryBusca.obtenIdFinalPersona());
                                    break;
                            }
                        }
                    }
                    CMensajes.msg("Usuario Registrado", "Registro Usuarios");
                } catch (SQLException ex) {

                } finally {

                }
                this.dispose();
            }
        } else {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BgMetodoDePago = new javax.swing.ButtonGroup();
        BgTipoCompra = new javax.swing.ButtonGroup();
        JpnlLienzo = new javax.swing.JPanel();
        JlblNombres = new javax.swing.JLabel();
        JtxtNombres = new javax.swing.JTextField();
        JspNombres = new javax.swing.JSeparator();
        JlblApPaterno = new javax.swing.JLabel();
        JtxtApPaterno = new javax.swing.JTextField();
        JspApPaterno = new javax.swing.JSeparator();
        JlblApMaterno = new javax.swing.JLabel();
        JtxtApMaterno = new javax.swing.JTextField();
        JspApMaterno = new javax.swing.JSeparator();
        JlblNumCuenta = new javax.swing.JLabel();
        JtxtNumCuenta = new javax.swing.JTextField();
        JspNumCuenta = new javax.swing.JSeparator();
        JlblCvv = new javax.swing.JLabel();
        JpwsCvv = new javax.swing.JPasswordField();
        JspCvv = new javax.swing.JSeparator();
        JcmbxMeses = new javax.swing.JComboBox<>();
        JcmbxAnios = new javax.swing.JComboBox<>();
        JcmbxTipoTarjeta = new javax.swing.JComboBox<>();
        JrbEfectivo = new javax.swing.JRadioButton();
        JrbTarjeta = new javax.swing.JRadioButton();
        JrbVentanilla = new javax.swing.JRadioButton();
        JrbLinea = new javax.swing.JRadioButton();
        JlblCorreo = new javax.swing.JLabel();
        JtxtCorreo = new javax.swing.JTextField();
        JspCorreo = new javax.swing.JSeparator();
        JbtnFinalizar = new javax.swing.JButton();
        JlblPagoPronto = new javax.swing.JLabel();
        JtxtCantidadPago = new javax.swing.JTextField();
        JlblFondoCompra = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de compra");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblNombres.setText("Nombre(s)");
        JpnlLienzo.add(JlblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        JtxtNombres.setBorder(null);
        JpnlLienzo.add(JtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, 130, -1));
        JpnlLienzo.add(JspNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 50, 130, 10));

        JlblApPaterno.setText("Apellido Paterno");
        JpnlLienzo.add(JlblApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 70, -1, -1));

        JtxtApPaterno.setBorder(null);
        JpnlLienzo.add(JtxtApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 92, 130, -1));
        JpnlLienzo.add(JspApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 114, 130, 10));

        JlblApMaterno.setText("Apellido Materno");
        JpnlLienzo.add(JlblApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 130, -1, -1));

        JtxtApMaterno.setBorder(null);
        JpnlLienzo.add(JtxtApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 152, 130, -1));
        JpnlLienzo.add(JspApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 174, 130, 10));

        JlblNumCuenta.setText("Numero de cuenta");
        JpnlLienzo.add(JlblNumCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        JtxtNumCuenta.setBorder(null);
        JpnlLienzo.add(JtxtNumCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 130, -1));
        JpnlLienzo.add(JspNumCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 130, 10));

        JlblCvv.setText("CVV");
        JpnlLienzo.add(JlblCvv, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        JpwsCvv.setBorder(null);
        JpnlLienzo.add(JpwsCvv, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 130, -1));
        JpnlLienzo.add(JspCvv, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 130, 10));

        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mes de Caducidad" }));
        JpnlLienzo.add(JcmbxMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        JcmbxAnios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Año de Caducidad" }));
        JpnlLienzo.add(JcmbxAnios, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        JcmbxTipoTarjeta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo deTarjeta" }));
        JpnlLienzo.add(JcmbxTipoTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 130, -1));

        BgMetodoDePago.add(JrbEfectivo);
        JrbEfectivo.setSelected(true);
        JrbEfectivo.setText("Efectivo");
        JpnlLienzo.add(JrbEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        BgMetodoDePago.add(JrbTarjeta);
        JrbTarjeta.setText("Tarjeta");
        JpnlLienzo.add(JrbTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        BgTipoCompra.add(JrbVentanilla);
        JrbVentanilla.setSelected(true);
        JrbVentanilla.setText("Ventanilla");
        JpnlLienzo.add(JrbVentanilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        BgTipoCompra.add(JrbLinea);
        JrbLinea.setText("Linea");
        JpnlLienzo.add(JrbLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        JlblCorreo.setText("Correo");
        JpnlLienzo.add(JlblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        JtxtCorreo.setBorder(null);
        JpnlLienzo.add(JtxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 130, -1));
        JpnlLienzo.add(JspCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 130, 10));

        JbtnFinalizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnFinalizar.setText("Finalizar");
        JpnlLienzo.add(JbtnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        JlblPagoPronto.setText("Pago pronto");
        JpnlLienzo.add(JlblPagoPronto, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        JtxtCantidadPago.setEditable(false);
        JpnlLienzo.add(JtxtCantidadPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 140, -1));

        JlblFondoCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoCompra.jpeg"))); // NOI18N
        JpnlLienzo.add(JlblFondoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 190, 120));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JfRegistroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRegistroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRegistroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRegistroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRegistroCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BgMetodoDePago;
    private javax.swing.ButtonGroup BgTipoCompra;
    private javax.swing.JButton JbtnFinalizar;
    private javax.swing.JComboBox<String> JcmbxAnios;
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JComboBox<String> JcmbxTipoTarjeta;
    private javax.swing.JLabel JlblApMaterno;
    private javax.swing.JLabel JlblApPaterno;
    private javax.swing.JLabel JlblCorreo;
    private javax.swing.JLabel JlblCvv;
    private javax.swing.JLabel JlblFondoCompra;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JLabel JlblNumCuenta;
    private javax.swing.JLabel JlblPagoPronto;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JPasswordField JpwsCvv;
    private javax.swing.JRadioButton JrbEfectivo;
    private javax.swing.JRadioButton JrbLinea;
    private javax.swing.JRadioButton JrbTarjeta;
    private javax.swing.JRadioButton JrbVentanilla;
    private javax.swing.JSeparator JspApMaterno;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspCorreo;
    private javax.swing.JSeparator JspCvv;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JSeparator JspNumCuenta;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtCantidadPago;
    private javax.swing.JTextField JtxtCorreo;
    private javax.swing.JTextField JtxtNombres;
    private javax.swing.JTextField JtxtNumCuenta;
    // End of variables declaration//GEN-END:variables
}
