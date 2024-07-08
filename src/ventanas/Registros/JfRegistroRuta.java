/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas.Registros;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CConsultas;
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
public class JfRegistroRuta extends javax.swing.JFrame {

    /**
     * Creates new form JfRegistroRuta
     */
    public JfRegistroRuta() {
        initComponents();
        cargaComboBox(JcmbxOrigenes, 1);
        cargaComboBox(JcmbxDestinos, 2);
    }

    
    
         //*************** ATRIBUTOS ******************

    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String> telefonos = new ArrayList<>();
    private String nombresRuta, distancia, duracion, horaSalida,horallegada,precio,origen,destino;
    private boolean sinTelefono = false;
    private String regexNombres = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?: [a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)?$";
    private String regexDuracion = "^[1-9]\\d*\\s+(hora|horas)$";
    private String regexHora = "^(0?[1-9]|1[0-2]):[0-5][0-9]\\s+(a\\. m\\.|p\\. m\\.)$";
    //private String regexPrecio = "^\\d{1,8}(\\.\\d{1,2})?$";
    private String regexPrecio = "^\\d{1,7}(\\.\\d{1,2})?$|^\\d{8}(\\.\\d{1,2})?$";
    private String regexDistancia = "^\\d{1,13}(\\.\\d{1,2})?\\s*km$";
     private DefaultComboBoxModel listas;
       private ArrayList<String> datosListas = new ArrayList<>();

    
    public void asignaValores() {
    // Obtener los valores de los campos de texto
    nombresRuta = JtxtNombre.getText();
    distancia = JtxtDistancia.getText();
    duracion = JtxtDuracion.getText();
    horaSalida = JtxtHSalida.getText();
    horallegada = JtxtLlegada.getText();
    precio = JtxtPrecio.getText();
    origen = (String)JcmbxOrigenes.getSelectedItem(); 
    destino = (String)JcmbxDestinos.getSelectedItem();
    
}
    
     public void limpiaValores() {
    // Obtener los valores de los campos de texto
    nombresRuta = null;
    distancia = null;
    duracion = null;
    horaSalida = null;
    horallegada = null;
    precio = null;
    origen = null;
    destino = null;
}
    
    /********************  METODOS   ****************************/
     
     
     
