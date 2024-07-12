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

    
    
    
    
    private CInserciones queryInserta1 = new CInserciones();
    private CInserciones queryInserta2 = new CInserciones();
    private CInserciones queryInserta3 = new CInserciones();
    private CInserciones queryInserta4 = new CInserciones();
    private CInserciones queryInserta5 = new CInserciones();
    private CInserciones queryInserta6 = new CInserciones();
    private CInserciones queryInserta7 = new CInserciones();
    private CInserciones queryInserta8 = new CInserciones();
    private CBusquedas queryBusca1 = new CBusquedas();
    private CBusquedas queryBusca2 = new CBusquedas();
    private CBusquedas queryBusca3 = new CBusquedas();
    private CBusquedas queryBusca4 = new CBusquedas();
    private CBusquedas queryBusca5 = new CBusquedas();
    private CBusquedas queryBusca6 = new CBusquedas();
    private CBusquedas queryBusca7 = new CBusquedas();
    private CBusquedas queryBusca8 = new CBusquedas();
    private CBusquedas queryBusca9 = new CBusquedas();
    private CBusquedas queryBusca10 = new CBusquedas();
    private CBusquedas queryBusca11 = new CBusquedas();
    private CBusquedas queryBusca12 = new CBusquedas();
    private CBusquedas queryBusca13 = new CBusquedas();
    private CBusquedas queryBusca14 = new CBusquedas();
    private CBusquedas queryBusca15 = new CBusquedas();
    private CBusquedas queryBusca16 = new CBusquedas();
    private CBusquedas queryBusca17 = new CBusquedas();
    private CBusquedas queryBusca18 = new CBusquedas();
    private CBusquedas queryBusca19 = new CBusquedas();
    private CBusquedas queryBusca20 = new CBusquedas();
    private CBusquedas queryBusca21 = new CBusquedas();
    private CBusquedas queryBusca22 = new CBusquedas();
    private CBusquedas queryBusca23 = new CBusquedas();
    private CBusquedas queryBusca24 = new CBusquedas();
    private CBusquedas queryBusca25 = new CBusquedas();
    private CEliminaciones queryElimina = new CEliminaciones();
    private CActualizaciones queryActualiza = new CActualizaciones();
      private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String> telefonos = new ArrayList<>();
    private DefaultComboBoxModel listas;
    private ArrayList<String> datosListas = new ArrayList<>();
    private String placa,capacidad,marca,modelo,numeroEconomico,AñoEmision,diaRegistro,AñoRegistro,mes;
    private String regexPlaca = "^[a-zA-Z0-9-]{1,8}$";
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
    return validaCampo(placa, JtxtPlaca, regexPlaca, "Ingrese la placa", "Placa invalida")
        && validaCampo(capacidad, JtxtCapacidad, regexCapacidad, "Ingrese la capacidad", "Capacidad invalida")
        && validaCampo(marca, JtxtMarca, regexmarca, "Ingrese la marca", "marca invalida")
        && validaCampo(modelo, JtxtModelo, regexmodelo, "Ingrese el modelo", "modelo invalida")
        && validaCampo(numeroEconomico, JtxtNumEconomico, regexnumeroEconomico, "Ingrese el numero economico", "numero invalido")
        && validaCampo(AñoEmision, JtxtAnioE, regexañoEmision, "Ingrese el año de emision", "numero invalido")
        && validaCampo(AñoRegistro, JtxtAnioR, regexañoRegistro, "Ingrese el año de registro", "numero invalido")
        && validaCombo(mes, JcmbxMeses, "Ingresa la marca");
        //&& validaCombo(diaRegistro, JcmbxDia, "Ingresa el dia");
    }

              
              
              
                     
          public void BuscaAñoEmision(String Año) throws SQLException{
        boolean buscar = false;
        try {
        String  idAño = queryBusca6.obtenIdFinalAñoo();
        String resultado =queryBusca1.obtenIdBuscaAñoo(Año);
        int IDAño=Integer.parseInt(idAño);
        if (resultado == null) {
           // CMensajes.msg_advertencia("No se encontro el año de emision", "Registro año emisiom");
            queryInserta1.insertaAnioo(IDAño + 1, Año);
           //queryInserta1.insertaAnio(idAño + 1, AñoEmision);
          //return buscar =false;
        }else{
          CMensajes.msg_advertencia("El año de emision ya esta registrado", "Registro año emision");
             
           //return buscar=true;
        }
    
        } catch (SQLException e) {
            System.out.println("error en busca marca");
        }
          // return buscar;
    }
          
          
              public void BuscaAñoRegistro(String Año) throws SQLException{
        boolean buscar = false;
        try {
        String  idAño = queryBusca6.obtenIdFinalAñoo();
        String resultado =queryBusca1.obtenIdBuscaAñoo(Año);
        int IDAño=Integer.parseInt(idAño);
        if (resultado == null) {
            //CMensajes.msg_advertencia("No se encontro el año registro", "Registro año registro");
            queryInserta1.insertaAnioo(IDAño + 1, Año);
           //queryInserta1.insertaAnio(idAño + 1, AñoEmision);
          //return buscar =false;
        }else{
          CMensajes.msg_advertencia("El año ya esta registrado el año registro", "Registro año registro");
             
           //return buscar=true;
        }
    
        } catch (SQLException e) {
            System.out.println("error en busca marca");
        }
          // return buscar;
    }
          
              
              
              
              

