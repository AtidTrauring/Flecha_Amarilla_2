package ventanas.Registros;

import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JfRegistroCompra extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultComboBoxModel listas;
    private final CCargaCombos queryCarga = new CCargaCombos();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CInserciones queryInserta = new CInserciones();
    private ArrayList<String[]> datosPasajeros = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();
    private String[] telefonos;
    private String nombres, apPaterno, apMaterno, correo;
    private String numeroCuenta, cvv, anioCaducidad;
    private int mes, tipoTarjeta;
    private boolean sinTelefono = false;
    private String regexNombres = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?: [a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)?$", regexCorreo = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public JfRegistroCompra() {
        initComponents();
        JcmbxMeses.setVisible(false);
        JcmbxTipoTarjeta.setVisible(false);

        JtxtNumCuenta.setVisible(false);
        JtxtNumCuenta.setEditable(false);
        JlblNumCuenta.setVisible(false);
        JspNumCuenta.setVisible(false);

        JtxtAnio.setVisible(false);
        JtxtAnio.setEditable(false);
        JlblAnio.setVisible(false);
        JspAnio.setVisible(false);

        JpwsCvv.setVisible(false);
        JpwsCvv.setEditable(false);
        JlblCvv.setVisible(false);
        JspCvv.setVisible(false);

        JtxtCorreo.setVisible(false);
        JtxtCorreo.setEditable(false);
        JlblCorreo.setVisible(false);
        JspCorreo.setVisible(false);
    }

    public ArrayList<String[]> capturaPasajeros(ArrayList<String[]> pasajerosInfo) {
        return datosPasajeros = pasajerosInfo;
    }

    public Object[] asignaValoresTarjeta() {
        numeroCuenta = JtxtNumCuenta.getText();
        cvv = new String(JpwsCvv.getPassword());
        anioCaducidad = JtxtAnio.getText();
        mes = JcmbxMeses.getSelectedIndex();
        tipoTarjeta = JcmbxTipoTarjeta.getSelectedIndex();
        return new Object[]{ };
    }

    public String[] asignaValoresConCorreo() {
        nombres = JtxtNombres.getText();
        apPaterno = JtxtApPaterno.getText();
        apMaterno = JtxtApMaterno.getText();
        correo = JtxtCorreo.getText();
        return new String[]{nombres, apPaterno, apMaterno, correo};
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
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

//    public void limpiaValores() {
//        nombres = null;
//        apPaterno = null;
//        apMaterno = null;
//        correo = null;
//    }
//    public void limpiarCampos() {
//        JtxtNombres.setText(null);
//        JtxtApPaterno.setText(null);
//        JtxtApMaterno.setText(null);
//        JtxtCorreo.setText(null);
//        JcmbxTelefonos.setSelectedIndex(0);
//
//    }
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

    public boolean validaCamposSinCorreo() {
        return validaCampo(nombres, JtxtNombres, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)")
                && validaCampo(apPaterno, JtxtApPaterno, regexNombres, "Ingrerse un apellido paterno.", "Valores invalidos para apellido paterno")
                && validaCampo(apMaterno, JtxtApMaterno, regexNombres, "Ingrese un apellido materno.", "Valores invalidos para apellido materno");
    }

    public boolean validaCamposConCorreo() {
        return validaCampo(nombres, JtxtNombres, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)")
                && validaCampo(apPaterno, JtxtApPaterno, regexNombres, "Ingrerse un apellido paterno.", "Valores invalidos para apellido paterno")
                && validaCampo(apMaterno, JtxtApMaterno, regexNombres, "Ingrese un apellido materno.", "Valores invalidos para apellido materno")
                && validaCampo(correo, JtxtCorreo, regexCorreo, "Ingrese un correo", "Valores invalidos para correo");
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

//    public boolean insertaCliente(){
//        
//    }
    public void insertaPasajero(String[] pasajero) {
        /*
        [0] - > Nombre
        [1] - > Apellido Paterno
        [2] - > Apellido Materno
        [3] - > Tipo de Pasajero
        [4] - > Descuento
        [5] - > ID Asiento
        [6] - > Tipo de Boleto (Primera Plus[PP], Comercial [C])
        [7] - > ID Ruta
        [8] - > Telefonos....
        [...] - > Teleefono(n)
         */
        try {
            String idPersonaS = queryBusca.buscaPersona(pasajero[0], pasajero[1], pasajero[2]);
            int idPersona = 0;
            if (idPersonaS == null) {
                idPersona = queryBusca.obtenIdFinalPersona() + 1;
                queryInserta.insertaPersona(idPersona, nombres, apPaterno, apMaterno);
            } else {
                idPersona = Integer.parseInt(idPersonaS);
                for (int i = 8; i < pasajero.length; i++) {
                    String idTelefonoS = queryBusca.buscaTelefono(pasajero[i], idPersona);
                    int idTelefono = 0;
                    if (idTelefonoS == null) {
                        idTelefono = queryBusca.obtenIdFinalTelefono() + 1;
                        queryInserta.insertaTelefonos(idTelefono, pasajero[i], idPersona);
                    }
                }
                String idPasajeroS = queryBusca.buscaPasajero(idPersona);
                int idPasajero = 0;
                if (idPasajeroS == null) {
                    idPasajero = queryBusca.obtenIdFinalPasajero() + 1;
                    queryInserta.insertaPasajeros(idPasajero, pasajero[3], Integer.parseInt(pasajero[4]), idPersona);
                } else {
                    idPasajero = Integer.parseInt(idPasajeroS);
                    // Inserta boleto
                }
            }
        } catch (Exception e) {
        }
    }

    public void insertaCliente(String[] cliente, String[] telefonos) {
        /*
        [0] - > Nombre
        [1] - > Apellido Paterno
        [2] - > Apellido Materno
        [3] - > Correo
        [4] - > Telefonos....
        [...] - > Teleefono(n)
         */
        try {
            String idPersonaS = queryBusca.buscaPersona(cliente[0], cliente[1], cliente[2]);
            int idPersona = 0;
            if (idPersonaS == null) {
                idPersona = queryBusca.obtenIdFinalPersona() + 1;
                queryInserta.insertaPersona(idPersona, nombres, apPaterno, apMaterno);
            } else {
                idPersona = Integer.parseInt(idPersonaS);
                for (int i = 0; i < telefonos.length; i++) {
                    String idTelefonoS = queryBusca.buscaTelefono(telefonos[i], idPersona);
                    int idTelefono = 0;
                    if (idTelefonoS == null) {
                        idTelefono = queryBusca.obtenIdFinalTelefono() + 1;
                        queryInserta.insertaTelefonos(idTelefono, telefonos[i], idPersona);
                    }
                }
                String idClienteS = queryBusca.buscaCliente(idPersona);
                int idCliente = 0;
                if (idClienteS == null) {
                    idCliente = queryBusca.obtenIdFinalCliente() + 1;
                    queryInserta.insertaClientes(idCliente, cliente[3], idPersona);
                } else {
                    idCliente = Integer.parseInt(idClienteS);
                }
            }
        } catch (Exception e) {
        }
    }

//    public void insertaBoleto() //    public boolean insertaMetodoDePago(){
    //        
    //    }
    public void enviarDatosCliente() {
        boolean exito = false;
        boolean exitoC = false;
        if (JrbLinea.isSelected()) {
            if (validaCamposConCorreo()) {
                telefonos = devuelveTelefonos();
                if (telefonos != null) {
                    if (sinTelefono == false) {
                        String[] valoresObtenidos = asignaValoresConCorreo();
                        try {
                            for (String[] datosPasajero : datosPasajeros) {
                                // Inserta a la persona-pasajero "i"
                                if (queryInserta.insertaPersona((queryBusca.obtenIdFinalPersona() + 1), datosPasajero[0], datosPasajero[1], datosPasajero[2])) {
                                    // Se inserto a la persona-pasajero
                                    System.out.println("Se inserto al pasajero");
                                    for (int i = 5; i <= datosPasajero.length; i++) {
                                        // Inserta los telefonos necesarios
                                        if (queryInserta.insertaTelefonos((queryBusca.obtenIdFinalTelefono() + 1), datosPasajero[i], queryBusca.obtenIdFinalPersona())) {
                                            // Mensaje se inserto el valor de manera correcta
                                            System.out.println("Se inserto el telefono del pasajero");
                                            exito = true;
                                        } else {
                                            // Ocurrio un error al insertar el telefono 
                                            System.out.println("No se pudo insertar el telefono");
                                            exito = false;
//                                            break;
                                        }

                                    }
                                    if (queryInserta.insertaPasajeros((queryBusca.obtenIdFinalPasajero() + 1), datosPasajero[3], Integer.parseInt(datosPasajero[4]), queryBusca.obtenIdFinalPersona())) {
                                        System.out.println("Se pudo insertar al pasajero en PASAJERO");
                                        // Mensaje de inserta el pasajero de manera correcta
                                        if (exito) {
                                            CMensajes.msg("Se registraron los pasajeros", correo);
                                        } else {
                                            CMensajes.msg_error("No se pudieron registrar a los pasajeros", correo);
                                        }
                                    } else {
                                        System.out.println("No se pudo insertar al pasajero en PASAJERO");
                                        // Ocurrio un error al insertar la informacion del pasajero
                                    }
                                } else {
                                    // No se pudo insertar a la persona-pasajero
                                    System.out.println("No se pudo insertar a la persona-pasajero");
                                }
                            }
                            if (queryInserta.insertaPersona((queryBusca.obtenIdFinalPersona() + 1), valoresObtenidos[0], valoresObtenidos[1], valoresObtenidos[2])) {
                                System.out.println("Se inserto a la persona-cliente");
                                // Se inserto a la persona-cliente
                                if (queryInserta.insertaClientes((queryBusca.obtenIdFinalCliente() + 1), valoresObtenidos[3], queryBusca.obtenIdFinalPersona())) {
                                    System.out.println("Se inserto al cliente en CLIENTES");
                                    // Se inserto el cliente
                                    for (int i = 0; i < telefonos.length; i++) {
                                        // Recorriendo el arreglo, insertamos todos los telefonos que se tengan
                                        if (queryInserta.insertaTelefonos((queryBusca.obtenIdFinalTelefono() + 1), telefonos[i], queryBusca.obtenIdFinalPersona())) {
                                            System.out.println("Se inserto el telefono del cliente");
                                            exitoC = true;
                                            // Se inserto el telefono del cliente
                                        } else {
                                            System.out.println("No se pudo insertar el telefono del cliente");
                                            // No se inserto el telefono del cliente
                                            exitoC = false;
                                        }
                                    }
                                    if (exitoC) {
                                        CMensajes.msg("Se registro al cliente", correo);
                                    } else {
                                        CMensajes.msg_error("No se pudo registrar al cliente", correo);
                                    }
                                } else {
                                    // No se pudo insertar el cliente
                                    System.out.println("No se pudo insertar al cliente en CLIENTES");
                                }
                            } else {
                                // No se pudo insertar a la persona-cliente
                                System.out.println("No se pudo insertar a la persona-cliente");
                            }
                        } catch (SQLException ex) {

                        } finally {
//                            limpiaValores();
                        }
                        this.dispose();
                    }
                } else {
                    CMensajes.msg_advertencia("Faltaron datos", "Compra");
                }
            }
        } else if (JrbTarjeta.isSelected()) {

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
        JcmbxTipoTarjeta = new javax.swing.JComboBox<>();
        JcmbxTelefonos = new javax.swing.JComboBox<>();
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
        JlblAnio = new javax.swing.JLabel();
        JtxtAnio = new javax.swing.JTextField();
        JspAnio = new javax.swing.JSeparator();
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
        JpnlLienzo.add(JlblNumCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        JtxtNumCuenta.setBorder(null);
        JpnlLienzo.add(JtxtNumCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 130, -1));
        JpnlLienzo.add(JspNumCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 130, 10));

        JlblCvv.setText("CVV");
        JpnlLienzo.add(JlblCvv, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        JpwsCvv.setBorder(null);
        JpnlLienzo.add(JpwsCvv, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 130, -1));
        JpnlLienzo.add(JspCvv, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 130, 10));

        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mes de Caducidad" }));
        JpnlLienzo.add(JcmbxMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 140, -1));

        JcmbxTipoTarjeta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo deTarjeta" }));
        JpnlLienzo.add(JcmbxTipoTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 140, -1));

        JcmbxTelefonos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numeros de Telefono", "1 Telefono", "2 Telefonos", "3 Telefonos", "4 Telefonos", "5 Telefonos" }));
        JpnlLienzo.add(JcmbxTelefonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 170, -1));

        BgMetodoDePago.add(JrbEfectivo);
        JrbEfectivo.setSelected(true);
        JrbEfectivo.setText("Efectivo");
        JpnlLienzo.add(JrbEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));

        BgMetodoDePago.add(JrbTarjeta);
        JrbTarjeta.setText("Tarjeta");
        JrbTarjeta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JrbTarjetaItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JrbTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, -1));

        BgTipoCompra.add(JrbVentanilla);
        JrbVentanilla.setSelected(true);
        JrbVentanilla.setText("Ventanilla");
        JpnlLienzo.add(JrbVentanilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        BgTipoCompra.add(JrbLinea);
        JrbLinea.setText("Linea");
        JrbLinea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JrbLineaItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JrbLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        JlblCorreo.setText("Correo");
        JpnlLienzo.add(JlblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        JtxtCorreo.setBorder(null);
        JpnlLienzo.add(JtxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 130, -1));
        JpnlLienzo.add(JspCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 130, 10));

        JbtnFinalizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnFinalizar.setText("Finalizar");
        JbtnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnFinalizarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, -1, -1));

        JlblPagoPronto.setText("Pago pronto");
        JpnlLienzo.add(JlblPagoPronto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        JtxtCantidadPago.setEditable(false);
        JpnlLienzo.add(JtxtCantidadPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 140, -1));

        JlblAnio.setText("Año de Caducidad");
        JpnlLienzo.add(JlblAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        JtxtAnio.setBorder(null);
        JpnlLienzo.add(JtxtAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 130, -1));
        JpnlLienzo.add(JspAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 130, 10));

        JlblFondoCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoCompra.jpeg"))); // NOI18N
        JpnlLienzo.add(JlblFondoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 200, 120));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JrbLineaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JrbLineaItemStateChanged
        JtxtCorreo.setVisible(JrbLinea.isSelected());
        JtxtCorreo.setEditable(JrbLinea.isSelected());
        JlblCorreo.setVisible(JrbLinea.isSelected());
        JspCorreo.setVisible(JrbLinea.isSelected());
    }//GEN-LAST:event_JrbLineaItemStateChanged

    private void JrbTarjetaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JrbTarjetaItemStateChanged
        JcmbxMeses.setVisible(JrbTarjeta.isSelected());
        cargaComboBox(JcmbxMeses, 2);
        JcmbxTipoTarjeta.setVisible(JrbTarjeta.isSelected());
        cargaComboBox(JcmbxTipoTarjeta, 3);

        JtxtNumCuenta.setVisible(JrbTarjeta.isSelected());
        JtxtNumCuenta.setEditable(JrbTarjeta.isSelected());
        JlblNumCuenta.setVisible(JrbTarjeta.isSelected());
        JspNumCuenta.setVisible(JrbTarjeta.isSelected());

        JpwsCvv.setVisible(JrbTarjeta.isSelected());
        JpwsCvv.setEditable(JrbTarjeta.isSelected());
        JlblCvv.setVisible(JrbTarjeta.isSelected());
        JspCvv.setVisible(JrbTarjeta.isSelected());

        JtxtAnio.setVisible(JrbTarjeta.isSelected());
        JtxtAnio.setEditable(JrbTarjeta.isSelected());
        JlblAnio.setVisible(JrbTarjeta.isSelected());
        JspAnio.setVisible(JrbTarjeta.isSelected());
    }//GEN-LAST:event_JrbTarjetaItemStateChanged

    private void JbtnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnFinalizarActionPerformed
        CMensajes.msg("La compra se realizo con exito", "Compra");
        this.dispose();
    }//GEN-LAST:event_JbtnFinalizarActionPerformed

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
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JComboBox<String> JcmbxTelefonos;
    private javax.swing.JComboBox<String> JcmbxTipoTarjeta;
    private javax.swing.JLabel JlblAnio;
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
    private javax.swing.JSeparator JspAnio;
    private javax.swing.JSeparator JspApMaterno;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspCorreo;
    private javax.swing.JSeparator JspCvv;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JSeparator JspNumCuenta;
    private javax.swing.JTextField JtxtAnio;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtCantidadPago;
    private javax.swing.JTextField JtxtCorreo;
    private javax.swing.JTextField JtxtNombres;
    private javax.swing.JTextField JtxtNumCuenta;
    // End of variables declaration//GEN-END:variables
}
