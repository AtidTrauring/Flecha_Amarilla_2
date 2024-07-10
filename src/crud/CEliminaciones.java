package crud;

import java.sql.SQLException;

public class CEliminaciones {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    //Rutas
    public boolean eliminarRutaCompleta(int idRuta) throws SQLException {
        String consulta = "DELETE FROM reembolso WHERE Id_boleto IN (SELECT Id_boleto FROM boleto WHERE Id_ruta = " + idRuta + ")";
        if (!cnslt.elimina(consulta)) {
            return false;
        }
        consulta = "DELETE FROM boletoCliente WHERE Id_boleto IN (SELECT Id_boleto FROM boleto WHERE Id_ruta = " + idRuta + ")";
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        consulta = "DELETE FROM boleto WHERE Id_ruta = " + idRuta;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        consulta = "DELETE FROM rutaConductor WHERE Id_ruta = " + idRuta;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        consulta = "DELETE FROM rutaAutobus WHERE Id_ruta = " + idRuta;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        consulta = "DELETE FROM rutaTerminal WHERE Id_ruta = " + idRuta;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        consulta = "DELETE FROM ruta WHERE Id_ruta = " + idRuta;
        return cnslt.elimina(consulta);
    }

    //Viajes
    public boolean eliminaRutaAutobus(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.rutaautobus WHERE rutaautobus.Id_RutAut= " + id;
        return cnslt.elimina(consulta);
    }

