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
import javax.swing.JTextField;

/**
 *
 * @author gelog
 */
public class JfRegistroTerminal extends javax.swing.JFrame {

    /**
     * Creates new form JfRegistroDireccion
     */
    public JfRegistroTerminal() {
        initComponents();
    }

    
     
    
    private final CInserciones queryInserta1 = new CInserciones();
    private final CInserciones queryInserta2 = new CInserciones();
    private final CInserciones queryInserta3 = new CInserciones();
    private final CInserciones queryInserta4 = new CInserciones();
    private final CInserciones queryInserta5 = new CInserciones();
    private final CInserciones queryInserta6 = new CInserciones();
    private final CInserciones queryInserta7 = new CInserciones();
    private final CInserciones queryInserta8 = new CInserciones();
    private final CBusquedas queryBusca1 = new CBusquedas();
    private final CBusquedas queryBusca2 = new CBusquedas();
    private final CBusquedas queryBusca3 = new CBusquedas();
    private final CBusquedas queryBusca4 = new CBusquedas();
    private final CBusquedas queryBusca5 = new CBusquedas();
    private final CBusquedas queryBusca6 = new CBusquedas();
    private final CBusquedas queryBusca7 = new CBusquedas();
    private final CBusquedas queryBusca8 = new CBusquedas();
    private final CBusquedas queryBusca9 = new CBusquedas();
    private final CBusquedas queryBusca10 = new CBusquedas();
    private final CBusquedas queryBusca11 = new CBusquedas();
    private final CBusquedas queryBusca12 = new CBusquedas();
    private final CBusquedas queryBusca13 = new CBusquedas();
    private final CBusquedas queryBusca14 = new CBusquedas();
    private final CBusquedas queryBusca15 = new CBusquedas();
    private final CBusquedas queryBusca16 = new CBusquedas();
    private final CBusquedas queryBusca17 = new CBusquedas();
    private final CBusquedas queryBusca18 = new CBusquedas();
    private final CBusquedas queryBusca19 = new CBusquedas();
    private final CBusquedas queryBusca20 = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String> telefonos = new ArrayList<>();
    private String nombresTerminal, estado,ciudad,calle,codigoPostal,Colonia,Numero;
    private boolean sinTelefono = false;
    private String regexNombre="^[a-zA-Z]{1,80}$";
    private String regexCodigoPostal="^[0-9]{5}$";
    private String regexEstado="^[a-zA-Z]{1,19}$";
    private String regexCiudad="^[a-zA-Z]{1,80}$";
    private String regexCalle="^[a-zA-Z]{1,80}$";
    private String regexNumero="^[0-9]{1,5}$";
    private String regexColonia="^[a-zA-Z]{1,80}$";

     private DefaultComboBoxModel listas;
       private ArrayList<String> datosListas = new ArrayList<>();

       
       
