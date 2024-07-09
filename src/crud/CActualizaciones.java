package crud;

import java.sql.SQLException;

public class CActualizaciones {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    public boolean actualizarPersona(String nombre, String ApPat, String ApMat, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.persona SET `nombre`='" + nombre + "',`ApPat`='" + ApPat + "',`ApMat`='" + ApMat + "' WHERE persona.Id_persona = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarTelefono(String tel, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.telefono_persona SET `telefono`='" + tel + "' WHERE telefono_persona.Id_persona = " + id;
        return cnslt.actualiza(consulta);
    }
    
    public boolean actualizarRuta(String ruta, int id) throws SQLException {
        consulta = "UPDATE ruta SET ruta = '" + ruta + "' WHERE ruta.Id_ruta = " + id;
        return cnslt.actualiza(consulta);
    }
    
    public boolean actualizarTerminal(String terminal, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.terminal SET `terminal`='" + terminal + "' WHERE .Id_terminal = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarCorreo(String correo, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.cliente SET `correo`='" + correo + "' WHERE cliente.Id_persona = " + id;
        return cnslt.actualiza(consulta);
    }

}
