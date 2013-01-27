package com.example.jsfdemo.web;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.example.jsfdemo.domain.File;
@Named("DB")
public class DB implements Serializable
{
    static String  dbURL="jdbc:postgresql://localhost:5432/postgres";//url z serwerem db
    @SuppressWarnings({ "rawtypes", "deprecation" })
    private static final long serialVersionUID = 1L;
    public static Connection initDB() 
    {
            try{
                    //wczytanie sterownika dla bazy danych postgresql
                    Class d=Class.forName("org.postgresql.Driver");
                    System.out.println("Wczytano: "+d);

                    //umozliwienie rejestracji
                    DriverManager.setLogStream(System.err);//sprawia ze wszystkie komunuikaty bedo zapisywane w standardowym steruminiu bledow

                    //System.out.println("Nawiazanie poloczenia");
                    Connection conn=DriverManager.getConnection(dbURL, "postgres", "qazqaz");
                    

                    //getWarnings - wyswietla dodatkowe inf i ostzezenia z obiektu Connection
                    SQLWarning warn=conn.getWarnings();
                    while(warn!=null)
                    {
                            System.out.println("Stan SQL: "+ warn.getSQLState());
                            System.out.println("Komunikat: "+warn.getMessage());
                            System.out.println("Sprzedawca: "+warn.getErrorCode());
                            System.out.println("");
                            warn=warn.getNextWarning();
                    }
                    //obsluga polonczenia...
                    return conn;
            }catch(ClassNotFoundException exc){        System.err.println(exc+". Nie mozna pobrac sterownika.");}
            catch(SQLException e){System.out.println("Nie mozna nawiazac poloczenia z BD "+e);}
            return null;       
    }
    @SuppressWarnings("rawtypes")
	private List<File> fileList = new ArrayList<File>();
    File newFile = new File("sadasf", "asdasd", "addadsa");
    File newFile2 = new File("sadasf1", "asdasd", "addadsa");
    File newFile3 = new File("sadasf2", "asdasd", "addadsa");
    File newFile4 = new File("sadasf3", "asdasd", "addadsa");
    File newFile5 = new File("sadasf5", "asdasd", "addadsa");
    File newFile6 = new File("sadasf4", "asdasd", "addadsa");
    File newFile7 = new File("sadasf6", "asdasd", "addadsa");
    File newFile8 = new File("sadasf7", "asdasd", "addadsa");
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List selAll() throws SQLException
    {
    	fileList.add(newFile);
    	fileList.add(newFile2);
    	fileList.add(newFile3);
    	fileList.add(newFile4);
    	fileList.add(newFile5);
    	fileList.add(newFile6);
    	fileList.add(newFile7);
    	fileList.add(newFile);
    	Connection conn = DB.initDB();
    	Statement st = conn.createStatement();
        ResultSet rs;
        rs = st.executeQuery("SELECT path, size, hash FROM files ORDER BY path;");
        while (rs.next()) 
        {
        	File newFile = new File(rs.getString(1), rs.getString(2), rs.getString(3));
        	fileList.add(newFile);
        }
        rs.close();
        st.close();
        conn.close();
		return fileList;
    } 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List selHash() throws SQLException
    {
    	Connection conn = DB.initDB();
    	Statement st = conn.createStatement();
        ResultSet rs;
        rs = st.executeQuery("SELECT path, size, hash FROM files WHERE hash ISNOTNULL ORDER BY path;");
        while (rs.next()) 
        {
        	File newFile = new File(rs.getString(1), rs.getString(2), rs.getString(3));
        	fileList.add(newFile);
        }
        rs.close();
        st.close();
        conn.close();
        return fileList;
    } 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List selNoHash() throws SQLException
    {
    	Connection conn = DB.initDB();
    	Statement st = conn.createStatement();
        ResultSet rs;
        rs = st.executeQuery("SELECT path, size FROM files WHERE hash ISNULL ORDER BY path;");
        while (rs.next()) 
        {
        	File newFile = new File(rs.getString(1), rs.getString(2), rs.getString(3));
        	fileList.add(newFile);
        }
        rs.close();
        st.close();
        conn.close();
		return fileList;
    }
}