package ventanas;
// Importacion de los Frames de Consulta

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import ventanas.Consultas.JfAutobusConsulta;
import ventanas.Consultas.JfBoletosConsulta;
import ventanas.Consultas.JfClientesConsulta;
import ventanas.Consultas.JfConduceConsulta;
import ventanas.Consultas.JfConductorConsulta;
import ventanas.Consultas.JfParadasConsulta;
import ventanas.Consultas.JfPasajeroConsulta;
import ventanas.Consultas.JfReembolsoConsulta;
import ventanas.Consultas.JfRutasConsulta;
import ventanas.Consultas.JfTerminalesConsulta;
import ventanas.Consultas.JfViajesConsulta;
// Importacion de los Frames de Registro
import ventanas.Registros.JfRegistroAutobuses;
import ventanas.Consultas.JfBoletoConsulta;
import ventanas.Consultas.JfConsultasComplejas;
import ventanas.Registros.JfRegistroConductores;
import ventanas.Registros.JfRegistroParadas;
import ventanas.Registros.JfRegistroRuta;
import ventanas.Registros.JfRegistroTerminal;
import ventanas.Registros.JfRegistroViajaConduce;

public class JfPrincipal extends javax.swing.JFrame {

    public JfPrincipal() {
        initComponents();
    }

    /*  Metodo que permite crear JFrame, recibiendo un objeto de tipo frame
        , el titulo que tendra y las medidas de este*/
    public void creaFrame(JFrame frm, String titulo, int ancho, int alto) {
        //Hacemos visible al nuevo frame
        frm.setVisible(true);
        // Centramos el frame
        frm.setLocationRelativeTo(null);
        // Asignamos las medidas
        frm.setSize(ancho, alto);
        // No permitimos que cambien las medidas
        frm.setResizable(false);
        // Agregamos un titulo
        frm.setTitle(titulo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JlblFondo = new javax.swing.JLabel();
        JmbListaDeOpciones = new javax.swing.JMenuBar();
        JmnBoletos = new javax.swing.JMenu();
        JmiComprar = new javax.swing.JMenuItem();
        JmiConsultarBoletos = new javax.swing.JMenuItem();
        JmiReembolso = new javax.swing.JMenuItem();
        JmnTerminales = new javax.swing.JMenu();
        JmnAgregarTP = new javax.swing.JMenu();
        JmiTerminales = new javax.swing.JMenuItem();
        JmiParadas = new javax.swing.JMenuItem();
        JmnBuscar = new javax.swing.JMenu();
        JmiBTerminales = new javax.swing.JMenuItem();
        JmiBParadas = new javax.swing.JMenuItem();
        JmnRutas = new javax.swing.JMenu();
        JmiAgregarRutas = new javax.swing.JMenuItem();
        JmiBuscarRutas = new javax.swing.JMenuItem();
        JmnUsuarios = new javax.swing.JMenu();
        JmnConductores = new javax.swing.JMenu();
        JmiAgregarConductores = new javax.swing.JMenuItem();
        JmiBuscarConductores = new javax.swing.JMenuItem();
        JmiBuscarClientes = new javax.swing.JMenuItem();
        JmiBuscarPasajeros = new javax.swing.JMenuItem();
        JmnViajes = new javax.swing.JMenu();
        JmiAgregarViajes = new javax.swing.JMenuItem();
        JmiAsignarConductores = new javax.swing.JMenuItem();
        JmnBuscarViajes = new javax.swing.JMenu();
        JmiBViajes = new javax.swing.JMenuItem();
        JmiBConductores = new javax.swing.JMenuItem();
        JmnAutobuses = new javax.swing.JMenu();
        JmnAgregarAutobuses = new javax.swing.JMenuItem();
        JmiBuscarAutobuses = new javax.swing.JMenuItem();
        JmnConsultasComplejas = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));

        JlblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(JlblFondo)
                .addGap(60, 60, 60))
        );
        JpnlLienzoLayout.setVerticalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JlblFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        JmnBoletos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/boleto.png"))); // NOI18N
        JmnBoletos.setText("Boletos");

        JmiComprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/vender.png"))); // NOI18N
        JmiComprar.setText("Comprar");
        JmiComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiComprarActionPerformed(evt);
            }
        });
        JmnBoletos.add(JmiComprar);

        JmiConsultarBoletos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/consulta.png"))); // NOI18N
        JmiConsultarBoletos.setText("Buscar boletos");
        JmiConsultarBoletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiConsultarBoletosActionPerformed(evt);
            }
        });
        JmnBoletos.add(JmiConsultarBoletos);

        JmiReembolso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/reembolso.png"))); // NOI18N
        JmiReembolso.setText("Reembolso");
        JmiReembolso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiReembolsoActionPerformed(evt);
            }
        });
        JmnBoletos.add(JmiReembolso);

        JmbListaDeOpciones.add(JmnBoletos);

        JmnTerminales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/terminal.png"))); // NOI18N
        JmnTerminales.setText("Terminales");

        JmnAgregarTP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/agregar.png"))); // NOI18N
        JmnAgregarTP.setText("Agregar");

        JmiTerminales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/terminal.png"))); // NOI18N
        JmiTerminales.setText("Terminales");
        JmiTerminales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiTerminalesActionPerformed(evt);
            }
        });
        JmnAgregarTP.add(JmiTerminales);

        JmiParadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/paradas.png"))); // NOI18N
        JmiParadas.setText("Paradas");
        JmiParadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiParadasActionPerformed(evt);
            }
        });
        JmnAgregarTP.add(JmiParadas);

        JmnTerminales.add(JmnAgregarTP);

        JmnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/consulta.png"))); // NOI18N
        JmnBuscar.setText("Buscar");

        JmiBTerminales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/terminal.png"))); // NOI18N
        JmiBTerminales.setText("Terminales");
        JmiBTerminales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBTerminalesActionPerformed(evt);
            }
        });
        JmnBuscar.add(JmiBTerminales);

        JmiBParadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/paradas.png"))); // NOI18N
        JmiBParadas.setText("Paradas");
        JmiBParadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBParadasActionPerformed(evt);
            }
        });
        JmnBuscar.add(JmiBParadas);

        JmnTerminales.add(JmnBuscar);

        JmbListaDeOpciones.add(JmnTerminales);

        JmnRutas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/ruta.png"))); // NOI18N
        JmnRutas.setText("Rutas");

        JmiAgregarRutas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/agregar.png"))); // NOI18N
        JmiAgregarRutas.setText("Agregar rutas");
        JmiAgregarRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiAgregarRutasActionPerformed(evt);
            }
        });
        JmnRutas.add(JmiAgregarRutas);

        JmiBuscarRutas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/consulta.png"))); // NOI18N
        JmiBuscarRutas.setText("Buscar rutas");
        JmiBuscarRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBuscarRutasActionPerformed(evt);
            }
        });
        JmnRutas.add(JmiBuscarRutas);

        JmbListaDeOpciones.add(JmnRutas);

        JmnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/todos.png"))); // NOI18N
        JmnUsuarios.setText("Usuarios");

        JmnConductores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/conductor.png"))); // NOI18N
        JmnConductores.setText("Conductores");

        JmiAgregarConductores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/asignarConductor.png"))); // NOI18N
        JmiAgregarConductores.setText("Agregar conductor");
        JmiAgregarConductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiAgregarConductoresActionPerformed(evt);
            }
        });
        JmnConductores.add(JmiAgregarConductores);

        JmiBuscarConductores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/consulta.png"))); // NOI18N
        JmiBuscarConductores.setText("Buscar conductor");
        JmiBuscarConductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBuscarConductoresActionPerformed(evt);
            }
        });
        JmnConductores.add(JmiBuscarConductores);

        JmnUsuarios.add(JmnConductores);

        JmiBuscarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/cliente.png"))); // NOI18N
        JmiBuscarClientes.setText("Buscar clientes");
        JmiBuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBuscarClientesActionPerformed(evt);
            }
        });
        JmnUsuarios.add(JmiBuscarClientes);

        JmiBuscarPasajeros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/pasajeros.png"))); // NOI18N
        JmiBuscarPasajeros.setText("Buscar pasajeros");
        JmiBuscarPasajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBuscarPasajerosActionPerformed(evt);
            }
        });
        JmnUsuarios.add(JmiBuscarPasajeros);

        JmbListaDeOpciones.add(JmnUsuarios);

        JmnViajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/viajes.png"))); // NOI18N
        JmnViajes.setText("Viajes");

        JmiAgregarViajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/creaViaje.png"))); // NOI18N
        JmiAgregarViajes.setText("Crear viaje");
        JmiAgregarViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiAgregarViajesActionPerformed(evt);
            }
        });
        JmnViajes.add(JmiAgregarViajes);

        JmiAsignarConductores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/asignarConductor.png"))); // NOI18N
        JmiAsignarConductores.setText("Asignar conductor");
        JmiAsignarConductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiAsignarConductoresActionPerformed(evt);
            }
        });
        JmnViajes.add(JmiAsignarConductores);

        JmnBuscarViajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/consulta.png"))); // NOI18N
        JmnBuscarViajes.setText("Buscar");

        JmiBViajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/viaje.png"))); // NOI18N
        JmiBViajes.setText("Viajes");
        JmiBViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBViajesActionPerformed(evt);
            }
        });
        JmnBuscarViajes.add(JmiBViajes);

        JmiBConductores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/conductor.png"))); // NOI18N
        JmiBConductores.setText("Conductores asignados");
        JmiBConductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBConductoresActionPerformed(evt);
            }
        });
        JmnBuscarViajes.add(JmiBConductores);

        JmnViajes.add(JmnBuscarViajes);

        JmbListaDeOpciones.add(JmnViajes);

        JmnAutobuses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/autobus.png"))); // NOI18N
        JmnAutobuses.setText("Autobuses");

        JmnAgregarAutobuses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/agregar.png"))); // NOI18N
        JmnAgregarAutobuses.setText("Agregar autobuses");
        JmnAgregarAutobuses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmnAgregarAutobusesActionPerformed(evt);
            }
        });
        JmnAutobuses.add(JmnAgregarAutobuses);

        JmiBuscarAutobuses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/consulta.png"))); // NOI18N
        JmiBuscarAutobuses.setText("Buscar autobuses");
        JmiBuscarAutobuses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiBuscarAutobusesActionPerformed(evt);
            }
        });
        JmnAutobuses.add(JmiBuscarAutobuses);

        JmbListaDeOpciones.add(JmnAutobuses);

        JmnConsultasComplejas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Iconos/consulta.png"))); // NOI18N
        JmnConsultasComplejas.setText("Consultas Complejas");
        JmnConsultasComplejas.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                JmnConsultasComplejasMenuSelected(evt);
            }
        });
        JmbListaDeOpciones.add(JmnConsultasComplejas);

        setJMenuBar(JmbListaDeOpciones);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JmiComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiComprarActionPerformed
        // Instanciamos el JfRegistroBoleto *Importen el Frame*
        JfBoletoConsulta rb = new JfBoletoConsulta();
        // 300,300
        creaFrame(rb, "Comprar", 315, 338);
    }//GEN-LAST:event_JmiComprarActionPerformed

    private void JmiConsultarBoletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiConsultarBoletosActionPerformed
        // Instanciamos JfBoletosConsulta
        JfBoletosConsulta bc = new JfBoletosConsulta();
        // 848, 300
        creaFrame(bc, "Buscar boletos", 895, 350);
    }//GEN-LAST:event_JmiConsultarBoletosActionPerformed

    private void JmiReembolsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiReembolsoActionPerformed
        // Instanciamos JfReembolsoConsulta
        JfReembolsoConsulta rc = new JfReembolsoConsulta();
        // 647, 365
        creaFrame(rc, "Reembolsos", 660, 401);
    }//GEN-LAST:event_JmiReembolsoActionPerformed

    private void JmiTerminalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiTerminalesActionPerformed
        // Instanciamos JfRegistroTerminales
        JfRegistroTerminal rt = new JfRegistroTerminal();
        // 610, 190
        creaFrame(rt, "Agregar terminal", 615, 225);
    }//GEN-LAST:event_JmiTerminalesActionPerformed

    private void JmiParadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiParadasActionPerformed
        // Instanciamos JfRegistroParadas
        JfRegistroParadas rPrds = new JfRegistroParadas();
        // 378, 160
        creaFrame(rPrds, "Agregar paradas", 400, 200);
    }//GEN-LAST:event_JmiParadasActionPerformed

    private void JmiBTerminalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBTerminalesActionPerformed

        JfTerminalesConsulta tc;
        try {
            tc = new JfTerminalesConsulta();
            creaFrame(tc, "Buscar terminales", 1013, 335);
        } catch (SQLException ex) {
            Logger.getLogger(JfPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JmiBTerminalesActionPerformed

    private void JmiBParadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBParadasActionPerformed
        // Instanciamos JfParadasConsulta
        JfParadasConsulta pc = new JfParadasConsulta();
        // 625, 384
        creaFrame(pc, "Buscar paradas", 640, 405);

    }//GEN-LAST:event_JmiBParadasActionPerformed

    private void JmiAgregarRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiAgregarRutasActionPerformed
        // Instanciamos JfRegistroRuta
        JfRegistroRuta rr = new JfRegistroRuta();
        // 700, 240
        creaFrame(rr, "Agregar rutas", 715, 280);
    }//GEN-LAST:event_JmiAgregarRutasActionPerformed

    private void JmiBuscarRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBuscarRutasActionPerformed
        // Instanciamos JfRutasConsultas
        JfRutasConsulta rtsc = new JfRutasConsulta();
        // 610, 311
        creaFrame(rtsc, "Buscar rutas", 625, 350);
    }//GEN-LAST:event_JmiBuscarRutasActionPerformed

    private void JmiAgregarConductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiAgregarConductoresActionPerformed
        // Instanciamos JfRegistroConductores
        JfRegistroConductores rCndt = new JfRegistroConductores();
        // 423, 250
        creaFrame(rCndt, "Agregar conductores", 435, 288);
    }//GEN-LAST:event_JmiAgregarConductoresActionPerformed

    private void JmiBuscarConductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBuscarConductoresActionPerformed
        //Instanciamos JfConductorConsulta
        JfConductorConsulta cc = new JfConductorConsulta();
        // 822, 220
        creaFrame(cc, "Buscar conductores", 836, 266);
    }//GEN-LAST:event_JmiBuscarConductoresActionPerformed

    private void JmiBuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBuscarClientesActionPerformed
        // Instanciamos JfClientesConsulta
        JfClientesConsulta cltsC = new JfClientesConsulta();
        // 780, 440
        creaFrame(cltsC, "Buscar clientes", 790, 476);
    }//GEN-LAST:event_JmiBuscarClientesActionPerformed

    private void JmiBuscarPasajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBuscarPasajerosActionPerformed
        // Instanciamos JfPasajeroConsulta
        JfPasajeroConsulta psjC = new JfPasajeroConsulta();
        // 735, 412
        creaFrame(psjC, "Buscar pasajeros", 746, 449);
    }//GEN-LAST:event_JmiBuscarPasajerosActionPerformed

    private void JmiAgregarViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiAgregarViajesActionPerformed
        // Instanciamos JfRegistroViaja
        JfRegistroViajaConduce rVC = new JfRegistroViajaConduce();
        // 426, 212
        creaFrame(rVC, "Crear viaje", 454, 277);
    }//GEN-LAST:event_JmiAgregarViajesActionPerformed

    private void JmiAsignarConductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiAsignarConductoresActionPerformed
        // Instanciamos Jf
    }//GEN-LAST:event_JmiAsignarConductoresActionPerformed

    private void JmiBViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBViajesActionPerformed
        // Instanciamos JfViajessConsulta
        JfViajesConsulta vc = new JfViajesConsulta();
        // 540, 304
        creaFrame(vc, "Buscar viajes", 580, 340);
    }//GEN-LAST:event_JmiBViajesActionPerformed

    private void JmiBConductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBConductoresActionPerformed
        // Instanciamos JfConduceConsulta
        JfConduceConsulta cc = new JfConduceConsulta();
        // 667, 310
        creaFrame(cc, "Buscar conductores asignados", 700, 350);
    }//GEN-LAST:event_JmiBConductoresActionPerformed

    private void JmnAgregarAutobusesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmnAgregarAutobusesActionPerformed
        // Instanciamos JfRegistroAutobuses
        JfRegistroAutobuses ra = new JfRegistroAutobuses();
        // 615, 270 
        creaFrame(ra, "Agregar autobuses", 624, 315);
    }//GEN-LAST:event_JmnAgregarAutobusesActionPerformed

    private void JmiBuscarAutobusesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiBuscarAutobusesActionPerformed
        // Instanciamos JfAutobusesConsulta
        JfAutobusConsulta ac = new JfAutobusConsulta();
        // 692, 338
        creaFrame(ac, "Buscar autobuses", 709, 380);
    }//GEN-LAST:event_JmiBuscarAutobusesActionPerformed

    private void JmnConsultasComplejasMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_JmnConsultasComplejasMenuSelected
        // Instanciamos JfConsultasComplejas
        JfConsultasComplejas cc = new JfConsultasComplejas();
        // 724, 402
        creaFrame(cc, "Consultas Compljeas", 764, 452);
    }//GEN-LAST:event_JmnConsultasComplejasMenuSelected

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
            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlblFondo;
    private javax.swing.JMenuBar JmbListaDeOpciones;
    private javax.swing.JMenuItem JmiAgregarConductores;
    private javax.swing.JMenuItem JmiAgregarRutas;
    private javax.swing.JMenuItem JmiAgregarViajes;
    private javax.swing.JMenuItem JmiAsignarConductores;
    private javax.swing.JMenuItem JmiBConductores;
    private javax.swing.JMenuItem JmiBParadas;
    private javax.swing.JMenuItem JmiBTerminales;
    private javax.swing.JMenuItem JmiBViajes;
    private javax.swing.JMenuItem JmiBuscarAutobuses;
    private javax.swing.JMenuItem JmiBuscarClientes;
    private javax.swing.JMenuItem JmiBuscarConductores;
    private javax.swing.JMenuItem JmiBuscarPasajeros;
    private javax.swing.JMenuItem JmiBuscarRutas;
    private javax.swing.JMenuItem JmiComprar;
    private javax.swing.JMenuItem JmiConsultarBoletos;
    private javax.swing.JMenuItem JmiParadas;
    private javax.swing.JMenuItem JmiReembolso;
    private javax.swing.JMenuItem JmiTerminales;
    private javax.swing.JMenuItem JmnAgregarAutobuses;
    private javax.swing.JMenu JmnAgregarTP;
    private javax.swing.JMenu JmnAutobuses;
    private javax.swing.JMenu JmnBoletos;
    private javax.swing.JMenu JmnBuscar;
    private javax.swing.JMenu JmnBuscarViajes;
    private javax.swing.JMenu JmnConductores;
    private javax.swing.JMenu JmnConsultasComplejas;
    private javax.swing.JMenu JmnRutas;
    private javax.swing.JMenu JmnTerminales;
    private javax.swing.JMenu JmnUsuarios;
    private javax.swing.JMenu JmnViajes;
    private javax.swing.JPanel JpnlLienzo;
    // End of variables declaration//GEN-END:variables
}
