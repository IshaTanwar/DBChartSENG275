package org.jfree.DBChartSENG275;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
   ChinookDB is the dependency on PieChartData (SUT) and PieChartDemowithDB

   Driver: Some parts by Sam
           Some parts by Tristan
*/
public class ChinookDB {

    /*
     * Function: Provides the List of invoices (country name and value)
     * Parameter: None
     * Returns: List<Invoices>
     *
     * IMPORTANT NOTE: THE CODE FOR THIS METHOD IS COPIED FROM
     *                 PROVIDED ReadChinookDataset() IN
     *                 PieChartData.java
     */
    public List<Invoice> all(){
        /* Create SQLite Database Connection */
        Connection conn = null;
        List<Invoice> Invoices = new ArrayList<Invoice>();
        try {
            // db parameters
            String url = "jdbc:sqlite:chinook.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Invoices");
            while (resultSet.next()) {
                String country = resultSet.getString("BillingCountry");
                Double val = resultSet.getDouble("Total");
                Invoice tempInvoice = new Invoice(country, val);
                Invoices.add(tempInvoice);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Invoices;
    }
}
