/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vo.Persona;

/**
 *
 * @author s206e18
 */
public class JDBCUtil {

    /**
     *
     * @param login
     * @param password
     * @return
     */
    public static Persona obtenerPersona(String login, String password) {
        Persona p = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            st = con.createStatement();
            rs = st.executeQuery("Select nombre, apellido, login, password, email "
                    + "from users where login = '" + login
                    + "' and password = '" + password + "'");

            while (rs.next()) {
                p = new Persona();
                p.setApellido(rs.getString("apellido"));
                p.setNombre(rs.getString("nombre"));
                p.setEmail(rs.getString("email"));
                p.setUsuario(rs.getString("login"));
                p.setPassword(rs.getString("password"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
        return p;
    }
}
