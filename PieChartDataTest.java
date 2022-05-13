package org.jfree.chart.demo2.DBChart;

import org.jfree.DBChartSENG275.ChinookDB;
import org.jfree.DBChartSENG275.Invoice;
import org.jfree.DBChartSENG275.PieChartData;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

// TASK: some of the test code will go in here
class PieChartDataTest {

    @Test
    void oneCountry(){
        // Dummy object for dependency
        ChinookDB Data = mock(ChinookDB.class);

        // Creating list of invoices for Data.all() to return
        Invoice i1 = new Invoice("Canada", 20.0);
        Invoice i2 = new Invoice("Canada", 30.0);
        Invoice i3 = new Invoice("Canada",45.0);
        Invoice i4 = new Invoice("Canada", 5.0);

        //Promoting to Stub
        when(Data.all()).thenReturn(List.of(i1,i2,i3,i4)); //returns list of invoices

        //create PieChartData with our stub
        PieChartData pieChartData = new PieChartData(Data);

        // Creating empty dataset to compare with return of SumInvoices()
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Canada",100.0);
        assertEquals(dataset,pieChartData.SumInvoices());
    }

    @Test
    void negativeValue(){
        // Dummy object for dependency
        ChinookDB Data = mock(ChinookDB.class);

        // Creating list of invoices for Data.all() to return
        Invoice i1 = new Invoice("Canada", -10.0);
        Invoice i2 = new Invoice("Canada", 5.0);
        Invoice i3 = new Invoice("Azerbaijan",45.0);
        Invoice i4 = new Invoice("Azerbaijan", 5.0);

        //Promoting to Stub
        when(Data.all()).thenReturn(List.of(i1,i2,i3,i4)); //returns list of invoices

        //create PieChartData with our stub
        PieChartData pieChartData = new PieChartData(Data);

        // Creating empty dataset to compare with return of SumInvoices()
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Canada",-5.0);
        dataset.setValue("Azerbaijan", 50.0);
        assertEquals(dataset,pieChartData.SumInvoices());
    }

    @Test
    void Empty() {
        // Dummy object for dependency
        ChinookDB Data = mock(ChinookDB.class);

        // promoting to stub
        when(Data.all()).thenReturn(List.of()); // Note all() returns List<Invoice>

        PieChartData pieChartData = new PieChartData(Data);
        // Creating empty dataset
        DefaultPieDataset emptydataset = new DefaultPieDataset();
        assertEquals(emptydataset,pieChartData.SumInvoices());

        //promoting to mock
        verify(Data,times(1)).all();
        verifyNoMoreInteractions(Data);
    }

    @Test
    void NoCountryRepetition_DataSet() {
        // Dummy object for dependency
        ChinookDB Data = mock(ChinookDB.class);

        // Creating list of invoices
        Invoice i1 = new Invoice("India", 45.0);
        Invoice i2 = new Invoice("Canada", 15.0);
        Invoice i3 = new Invoice("USA",25.2);
        // promoting to stub
        when(Data.all()).thenReturn(List.of(i1,i2,i3)); // Note all() returns List<Invoice>

        PieChartData pieChartData = new PieChartData(Data);
        // Creating empty dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("India",45.0);
        dataset.setValue("Canada",15.0);
        dataset.setValue("USA",25.2);
        assertEquals(dataset,pieChartData.SumInvoices());

        //promoting to mock
        verify(Data,times(1)).all();
        verifyNoMoreInteractions(Data);
    }

    @Test
    void CountryRepetition_DataSet() {
        // Dummy object for dependency
        ChinookDB Data = mock(ChinookDB.class);

        // Creating list of invoices
        Invoice i1 = new Invoice("India", 45.0);
        Invoice i2 = new Invoice("Canada", 15.0);
        Invoice i3 = new Invoice("USA",25.2);
        Invoice i4 = new Invoice("India", 10.0);
        Invoice i5 = new Invoice("USA", 10.0);
        // promoting to stub
        when(Data.all()).thenReturn(List.of(i1,i2,i3,i4,i5)); // Note all() returns List<Invoice>

        PieChartData pieChartData = new PieChartData(Data);
        // Creating empty dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("India",55.0);
        dataset.setValue("Canada",15.0);
        dataset.setValue("USA",35.2);
        assertEquals(dataset,pieChartData.SumInvoices());

        //promoting to mock
        verify(Data,times(1)).all();
        verifyNoMoreInteractions(Data);
    }
}