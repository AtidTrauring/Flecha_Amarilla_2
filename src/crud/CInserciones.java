package crud;

import java.sql.SQLException;

public class CInserciones {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    public boolean insertaTelefonos(int idTelefono, String telefono, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.telefono_persona (`Id_telefonoPersona`, `telefono`, `Id_persona`) VALUES (" + idTelefono + ",'" + telefono + "'," + idPersona + ")";
        return cnslt.inserta(consulta);
    }

    public boolean insertaPersona(int id, String nombres, String apPaterno, String apMaterno) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.persona (`Id_persona`, `nombre`, `ApPat`, `ApMat`) VALUES('" + id + "','" + nombres + "','" + apPaterno + "','" + apMaterno + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaClientes(int idCliente, String correo, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.cliente (`Id_cliente`, `correo`, `Id_persona`) VALUES ('" + idCliente + "','" + correo + "','" + idPersona + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaPasajeros(int idPasajero, String tipoPasajero, int descuento, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.pasajero (`Id_pasajero`, `tipoPasajero`, `descuento`, `Id_persona`) VALUES ('" + idPasajero + "','" + tipoPasajero + "','" + descuento + "','" + idPersona + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaConductores(int idConductor, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.conductor (`Id_conductor`, `Id_persona`) VALUES ('" + idConductor + "','" + idPersona + "')";
        return cnslt.inserta(consulta);
    }
    
     public boolean insertaRuta(int idRuta, String nombre, String duración, String horaSalida,String horaLegada,float precio,String distancia,int idOrigen,int idDestino) throws SQLException {
        consulta = "INSERT INTO `ruta`(`Id_ruta`, `nombre`, `duracion_ruta`, `hora_salida`, `hora_llegada`, `precio`, `distancia`, `Id_origen`, `Id_destino`)"
                + " VALUES ("+idRuta+",'"+nombre+"','"+duración+"','"+horaSalida+"','"+horaLegada+"',"+precio+",'"+distancia+"','"+idOrigen+"','"+idDestino+"')";
        return cnslt.inserta(consulta);
    }
     
     public boolean insertaParada(int idParada, int idRuta, int idTerminal) throws SQLException {
        consulta ="INSERT INTO `rutaterminal`(`Id_RutTer`, `Id_ruta`, `Id_terminal`) "+
                "VALUES ('"+idParada+"','"+idRuta+"','"+idTerminal+"')";
        return cnslt.inserta(consulta);
    }
     
      public boolean insertaAutobusConductor(int Id_AutCon,int Id_conductor, int Id_autobus) throws SQLException {
        consulta ="INSERT INTO `autobusconductor`(`Id_AutCon`, `Id_conductor`, `Id_autobus`) "+
                "VALUES ('"+Id_AutCon+"','"+Id_conductor+"','"+Id_autobus+"')";
        return cnslt.inserta(consulta);
    }
      
        public boolean insertaRutaConductor(int Id_RutCon, int Id_ruta, int Id_conductor) throws SQLException {
        consulta ="INSERT INTO `rutaconductor`(`Id_RutCon`, `Id_ruta`, `Id_conductor`)"+
                " VALUES ('"+Id_RutCon+"','"+Id_ruta+"','"+Id_conductor+"');";
        return cnslt.inserta(consulta);
    }
        
          public boolean insertaRutaAutobus(int Id_RutAut, int Id_ruta, int Id_autobus, int Id_fecha) throws SQLException {
        consulta ="INSERT INTO `rutaautobus`(`Id_RutAut`, `Id_ruta`, `Id_autobus`, `Id_fecha`) "+
                "VALUES ('"+Id_RutAut+"','"+Id_ruta+"','"+Id_autobus+"','"+Id_fecha+"');";
        return cnslt.inserta(consulta);
    }
}