     //asignamos valores al combo box
      public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaComboOrigenes();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = queryCarga.cargaComboDestinos();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }
        } catch (SQLException e) {
        }
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
      
       public String devuelveCadenaDuracion(JTextField campo, String regex) {
        String cadena = null;
        cadena = campo.getText();
        if (cadena.isEmpty()) {
            cadena = null;
        } else if (cadena.matches(regexDuracion)) {
            return cadena;
        } else {
            cadena = "NoValido";
        }
        return cadena;
    }
       
       
        public String devuelveCadenaDistancia(JTextField campo, String regex) {
        String cadena = null;
        cadena = campo.getText();
        if (cadena.isEmpty()) {
            cadena = null;
        } else if (cadena.matches(regexDistancia)) {
            return cadena;
        } else {
            cadena = "NoValido";
        }
        return cadena;
    }
        
          
        public String devuelveCadenaHora(JTextField campo, String regex) {
        String cadena = null;
        cadena = campo.getText();
        if (cadena.isEmpty()) {
            cadena = null;
        } else if (cadena.matches(regexHora)) {
            return cadena;
        } else {
            cadena = "NoValido";
        }
        return cadena;
    }
        
           public String devuelveCadenaPrecio(JTextField campo, String regex) {
        String cadena = null;
        cadena = campo.getText();
        if (cadena.isEmpty()) {
            cadena = null;
        } else if (cadena.matches(regexPrecio)) {
            return cadena;
        } else {
            cadena = "NoValido";
        }
        return cadena;
    }
      
      
       public boolean validaCampoNombre(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
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
       
         public boolean validaCampoDuracion(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadenaDuracion(campo, regex);
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
         
         
         public boolean validaCampoDistancia(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadenaDistancia(campo, regex);
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
         
            public boolean validaCampoHora(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadenaHora(campo, regex);
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
            
        public boolean validaCampoPrecio(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadenaPrecio(campo, regex);
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
        
    public boolean validaCampoOrigen(String campoTexto, JComboBox<String> comboBox, String mensajeVacio) {
    boolean valida = true;
    
    campoTexto = (String) comboBox.getSelectedItem(); // Obtener el texto seleccionado del JComboBox
    
    if (campoTexto.equals("Selecciona una opcion")) {
        CMensajes.msg_advertencia(mensajeVacio, "Registro Usuarios");
        valida = false;
    } 
    return valida;
}
       
        
        
           public boolean validaCampos() {
        return (validaCampoNombre(nombresRuta, JtxtNombre, regexNombres, "Ingrese nombre(s)", "Valores invalidos para nombre(s)"))
                && (validaCampoDistancia(distancia, JtxtDistancia, regexDistancia, "Ingrerse la distancia.", "Valores invalidos para la distancia"))
                && (validaCampoDuracion(duracion, JtxtDuracion, regexDuracion, "Ingrese la duración.", "Valores invalidos para la duración"))
                && (validaCampoHora(horaSalida, JtxtHSalida, regexHora, "Ingrese la hora de salida.", "Valores invalidos para la hora de salida"))
                && (validaCampoHora(horallegada, JtxtLlegada, regexHora, "Ingrese la hora de llegada.", "Valores invalidos para la hora de llegada"))
                && (validaCampoOrigen(origen, JcmbxOrigenes, "Ingrese el origen de la ruta"))
                && (validaCampoOrigen(destino, JcmbxDestinos, "Ingrese el destino de la ruta"))
                && (validaCampoPrecio(precio, JtxtPrecio, regexPrecio, "Ingrese el precio.", "Valores invalidos para el precio"));
    }
       
       
       
       public void enviarDatos() {
        int idRuta, idOrigen,idDestino;
        //int precio1=Integer.parseInt(precio);
        if (validaCampos()) {
            //telefonos = devuelveTelefonos();
            //if (telefonos != null) {
               // if (sinTelefono == false) {
                    asignaValores();
                    try {
                        idRuta = queryBusca.obtenIdFinalRuta();
                        idOrigen=queryBusca.obtenIdOrigenSeleccionado(origen);
                        idDestino=queryBusca.obtenIdDestinoSeleccionado(destino);
                        float precio1=Float.parseFloat(precio);
                        System.out.println("NombreRuta:" + nombresRuta + "\nDistancia:" + distancia + "\nDuracion: " + duracion+ "\nprecio: " + precio);
                        //query.insertaPersona((id + 1), nombres, apPaterno, apMaterno);
                        System.out.println("id ruta:" + idRuta);
                        System.out.println("Hora salida: "+horaSalida+ "\nHora llegada:" + horallegada);
                        System.out.println("origen: "+origen+ "\ndestino:" + destino);
                        System.out.println("idOrigen: "+idOrigen+ "\ndestino:" + idDestino);
                        //query.insertaConductores((query.obtenIdFinalConductor() + 1), query.obtenIdFinalPersona());
                        queryInserta.insertaRuta(idRuta+1, nombresRuta, duracion, horaSalida, horallegada, precio1, distancia, idOrigen, idDestino);
                        
                        
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
        JlblOrigen = new javax.swing.JLabel();
        JlblDestino = new javax.swing.JLabel();
        JlblNombre = new javax.swing.JLabel();
        JlblDistancia = new javax.swing.JLabel();
        JlblDuracion = new javax.swing.JLabel();
        JlblHLlegada = new javax.swing.JLabel();
        JlblHSalida = new javax.swing.JLabel();
        JlblPrecio = new javax.swing.JLabel();
        JcmbxOrigenes = new javax.swing.JComboBox<>();
        JcmbxDestinos = new javax.swing.JComboBox<>();
        JtxtNombre = new javax.swing.JTextField();
        JtxtDistancia = new javax.swing.JTextField();
        JtxtDuracion = new javax.swing.JTextField();
        JtxtHSalida = new javax.swing.JTextField();
        JtxtLlegada = new javax.swing.JTextField();
        JtxtPrecio = new javax.swing.JTextField();
        JspNombre = new javax.swing.JSeparator();
        JspDistancia = new javax.swing.JSeparator();
        JspDuracion = new javax.swing.JSeparator();
        JspHS = new javax.swing.JSeparator();
        JspHLL = new javax.swing.JSeparator();
        JspP = new javax.swing.JSeparator();
        JbtnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar rutas");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblOrigen.setText("Origen");
        JpnlLienzo.add(JlblOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        JlblDestino.setText("Destino");
        JpnlLienzo.add(JlblDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, -1, -1));

        JlblNombre.setText("Nombre de la ruta");
        JpnlLienzo.add(JlblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        JlblDistancia.setText("Distancia");
        JpnlLienzo.add(JlblDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        JlblDuracion.setText("Duracion");
        JpnlLienzo.add(JlblDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        JlblHLlegada.setText("Hora de salida");
        JpnlLienzo.add(JlblHLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        JlblHSalida.setText("Hora de llegada");
        JpnlLienzo.add(JlblHSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        JlblPrecio.setText("Precio");
        JpnlLienzo.add(JlblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        JcmbxOrigenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JpnlLienzo.add(JcmbxOrigenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 34, 170, -1));

        JcmbxDestinos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JpnlLienzo.add(JcmbxDestinos, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 106, 170, -1));

        JtxtNombre.setBorder(null);
        JpnlLienzo.add(JtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        JtxtDistancia.setToolTipText("Llene el campo como el ejemplo: \"120 km\"");
        JtxtDistancia.setBorder(null);
        JpnlLienzo.add(JtxtDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 180, -1));

        JtxtDuracion.setToolTipText("Llene el campo com el siguiente ejemplo: \"20 horas\"");
        JtxtDuracion.setBorder(null);
        JpnlLienzo.add(JtxtDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 180, -1));

        JtxtHSalida.setToolTipText("Llene el campo como el siguiente ejemplo: 8:00 a. m.  ,8:00 p. m. ");
        JtxtHSalida.setBorder(null);
        JpnlLienzo.add(JtxtHSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 180, -1));

        JtxtLlegada.setToolTipText("Llene el campo como el siguiente ejemplo: 8:00 a. m. ,8:00 p. m. ");
        JtxtLlegada.setBorder(null);
        JtxtLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtxtLlegadaActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JtxtLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 165, -1));

        JtxtPrecio.setToolTipText("Solo ingrese numeros enteros o con decimales con un maximo de dos decimales");
        JtxtPrecio.setBorder(null);
        JpnlLienzo.add(JtxtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 165, -1));
        JpnlLienzo.add(JspNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 180, 10));
        JpnlLienzo.add(JspDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, 10));
        JpnlLienzo.add(JspDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 180, 10));
        JpnlLienzo.add(JspHS, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 180, 10));
        JpnlLienzo.add(JspHLL, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 180, 10));
        JpnlLienzo.add(JspP, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 180, 10));

        JbtnEnviar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEnviar.setText("Enviar");
        JbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEnviarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, -1));

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
        enviarDatos();
    }//GEN-LAST:event_JbtnEnviarActionPerformed

    private void JtxtLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtxtLlegadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxtLlegadaActionPerformed

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
            java.util.logging.Logger.getLogger(JfRegistroRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRegistroRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRegistroRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRegistroRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRegistroRuta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JComboBox<String> JcmbxDestinos;
    private javax.swing.JComboBox<String> JcmbxOrigenes;
    private javax.swing.JLabel JlblDestino;
    private javax.swing.JLabel JlblDistancia;
    private javax.swing.JLabel JlblDuracion;
    private javax.swing.JLabel JlblHLlegada;
    private javax.swing.JLabel JlblHSalida;
    private javax.swing.JLabel JlblNombre;
    private javax.swing.JLabel JlblOrigen;
    private javax.swing.JLabel JlblPrecio;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspDistancia;
    private javax.swing.JSeparator JspDuracion;
    private javax.swing.JSeparator JspHLL;
    private javax.swing.JSeparator JspHS;
    private javax.swing.JSeparator JspNombre;
    private javax.swing.JSeparator JspP;
    private javax.swing.JTextField JtxtDistancia;
    private javax.swing.JTextField JtxtDuracion;
    private javax.swing.JTextField JtxtHSalida;
    private javax.swing.JTextField JtxtLlegada;
    private javax.swing.JTextField JtxtNombre;
    private javax.swing.JTextField JtxtPrecio;
    // End of variables declaration//GEN-END:variables
}
