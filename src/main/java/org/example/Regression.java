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
    private static XYSeries regressedSeries;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Regression::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Stress-Strain Curve Interpolator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        // Apply dark theme to UI
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Label.foreground", Color.WHITE);

        // Main panel split into two regions: Input Panel (Left) and Graph Panel (Right)
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        frame.add(mainPanel, BorderLayout.CENTER);

        // Left Panel: Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.DARK_GRAY); // Changed background to dark gray
        inputPanel.setLayout(new BorderLayout());

        JTextArea dataInputArea = new JTextArea(15, 30);
        JScrollPane scrollPane = new JScrollPane(dataInputArea);
        JButton addDatasetButton = new JButton("Add Dataset");

        // Button action to parse and add dataset
        addDatasetButton.addActionListener(e -> {
            try {
                XYSeries newSeries = new XYSeries("User Data");
                String[] lines = dataInputArea.getText().split("\n");
                for (String line : lines) {
                    String[] values = line.trim().split("\\s+");
                    if (values.length == 2) {
                        double strain = Double.parseDouble(values[0]);
                        double stress = Double.parseDouble(values[1]);
                        newSeries.add(strain, stress);
                    } else {
                        throw new NumberFormatException("Incorrect format");
                    }
                }

                dataset.addSeries(newSeries);




                dataInputArea.setText(""); // Clear input area after adding dataset
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input format. Please enter data as 'strain stress' on each line.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputPanel.add(new JLabel("Enter Strain-Stress Pairs:"), BorderLayout.NORTH);
        inputPanel.add(scrollPane, BorderLayout.CENTER);
        inputPanel.add(addDatasetButton, BorderLayout.SOUTH);

        // Right Panel: Chart Panel
        dataset = new XYSeriesCollection(); // Initialize dataset
        chart = ChartFactory.createXYLineChart(
                "Regressed curve", "Strain (%)", "Stress (MPa)", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.BLACK);
        chart.getPlot().setBackgroundPaint(Color.DARK_GRAY);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        chartPanel.setMinimumSize(new Dimension(700, 500)); // Prevents shrinking

        mainPanel.add(inputPanel);
        mainPanel.add(chartPanel);

        // Control Panel for regression options (Top Section)
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.DARK_GRAY);

        String[] regressionTypes = {"Polynomial", "Exponential", "Logarithmic", "Power Law"};
        JComboBox<String> regressionTypeBox = new JComboBox<>(regressionTypes);

        String[] regressionOrders = {"1st Order", "2nd Order", "3rd Order", "4th Order"};
        JComboBox<String> regressionOrderBox = new JComboBox<>(regressionOrders);

        JButton regressButton = new JButton("Regress Curve");
        JButton plotRegressedButton = new JButton("Plot Regressed Curve");

        regressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) regressionTypeBox.getSelectedItem();
                String selectedOrder = (String) regressionOrderBox.getSelectedItem();
                regressedSeries = performRegression(selectedType, selectedOrder);
            }
        });

        plotRegressedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (regressedSeries != null) {
                    dataset.addSeries(regressedSeries);
                } else {
                    JOptionPane.showMessageDialog(frame, "No regressed data available. Perform regression first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        controlPanel.add(new JLabel("Regression Type:"));
        controlPanel.add(regressionTypeBox);
        controlPanel.add(new JLabel("Order:"));
        controlPanel.add(regressionOrderBox);
        controlPanel.add(regressButton);
        controlPanel.add(plotRegressedButton);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private static XYSeries performRegression(String type, String order) {
        // Placeholder for regression logic
        System.out.println("Performing " + type + " regression of " + order);
        return new XYSeries("Regressed Curve"); // Return empty series for now
    }
}






