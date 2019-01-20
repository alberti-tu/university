package eetac.dsa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Vector;

public class DAO {
/*
    private Connection getConnection() {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/karthicraj","mysql","mysql");
        return conn;
    }
*/
    private String queryInsert() {
        String classname = this.getClass().getSimpleName();
        Method[] methods = this.getClass().getDeclaredMethods();

        Vector<Method> gets = new Vector<Method>();
        for (Method m: methods)
        {
            if (m.getName().regionMatches(0, "get", 0,3))
                gets.add(m);
        }

        Vector<Method> sets = new Vector<Method>();
        for (Method m: methods)
        {
            if (m.getName().regionMatches(0, "set", 0,3))
                sets.add(m);
        }

        StringBuffer camps = new StringBuffer("(");
        camps.toString();
        StringBuffer valors = new StringBuffer("(");
        valors.toString();
        for (Method metode: gets)
        {
            camps.append(metode.getName().substring(3));
            camps.append(",");

            try { valors.append(metode.invoke(this, null)); }
            catch (IllegalAccessException e) {}
            catch (InvocationTargetException e){}
            valors.append(",");
        }
        camps = camps.substring(0, camps.length() - 1);
        camps = camps + ")";
        valors = valors.substring(0, valors.length() - 1);
        valors = valors + ")";

        String comanda = "INSERT INTO " + classname + " " + camps + " VALUES " + valors;
        System.out.println(comanda);
        return comanda;
    }

    public void insert() throws SQLException {
        String theQuery = this.queryInsert();
        Connection con = this.getConnection();

        PreparedStatement pstm = con.prepareStatement(pstm.setString(1, "Mc Dpmañds' ","");
        pstm.setInt(2, 1);
        pstm.execute();
    }

    public void select(){
        String theQuery = null; //
        Connection con = this.getConnection();

        PreparedStatement pstm = con.prepareStatement(
                pstm.setString(1, "Mc Dpmañds' ");
        pstm.setInt(2, 1);theQuery);
        pstm.setObject(3, )
        ResultSet rs = pstm.executeQuery();


        rs.next();
        rs.getString(1);
        rs.getDate(2);
        rs.getObject(3);

        ResultSetMetaData metaData = rs.getMetaData();
        metaData.getColumnType(1);
        metaData.get




    }
*/
    public void update(){}
    public void delete(){}
}
