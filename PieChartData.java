package org.jfree.DBChartSENG275;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/*
   PieChartData (SUT) :
   Refactored to make it testable

   Function: SumInvoices() - summing invoices per country
   Driver: Isha (Refactored PieChartData class
                 except the code inside SumInvoices)
 */

public class PieChartData {
    // Database dependency to mock
    final ChinookDB Data;

    // Dependency inside constructor to make it testable (uniqueness)
    public PieChartData(ChinookDB Data){
        this.Data = Data;
    }

    /*
     * Function: Summing invoices per country and providing list in a dataset
     * Parameter: None
     * Returns: PieDataset- A Dataset with country and value.
     *                      (value = sum of invoices for that country)
     *
     * Driver for SumInvoices: Sam (Refactored code inside SumInvoices)
     */

    public PieDataset SumInvoices() {

        // inbuilt java class: constructs a new dataset, initially empty
        DefaultPieDataset dataset = new DefaultPieDataset( );

        for (Invoice invoice : Data.all()) {
            if (dataset.getKeys().contains(invoice.country)) {
                invoice.value += (double) dataset.getValue(invoice.country);
            }
            dataset.setValue(invoice.country, invoice.value);
        }
        return dataset;
    }
}