    //Terminales
    public boolean eliminarTerminalTelefono(int id) throws SQLException {
        consulta = "DELETE FROM telefonoTerminal WHERE Id_terminal = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarTerminalReembolso(int id) throws SQLException {
        consulta = "DELETE FROM reembolso WHERE id_boleto IN (SELECT id_boleto FROM boleto WHERE id_ruta IN (SELECT id_ruta FROM ruta WHERE id_origen IN ("
                + "SELECT id_origen FROM origen WHERE id_terminal = " + id + ") OR id_destino IN (SELECT id_destino FROM destino WHERE id_terminal = " + id + ")))";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarTerminalBoleto(int id) throws SQLException {
        consulta = "DELETE FROM boleto WHERE id_ruta IN (SELECT id_ruta FROM ruta WHERE id_origen IN (SELECT id_origen FROM origen WHERE id_terminal = " + id
                + ") OR id_destino IN ( SELECT id_destino FROM destino WHERE id_terminal = " + id + "))";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarTerminalRuta(int id) throws SQLException {
        consulta = "DELETE FROM ruta WHERE id_origen IN (SELECT id_origen FROM origen WHERE id_terminal = " + id + ") OR id_destino IN ("
                + "SELECT id_destino FROM destino WHERE id_terminal = " + id + ")";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarTerminalOrigen(int id) throws SQLException {
        consulta = "DELETE FROM origen WHERE id_terminal = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarTerminalDestino(int id) throws SQLException {
        consulta = "DELETE FROM destino WHERE id_terminal = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarTerminal(int id) throws SQLException {
        consulta = "DELETE FROM terminal WHERE id_terminal = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarBoletoClientePorTerminal(int id) throws SQLException {
        consulta = "DELETE FROM boletoCliente WHERE id_boleto IN (SELECT id_boleto FROM boleto WHERE id_ruta IN (SELECT id_ruta FROM ruta WHERE id_origen IN ("
                + "SELECT id_origen FROM origen WHERE id_terminal = " + id + ") OR id_destino IN (SELECT id_destino FROM destino WHERE id_terminal = " + id + ")))";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarRutaAutobusPorTerminal(int id) throws SQLException {
        consulta = "DELETE FROM rutaAutobus WHERE id_ruta IN (SELECT id_ruta FROM ruta WHERE id_origen IN (SELECT id_origen FROM origen WHERE id_terminal = " + id
                + ") OR id_destino IN (SELECT id_destino FROM destino WHERE id_terminal = " + id + "))";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarRutaTerminalRPorTerminal(int id) throws SQLException {
        consulta = "DELETE FROM rutaTerminal WHERE id_ruta IN (SELECT id_ruta FROM ruta WHERE id_origen IN (SELECT id_origen FROM origen WHERE id_terminal = " + id
                + ") OR id_destino IN (SELECT id_destino FROM destino WHERE id_terminal = " + id + "))";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarRutaTerminalPorTerminal(int id) throws SQLException {
        consulta = "DELETE FROM rutaTerminal WHERE id_terminal = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarRutaConductorPorTerminal(int id) throws SQLException {
        consulta = "DELETE FROM rutaConductor WHERE id_ruta IN (SELECT id_ruta FROM ruta WHERE id_origen IN (SELECT id_origen FROM origen WHERE id_terminal = " + id
                + ") OR id_destino IN (SELECT id_destino FROM destino WHERE id_terminal = " + id + "))";
        return cnslt.elimina(consulta);

    }

    //Paradas
    public boolean eliminaRutaTerminal(int idRuta) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.rutaterminal WHERE rutaterminal.Id_RutTer = " + idRuta;
        return cnslt.elimina(consulta);
    }

    //Conductor
    public boolean eliminaTelefono(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.telefono_persona WHERE telefono_persona.Id_persona = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaConductor(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.conductor WHERE conductor.Id_persona = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaAutbousConductor(int idConductor) throws SQLException {
        consulta = "DELETE FROM `autobusconductor` WHERE autobusconductor.Id_conductor = " + idConductor;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaRutaConductor(int idConductor) throws SQLException {
        consulta = "DELETE FROM `rutaconductor` WHERE rutaconductor.Id_conductor = " + idConductor;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaPersona(int id) throws SQLException {
        consulta = "DELETE FROM persona WHERE persona.Id_persona = " + id;
        return cnslt.elimina(consulta);
    }

    //Cliente
    public boolean eliminaBoletoCliente(int idCliente) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.boletocliente WHERE boletocliente.Id_cliente = " + idCliente;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaTarjetaCliente(int idCliente) throws SQLException {
        consulta = "DELETE FROM `tarjeta` WHERE tarjeta.Id_cliente = " + idCliente;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaCliente(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.cliente WHERE cliente.Id_persona = " + id;
        return cnslt.elimina(consulta);
    }

    //Pasajero
    public boolean eliminarPasajeroBoletoCliente(int id) throws SQLException {
        consulta = "DELETE FROM boletocliente WHERE Id_boleto IN (SELECT Id_boleto FROM boleto WHERE Id_pasajero = " + id + ")";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarPasajeroReembolso(int id) throws SQLException {
        consulta = "DELETE FROM reembolso WHERE Id_boleto IN (SELECT Id_boleto FROM boleto WHERE Id_pasajero = " + id + ")";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarPasajeroBoleto(int id) throws SQLException {
        consulta = "DELETE FROM boleto WHERE Id_pasajero = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarPasajero(int id) throws SQLException {
    consulta = "DELETE FROM pasajero WHERE pasajero.Id_pasajero = " + id;
    return cnslt.elimina(consulta);
}
    
    //Autobus
    public boolean eliminarAutobusConductor(int id) throws SQLException {
        consulta = "DELETE FROM autobusConductor WHERE autobusConductor.id_autobus = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarAutobusRuta(int id) throws SQLException {
        consulta = "DELETE FROM rutaAutobus WHERE id_autobus = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarAutobusBaja(int id) throws SQLException {
        consulta = "DELETE FROM baja WHERE baja.id_autobus = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarAutobusReembolsos(int id) throws SQLException {
        consulta = "DELETE FROM reembolso WHERE id_boleto IN (SELECT id_boleto FROM boleto WHERE id_asiento IN (SELECT id_asiento FROM asiento WHERE id_autobus = " + id + "))";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarAutobusBoletosCliente(int id) throws SQLException {
        consulta = "DELETE FROM boletoCliente WHERE id_boleto IN (SELECT id_boleto FROM boleto WHERE id_asiento IN (SELECT id_asiento FROM asiento WHERE id_autobus = " + id + "))";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarAutobusBoletos(int id) throws SQLException {
        consulta = "DELETE FROM boleto WHERE id_asiento IN (SELECT id_asiento FROM asiento WHERE id_autobus = " + id + ")";
        return cnslt.elimina(consulta);
    }

    public boolean eliminarAutobusAsiento(int id) throws SQLException {
        consulta = "DELETE FROM asiento WHERE asiento.id_autobus = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminarAutobus(int id) throws SQLException {
        consulta = "DELETE FROM autobus WHERE autobus.id_autobus = " + id;
        return cnslt.elimina(consulta);
    }

    //Reembolso
    public boolean eliminarReembolso(int id) throws SQLException {
        consulta = "DELETE FROM reembolso WHERE reembolso.Id_reembolso = " + id;
        return cnslt.elimina(consulta);
    }
//    public boolean eliminaRutaTerminal(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.rutaterminal WHERE rutaterminal.Id_RutTer= " + id;
//        return cnslt.elimina(consulta);
//    }
//    
//    public boolean eliminaRutaAutobus(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.rutaautobus WHERE rutaautobus.Id_RutAut= " + id;
//        return cnslt.elimina(consulta);
//    }
//    
//    public boolean eliminaConduce(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.autobusconductor WHERE autobusconductor.Id_AutCon= " + id;
//        return cnslt.elimina(consulta);
//    }
//    
//
//    public boolean eliminaClienteBoleto(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.boletocliente WHERE boletocliente.Id_BolClie = " + id;
//        return cnslt.elimina(consulta);
//    }
//    public boolean eliminaBoleto(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.boleto WHERE Id_boleto = " + id;
//        return cnslt.elimina(consulta);
//    }
//    
//    public boolean eliminaPasajero(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.pasajero WHERE pasajero.Id_persona = " + id;
//        return cnslt.elimina(consulta);
//    }
//
//
//    public boolean eliminaTerminal(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.terminal WHERE terminal.Id_terminal = " + id;
//        return cnslt.elimina(consulta);
//    }
//    public boolean eliminaDestinoTerminal(int idTerminal) throws SQLException {
//        consulta = "DELETE FROM `destino` WHERE destino.Id_terminal = " + idTerminal;
//        return cnslt.elimina(consulta);
//    }
//    
//    public boolean eliminaOrigenTerminal(int id) throws SQLException {
//        consulta = "DELETE FROM `origen` WHERE origen.Id_terminal = " + id;
//        return cnslt.elimina(consulta);
//    }
//    
//    public boolean eliminaTerminalRuta(int idTerminal) throws SQLException {
//        consulta = "DELETE FROM `rutaterminal` WHERE rutaterminal.Id_terminal = " + idTerminal;
//        return cnslt.elimina(consulta);
//    }
//    public boolean eliminaTeriminalTelefono(int id) throws SQLException {
//        consulta = "DELETE FROM `telefonoterminal` WHERE telefonoterminal.Id_terminal = " + id;
//        return cnslt.elimina(consulta);
//    }
//    public boolean eliminaRuta(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.terminal WHERE terminal.Id_terminal = " + id;
//        return cnslt.elimina(consulta);
//    }
}
