package repository;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class Repository {
static File file = Paths.get(".").toAbsolutePath().normalize().toFile();
static String route = file.toString() + "/src/main/resources/";

static final String JDBC_DRIVER = "org.h2.Driver"; 
static final String DB_URL = "jdbc:h2:" + ruta + "DBtest";

static final String USER = "sa";
static final String PASS = "";

	public void DeleteTable(String language){
		Connection conn = null;
	    Statement stmt = null;
	
	    try {
	        Class.forName("org.h2.Driver");
	
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM Paises WHERE idiomaPaises = '" + language + "'";
	
	        stmt.executeUpdate(sql);
	
	    } catch (SQLException se) {
	        se.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeStm(conn, stmt);
	        closeCon(conn);
	    } 
	} 
	public void DeleteTableTwo(String language){
		Connection conn = null;
	    Statement stmt = null;
	
	    try {
	        Class.forName("org.h2.Driver");
	
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM Idiomas WHERE idioma = '" + language + "'";
	
	        stmt.executeUpdate(sql);

	
	    } catch (SQLException se) {
	        se.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeStm(conn, stmt);
	        closeCon(conn);
	    } 
	} 

	private void closeCon(Connection conn) {
		try {
		    if (conn!= null)
		        conn.close();
		} catch (SQLException se) {
		    se.printStackTrace();
		} 
	}

	private void closeStm(Connection conn, Statement stmt) {
		try {
		    if (stmt!=null)
		        conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
	}

	public  List<Countries> listCountries(){
		Connection conn = null;
		List<Countries> listCountries= new ArrayList<Countries>();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Statement stmt = null;
	
	    try {
		        Class.forName("org.h2.Driver");
		
			    conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   
			    stmt = conn.createStatement();
				
				prepareStatement = conn.prepareStatement("SELECT * FROM Paises");
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next()){
					Paises userInDatabase = new Paises();
					userInDatabase.setPais(resultSet.getString(1));
					userInDatabase.setIdioma(resultSet.getString(2));
					
					listCountries.add(userInDatabase);
				}
	
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStm(conn, stmt);
            closeRs(resultSet);
            closeCon(conn);
            
        } 
	    return listCountries;
	}
	
	public  List<Idiomas> listarCountries(){
    	Connection conn = null;
    	List<Countries> listIdiomas = new ArrayList<Countries>();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");

		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   
		    stmt = conn.createStatement();
			
			prepareStatement = conn.prepareStatement("SELECT * FROM Idiomas");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Countries userInDatabase = new Countries();
				userInDatabase.setCountries(resultSet.getString(1));
				
				listCountries.add(userInDatabase);
			}

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStm(conn, stmt);
            closeRs(resultSet);
            closeCon(conn);
            
        } 
        return listCountries;
   }

	private void closeRs(ResultSet resultSet) {
		if(
			resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
    
   public void InsertCountryTable(String country, String language){
    	Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");

		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		    stmt = conn.createStatement();
		
		    String sql = "REPLACE INTO Paises (pais,idiomaPaises) VALUES ('" + country + "', '" + language + "')";
                   
            stmt.executeUpdate(sql);
        } catch (SQLException se) {            
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStm(conn, stmt);
            closeCon(conn);
        } 
    }
   
   public  void InsertLanguageTable(String language){
	   Connection conn = null;
       Statement stmt = null;

       try {
           Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 
		   stmt = conn.createStatement();
		
		   String sql = "REPLACE INTO Idiomas (idioma) VALUES ('" + language + "')";
                  
           stmt.executeUpdate(sql);
       } catch (SQLException se) {            
           se.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           closeStm(conn, stmt);
           closeCon(conn);
       } 
   }
   
   public void CreateLanguageTable(){
	   Connection conn = null;
       Statement stmt = null;

       try {
           Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		   stmt = conn.createStatement();
		
		   String sql = "CREATE TABLE IF NOT EXISTS Idiomas (idioma VARCHAR(255), PRIMARY KEY (idioma))";

           stmt.executeUpdate(sql);
       } catch (SQLException se) {
           se.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
    	   closeStm(conn, stmt);
           closeCon(conn);
       } 
	}

   
   public void CreateCountryTable(){
	   Connection conn = null;
	   Statement stmt = null;
	
	   try {
	       Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		   stmt = conn.createStatement();
		
		   String sql = "CREATE TABLE IF NOT EXISTS Paises(pais VARCHAR(255), idiomaPaises VARCHAR(255), PRIMARY KEY (pais))";
	
	       stmt.executeUpdate(sql);
	   } catch (SQLException se) {
	       se.printStackTrace();
	   } catch (Exception e) {
	       e.printStackTrace();
	   } finally {
		   closeStm(conn, stmt);
	       closeCon(conn);
	   } 
   }  
}
