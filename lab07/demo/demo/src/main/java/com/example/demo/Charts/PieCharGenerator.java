package com.example.demo.Charts;

import com.example.demo.Services.ChartsType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.io.IOException;

public class PieCharGenerator extends ChartGenerator{
    private final DefaultPieDataset dataset = new DefaultPieDataset() {};

    @Override
    public byte[] generate(String title, ChartsType type, String xLabel, String yLabel) throws IOException {
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true, true, false
        );
        return saveChart(title, chart, type);
    }

    @Override
    public AbstractDataset getDataSet() {
        return dataset;
    }
}
