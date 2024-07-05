package crud;

import javax.swing.JOptionPane;

public class CMensajes {

    public static void msg(String msg, String origen) {
        JOptionPane.showMessageDialog(null, msg, origen, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void msg_error(String msg, String origen) {
        JOptionPane.showMessageDialog(null, msg, origen, JOptionPane.ERROR_MESSAGE);
    }

    public static void msg_advertencia(String msg, String origen) {
        JOptionPane.showMessageDialog(null, msg, origen, JOptionPane.WARNING_MESSAGE);
    }
}
