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
}
