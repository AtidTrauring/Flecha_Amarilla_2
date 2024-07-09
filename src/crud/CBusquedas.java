package crud;

import java.util.ArrayList;
import java.sql.SQLException;

public class CBusquedas {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

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
//        return cnslt.buscarCon7(consulta);
        return cnslt.buscarValores(consulta, 7);

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
//        return cnslt.buscarCon7(consulta);
        return cnslt.buscarValores(consulta, 7);
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
//        return cnslt.buscarCon6(consulta);
        return cnslt.buscarValores(consulta, 6);
    }

    public ArrayList<String[]> buscarPrueba(int camposRecibidos) throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat, cliente.correo  FROM cliente INNER JOIN persona ON persona.Id_persona = cliente.Id_persona;";
        return cnslt.buscarValores(consulta, camposRecibidos);
    }

    public ArrayList<String[]> buscarClientes() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat, cliente.correo  FROM cliente INNER JOIN persona ON persona.Id_persona = cliente.Id_persona;";
//        return cnslt.buscarCon4(consulta);
        return cnslt.buscarValores(consulta, 4);
    }

    public ArrayList<String[]> buscarPasajeros() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat, pasajero.tipoPasajero, pasajero.descuento FROM pasajero INNER JOIN persona ON pasajero.Id_persona = persona.Id_persona";
//        return cnslt.buscarCon5(consulta);
        return cnslt.buscarValores(consulta, 5);
    }

    public ArrayList<String[]> buscarConductores() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat, telefono_persona.telefono FROM conductor INNER JOIN persona ON conductor.Id_persona = persona.Id_persona INNER JOIN telefono_persona ON telefono_persona.Id_persona = persona.Id_persona";
