/* ==================
 * PieChartDemowithDB.java
 * ==================
 *
 * Copied from:
 * https://github.com/jfree/jfree-demos
 * Do not repost - this is for the purposes of our assignment only in SENG 275, 2022
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   - Neither the name of the JFree organisation nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL OBJECT REFINERY LIMITED BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package org.jfree.DBChartSENG275;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import javax.swing.*;
import java.awt.*;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.
 */
public class PieChartDemowithDB extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    static {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public PieChartDemowithDB(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /*
     * Creates a chart.
     * @param dataset  the dataset.
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
            "Smart Phones Manufactured / Q3 2011",  // chart title
            dataset,            // data
            false,              // no legend
            true,               // tooltips
            false               // no URL generation
        );

        // sets a custom background for the chart
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), 
                new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

        // customises the title position and font
        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);

        plot.setDefaultSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0f));

        // customise the section label appearance
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);
        
        // add a subtitle giving the data source
        TextTitle source = new TextTitle("Source: http://www.bbc.co.uk/news/business-15489523", 
                new Font("Courier New", Font.PLAIN, 12));
        source.setPaint(Color.WHITE);
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        // SENG 275: Task: currently testing this application relies on a database - use mocks to replace the dependency on the database
        // Use the concepts you have learned about dependency injection for the mocking

        /*
         * Change: Replaced [JFreeChart chart = createChart(PieChartData.readChinookDataset());]
         *         By two lines below
         */
        PieChartData Data = new PieChartData(new ChinookDB());
        JFreeChart chart = createChart(Data.SumInvoices());

        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart, false);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }


    /**
     * Starting point for the demonstration application.
     * @param args  ignored.
     */
    public static void main(String[] args) {
        PieChartDemowithDB demo = new PieChartDemowithDB("JFreeChart: Pie Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}