           public void asignaValores() {
    // Obtener los valores de los campos de texto
    nombresTerminal = JtxtNombreTerminal.getText();
    estado = JtxtEstado.getText();
    ciudad = JtxtCiudad.getText();
    calle = JtxtCalle.getText();
    codigoPostal = JtxtCP.getText();
    Colonia = JtxtBarrio.getText();
    Numero = JtxtNumero.getText();
    
}
           
           
    public void limpiaValores() {
    // Obtener los valores de los campos de texto
    nombresTerminal = null;
    estado = null;
    ciudad = null;
    calle = null;
    codigoPostal = null;
    Colonia = null;
    Numero = null;
    
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
      
      
        
      
      public boolean validaCampo(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadena(campo, regex);
        if (campoTexto == null) {
            CMensajes.msg_advertencia(mensajeVacio, "Registro terminal");
            valida = false;
        } else if (campoTexto.equals("NoValido")) {
            CMensajes.msg_error(mensajeInvalido, "Registro terminal");
            valida = false;
        } else {
            valida = true;
        }
        return valida;
    }
      
      
      
      
    public boolean validaCampos() {
    return validaCampo(nombresTerminal, JtxtNombreTerminal, regexNombre, "Ingrese la terminal", "Terminal invalida")
            &&  validaCampo(estado, JtxtEstado, regexEstado, "Ingrese el estado", "Estado invalida")
            &&  validaCampo(ciudad, JtxtCiudad, regexCiudad, "Ingrese la ciudad", "Ciudad invalida")
            &&  validaCampo(Colonia, JtxtBarrio, regexColonia, "Ingrese la colonia", "Colonia invalida")
            &&  validaCampo(calle, JtxtCalle, regexCalle, "Ingrese la calle", "Calle invalida")
            &&  validaCampo(Numero, JtxtNumero, regexNumero, "Ingrese el numero", "Numero invalido")
            &&  validaCampo(codigoPostal, JtxtCP, regexCodigoPostal, "Ingrese el Codigo postal", "Codigo postal invalido");
            
    }
    
    public void BuscaCamposNombre(String nombreTerminal) throws SQLException{
   
        String terminal=nombreTerminal;
        
        try {
        String resultado=queryBusca1.buscarIdNombreTerminal(terminal);
       
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro el campo", "Registro terminal");
              String idBuscaCiudad=queryBusca15.buscarIdCiudad(ciudad);
        String idBuscaColonia=queryBusca16.buscarIdColonia(Colonia);
        String idBuscaCodigoPostal=queryBusca17.buscarIdCodigoPostal(codigoPostal);
        String idDireccion=queryBusca19.buscarIdDireccion(calle, Numero, idBuscaCiudad, idBuscaColonia, idBuscaCodigoPostal);
        int idTerminal=queryBusca20.obtenIdFinalTerminal();
        queryInserta6.insertaTerminal(idTerminal+1, nombreTerminal, idDireccion);
             System.out.println("Se registro exitosamente la terminal");
             CMensajes.msg("La terminal se registro correctamente", "Registro terminal");
               
            
             System.out.println("Nombre de la terminal no encontrado");
        }else{
            CMensajes.msg_advertencia("Ten en cuenta que ese nombre ya esta registrado", "Registro Nombre de la terminal");
             //CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro terminal");
            //CMensajes.msg_error("El nombre de la terminal ya esta registrado", "Registro Nombre terminal");
              System.out.println("Encontrado");
              System.out.println("No se registro la terminal");
          // return buscar=true;
        }
    
        } catch (Exception e) {
        }
       
    }
    
    
     public void insertaDireccion(String nombreTerminal,String calle,String Numero,String ciudad,String Colonia,String codigoPostal) throws SQLException{
         System.out.println("--------------------------------------");
        String terminal=nombreTerminal;
        
        try {
        //String resultadoNombre=queryBusca14.buscarIdNombreTerminal(terminal);
        String idBuscaCiudad=queryBusca15.buscarIdCiudad(ciudad);
        String idBuscaColonia=queryBusca16.buscarIdColonia(Colonia);
        String idBuscaCodigoPostal=queryBusca17.buscarIdCodigoPostal(codigoPostal);
        int idDireccion=queryBusca18.obtenIdFinalDireciion();
//        if (resultadoNombre == null) {
//             CMensajes.msg_advertencia("No se encontro el campo", "Registro terminal");
//             System.out.println("Nombre de la terminal no encontrado");
//             
             if (idBuscaCiudad != null) {
                 System.out.println("Encontro Ciudad");
                   
                 if (idBuscaColonia!=null) {
                      System.out.println("Encontro Colonia");
                     if (idBuscaCodigoPostal!=null) {
                         System.out.println("Encontro CodigoPostal"); 
                         queryInserta5.insertaDireccion(idDireccion+1, calle, Numero, idBuscaCiudad, idBuscaColonia, idBuscaCodigoPostal);
                         System.out.println("se inseto direccion");
                     }else{
                      
                     }
                     
                 }else{
                 
                 }
                 
                
            }else{
                    
             }
             

    
        } catch (SQLException e) {
        }
       
    }
    
    
    
    
     public void insertaTerminal(String nombreTerminal,String calle,String Numero,String ciudad,String Colonia,String codigoPostal) throws SQLException{
         System.out.println("--------------------------------------");
         
       
      
        
        try {
         String idBuscaCiudad=queryBusca15.buscarIdCiudad(ciudad);
        String idBuscaColonia=queryBusca16.buscarIdColonia(Colonia);
        String idBuscaCodigoPostal=queryBusca17.buscarIdCodigoPostal(codigoPostal);
        String idDireccion=queryBusca19.buscarIdDireccion(calle, Numero, idBuscaCiudad, idBuscaColonia, idBuscaCodigoPostal);
        int idTerminal=queryBusca20.obtenIdFinalTerminal();
        queryInserta6.insertaTerminal(idTerminal+1, nombreTerminal, idDireccion);
            System.out.println("Se registro la terminal");
               
            
  

    
        } catch (SQLException e) {
        }
       
    }
    
    
    
    
    public void BuscaCamposEstado(String estado) throws SQLException{
        
        
        try {
        String resultado=queryBusca2.buscarIdEstado(estado);
       String idEstado =queryBusca6.obtenIdFinalEstadoo();
        int idEstadoIngresado= Integer.parseInt(idEstado);
        if (resultado == null) {
            // CMensajes.msg_advertencia("No se encontro el estado", "Registro terminal");
             System.out.println("Nombre del estado no encontrado");
             queryInserta1.insertaEstado(idEstadoIngresado+1, estado);
             System.out.println("se inserto un estado:"+estado);
         //  return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
           // CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro terminal");
            CMensajes.msg_error("El nombre del estado ya esta registrado", "Registro Nombre estado");
             System.out.println("Encontrado");
             
          // return buscar=true;
        }
    
        } catch (Exception e) {
        }
    }
    
    
    public void BuscaCamposCodigoPostal(String codigoPostal) throws SQLException{
        try {
        String resultado=queryBusca3.buscarIdCodigoPostal(codigoPostal);
        String idCodPos=queryBusca13.obtenIdFinalCodigoPostall();
        int idCodigo=Integer.parseInt(idCodPos);
       
        
        if (resultado == null) {
            // CMensajes.msg_advertencia("No se encontro el codigo postal", "Registro codigoPostal");
            
             queryInserta4.insertaCodigoPostal(idCodigo+1, codigoPostal);
              System.out.println("codigo insertado:"+codigoPostal);
         //  return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
             //CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro terminal");
             CMensajes.msg_advertencia("Ten en cuenta que el codigo postal ya esta registrado", "Registro codigo postal");
            //CMensajes.msg_error("El codigo ya esta registrado", "Registro codigo postal");
             System.out.println("Encontrado");
          // return buscar=true;
         
        }
    
        } catch (Exception e) {
        }
    }
    
    
    
    
     