//        return cnslt.buscarCon4(consulta);
        return cnslt.buscarValores(consulta, 4);
    }

    public String buscarConductor(int id) throws SQLException {
        String idConductor = cnslt.buscarValor("SELECT conductor.Id_conductor FROM flecha_amarilla.conductor WHERE conductor.Id_persona = " + id);
        return idConductor;
    }

    public ArrayList<String[]> buscarConductoresCompletos() throws SQLException {
        consulta = "SELECT conductor.id_conductor, persona.nombre, persona.ApPat, persona.ApMat, telefono_persona.telefono FROM conductor INNER JOIN persona ON conductor.Id_persona = persona.Id_persona INNER JOIN telefono_persona ON telefono_persona.Id_persona = persona.Id_persona";
//        return cnslt.buscarCon5(consulta);
        return cnslt.buscarValores(consulta, 5);
    }

    public ArrayList<String[]> buscaUsuarios() throws SQLException {
        consulta = "SELECT persona.nombre, persona.ApPat, persona.ApMat FROM persona";
//        return cnslt.buscarCon3(consulta);
        return cnslt.buscarValores(consulta, 3);
    }

    public ArrayList<String[]> buscaAutobuses() throws SQLException {
        consulta = "SELECT marca.nombre, modelo.nombre, autobus.capacidad,"
                + " CONCAT(fecha.dia, ' - ', mes.mes, ' - ', anio.anio) FROM autobus,"
                + " fecha, mes, modelo, anio, marca WHERE autobus.Id_modelo = modelo.Id_modelo"
                + " AND autobus.Id_fecha = fecha.Id_fecha AND fecha.Id_mes = mes.Id_mes "
                + " AND fecha.Id_anio = anio.Id_anio AND modelo.Id_marca = marca.Id_marca";
//        return cnslt.buscarCon4(consulta);
        return cnslt.buscarValores(consulta, 4);
    }

    public ArrayList<String[]> buscaParadas() throws SQLException {
        consulta = " SELECT DISTINCT ruta.nombre,terminal.nombre FROM rutaterminal "
                + " INNER JOIN ruta ON ruta.Id_ruta = rutaterminal.Id_ruta "
                + " INNER JOIN terminal ON terminal.Id_terminal = rutaterminal.Id_terminal ";
//        return cnslt.buscarCon2(consulta);
        return cnslt.buscarValores(consulta, 2);
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
//        return cnslt.buscarCon8(consulta);
        return cnslt.buscarValores(consulta, 8);
    }

    public ArrayList<String[]> buscaRutas() throws SQLException {
        consulta = "SELECT DISTINCT ruta.nombre AS NombreRuta,terminal_origen.nombre AS Origen,"
                + " terminal_destino.nombre AS Destino, ruta.distancia, ruta.hora_salida,"
                + " ruta.hora_llegada,ruta.duracion_ruta,ruta.precio FROM ruta"
                + " JOIN origen ON ruta.Id_origen = origen.Id_origen"
                + " JOIN destino ON ruta.Id_destino = destino.Id_destino"
                + " JOIN terminal AS terminal_origen ON origen.Id_terminal = terminal_origen.Id_terminal"
                + " JOIN terminal AS terminal_destino ON destino.Id_terminal = terminal_destino.Id_terminal";
//        return cnslt.buscarCon8(consulta);
        return cnslt.buscarValores(consulta, 8);
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
//        return cnslt.buscarCon8(consulta);
        return cnslt.buscarValores(consulta, 8);
    }

    public int obtenIdFinalPersona() throws SQLException {
        consulta = "SELECT MAX(Id_persona) FROM flecha_amarilla.persona;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalTelefono() throws SQLException {
        consulta = "SELECT MAX(Id_telefonoPersona) FROM flecha_amarilla.telefono_persona;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalCliente() throws SQLException {
        consulta = "SELECT MAX(Id_cliente) FROM flecha_amarilla.cliente;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalConductor() throws SQLException {
        consulta = "SELECT MAX(Id_conductor) FROM flecha_amarilla.conductor;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalPasajero() throws SQLException {
        consulta = "SELECT MAX(Id_pasajero) FROM flecha_amarilla.pasajero;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public ArrayList<String[]> consulta8() throws SQLException {
        consulta = "SELECT CONCAT(p.nombre, ' ', p.ApPat, ' ', p.ApMat) AS Nombre_Pasajero,"
                + " CONCAT(pCon.nombre, ' ', pCon.ApPat, ' ', pCon.ApMat) AS Nombre_Conductor,"
                + " ruta.nombre AS Nombre_Ruta, CONCAT(fecha.dia,' ', mes.mes,' ',anio.anio)"
                + " AS Fecha FROM boleto b, pasajero pa, persona p, ruta, rutaAutobus ra,"
                + " autobusConductor ac, conductor co, persona pCon, fecha, mes, anio"
                + " WHERE b.Id_pasajero = pa.Id_pasajero AND pa.Id_persona = p.Id_persona"
                + " AND b.Id_ruta = ruta.Id_ruta AND ruta.Id_ruta = ra.Id_ruta AND ra.Id_autobus"
                + " = ac.Id_autobus AND ac.Id_conductor = co.Id_conductor AND co.Id_persona"
                + " = pCon.Id_persona AND b.Id_fecha = fecha.Id_fecha AND fecha.Id_mes = mes.Id_mes"
                + " AND fecha.Id_anio = anio.Id_anio AND fecha.dia = 16 AND mes.mes = 'Junio' AND"
                + " anio.anio = 2024;";
        return cnslt.buscarValores(consulta, 4);
    }

    public ArrayList<String[]> consulta12() throws SQLException {
        consulta = "SELECT DISTINCT CONCAT( p.nombre, ' ', p.ApPat, ' ', p.ApMat ) AS Nombre_Chofer,"
                + " r.nombre AS Nombre_Ruta, cor.nombre AS Ciudad_Origen, cde.nombre AS Ciudad_Destino,"
                + " ter.nombre AS Terminal_Parada, CONCAT( per.nombre, ' ', per.ApPat, ' ', per.ApMat )"
                + " AS Nombre_cliente FROM ruta r, rutaConductor rc, conductor c, persona p, origen o,"
                + " destino d, terminal tor, direccion dor, ciudad cor, terminal tde, direccion dde,"
                + " ciudad cde, rutaTerminal rt, terminal ter, direccion dir_parada, boleto b, pasajero pas,"
                + " persona per WHERE r.Id_ruta = rc.Id_ruta AND rc.Id_conductor = c.Id_conductor AND"
                + " c.Id_persona = p.Id_persona AND r.Id_origen = o.Id_origen AND r.Id_destino = d.Id_destino"
                + " AND o.Id_terminal = tor.Id_terminal AND tor.Id_direccion = dor.Id_direccion AND"
                + " dor.Id_ciudad = cor.Id_ciudad AND d.Id_terminal = tde.Id_terminal AND tde.Id_direccion"
                + " = dde.Id_direccion AND dde.Id_ciudad = cde.Id_ciudad AND r.Id_ruta = rt.Id_ruta AND"
                + " rt.Id_terminal = ter.Id_terminal AND ter.Id_direccion = dir_parada.Id_direccion AND"
                + " r.Id_ruta = b.Id_ruta AND b.Id_pasajero = pas.Id_pasajero AND pas.Id_persona"
                + " = per.Id_persona AND cor.nombre = 'Ciudad de México' AND cde.nombre = 'Cancún';";
        return cnslt.buscarValores(consulta, 6);
    }

    public ArrayList<String[]> consulta13() throws SQLException {
        consulta = "SELECT co.nombre AS Ciudad_Origen, cd.nombre AS Ciudad_Destino FROM ruta r JOIN"
                + " origen o ON r.Id_origen = o.Id_origen JOIN destino d ON r.Id_destino = d.Id_destino JOIN"
                + " terminal torigen ON o.Id_terminal = torigen.Id_terminal JOIN direccion dorigen ON"
                + " torigen.Id_direccion = dorigen.Id_direccion JOIN ciudad co ON dorigen.Id_ciudad = co.Id_ciudad JOIN"
                + " terminal tdestino ON d.Id_terminal = tdestino.Id_terminal JOIN direccion ddestino ON"
                + " tdestino.Id_direccion = ddestino.Id_direccion JOIN ciudad cd ON ddestino.Id_ciudad = cd.Id_ciudad JOIN"
                + " rutaConductor rc ON r.Id_ruta = rc.Id_ruta GROUP BY r.Id_ruta, co.nombre, cd.nombre"
                + " HAVING COUNT(DISTINCT rc.Id_conductor) = 3;";
        return cnslt.buscarValores(consulta, 2);
    }

    public ArrayList<String[]> consulta14() throws SQLException {
        consulta = "SELECT asiento.asiento AS 'Nombre Asiento', marca.nombre AS 'Marca', modelo.nombre AS 'Modelo' FROM"
                + " autobus JOIN rutaAutobus ON autobus.Id_autobus = rutaAutobus.Id_autobus JOIN"
                + " ruta ON rutaAutobus.Id_ruta = ruta.Id_ruta JOIN asiento ON"
                + " autobus.Id_autobus = asiento.Id_autobus LEFT JOIN boleto ON"
                + " asiento.Id_asiento = boleto.Id_asiento JOIN fecha ON"
                + " rutaAutobus.Id_fecha = fecha.Id_fecha JOIN origen ON"
                + " ruta.Id_origen = origen.Id_origen JOIN destino ON"
                + " ruta.Id_destino = destino.Id_destino JOIN ciudad AS"
                + " ciudad_origen ON origen.Id_terminal = ciudad_origen.Id_ciudad JOIN"
                + " ciudad AS ciudad_destino ON destino.Id_terminal = ciudad_destino.Id_ciudad JOIN"
                + " mes ON fecha.Id_mes = mes.Id_mes JOIN anio ON fecha.Id_anio = anio.Id_anio JOIN"
                + " modelo ON autobus.Id_modelo = modelo.Id_modelo JOIN marca ON"
                + " modelo.Id_marca = marca.Id_marca WHERE ciudad_origen.nombre = 'Ciudad de Mexico' AND"
                + " ciudad_destino.nombre = 'Morelia' AND fecha.dia = 20 AND mes.mes = 'Junio' AND"
                + " anio.anio = 2024 AND boleto.Id_boleto IS NULL;";
        return cnslt.buscarValores(consulta, 3);
    }

    public ArrayList<String> buscaAsientos(String destino, String origen, String mes, String dia, String anio) throws SQLException {
        consulta = "SELECT asiento.asiento AS 'Nombre Asiento'\n"
                + "FROM autobus\n"
                + "JOIN rutaAutobus ON autobus.Id_autobus = rutaAutobus.Id_autobus\n"
                + "JOIN ruta ON rutaAutobus.Id_ruta = ruta.Id_ruta\n"
                + "JOIN asiento ON autobus.Id_autobus = asiento.Id_autobus\n"
                + "LEFT JOIN boleto ON asiento.Id_asiento = boleto.Id_asiento\n"
                + "JOIN fecha ON rutaAutobus.Id_fecha = fecha.Id_fecha\n"
                + "JOIN origen ON ruta.Id_origen = origen.Id_origen\n"
                + "JOIN destino ON ruta.Id_destino = destino.Id_destino\n"
                + "JOIN ciudad AS ciudad_origen ON origen.Id_terminal = ciudad_origen.Id_ciudad\n"
                + "JOIN ciudad AS ciudad_destino ON destino.Id_terminal = ciudad_destino.Id_ciudad\n"
                + "JOIN mes ON fecha.Id_mes = mes.Id_mes\n"
                + "JOIN anio ON fecha.Id_anio = anio.Id_anio\n"
                + "JOIN modelo ON autobus.Id_modelo = modelo.Id_modelo\n"
                + "JOIN marca ON modelo.Id_marca = marca.Id_marca\n"
                + "WHERE ciudad_origen.nombre = '" + origen + "'\n"
                + "  AND ciudad_destino.nombre = '" + destino + "'\n"
                + "  AND fecha.dia = " + dia + "\n"
                + "  AND mes.mes = '" + mes + "'\n"
                + "  AND anio.anio = " + anio + "\n"
                + "  AND boleto.Id_boleto IS NULL;";
        return cnslt.buscarValoresCombos(consulta);
    }

    public int obtenIdFinalRuta() throws SQLException {
        consulta = "SELECT MAX(Id_ruta) FROM flecha_amarilla.ruta;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdRuta(String ruta) throws SQLException {
        consulta = "SELECT\n"
                + "ruta.Id_ruta\n"
                + "FROM\n"
                + "ruta\n"
                + "WHERE\n"
                + "ruta.nombre = '" + ruta + "';";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFecha(int dia, String mes, int anio) throws SQLException {
        consulta = "SELECT\n"
                + "fecha.Id_fecha\n"
                + "FROM\n"
                + "fecha\n"
                + "INNER JOIN anio ON anio.Id_anio = fecha.Id_anio\n"
                + "INNER JOIN mes ON mes.Id_mes = fecha.Id_mes\n"
                + "WHERE\n"
                + "fecha.dia =" + dia + " AND\n"
                + "mes.mes = '" + mes + "' AND\n"
                + "anio.anio = " + 2024 + ";";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdtTerminal(String terminal) throws SQLException {
        consulta = "SELECT\n"
                + "terminal.Id_terminal\n"
                + "FROM\n"
                + "terminal\n"
                + "WHERE\n"
                + "terminal.nombre = '" + terminal + "';";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalParada() throws SQLException {
        consulta = "SELECT MAX(Id_RutTer) FROM flecha_amarilla.rutaterminal;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalAutobusConductor() throws SQLException {
        consulta = "SELECT MAX(Id_AutCon) FROM flecha_amarilla.autobusconductor;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalRutaAutobus() throws SQLException {
        consulta = "SELECT MAX(Id_RutAut) FROM flecha_amarilla.rutaautobus;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdFinalRutaConductor() throws SQLException {
        consulta = "SELECT MAX(Id_RutCon) FROM flecha_amarilla.rutaconductor;";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdOrigenSeleccionado(String origen) throws SQLException {
        consulta = "SELECT\n"
                + "origen.Id_origen\n"
                + "FROM\n"
                + "origen\n"
                + "INNER JOIN terminal ON terminal.Id_terminal = origen.Id_terminal\n"
                + "WHERE\n"
                + "terminal.nombre ='" + origen + "'";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdDestinoSeleccionado(String destino) throws SQLException {
        consulta = "SELECT\n"
                + "origen.Id_origen\n"
                + "FROM\n"
                + "origen\n"
                + "INNER JOIN terminal ON terminal.Id_terminal = origen.Id_terminal\n"
                + "WHERE\n"
                + "terminal.nombre ='" + destino + "'";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdBuscaConductor(String conductor) throws SQLException {
        consulta = "SELECT\n"
                + "conductor.Id_conductor\n"
                + "FROM\n"
                + "persona\n"
                + "INNER JOIN conductor ON persona.Id_persona = conductor.Id_persona\n"
                + "WHERE\n"
                + "persona.nombre = '" + conductor + "';";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

    public int obtenIdBuscaAutobus(String numAutobus) throws SQLException {
        consulta = "SELECT\n"
                + "autobus.Id_autobus\n"
                + "FROM\n"
                + "autobus\n"
                + "WHERE\n"
                + "autobus.num_economico = '" + numAutobus + "';";
        return Integer.parseInt(cnslt.buscarValor(consulta));
    }

}
