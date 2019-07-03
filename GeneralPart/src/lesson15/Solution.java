package lesson15;

import java.sql.*;

public class Solution {


    public static void main(String[] args) {


        try {
            Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usermanager",
                    "postgres",
                    "landlord17");


            Statement st = cn.createStatement();

            String sqlMakeRole = "INSERT INTO test.role(name, description) VALUES(?,?)";
            PreparedStatement ps = cn.prepareStatement(sqlMakeRole);

            ps.setString(1, "Administrator");
            ps.setString(2, "Head manager");

            ps.executeUpdate();

            ps.setString(1, "Client");
            ps.setString(2, "The best");

            ps.executeUpdate();

            ps.setString(1, "Billing");
            ps.setString(2, "");

            ps.executeUpdate();

            ps.close();

            cn.setAutoCommit(false);

            String sqlMakingUser = "INSERT INTO \"test\".\"USER\"(name, birthday, login_id, city, description) VALUES(?,?,?,?,?)";


            ps = cn.prepareStatement(sqlMakingUser);

            ps.setString(1, "Alex");
            ps.setString(2, "25.05.1980");
            ps.setInt(3, 0);
            ps.setString(4, "Tol");
            ps.setString(5, "goodMan");


            st.addBatch(ps.toString());

            sqlMakingUser = "INSERT INTO \"test\".\"USER\"(name, birthday, login_id, city, description) VALUES('Oleg', 'may', 1, 'Mos', 'not good employee')";

            st.addBatch(sqlMakingUser);

            st.executeBatch();

            cn.commit();

            String sqlTestQuery = "SELECT login_id, name FROM \"test\".\"USER\" where city = ?";

            ps = cn.prepareStatement(sqlTestQuery);


            ps.setString(1, "Mos");

            System.out.println(ps.toString());
            ResultSet rs = st.executeQuery(ps.toString());

            while (rs.next()) {
                int login_id = rs.getInt("login_id");
                String name = rs.getString("name");


                System.out.println("\n====================");
                System.out.println("login_id: " + login_id);
                System.out.println("Name: " + name);
                System.out.println("====================\n");
            }

            Savepoint a = cn.setSavepoint("A");

            try {

                st.executeUpdate("INSERT INTO \"test\".\"user_role\"(user_id, role_id) VALUES (1,3)");
                st.executeUpdate("INSERT INTO \"test\".\"user_role\"(user_id, role_id) VALUES (2,2)");
                st.executeUpdate("INSERT INTO \"test\".\"user_role\"(user_id, role_id) VALUES (3,1)");
                cn.commit();

            } catch (SQLException e) {

                System.out.println("Откат");

            }

            Savepoint b = cn.setSavepoint("B");

            st.executeUpdate("INSERT INTO \"test\".\"USER\"(name, birthday, login_id, city, description) VALUES('V12LAS', 'may', 1, 'Mos', 'not good employee')");
            try {
                st.executeUpdate("INSERT INTO \"test\".\"AnotherUser\"(name, birthday, login_id, city, description) VALUES(12, 'may', 1, 'Mos', 'not good employee')");
                cn.commit();

            } catch (SQLException e) {
                System.out.println("Rollback test");
                cn.rollback(b);
            }

            ps.close();

            st.close();

            cn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