//         
          public void BuscaMarca(String marca) throws SQLException{
        boolean buscar = false;
        try {
        String resultado =queryBusca3.obtenIdBuscaMarcaa(marca);
        String  idMarca = queryBusca7.obtenIdFinalMarcaa();
        int IDmarca= Integer.parseInt(idMarca);
        if (resultado == null) {
            //CMensajes.msg_advertencia("No se encontro la marca", "Registro terminal");
            queryInserta3.insertaMarca(IDmarca + 1, marca);
            System.out.println("Se registro la marca");
        
           
          //return buscar =false;
        }else{
          CMensajes.msg_advertencia("La marca ya esta registrado", "Registro marca");
             
           //return buscar=true;
        }
    
        } catch (SQLException e) {
            System.out.println("error en busca marca");
        }
          // return buscar;
    }
          
          
        public void BuscaModelo(String modelo) throws SQLException{
        boolean buscar = false;
        try {
        String idBuscaMarca = queryBusca8.obtenIdBuscaMarcaa(marca);
        String resultado = queryBusca4.obtenIdBuscaModeloo(modelo);
        String idModelo = queryBusca9.obtenIdFinalModeloo();
        int IDModelo= Integer.parseInt(idModelo);
        if (resultado == null) {
            //CMensajes.msg_advertencia("No se encontro el modelo", "Registro modelo");
            queryInserta4.insertaMadeloo(IDModelo + 1, modelo, idBuscaMarca);
            System.out.println("Se registro el modelo");
        
           
          //return buscar =false;
        }else{
          CMensajes.msg_advertencia("El modelo ya esta registrado", "Registro modelo");
             
           //return buscar=true;
        }
    
        } catch (SQLException e) {
            System.out.println("error en busca marca");
        }
          // return buscar;
    }
        
        
        public void Insertafecha(String dia,String mes,String añoRegistro) throws SQLException{
        boolean buscar = false;
        try {
       
        //int fechaBuscada = queryBusca13.obtenIdBuscaFechaa(dia, mes, añoRegistro);
        String idfech=queryBusca16.obtenIdFinalFechaa();
        String idmes=queryBusca17.obtenIdBuscaMes(mes);
        String idanio=queryBusca17.obtenIdBuscaAñoo(añoRegistro);
        int idFecha=Integer.parseInt(idfech);
        queryInserta6.insertaFechaa(idFecha+1, dia, idmes, idanio);
            
            
            
            
    
        } catch (Exception e) {
            System.out.println("error en busca dia");
        }
          // return buscar;
    }
        
              
        public void BuscaPlaca(String placa,String numeroEconomico, String capacidad,String modelo,String marca, String diaRegistro,String AñoEmision,String mes, String AñoRegistro) throws SQLException{
        boolean buscar = false;
        try {
       
        String resultado =queryBusca5.obtenIdBuscaPlacaa(placa);
        
                //=queryBusca13.obtenIdBuscaFechaa(dia, mes, AñoRegi);
       
        if (resultado == null) {
            //CMensajes.msg_advertencia("No se encontro la placa", "Registro placa");
           String resultado2=queryBusca18.obtenIdBuscaNunEconomicoo(numeroEconomico);
            if (resultado2== null) {
                
                String idAutobusss=queryBusca19.obtenIdFinalAutobuss();
                
                
                
                
                
                String idMes=queryBusca22.buscarIdMes(mes);
                String AñoRegist=queryBusca20.buscaAnioo(AñoRegistro);
                
                        
                String IdFechaBusca=queryBusca21.buscaFechaa(diaRegistro, idMes, AñoRegist);
                String AñoEmisi= queryBusca23.buscaAnioo(AñoEmision);
                int idAutobus=Integer.parseInt(idAutobusss);
                String idModelo=queryBusca24.obtenIdBuscaModeloo(modelo);
                
                queryInserta8.insertaAutobus(idAutobus+1, capacidad, numeroEconomico, placa, AñoEmisi, IdFechaBusca, idModelo);
                
                CMensajes.msg("El autobus se registro correctamente", "Registro autobus");
                System.out.println("se registro correctamente el autobus");
                
                
            }else{
                System.out.println("el num economico ya esta ");
                          CMensajes.msg_advertencia("El numero economico ya esta registrado", "Registro numero");

                
            }
            
            
            
            if (buscar) {
                
            }
           
            //System.out.println("Se registro el la placa");
        
           
          //return buscar =false;
        }else{
          CMensajes.msg_advertencia("La placa ya esta registrado", "Registro placa");
             
           //return buscar=true;
        }
    
        } catch (Exception e) {
            System.out.println("error en busca dia");
        }
          // return buscar;
    }
        
        
        
      
              
              
              
       
      public void enviarDatos() {

        //int precio1=Integer.parseInt(precio);
        if (validaCampos()) {
            //telefonos = devuelveTelefonos();
            //if (telefonos != null) {
            // if (sinTelefono == false) {
            asignaValores();
            try {
              
                
                
                
                
                
                
               
                BuscaMarca(marca);
                BuscaModelo(modelo);
                
               
                BuscaAñoRegistro(AñoEmision);
                BuscaAñoEmision(AñoRegistro);
                Insertafecha(diaRegistro, mes, AñoRegistro);
                
                BuscaPlaca(placa, numeroEconomico, capacidad, modelo, marca, diaRegistro, AñoEmision, mes, AñoRegistro);
                
                
                
                
                
                
                
    

              
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar autobuses");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblPlaca.setText("Placa");
        JpnlLienzo.add(JlblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 50, -1));

        JtxtPlaca.setToolTipText("Guiese del siguiente ejemplo: QSR-45678");
        JtxtPlaca.setBorder(null);
        JpnlLienzo.add(JtxtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 129, -1));
        JpnlLienzo.add(JspPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 129, 10));

        JlblCapacidad.setText("Capacidad");
        JpnlLienzo.add(JlblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 60, -1));

        JtxtCapacidad.setToolTipText("Solo ingrese dos numero señalando la capacidad");
        JtxtCapacidad.setBorder(null);
        JpnlLienzo.add(JtxtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 129, -1));
        JpnlLienzo.add(JspCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 129, 10));

        JlblMarca.setText("Marca");
        JpnlLienzo.add(JlblMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 129, -1));

        JtxtMarca.setToolTipText("Solo ingrese letras");
        JtxtMarca.setBorder(null);
        JpnlLienzo.add(JtxtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 129, -1));
        JpnlLienzo.add(JspMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 129, 10));

        JlblModelo.setText("Modelo");
        JpnlLienzo.add(JlblModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 129, -1));

        JtxtModelo.setToolTipText("Puede ingresar letras y numeros");
        JtxtModelo.setBorder(null);
        JpnlLienzo.add(JtxtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 129, -1));
        JpnlLienzo.add(JspModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 129, 10));

        JlblAnioR.setText("Año de Registro");
        JpnlLienzo.add(JlblAnioR, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 129, -1));

        JtxtAnioR.setToolTipText("Solo ingrese los 4 numeros del año");
        JtxtAnioR.setBorder(null);
        JpnlLienzo.add(JtxtAnioR, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 129, -1));
        JpnlLienzo.add(JspAnioR, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 129, 10));

        JlblDia.setText("Dia de Registro");
        JpnlLienzo.add(JlblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 129, -1));

        JlblAnioE.setText("Año de Emision");
        JpnlLienzo.add(JlblAnioE, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 129, -1));

        JtxtAnioE.setToolTipText("Solo ingrese los 4  numero del año");
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

        JtxtNumEconomico.setToolTipText("Solo ingrese numen un maximo de 5 digitos");
        JtxtNumEconomico.setBorder(null);
        JpnlLienzo.add(JtxtNumEconomico, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 129, -1));
        JpnlLienzo.add(JspNumEconomico, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 129, 10));

        JlblMesRegistro.setText("Mes de Registro");
        JpnlLienzo.add(JlblMesRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, -1, -1));

        JcmbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JpnlLienzo.add(JcmbxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoRegistroAutobuses.png"))); // NOI18N
        JpnlLienzo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JpnlLienzo, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        try {
            String AñoEmision= JtxtAnioE.getText();
            String AñoRegistro=JtxtAnioR.getText();
            int añoEmision= Integer.parseInt(AñoEmision);
            int añoRegistro= Integer.parseInt(AñoRegistro);
            
            if (añoEmision <= añoRegistro) {
                 enviarDatos();
            }else{
            
             CMensajes.msg_error("El año de Emision no puede ser mayor al año de Registro ", "Registro placa");
            }
        } catch (Exception e) {
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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
