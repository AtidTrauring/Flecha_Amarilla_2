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
import javax.swing.JTextField;

/**
 *
 * @author gelog
 */
public class JfRegistroAutobuses extends javax.swing.JFrame {

    /**
     * Creates new form JfRegistroAutobuses
     */
    public JfRegistroAutobuses() {
        initComponents();
          cargaComboBox(JcmbxMeses, 1);
        
        JcmbxDia.setVisible(false);
       
    }

    
    
    
    
    private CInserciones queryInserta = new CInserciones();
    private CBusquedas queryBusca = new CBusquedas();
    private CEliminaciones queryElimina = new CEliminaciones();
    private CActualizaciones queryActualiza = new CActualizaciones();
      private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String> telefonos = new ArrayList<>();
    private DefaultComboBoxModel listas;
    private ArrayList<String> datosListas = new ArrayList<>();
    private String placa,capacidad,marca,modelo,numeroEconomico,AñoEmision,diaRegistro,AñoRegistro,mes;
    private String regexPlaca = "\"^[a-zA-Z0-9]+$\"";
    private String regexCapacidad = "^\\d{2}$";
    private String regexmarca = "^[a-zA-Z ]{1,50}$";
    private String regexmodelo = "^[a-zA-Z0-9 ]{1,80}$";
    private String regexnumeroEconomico = "^\\d{1,8}$";
    private String regexañoEmision = "^\\d{1,4}$";
    private String regexdiaRegistro = "^(0?[1-9]|[1-2][0-9]|3[0-1])$";
    private String regexañoRegistro = "^\\d{1,4}$";
    
    
     public void asignaValores() {
        placa = JtxtPlaca.getText();
        capacidad = JtxtCapacidad.getText();
        marca = JtxtMarca.getText();
        modelo = JtxtModelo.getText();
        numeroEconomico = JtxtNumEconomico.getText();
        AñoEmision = JtxtAnioE.getText();
        diaRegistro = JcmbxDia.getSelectedItem().toString();
        mes = (String)JcmbxMeses.getSelectedItem();
        AñoRegistro = JtxtAnioR.getText();
    }
     
     public void limpiaValores() {
        placa = null;
        capacidad = null;
        marca = null;
        modelo = null;
        numeroEconomico = null;
        AñoEmision = null;
        diaRegistro = null;
        AñoRegistro = null;
    }
     
     
     public String devuelveCadena(JTextField campo, String regex) {
        String cadena = null;
        cadena = campo.getText();
        if (cadena.isEmpty()) {
            cadena = null;
        } else if (cadena.matches(regex)) {
            return cadena;
        } else {
            cadena = "NoValido";
        }
        return cadena;
    }
    
     
     
