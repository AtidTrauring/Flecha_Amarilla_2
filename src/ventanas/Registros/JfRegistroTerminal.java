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

    
    
    
      private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
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
    private String regexNumero="^[0-9]{5}$";
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
            &&  validaCampo(Numero, JtxtNumero, regexNumero, "Ingrese el numero", "Numero invalidp")
            &&  validaCampo(codigoPostal, JtxtCP, regexCodigoPostal, "Ingrese el Codigo postal", "Codigo postal invalidp");
            
    }
    
    public boolean BuscaCamposNombre(String nombreTerminal) throws SQLException{
        boolean buscar=false;
        String terminal=nombreTerminal;
        
        try {
           Integer resultado=queryBusca.obtenIdtTerminal(terminal);
        CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro terminal");
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro el campo", "Registro Autobus");
           return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
             //CMensajes.msg_error("El nombre de la terminal ya esta registrado", "Registro Nombre terminal");
           return buscar=true;
        }
    
        } catch (Exception e) {
        }
        return buscar;
    }
    
    
    
    
    
    
    public boolean BuscaCamposEstado(String estado) throws SQLException{
        boolean buscar=false;
        String Nombreestados=estado;
        
        try {
           Integer resultado=queryBusca.obtenIdtEstado(Nombreestados);
        CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro estado");
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro el campo", "Registro Autobus");
           return buscar =false;
        }else{
           // CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
             //CMensajes.msg_error("El nombre del estado ya esta registrado", "Registro estado");
           return buscar=true;
        }
    
        } catch (Exception e) {
        }
        return buscar;
    }
    
    
    public boolean BuscaCamposCodigoPostal(int codigoPostal) throws SQLException{
        boolean buscar=false;
        int CP=codigoPostal;
        
        try {
           Integer resultado=null;
           resultado=queryBusca.obtenIdCodigoPostal(CP);
        CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro A codigo postal");
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro el campo", "Registro Autobus");
           return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
            // CMensajes.msg_error("El nombre del estado ya esta registrado", "Registro estado");
           return buscar=true;
        }
    
        } catch (Exception e) {
        }
        
        return buscar;
    }
    
    
    
    
     
    public boolean BuscaCamposCiudad(String ciudad) throws SQLException{
        boolean buscar=false;
        String NombreeCiudad=ciudad;
        
        try {
           Integer resultado=queryBusca.obtenIdtCiudad(NombreeCiudad);
        CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro ciudad");
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro el campo", "Registro Autobus");
           return buscar =false;
        }else{
           // CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
            // CMensajes.msg_error("El nombre de la ciudad ya esta registrado", "Registro ciudad");
           return buscar=true;
        }
    
        } catch (Exception e) {
        }
        return buscar;
    }
    
    
    
    
    
     
    public boolean BuscaCamposColonia(String colonia) throws SQLException{
        boolean buscar=false;
        String col=colonia;
        
        try {
           Integer resultado=queryBusca.obtenIdtColonia(col);
        CMensajes.msg_advertencia("Nombre buscado:"+resultado, "Registro colonia");
        
        if (resultado == null) {
             //CMensajes.msg_advertencia("No se encontro el campo", "Registro Autobus");
           return buscar =false;
        }else{
            //CMensajes.msg_advertencia("Si se encontro el campo", "Registro Autobus");
             //CMensajes.msg_error("El nombre de la ciudad ya esta registrado", "Registro ciudad");
           return buscar=true;
        }
    
        } catch (Exception e) {
        }
        return buscar;
    }
    
    
    
     public void enviarDatos() {
        int idAutobus, idFecha, idMes, idModelo, idAño, idMarca, idBuscaMarca;
        //int precio1=Integer.parseInt(precio);
        if (validaCampos()) {
          
            
            asignaValores();
            try {
                
                
                if (BuscaCamposNombre(nombresTerminal)) {
                    System.out.println("si se encontro"); 
                    CMensajes.msg_error("El nombre de la terminal ya esta registrado", "Registro Nombre terminal");
                }else{
                
                   System.out.println("no se encontro"); 
                }
                
             
                if (BuscaCamposEstado(estado)) {
                    System.out.println("si se encontro"); 
                    CMensajes.msg_error("El nombre del estado ya esta registrado", "Registro estado");
                }else{
                int idEstado=queryBusca.obtenIdFinalEstado();
               // queryInserta.insertaEstado(idEstado+1, estado);
                   System.out.println("se registro el estado exitosamente:"+estado); 
                }
              
                 if (BuscaCamposCiudad(ciudad)) {
                    System.out.println("si se encontro"); 
                     CMensajes.msg_error("El nombre de la ciudad ya esta registrado", "Registro ciudad");
               }else{
                int estadoBuscado=queryBusca.obtenIdBuscaEstado(estado);
                int idCiudad=queryBusca.obtenIdFinalCiudad();
                queryInserta.insertaCiudad(idCiudad+1, ciudad, estadoBuscado);
                System.out.println("se registro la ciudad exitosamente:"+ciudad); 
                  //System.out.println("no se encontro"); 
                }
               
               int codiPost= Integer.parseInt(codigoPostal);
                  if (BuscaCamposCodigoPostal(codiPost)) {
                   System.out.println("si se encontro");
                   CMensajes.msg_error("El nombre del estado ya esta registrado", "Registro codigo postal");
              }else{
               int idCodigo=queryBusca.obtenIdFinalCodigoPostal();
               queryInserta.insertaCodigoPostal(idCodigo+1, codiPost);
  
               System.out.println("se registro el codigo postal exitosamente:"+codigoPostal); 
                 //System.out.println("no se encontro"); 
               }
//                  
//                  
                // int codiPost= Integer.parseInt(codigoPostal);
                  if (BuscaCamposColonia(Colonia)) {
                  System.out.println("si se encontro"); 
                    CMensajes.msg_error("El nombre de la colonia ya esta registrado", "Registro colonia");
               }else{
                int idColonia=queryBusca.obtenIdFinalColonia();
                queryInserta.insertaColonia(idColonia+1, Colonia);
   
                System.out.println("se registro el codigo postal exitosamente:"+codigoPostal); 
                   //System.out.println("no se encontro"); 
                }
//                
//                
                if (BuscaCamposNombre(nombresTerminal) && BuscaCamposEstado(estado) && BuscaCamposCiudad(ciudad) && BuscaCamposCodigoPostal(codiPost) && BuscaCamposColonia(Colonia)) {
              
               }else{
                   int idBucaCiudad=queryBusca.obtenIdtCiudad(ciudad);
                    int idBucaColonia=queryBusca.obtenIdtColonia(Colonia);
                    int idBucaCodigoPostal=queryBusca.obtenIdCodigoPostal(codiPost);
                    int idDireccion=queryBusca.obtenIdFinalDireciion();
                    int num=Integer.parseInt(Numero);
                    queryInserta.insertaDireccion(idDireccion+1, calle, num, idBucaCiudad, idBucaColonia, idBucaCodigoPostal);
                    System.out.println("se registro exitosamente la direccion");
                
                int idBuscaDireccio=queryBusca.obtenIdBuscaDireccion(calle, num);
                int idTerminal=queryBusca.obtenIdFinalTerminal();
                queryInserta.insertaTerminal(idTerminal+1, nombresTerminal, idDireccion);
                CMensajes.msg("Terminal Registrada", "Registro terminal");
                }
//                
//                
//             
//                
                
                
                
                
                
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
        JlblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar terminales");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblEstado.setText("Estado");
        JpnlLienzo.add(JlblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        JtxtEstado.setBorder(null);
        JpnlLienzo.add(JtxtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));
        JpnlLienzo.add(JspEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, -1));

        JlblCiudad.setText("Ciudad");
        JpnlLienzo.add(JlblCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        JtxtCiudad.setBorder(null);
        JpnlLienzo.add(JtxtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, -1));
        JpnlLienzo.add(JspCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 110, -1));

        JlblCalle.setText("Nombre de la vialidad");
        JpnlLienzo.add(JlblCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        JtxtCalle.setBorder(null);
        JpnlLienzo.add(JtxtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 120, -1));
        JpnlLienzo.add(JspCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 120, 10));

        JlblBarrio.setText("Colonia/Barrio");
        JpnlLienzo.add(JlblBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        JtxtBarrio.setBorder(null);
        JpnlLienzo.add(JtxtBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 110, -1));
        JpnlLienzo.add(JspBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 120, -1));

        JlblCP.setText("Codigo Postal");
        JpnlLienzo.add(JlblCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        JtxtCP.setBorder(null);
        JpnlLienzo.add(JtxtCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 110, -1));
        JpnlLienzo.add(JspCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 110, -1));

        JlblNumero.setText("Numero");
        JpnlLienzo.add(JlblNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        JtxtNumero.setBorder(null);
        JpnlLienzo.add(JtxtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 110, -1));
        JpnlLienzo.add(JspNumerp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 110, -1));

        JbtnEnviar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEnviar.setText("Enviar");
        JpnlLienzo.add(JbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 70, -1));

        JlblNombreTerminal.setText("Nombre");
        JpnlLienzo.add(JlblNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        JtxtNombreTerminal.setBorder(null);
        JpnlLienzo.add(JtxtNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, -1));
        JpnlLienzo.add(JspNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, -1));

        JlblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoRegistroTerminal.png"))); // NOI18N
        JpnlLienzo.add(JlblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 200, 190));

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
    private javax.swing.JLabel JlblFondo;
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
    // End of variables declaration//GEN-END:variables
}
