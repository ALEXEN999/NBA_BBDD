package BBDD;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class App 
{
    public App() {}

    public Connection getConnection() {
        Connection connection = null;
        String nombre= null;
        String cognom = null;
        Scanner in = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.22.215:3306/NBA_BBDD","root","#Password0");
            System.out.println("BBD CONNECTED");
            // Ejercicio1

            PreparedStatement st1 = connection.prepareStatement("SELECT * FROM jugadores WHERE Nombre_equipo = ?");

            st1.setString(1,"Cavaliers");

            ResultSet rs = st1.executeQuery();
            while (rs.next()) System.out.println("Procedencia: "+rs.getString("Procedencia")+"\nPosicion: "+rs.getString("Posicion")+"\n-------------");


            // Ejercicio2

            PreparedStatement st2 = connection.prepareStatement("SELECT count(*) AS contador FROM jugadores WHERE Procedencia = ?");

            st2.setString(1,"Spain");
            //Consultem les dades de tots els usuaris
            rs = st2.executeQuery();
            while (rs.next()) System.out.println("Españoles: "+rs.getString("contador")+"\n-------------");


            // Ejercicio3


            //  código: 666, Luka Dončić, Slovenia, Mavericks, 6-7, 230, G-F.

            PreparedStatement st3 = connection.prepareStatement("INSERT INTO jugadores (codigo, Nombre, Procedencia, Altura, Peso, Posicion, Nombre_equipo) VALUES (?,?,?,?,?,?,?)");

            st3.setString(1,"614");
            st3.setString(2,"Luka Dončić");
            st3.setString(3,"Slovenia");
            st3.setString(4,"6-7");
            st3.setString(5,"230");
            st3.setString(6,"G-F");
            st3.setString(7,"Mavericks");
            rs.next();
//            st3.executeUpdate();

            // Ejercicio4

            PreparedStatement st4 = connection.prepareStatement("SELECT * FROM jugadores LEFT JOIN estadisticas ON jugadores.codigo = estadisticas.jugador WHERE temporada = ? AND puntos_por_partido > ?");

            st4.setString(1,"04/05");
            st4.setString(2,"10");
            //Consultem les dades de tots els usuaris
            rs = st4.executeQuery();
            while (rs.next()) System.out.println("Jugador: "+rs.getString("nombre")+" --> "+rs.getString("puntos_por_partido")+"\n-------------");


            rs.close();

            connection.close();

        }catch (SQLException e ){
            System.out.println(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        App app  = new App();
        Connection connection = app.getConnection();
    }

}
