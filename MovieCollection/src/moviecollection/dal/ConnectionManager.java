/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Pepe15224
 */
public class ConnectionManager {
    
    private SQLServerDataSource ds = new SQLServerDataSource();
    
    public ConnectionManager() {
        ds.setDatabaseName("Private Movie Collection");
        ds.setUser("CS2017B_27_java");
        ds.setPassword("javajava");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }    
}
