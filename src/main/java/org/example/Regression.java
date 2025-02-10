package org.example;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Regression {
    private static XYSeriesCollection dataset = new XYSeriesCollection();
    private static JFreeChart chart;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Regression::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Stress-Strain Curve Interpolator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        // Apply dark theme
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Label.foreground", Color.DARK_GRAY);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);

        // Regression type dropdown
        String[] regressionTypes = {"Polynomial", "Exponential", "Logarithmic", "Power Law"};
        JComboBox<String> regressionTypeBox = new JComboBox<>(regressionTypes);

        // Order selection dropdown
        String[] regressionOrders = {"1st Order", "2nd Order", "3rd Order", "4th Order"};
        JComboBox<String> regressionOrderBox = new JComboBox<>(regressionOrders);

        // Button to apply regression
        JButton regressButton = new JButton("Regress Curve");
        regressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) regressionTypeBox.getSelectedItem();
                String selectedOrder = (String) regressionOrderBox.getSelectedItem();
                performRegression(selectedType, selectedOrder);
            }
        });

        controlPanel.add(new JLabel("Regression Type:"));
        controlPanel.add(regressionTypeBox);
        controlPanel.add(new JLabel("Order:"));
        controlPanel.add(regressionOrderBox);
        controlPanel.add(regressButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        // Create dataset
        XYSeries series = new XYSeries("Example Data");
        series.add(0.15216, 28.3075);
        series.add(0.28992, 54.8941);
        series.add(0.43104, 79.9467);
        series.add(0.57072, 103.56);
        series.add(0.70944, 125.695);
        series.add(0.84912, 146.326);
        series.add(0.99744, 165.307);
        series.add(1.14336, 182.601);
        series.add(1.29888, 198.513);
        series.add(1.46208, 212.668);
        series.add(1.62624, 224.905);
        series.add(1.79328, 235.263);

        dataset.addSeries(series);
        chart = ChartFactory.createXYLineChart(
                "Stress-Strain Curve", "Strain (%)", "Stress (MPa)", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.BLACK);
        chart.getPlot().setBackgroundPaint(Color.DARK_GRAY);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        chartPanel.setMinimumSize(new Dimension(700, 500)); // Prevents shrinking

        frame.add(chartPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void performRegression(String type, String order) {
        // Placeholder for regression logic
        System.out.println("Performing " + type + " regression of " + order);
    }
}

