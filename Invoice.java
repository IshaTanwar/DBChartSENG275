package org.jfree.DBChartSENG275;
/*
    Invoice Class
    Purpose: To create objects with country and value
             Required to create dataset
    Driver: Isha
 */
public class Invoice {
    public String country;
    public double value;

    public Invoice(String country, double value) {
        this.country = country;
        this.value = value;
    }
}