    //asignamos valores al combo box
      public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaComboMes();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }
        } catch (SQLException e) {
        }
    }
      
         public void cargaComboBoxDia(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    
                   
                    for (int i = 1; i <= 30; i++) {
                        listas.addElement(i);
                        System.out.println(i);
                    }

                    datosListas.clear();
                    break;

                case 2:

                    for (int i = 1; i <= 31; i++) {
                        listas.addElement(i);
                        System.out.println(i);
                    }

                    datosListas.clear();
                    break;
                    
                     case 3:

                    for (int i = 1; i <= 28; i++) {
                        listas.addElement(i);
                        System.out.println(i);
                    }

                    datosListas.clear();
                    break;
            }
        } catch (Exception e) {
        }
    }
      
    public boolean validaMes(String campoTexto, JComboBox<String> comboBox, String mensajeVacio) {
    boolean valida = true;
    
    campoTexto = (String) comboBox.getSelectedItem(); // Obtener el texto seleccionado del JComboBox
    
    if (campoTexto.equals("Selecciona una opcion")) {
        CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
        valida = false;
    } 
    return valida;
}
    
    
    public int validaDia( JComboBox<String> comboBox) {
    int opcion = 0;
    String campoTexto;
    campoTexto = (String) comboBox.getSelectedItem(); // Obtener el texto seleccionado del JComboBox
    
    if (campoTexto.equals("Abril") || campoTexto.equals("Junio") || campoTexto.equals("Septiembre") || campoTexto.equals("Noviembre")) {
        opcion=1;
        
         return opcion;
       // CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
        
    } 
     if (campoTexto.equals("Febrero") ) {
        opcion=3;
        
             return opcion;
        //CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
        
    }else{
     opcion=2;
          return opcion;
     }
    
}
      
     
      
        public boolean validaCampo(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadena(campo, regex);
        if (campoTexto == null) {
            CMensajes.msg_advertencia(mensajeVacio, "Registro Autobus");
            valida = false;
        } else if (campoTexto.equals("NoValido")) {
            CMensajes.msg_error(mensajeInvalido, "Registro Autobus");
            valida = false;
        } else {
            valida = true;
        }
        return valida;
    }
         
             public boolean validaCombo(String campoTexto, JComboBox<String> comboBox, String mensajeVacio) {
    boolean valida = true;
    
    campoTexto =  (String) comboBox.getSelectedItem(); // Obtener el texto seleccionado del JComboBox
    
    if (campoTexto.equals("Selecciona una opcion")) {
        CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
        valida = false;
    } 
    return valida;
}
             
             
         
         
       
         
              public boolean validaCampos() {
    return //validaCampo(placa, JtxtPlaca, regexPlaca, "Ingrese la placa", "Placa invalida")
          validaCampo(capacidad, JtxtCapacidad, regexCapacidad, "Ingrese la capacidad", "Capacidad invalida")
        && validaCampo(marca, JtxtMarca, regexmarca, "Ingrese la marca", "marca invalida")
        && validaCampo(modelo, JtxtModelo, regexmodelo, "Ingrese el modelo", "modelo invalida")
        && validaCampo(numeroEconomico, JtxtNumEconomico, regexnumeroEconomico, "Ingrese el numero economico", "numero invalido")
        && validaCampo(AñoEmision, JtxtAnioE, regexañoEmision, "Ingrese el año de emision", "numero invalido")
        && validaCampo(AñoRegistro, JtxtAnioR, regexañoRegistro, "Ingrese el año de registro", "numero invalido")
        && validaCombo(mes, JcmbxMeses, "Ingresa la marca");
        //&& validaCombo(diaRegistro, JcmbxDia, "Ingresa el dia");
    }
    
              
        public boolean BuscaAño(JTextField campo) {
        boolean buscar = false;
        int año = 0;
        try {
            año = Integer.parseInt(campo.getText());
            int idAño = queryBusca.obtenIdBuscaAño(año);

            if (idAño > 0) {

               // CMensajes.msg_error("El año ya esta registrado", "Registro año");
                // Si se encontró el año en la base de datos
                buscar = true;

            } else {
                // Si no se encontró el año
                buscar = false;
            }
        } catch (SQLException e) {
            System.out.println("error en busca año");
        }

        return buscar;
    }
        
        
        
        
         public boolean BuscaNumEconomico(JTextField campo) {
        boolean buscar = false;
        String numEconomico = "";
        try {
            numEconomico = campo.getText();
            int idAño = queryBusca.obtenIdBuscaNunEconomico(numEconomico);

            if (idAño > 0) {

              
                // Si se encontró el año en la base de datos
                buscar = true;

            } else {
                // Si no se encontró el año
                buscar = false;
            }
        } catch (SQLException e) {
            System.out.println("error en busca numero economico");
        }

        return buscar;
    }
         
         
             public boolean BuscaMarca(JTextField campo) {
        boolean buscar = false;
        String marca = "";
        try {
            marca = campo.getText();
            int idAño = queryBusca.obtenIdBuscaMarca(marca);

            if (idAño > 0) {

                //CMensajes.msg_error("La marca ya esta registrado", "Registro marca");
                // Si se encontró el año en la base de datos
                buscar = true;

            } else {
                // Si no se encontró el año
                buscar = false;
            }
        } catch (SQLException e) {
            System.out.println("error en busca marca");
        }

        return buscar;
    }
             
             
        public boolean BuscaModelo(JTextField campo) {
        boolean buscar = false;
        String modelo = "";
        try {
            modelo = campo.getText();
            int idAño = queryBusca.obtenIdBuscaModelo(modelo);

            if (idAño > 0) {

                //CMensajes.msg_error("El modelo ya esta registrado", "Registro modelo");
                // Si se encontró el año en la base de datos
                buscar = true;

            } else {
                // Si no se encontró el año
                buscar = false;
            }
        } catch (SQLException e) {
            System.out.println("error en busca modelo");
        }

        return buscar;
    }
        
        
         public boolean BuscaPlaca(JTextField campo) {
        boolean buscar = false;
        String placa = "";
        try {
            placa = campo.getText();
            int idAño = queryBusca.obtenIdBuscaPlaca(placa);

            if (idAño > 0) {

                //CMensajes.msg_error("La placa ya esta registrada", "Registro placa");
                // Si se encontró el año en la base de datos
                buscar = true;

            } else {
                // Si no se encontró el año
                buscar = false;
            }
        } catch (SQLException e) {
            System.out.println("error en busca placa");
        }

        return buscar;
    }
              
              
              
              
       
      public void enviarDatos() {
        int idAutobus, idFecha, idMes, idModelo, idAño, idMarca, idBuscaMarca;
        //int precio1=Integer.parseInt(precio);
        if (validaCampos()) {
            //telefonos = devuelveTelefonos();
            //if (telefonos != null) {
            // if (sinTelefono == false) {
            asignaValores();
            try {
                idAño = queryBusca.obtenIdFinalAño();
                idMarca = queryBusca.obtenIdFinalMarca();
                idBuscaMarca = queryBusca.obtenIdBuscaMarca(marca);
                idModelo = queryBusca.obtenIdFinalModelo();
                idAutobus = queryBusca.obtenIdFinalAutobus();
                System.out.println("id año maximo:" + idAño);

                if (BuscaAño(JtxtAnioE)) {
                    CMensajes.msg_error("El año ya esta registrado", "Registro año");
                    System.out.println("el año de existencia ya esta registrado");
                } else {
                    int AñoEmi = Integer.parseInt(AñoEmision);
                     queryInserta.insertaAnio(idAño + 1, AñoEmi);
                    System.out.println("id año maximo:" + AñoEmi);
                    System.out.println("se inserto correcrtamente el año");
                }

                if (BuscaAño(JtxtAnioR)) {
                    System.out.println("el año de registro ya esta registrado");
                } else {
                    int AñoRegi = Integer.parseInt(AñoRegistro);
                    queryInserta.insertaAnio(idAño + 1, AñoRegi);
                    System.out.println("id año maximo:" + AñoRegi);
                    System.out.println("se inserto correcrtamente el año");
                }

                if (BuscaMarca(JtxtMarca)) {
                    System.out.println("el año ya esta registrado");
                    CMensajes.msg_error("La marca ya esta registrado", "Registro marca");
                } else {
                    int AñoEmi = Integer.parseInt(AñoEmision);
                    queryInserta.insertaMarca(idMarca + 1, marca);
                    System.out.println("id marca:" + idMarca);
                    System.out.println("se inserto correcrtamente la marca");
                }

                if (BuscaModelo(JtxtModelo)) {
                    System.out.println("el año ya esta registrado");
                } else {
                    int AñoEmi = Integer.parseInt(AñoEmision);
                    queryInserta.insertaMadelo(idModelo + 1, marca, idBuscaMarca);
                    System.out.println("id año maximo:" + AñoEmi);
                    System.out.println("se inserto correcrtamente el modelo");
                }

                if (BuscaPlaca(JtxtPlaca)) {
                    System.out.println("La placa ya esta registrada");
                    CMensajes.msg_error("La placa ya esta registrada", "Registro placa");

                } else {

                }

                if (BuscaNumEconomico(JtxtNumEconomico)) {
                    CMensajes.msg_error("El numero economico ya esta registrado", "Registro numero economico");
                    System.out.println("el año ya esta registrado");
                } else {
                    int AñoEmi = Integer.parseInt(AñoEmision);
                    System.out.println("se inserto correcrtamente el nun economico");

                }

                if (BuscaNumEconomico(JtxtNumEconomico) && !BuscaPlaca(JtxtPlaca)) {
                    //System.out.println("el año ya esta registrado");
                } else {
                    int capacidadnum = Integer.parseInt(capacidad);
                    int dia = Integer.parseInt(diaRegistro);
                    int AñoEmi = Integer.parseInt(AñoEmision);
                    int AñoRegi = Integer.parseInt(AñoRegistro);
                    int idAnioBuscado = queryBusca.obtenIdBuscaAnio(AñoEmi);
                    int idModeloBuscado = queryBusca.obtenIdBuscaModelo(modelo);
                    int fechaBuscada = queryBusca.obtenIdBuscaFecha(dia, mes, AñoRegi);
                    queryInserta.insertaAutobus(idAutobus + 1, capacidadnum, numeroEconomico, placa, idAnioBuscado, fechaBuscada, idModeloBuscado);

                }

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
        JlblPlaca = new javax.swing.JLabel();
        JtxtPlaca = new javax.swing.JTextField();
        JspPlaca = new javax.swing.JSeparator();
        JlblCapacidad = new javax.swing.JLabel();
        JtxtCapacidad = new javax.swing.JTextField();
        JspCapacidad = new javax.swing.JSeparator();
        JlblMarca = new javax.swing.JLabel();
        JtxtMarca = new javax.swing.JTextField();
        JspMarca = new javax.swing.JSeparator();
        JlblModelo = new javax.swing.JLabel();
        JtxtModelo = new javax.swing.JTextField();
        JspModelo = new javax.swing.JSeparator();
        JlblAnioR = new javax.swing.JLabel();
        JtxtAnioR = new javax.swing.JTextField();
        JspAnioR = new javax.swing.JSeparator();
        JlblDia = new javax.swing.JLabel();
        JlblAnioE = new javax.swing.JLabel();
        JtxtAnioE = new javax.swing.JTextField();
        JspAnioE = new javax.swing.JSeparator();
        JcmbxMeses = new javax.swing.JComboBox<>();
        JbtnEnviar = new javax.swing.JButton();
        JlblNumEconomico = new javax.swing.JLabel();
        JtxtNumEconomico = new javax.swing.JTextField();
        JspNumEconomico = new javax.swing.JSeparator();
        JlblMesRegistro = new javax.swing.JLabel();
        JcmbxDia = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar autobuses");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblPlaca.setText("Placa");
        JpnlLienzo.add(JlblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 50, -1));

        JtxtPlaca.setBorder(null);
        JpnlLienzo.add(JtxtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 129, -1));
        JpnlLienzo.add(JspPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 129, 10));

        JlblCapacidad.setText("Capacidad");
        JpnlLienzo.add(JlblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 60, -1));

        JtxtCapacidad.setBorder(null);
        JpnlLienzo.add(JtxtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 129, -1));
        JpnlLienzo.add(JspCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 129, 10));

        JlblMarca.setText("Marca");
        JpnlLienzo.add(JlblMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 129, -1));

        JtxtMarca.setBorder(null);
        JpnlLienzo.add(JtxtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 129, -1));
        JpnlLienzo.add(JspMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 129, 10));

        JlblModelo.setText("Modelo");
        JpnlLienzo.add(JlblModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 129, -1));

        JtxtModelo.setBorder(null);
        JpnlLienzo.add(JtxtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 129, -1));
        JpnlLienzo.add(JspModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 129, 10));

        JlblAnioR.setText("Año de Registro");
        JpnlLienzo.add(JlblAnioR, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 129, -1));

        JtxtAnioR.setBorder(null);
        JpnlLienzo.add(JtxtAnioR, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 129, -1));
        JpnlLienzo.add(JspAnioR, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 129, 10));

        JlblDia.setText("Dia de Registro");
        JpnlLienzo.add(JlblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 129, -1));

        JlblAnioE.setText("Año de Emision");
        JpnlLienzo.add(JlblAnioE, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 129, -1));

        JtxtAnioE.setBorder(null);
        JpnlLienzo.add(JtxtAnioE, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 129, -1));
        JpnlLienzo.add(JspAnioE, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 129, 10));

        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMesesItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 160, -1));

        JbtnEnviar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEnviar.setText("Enviar");
        JbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEnviarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 80, -1));

        JlblNumEconomico.setText("Numero Economico");
        JpnlLienzo.add(JlblNumEconomico, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 129, -1));

        JtxtNumEconomico.setBorder(null);
        JpnlLienzo.add(JtxtNumEconomico, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 129, -1));
        JpnlLienzo.add(JspNumEconomico, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 129, 10));

        JlblMesRegistro.setText("Mes de Registro");
        JpnlLienzo.add(JlblMesRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, -1, -1));

        JcmbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JpnlLienzo.add(JcmbxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMesesItemStateChanged
        // TODO add your handling code here:
        //        JcmbxDia.setVisible(true);
        //        Object primerElemento = JcmbxDia.getItemAt(0);
        //
        //// Limpia todos los elementos excepto el primero
        //JcmbxDia.removeAllItems();
        //
        //// Agrega el primer elemento de vuelta
        //JcmbxDia.addItem((String) primerElemento);
        //        cargaComboBoxDia(JcmbxDia, validaDia(JcmbxDia));
        //

        String mesSeleccionado = (String) JcmbxMeses.getSelectedItem();

        // Mostrar JComboBox JcmbxDia si no está visible
        JcmbxDia.setVisible(true);

        // Guardar el primer elemento seleccionado actualmente en JcmbxDia
        Object primerElemento = JcmbxDia.getItemAt(0);

        // Limpiar todos los elementos excepto el primero
        JcmbxDia.removeAllItems();

        // Agregar de vuelta el primer elemento guardado
        if (primerElemento != null) {
            JcmbxDia.addItem((String) primerElemento);
        }

        // Determinar qué días cargar según el mes seleccionado
        int metodoCargaDia = validaDia(JcmbxMeses); // Obtener el método de carga de días según el mes seleccionado

        // Cargar los días correspondientes al mes seleccionado
        cargaComboBoxDia(JcmbxDia, metodoCargaDia);
    }//GEN-LAST:event_JcmbxMesesItemStateChanged

    private void JbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEnviarActionPerformed
        // TODO add your handling code here:
        enviarDatos();
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
            java.util.logging.Logger.getLogger(JfRegistroAutobuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRegistroAutobuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRegistroAutobuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRegistroAutobuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRegistroAutobuses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JComboBox<String> JcmbxDia;
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JLabel JlblAnioE;
    private javax.swing.JLabel JlblAnioR;
    private javax.swing.JLabel JlblCapacidad;
    private javax.swing.JLabel JlblDia;
    private javax.swing.JLabel JlblMarca;
    private javax.swing.JLabel JlblMesRegistro;
    private javax.swing.JLabel JlblModelo;
    private javax.swing.JLabel JlblNumEconomico;
    private javax.swing.JLabel JlblPlaca;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspAnioE;
    private javax.swing.JSeparator JspAnioR;
    private javax.swing.JSeparator JspCapacidad;
    private javax.swing.JSeparator JspMarca;
    private javax.swing.JSeparator JspModelo;
    private javax.swing.JSeparator JspNumEconomico;
    private javax.swing.JSeparator JspPlaca;
    private javax.swing.JTextField JtxtAnioE;
    private javax.swing.JTextField JtxtAnioR;
    private javax.swing.JTextField JtxtCapacidad;
    private javax.swing.JTextField JtxtMarca;
    private javax.swing.JTextField JtxtModelo;
    private javax.swing.JTextField JtxtNumEconomico;
    private javax.swing.JTextField JtxtPlaca;
    // End of variables declaration//GEN-END:variables
}