    public void BuscaCamposCiudad(String ciudad) throws SQLException{
        try {
        String resultado=queryBusca4.buscarIdCiudad(ciudad);
       
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro la ciudad", "Registro ciudad");
             
              int estadoBuscado=queryBusca7.obtenIdBuscaEstado(estado);
              int idCiudad=queryBusca9.obtenIdFinalCiudad();
              queryInserta2.insertaCiudad(idCiudad+1, ciudad, estadoBuscado);
              System.out.println("ciudad insertada: "+ciudad);
              
         //  return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
             //CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro terminal");
             CMensajes.msg_advertencia("Ten en cuenta que la ciudad ya esta registrada", "Registro ciudad");
            //CMensajes.msg_error("Ciudad ya esta registrado", "Registro ciudad");
             System.out.println("Encontrado");
          // return buscar=true;
         
        }
    
        } catch (Exception e) {
        }
    }
    
    
    
    
    
     
    public void BuscaCamposColonia(String colonia) throws SQLException{
              try {
        String resultado=queryBusca10.buscarIdColonia(colonia);
        String IdCol=queryBusca12.obtenIdFinalColoniaa();
        int idColonia=Integer.parseInt(IdCol);
       
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro la colonia", "Registro colonia");
           
               queryInserta3.insertaColonia(idColonia+1, Colonia);
               System.out.println("Seinserto colonia:"+Colonia);
         //  return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
             //CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro colonina");
             CMensajes.msg_advertencia("Ten en cuenta que la colonia ya esta registrada", "Registro colonia");
            //CMensajes.msg_error("Colonia ya esta registrado", "Registro colonia");
             System.out.println("Encontrado");
          // return buscar=true;
         
        }
    
        } catch (Exception e) {
        }
    }
    
    
    
       public void BuscaCamposNumeroHabitacional(String numero) throws SQLException{
              try {
        String resultado=queryBusca11.buscarIdNumeroHabitacional(numero);
       
        
        if (resultado == null) {
             CMensajes.msg_advertencia("No se encontro el numero", "Registro numero");
             System.out.println("numero no encontrado");
         //  return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
             CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro colonina");
            CMensajes.msg_error("Numero habitacional ya esta registrado", "Registro numero");
             System.out.println("Encontrado");
          // return buscar=true;
         
        }
    
        } catch (Exception e) {
        }
    }
    
    
    
     public void enviarDatos() {
       
        if (validaCampos()) {
          
            
           asignaValores();
          try {
              
             
              BuscaCamposEstado(estado);
              BuscaCamposCiudad(ciudad);
              BuscaCamposColonia(Colonia);
              BuscaCamposCodigoPostal(codigoPostal);
              //BuscaCamposNumeroHabitacional(Numero);
              
              insertaDireccion(nombresTerminal, calle, Numero, ciudad, Colonia, codigoPostal);
               BuscaCamposNombre(nombresTerminal);
              //insertaTerminal(nombresTerminal, calle, Numero, ciudad, Colonia, codigoPostal);

                
                
                
                
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
        JlblEstado = new javax.swing.JLabel();
        JtxtEstado = new javax.swing.JTextField();
        JspEstado = new javax.swing.JSeparator();
        JlblCiudad = new javax.swing.JLabel();
        JtxtCiudad = new javax.swing.JTextField();
        JspCiudad = new javax.swing.JSeparator();
        JlblCalle = new javax.swing.JLabel();
        JtxtCalle = new javax.swing.JTextField();
        JspCalle = new javax.swing.JSeparator();
        JlblBarrio = new javax.swing.JLabel();
        JtxtBarrio = new javax.swing.JTextField();
        JspBarrio = new javax.swing.JSeparator();
        JlblCP = new javax.swing.JLabel();
        JtxtCP = new javax.swing.JTextField();
        JspCP = new javax.swing.JSeparator();
        JlblNumero = new javax.swing.JLabel();
        JtxtNumero = new javax.swing.JTextField();
        JspNumerp = new javax.swing.JSeparator();
        JbtnEnviar = new javax.swing.JButton();
        JlblNombreTerminal = new javax.swing.JLabel();
        JtxtNombreTerminal = new javax.swing.JTextField();
        JspNombreTerminal = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar terminales");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblEstado.setText("Estado");
        JpnlLienzo.add(JlblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        JtxtEstado.setToolTipText("Ingrese el estado usando puras letras mayusculas o minusculas");
        JtxtEstado.setBorder(null);
        JpnlLienzo.add(JtxtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));
        JpnlLienzo.add(JspEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, -1));

        JlblCiudad.setText("Ciudad");
        JpnlLienzo.add(JlblCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        JtxtCiudad.setToolTipText("Ingrese la ciudad usando puras letras mayusculas o minusculas");
        JtxtCiudad.setBorder(null);
        JtxtCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtxtCiudadActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JtxtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, -1));
        JpnlLienzo.add(JspCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 110, -1));

        JlblCalle.setText("Nombre de la vialidad");
        JpnlLienzo.add(JlblCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        JtxtCalle.setToolTipText("Ingrese la vialidad usando puras letras mayusculas o minusculas");
        JtxtCalle.setBorder(null);
        JpnlLienzo.add(JtxtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 120, -1));
        JpnlLienzo.add(JspCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 120, 10));

        JlblBarrio.setText("Colonia/Barrio");
        JpnlLienzo.add(JlblBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        JtxtBarrio.setToolTipText("Ingrese la colonia usando puras letras mayusculas o minusculas");
        JtxtBarrio.setBorder(null);
        JpnlLienzo.add(JtxtBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 110, -1));
        JpnlLienzo.add(JspBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 120, -1));

        JlblCP.setText("Codigo Postal");
        JpnlLienzo.add(JlblCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        JtxtCP.setToolTipText("Ingrese el codigo postal usando solo 5 numeros en total");
        JtxtCP.setBorder(null);
        JpnlLienzo.add(JtxtCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 110, -1));
        JpnlLienzo.add(JspCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 110, -1));

        JlblNumero.setText("Numero");
        JpnlLienzo.add(JlblNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        JtxtNumero.setToolTipText("Ingrese el numero habitacional usando puros numeros que puede ir del 1 a 5 como maximo");
        JtxtNumero.setBorder(null);
        JpnlLienzo.add(JtxtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 110, -1));
        JpnlLienzo.add(JspNumerp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 110, -1));

        JbtnEnviar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEnviar.setText("Enviar");
        JbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEnviarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 70, -1));

        JlblNombreTerminal.setText("Nombre");
        JpnlLienzo.add(JlblNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        JtxtNombreTerminal.setToolTipText("Ingrese el nombre usando puras letras mayusculas o minusculas");
        JtxtNombreTerminal.setBorder(null);
        JpnlLienzo.add(JtxtNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, -1));
        JpnlLienzo.add(JspNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoRegistroTerminal.png"))); // NOI18N
        JpnlLienzo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEnviarActionPerformed
        // TODO add your handling code here:
        enviarDatos();
    }//GEN-LAST:event_JbtnEnviarActionPerformed

    private void JtxtCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtxtCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxtCiudadActionPerformed

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
            java.util.logging.Logger.getLogger(JfRegistroTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRegistroTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRegistroTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRegistroTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRegistroTerminal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JLabel JlblBarrio;
    private javax.swing.JLabel JlblCP;
    private javax.swing.JLabel JlblCalle;
    private javax.swing.JLabel JlblCiudad;
    private javax.swing.JLabel JlblEstado;
    private javax.swing.JLabel JlblNombreTerminal;
    private javax.swing.JLabel JlblNumero;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspBarrio;
    private javax.swing.JSeparator JspCP;
    private javax.swing.JSeparator JspCalle;
    private javax.swing.JSeparator JspCiudad;
    private javax.swing.JSeparator JspEstado;
    private javax.swing.JSeparator JspNombreTerminal;
    private javax.swing.JSeparator JspNumerp;
    private javax.swing.JTextField JtxtBarrio;
    private javax.swing.JTextField JtxtCP;
    private javax.swing.JTextField JtxtCalle;
    private javax.swing.JTextField JtxtCiudad;
    private javax.swing.JTextField JtxtEstado;
    private javax.swing.JTextField JtxtNombreTerminal;
    private javax.swing.JTextField JtxtNumero;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
