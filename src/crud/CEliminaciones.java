package crud;

import java.sql.SQLException;

public class CEliminaciones {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    //Viajes
    public boolean eliminaRutaAutobus(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.rutaautobus WHERE rutaautobus.Id_RutAut= " + id;
        return cnslt.elimina(consulta);
    }
        
     //Terminales
     public boolean eliminarTerminalCompleta(int idTerminal) throws SQLException {
        // Eliminar filas en telefonoTerminal relacionadas con Id_terminal
        consulta = "DELETE FROM telefonoTerminal WHERE Id_terminal = " + idTerminal;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        // Eliminar filas en rutaTerminal relacionadas con Id_terminal
        consulta = "DELETE FROM rutaTerminal WHERE Id_terminal = " + idTerminal;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        // Eliminar filas en origen relacionadas con Id_terminal
        consulta = "DELETE FROM origen WHERE Id_terminal = " + idTerminal;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        // Eliminar filas en destino relacionadas con Id_terminal
        consulta = "DELETE FROM destino WHERE Id_terminal = " + idTerminal;
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        // Eliminar filas en ruta relacionadas con Id_origen/Id_destino
        consulta = "DELETE FROM ruta WHERE Id_origen IN (SELECT Id_origen FROM origen WHERE Id_terminal = " + idTerminal + ") OR Id_destino IN (SELECT Id_destino FROM destino WHERE Id_terminal = " + idTerminal + ")";
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        // Eliminar filas en boleto relacionadas con Id_ruta
        consulta = "DELETE FROM boleto WHERE Id_ruta IN (SELECT Id_ruta FROM ruta WHERE Id_origen IN (SELECT Id_origen FROM origen WHERE Id_terminal = " + idTerminal + ") OR Id_destino IN (SELECT Id_destino FROM destino WHERE Id_terminal = " + idTerminal + "))";
        if (!cnslt.elimina(consulta)) {
            return false;
        }

        // Finalmente, eliminar la fila en terminal
        consulta = "DELETE FROM terminal WHERE Id_terminal = " + idTerminal;
        return cnslt.elimina(consulta);
    }
//    public boolean eliminaTelefono(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.telefono_persona WHERE telefono_persona.Id_persona = " + id;
//        return cnslt.elimina(consulta);
//    }
//
//    public boolean eliminaConductor(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.conductor WHERE conductor.Id_persona = " + id;
//        return cnslt.elimina(consulta);
//    }
//
//    public boolean eliminaAutbousConductor(int idConductor) throws SQLException {
//        consulta = "DELETE FROM `autobusconductor` WHERE autobusconductor.Id_conductor = " + idConductor;
//        return cnslt.elimina(consulta);
//    }
//
//    public boolean eliminaRutaConductor(int idConductor) throws SQLException {
//        consulta = "DELETE FROM `rutaconductor` WHERE rutaconductor.Id_conductor = " + idConductor;
//        return cnslt.elimina(consulta);
//    }
//
//    public boolean eliminaPersona(int id) throws SQLException {
//        consulta = "DELETE FROM persona WHERE persona.Id_persona = " + id;
//        return cnslt.elimina(consulta);
//    }
//
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
//    public boolean eliminaCliente(int id) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.cliente WHERE cliente.Id_persona = " + id;
//        return cnslt.elimina(consulta);
//    }
//
//    public boolean eliminaBoletoCliente(int idCliente) throws SQLException {
//        consulta = "DELETE FROM flecha_amarilla.boletocliente WHERE boletocliente.Id_cliente = " + idCliente;
//        return cnslt.elimina(consulta);
//    }
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
//    public boolean eliminaTarjetaCliente(int idCliente) throws SQLException {
//        consulta = "DELETE FROM `tarjeta` WHERE tarjeta.Id_cliente = " + idCliente;
//        return cnslt.elimina(consulta);
//    }
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
