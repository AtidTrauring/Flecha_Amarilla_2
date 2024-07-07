package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CConsultas {

    //************ Atributos ************
    private Connection conn = null;
    private Statement stmt = null; //Capacidad para traducir las query
    private ResultSet rs = null;
    private final CConecta conector = new CConecta();
    private ArrayList<String[]> resultados;
    private ArrayList<String> resultadosSimples;
    private String consulta;

    //************ Metodos ************
    public ArrayList<String> buscarCon1(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultadosSimples = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultadosSimples.add(rs.getString(1));

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultadosSimples;
    }

    public ArrayList<String[]> buscarCon2(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultados.add(new String[]{
                        rs.getString(1),
                        rs.getString(2)});

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultados;
    }

    public ArrayList<String[]> buscarCon3(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultados.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)});

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultados;
    }

    public ArrayList<String[]> buscarCon4(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultados.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)});

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultados;
    }

    public ArrayList<String[]> buscarCon5(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultados.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)});

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultados;
    }

    public ArrayList<String[]> buscarCon6(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultados.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)});

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultados;
    }

    public ArrayList<String[]> buscarCon7(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultados.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)});

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultados;
    }

    public ArrayList<String[]> buscarCon8(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CMensajes.msg_advertencia("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultados.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)});

                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CMensajes.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return resultados;
    }

    public int obtieneIdFinal(String consulta) throws SQLException {
        int id = 1;
        //1. Abrir la conexion
        conn = conector.conecta();
        //2, Ejecutar la query
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                return 0;
            } else {
                while (rs.next()) {
                    if (rs.getString(1) == null) {
                        return 0;
                    } else {
                        id = Integer.parseInt(rs.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            CMensajes.msg_error("Error: \n" + e.getMessage(), "ObtieneIdFinal");
        } finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.desconecta(conn);
        }
        return id;
    }

    public boolean inserta(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2, Ejecutar la query
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
//            PreparedStatement pstmt = conn.prepareStatement();
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            CMensajes.msg_error("Error: \n" + e.getMessage(), "Inserta ");
        } finally {
            //3. Cerrar conex
            conector.desconecta(conn);
        }
        return false;
    }

    public boolean elimina(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conecta();
        //2. Correr la query
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            CMensajes.msg_error("Error: " + e.getMessage(), "Elimina");
        } finally {
            //3. Cerrarla conexion
            conector.desconecta(conn);
        }
        return false;
    }

    public boolean actualiza(String consulta) throws SQLException {
        conn = conector.conecta();
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            CMensajes.msg_error("Error: " + e.getMessage(), "Actualiza Objeto");
        } finally {
            //3. Cerrarla conexion
            conector.desconecta(conn);
        }
        return false;
    }

    public boolean buscar(String consulta) throws SQLException {
        conn = conector.conecta();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                return false;
            } else {
                while (rs.next()) {
                    if (rs.getString(1) == null) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            CMensajes.msg_error("Error: " + e.getMessage(), "Buscar objeto");
        } finally {
            //3. Cerrarla conexion
            conector.desconecta(conn);
        }
        return false;
    }

    // *************** Declaracion de consultas ******************
    /**
     * ***************************CONSULTAS
     *
     ***********************************
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<String[]> buscarReembolso() throws SQLException {
        consulta = "SELECT p.nombre,\n"
                + "       p.ApPat,\n"
                + "       p.ApMat,\n"
                + "       r.cantidad AS cantidad_reembolso,\n"
                + "       f.dia,\n"
                + "       m.mes,\n"
                + "       a.anio\n"
                + "FROM reembolso r\n"
                + "JOIN boleto b ON r.Id_boleto = b.Id_boleto\n"
                + "JOIN pasajero pas ON b.Id_pasajero = pas.Id_pasajero\n"
                + "JOIN persona p ON pas.Id_persona = p.Id_persona\n"
                + "JOIN fecha f ON r.Id_fecha = f.Id_fecha\n"
                + "JOIN mes m ON f.Id_mes = m.Id_mes\n"
                + "JOIN anio a ON f.Id_anio = a.Id_anio;";
        return buscarCon7(consulta);

    }

    public ArrayList<String[]> buscaViaje() throws SQLException {
        consulta = "SELECT t_origen.nombre AS origen,\n"
                + "       t_destino.nombre AS destino,\n"
                + "       a.placa,\n"
                + "       m.nombre AS modelo,\n"
                + "       ma.nombre AS marca,\n"
                + "       f.dia,\n"
                + "       me.mes\n"
                + "FROM ruta r\n"
                + "JOIN terminal t_origen ON r.Id_origen = t_origen.Id_terminal\n"
                + "JOIN terminal t_destino ON r.Id_destino = t_destino.Id_terminal\n"
                + "JOIN rutaAutobus ra ON r.Id_ruta = ra.Id_ruta\n"
                + "JOIN autobus a ON ra.Id_autobus = a.Id_autobus\n"
                + "JOIN modelo m ON a.Id_modelo = m.Id_modelo\n"
                + "JOIN marca ma ON m.Id_marca = ma.Id_marca \n"
                + "JOIN fecha f ON ra.Id_fecha = f.Id_fecha\n"
                + "JOIN mes me ON f.Id_mes = me.Id_mes;";
        return buscarCon7(consulta);
    }

    public ArrayList<String[]> buscaConduce() throws SQLException {
        consulta = "SELECT\n"
                + "    persona.nombre,\n"
                + "    persona.ApPat,\n"
                + "    persona.ApMat,\n"
                + "    autobus.placa,\n"
                + "    marca.nombre AS nombre_marca,\n"
                + "    modelo.nombre AS nombre_modelo\n"
                + "FROM\n"
                + "    autobus\n"
                + "INNER JOIN autobusconductor ON autobus.Id_autobus = autobusconductor.Id_autobus\n"
                + "INNER JOIN conductor ON autobusconductor.Id_conductor = conductor.Id_conductor\n"
                + "INNER JOIN modelo ON autobus.Id_modelo = modelo.Id_modelo\n"
                + "INNER JOIN marca ON modelo.Id_marca = marca.Id_marca\n"
                + "INNER JOIN persona ON conductor.Id_persona = persona.Id_persona;";
        return buscarCon6(consulta);
    }

    public ArrayList<String[]> buscarClientes() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat, cliente.correo  FROM cliente INNER JOIN persona ON persona.Id_persona = cliente.Id_persona;";
        return buscarCon4(consulta);
    }

    public ArrayList<String[]> buscarPasajeros() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat, pasajero.tipoPasajero, pasajero.descuento FROM pasajero INNER JOIN persona ON pasajero.Id_persona = persona.Id_persona";
        return buscarCon5(consulta);
    }

    public ArrayList<String[]> buscarConductores() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat, telefono_persona.telefono FROM conductor INNER JOIN persona ON conductor.Id_persona = persona.Id_persona INNER JOIN telefono_persona ON telefono_persona.Id_persona = persona.Id_persona";
        return buscarCon4(consulta);
    }

    public ArrayList<String[]> buscarConductor(int id) throws SQLException {
        consulta = "SELECT conductor.Id_conductor, conductor.Id_persona FROM flecha_amarilla.conductor WHERE conductor.Id_persona = " + id;
        return buscarCon2(consulta);
    }

    public ArrayList<String[]> buscarConductoresCompletos() throws SQLException {
        consulta = "SELECT conductor.id_conductor, persona.nombre, persona.ApPat, persona.ApMat, telefono_persona.telefono FROM conductor INNER JOIN persona ON conductor.Id_persona = persona.Id_persona INNER JOIN telefono_persona ON telefono_persona.Id_persona = persona.Id_persona";
        return buscarCon5(consulta);
    }

    public ArrayList<String[]> buscaUsuarios() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat FROM persona";
        return buscarCon3(consulta);
    }

    public ArrayList<String[]> buscaAutobuses() throws SQLException {
        consulta = "SELECT marca.nombre, modelo.nombre, autobus.capacidad,"
                + " CONCAT(fecha.dia, ' - ', mes.mes, ' - ', anio.anio) FROM autobus,"
                + " fecha, mes, modelo, anio, marca WHERE autobus.Id_modelo = modelo.Id_modelo"
                + " AND autobus.Id_fecha = fecha.Id_fecha AND fecha.Id_mes = mes.Id_mes "
                + " AND fecha.Id_anio = anio.Id_anio AND modelo.Id_marca = marca.Id_marca";
        return buscarCon4(consulta);
    }

    public ArrayList<String[]> buscaParadas() throws SQLException {
        consulta = " SELECT DISTINCT ruta.nombre,terminal.nombre FROM rutaterminal "
                + " INNER JOIN ruta ON ruta.Id_ruta = rutaterminal.Id_ruta "
                + " INNER JOIN terminal ON terminal.Id_terminal = rutaterminal.Id_terminal ";

        return buscarCon2(consulta);
    }

    public ArrayList<String[]> buscaBoletos() throws SQLException {
        consulta = "SELECT DISTINCT\n"
                + "    asiento.asiento,\n"
                + "    terminal_origen.nombre AS Origen,\n"
                + "    terminal_destino.nombre AS Destino,\n"
                + "    fecha.dia,\n"
                + "    mes.mes,\n"
                + "    anio.anio,\n"
                + "    boleto.tipo_boleto,\n"
                + "    boleto.precioDescuento\n"
                + "FROM boleto\n"
                + "JOIN asiento ON boleto.Id_asiento = asiento.Id_asiento\n"
                + "JOIN ruta ON boleto.Id_ruta = ruta.Id_ruta\n"
                + "JOIN origen ON ruta.Id_origen = origen.Id_origen\n"
                + "JOIN destino ON ruta.Id_destino = destino.Id_destino\n"
                + "JOIN terminal AS terminal_origen ON origen.Id_terminal = terminal_origen.Id_terminal\n"
                + "JOIN terminal AS terminal_destino ON destino.Id_terminal = terminal_destino.Id_terminal\n"
                + "JOIN fecha ON boleto.Id_fecha = fecha.Id_fecha\n"
                + "JOIN mes ON fecha.Id_mes = mes.Id_mes\n"
                + "JOIN anio ON fecha.Id_anio = anio.Id_anio;";
        return buscarCon8(consulta);
    }

    public ArrayList<String[]> buscaRutas() throws SQLException {
        consulta = "SELECT DISTINCT ruta.nombre AS NombreRuta,terminal_origen.nombre AS Origen,"
                + " terminal_destino.nombre AS Destino, ruta.distancia, ruta.hora_salida,"
                + " ruta.hora_llegada,ruta.duracion_ruta,ruta.precio FROM ruta"
                + " JOIN origen ON ruta.Id_origen = origen.Id_origen"
                + " JOIN destino ON ruta.Id_destino = destino.Id_destino"
                + " JOIN terminal AS terminal_origen ON origen.Id_terminal = terminal_origen.Id_terminal"
                + " JOIN terminal AS terminal_destino ON destino.Id_terminal = terminal_destino.Id_terminal";
        return buscarCon8(consulta);
    }

    public ArrayList<String[]> buscaTerminales() throws SQLException {
        consulta = "SELECT\n"
                + "terminal.nombre,\n"
                + "estado.nombre,\n"
                + "ciudad.nombre,\n"
                + "direccion.nombre_calle,\n"
                + "direccion.numero,\n"
                + "colonia.colonia,\n"
                + "codigo_postal.codigo_postal,\n"
                + "telefonoterminal.telefono\n"
                + "FROM\n"
                + "terminal\n"
                + "INNER JOIN direccion ON direccion.Id_direccion = terminal.Id_direccion\n"
                + "INNER JOIN ciudad ON ciudad.Id_ciudad = direccion.Id_ciudad\n"
                + "INNER JOIN estado ON estado.Id_estado = ciudad.Id_estado\n"
                + "INNER JOIN colonia ON colonia.Id_colonia = direccion.Id_colonia\n"
                + "INNER JOIN codigo_postal ON codigo_postal.Id_CP = direccion.Id_CP\n"
                + "INNER JOIN telefonoterminal ON terminal.Id_terminal = telefonoterminal.Id_terminal";
        return buscarCon8(consulta);
    }

    /*                        CARGA DE LOS COMBOBOX                           */
    public ArrayList<String> cargaComboDias() throws SQLException {
        consulta = "SELECT DISTINCT fecha.dia FROM fecha ORDER BY fecha.dia ASC;";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboPlaca() throws SQLException {
        consulta = "SELECT autobus.placa FROM autobus;";
        return buscarCon1(consulta);
    }

    public ArrayList<String[]> cargaComboMeses() throws SQLException {
        consulta = "SELECT mes.Id_mes, mes.mes FROM `mes` ORDER BY mes.Id_mes ASC";
        return buscarCon2(consulta);
    }

    public ArrayList<String[]> cargaComboAnios() throws SQLException {
        consulta = "SELECT anio.Id_anio, anio.anio FROM anio ORDER BY anio.Id_anio ASC";
        return buscarCon2(consulta);
    }

    public ArrayList<String> cargaComboCantidad() throws SQLException {
        consulta = "SELECT anio.anio FROM `anio`";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboMarca() throws SQLException {
        consulta = "SELECT marca.nombre from marca";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboRutas() throws SQLException {
        consulta = "SELECT ruta.nombre from ruta";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboTerminales() throws SQLException {
        consulta = "SELECT terminal.nombre from terminal";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboModelo() throws SQLException {
        consulta = "SELECT DISTINCT modelo.nombre FROM modelo;";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboCapacidad() throws SQLException {
        consulta = "SELECT DISTINCT capacidad FROM autobus ORDER BY capacidad ASC;";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboDuracion() throws SQLException {
        consulta = "SELECT DISTINCT duracion_ruta FROM ruta ORDER BY CAST(SUBSTRING_INDEX(duracion_ruta, ' ', 1) AS UNSIGNED) ASC";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboDistacia() throws SQLException {
        consulta = "SELECT DISTINCT distancia FROM ruta ORDER BY CAST(SUBSTRING_INDEX(distancia, ' ', 1) AS DECIMAL(10, 2)) ASC";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboOrigenes() throws SQLException {
        consulta = "SELECT DISTINCT terminal.nombre \n"
                + "FROM origen \n"
                + "INNER JOIN terminal ON terminal.Id_terminal = origen.Id_terminal\n"
                + "ORDER BY nombre ASC";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboDestinos() throws SQLException {
        consulta = "SELECT DISTINCT terminal.nombre \n"
                + "FROM destino \n"
                + "INNER JOIN terminal ON terminal.Id_terminal = destino.Id_terminal\n"
                + "ORDER BY nombre ASC";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboTipoBoletos() throws SQLException {
        consulta = "SELECT DISTINCT boleto.tipo_boleto FROM boleto";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboPrecio() throws SQLException {
        consulta = "SELECT DISTINCT boleto.precioDescuento FROM boleto ORDER BY boleto.precioDescuento ASC;";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboPrecioRuta() throws SQLException {
        consulta = "SELECT DISTINCT ruta.precio FROM ruta ORDER BY ruta.precio ASC";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboCiudad() throws SQLException {
        consulta = "SELECT ciudad.nombre FROM ciudad";
        return buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboEstado() throws SQLException {
        consulta = "SELECT estado.nombre FROM estado";
        return buscarCon1(consulta);
    }

    public int obtenIdFinalPersona() throws SQLException {
        consulta = "SELECT MAX(Id_persona) FROM flecha_amarilla.persona;";
        return obtieneIdFinal(consulta);
    }

    public int obtenIdFinalTelefono() throws SQLException {
        consulta = "SELECT MAX(Id_telefonoPersona) FROM flecha_amarilla.telefono_persona;";
        return obtieneIdFinal(consulta);
    }

    public int obtenIdFinalCliente() throws SQLException {
        consulta = "SELECT MAX(Id_cliente) FROM flecha_amarilla.cliente;";
        return obtieneIdFinal(consulta);
    }

    public int obtenIdFinalConductor() throws SQLException {
        consulta = "SELECT MAX(Id_conductor) FROM flecha_amarilla.conductor;";
        return obtieneIdFinal(consulta);
    }

    public int obtenIdFinalPasajero() throws SQLException {
        consulta = "SELECT MAX(Id_pasajero) FROM flecha_amarilla.pasajero;";
        return obtieneIdFinal(consulta);
    }

    /**
     * ****************************INSERCIONES********************************
     */
    public boolean insertaTelefonos(int idTelefono, String telefono, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.telefono_persona (`Id_telefonoPersona`, `telefono`, `Id_persona`) VALUES (" + idTelefono + ",'" + telefono + "'," + idPersona + ")";
        return inserta(consulta);
    }

    public boolean insertaPersona(int id, String nombres, String apPaterno, String apMaterno) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.persona (`Id_persona`, `nombre`, `ApPat`, `ApMat`) VALUES('" + id + "','" + nombres + "','" + apPaterno + "','" + apMaterno + "')";
        return inserta(consulta);
    }

    public boolean insertaClientes(int idCliente, String correo, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.cliente (`Id_cliente`, `correo`, `Id_persona`) VALUES ('" + idCliente + "','" + correo + "','" + idPersona + "')";
        return inserta(consulta);
    }

    public boolean insertaPasajeros(int idPasajero, String tipoPasajero, int descuento, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.pasajero (`Id_pasajero`, `tipoPasajero`, `descuento`, `Id_persona`) VALUES ('" + idPasajero + "','" + tipoPasajero + "','" + descuento + "','" + idPersona + "')";
        return inserta(consulta);
    }

    public boolean insertaConductores(int idConductor, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.conductor (`Id_conductor`, `Id_persona`) VALUES ('" + idConductor + "','" + idPersona + "')";
        return inserta(consulta);
    }

    /**
     * ***************************ACTUALIZACIONES*****************************
     */
    public boolean actualizarPersona(String nombre, String ApPat, String ApMat, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.persona SET `nombre`='" + nombre + "',`ApPat`='" + ApPat + "',`ApMat`='" + ApMat + "' WHERE persona.Id_persona = " + id;
        return actualiza(consulta);
    }

    public boolean actualizarTelefono(String tel, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.telefono_persona SET `telefono`='" + tel + "' WHERE telefono_persona.Id_persona = " + id;
        return actualiza(consulta);
    }

    /**
     * ***************************ELIMINACIONES*******************************
     */
    public boolean eliminaTelefono(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.telefono_persona WHERE telefono_persona.Id_persona = " + id;
        return elimina(consulta);
    }

    public boolean eliminaConductor(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.conductor WHERE conductor.Id_persona = " + id;
        return elimina(consulta);
    }

    public boolean eliminaAutbousConductor(int idConductor) throws SQLException {
        consulta = "DELETE FROM `autobusconductor` WHERE autobusconductor.Id_conductor = " + idConductor;
        return elimina(consulta);
    }

    public boolean eliminaRutaConductor(int idConductor) throws SQLException {
        consulta = "DELETE FROM `rutaconductor` WHERE rutaconductor.Id_conductor = " + idConductor;
        return elimina(consulta);
    }

    public boolean eliminaPersona(int id) throws SQLException {
        consulta = "DELETE FROM persona WHERE persona.Id_persona = " + id;
        return elimina(consulta);
    }

}
