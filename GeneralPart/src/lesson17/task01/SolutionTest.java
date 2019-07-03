package lesson17.task01;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;

import static org.mockito.Mockito.when;

//Бесполезная строчка ломающая все
//@RunWith(MockitoJUnitRunner.class)
public class SolutionTest {

    static Connection cn;
    Statement st;
    PreparedStatement ps;

    @Mock
    ResultSet mockRs;

    ResultSet rs;

    static String testCreateSQL, testReadSQL, testUpdateSQL, testDeleteSQL;

    @BeforeAll
    static void conn() {
        try {
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usermanager",
                    "postgres",
                    "landlord17");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void init() {

        testCreateSQL = "INSERT INTO \"test\".\"USER\"(name, birthday, login_id, city, description) VALUES(?,?,?,?,?)";
        testReadSQL = "SELECT login_id, name FROM \"test\".\"USER\" where city = ?";
        testUpdateSQL = "UPDATE \"test\".\"USER\" SET city = 'Sam' WHERE name = 'Alex'";
        testDeleteSQL = "DELETE FROM \"test\".\"USER\" WHERE city = 'Sam'";

    }

    @BeforeEach
    void testCreate() {
        try {

            ps = cn.prepareStatement(testCreateSQL);

            ps.setString(1, "Alex");
            ps.setString(2, "25.05.1980");
            ps.setInt(3, 0);
            ps.setString(4, "Togs");
            ps.setString(5, "goodMan");

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMock() {
        mockRs = Mockito.mock(ResultSet.class);
        try {
            when(mockRs.getInt("login_id")).thenReturn(200);


            when(mockRs.getInt("login_id")).thenReturn(200);
            when(mockRs.getString("name")).thenReturn("test");
            when(mockRs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
            while (mockRs.next()) {
                int login_id = mockRs.getInt("login_id");
                String name = mockRs.getString("name");


                System.out.println("\n====================");
                System.out.println("login_id: " + login_id);
                System.out.println("Name: " + name);
                System.out.println("====================\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRead() {
        try {

            ps = cn.prepareStatement(testReadSQL);

            ps.setString(1, "Togs");



            rs = ps.executeQuery();

            while (rs.next()) {
                int login_id = rs.getInt("login_id");
                String name = rs.getString("name");


                System.out.println("\n====================");
                System.out.println("login_id: " + login_id);
                System.out.println("Name: " + name);
                System.out.println("====================\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        try {
            st = cn.createStatement();
            st.executeUpdate(testUpdateSQL);


            } catch(SQLException e){
                e.getStackTrace();
            }
        }

        @AfterAll
        static void testDelete() {
            try {
                Statement dst = cn.createStatement();
                dst.execute(testDeleteSQL);


            } catch(SQLException e){
                e.getStackTrace();
            }
        }


}