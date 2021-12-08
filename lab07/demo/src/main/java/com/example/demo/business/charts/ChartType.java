package com.example.demo.business.charts;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;

public enum ChartType {
    PIE {
        @Override
        JFreeChart makeChart(CategoryDataset categoryDataset) {
            return ChartFactory.createPieChart("", null);
        }
    },

    BAR{
        @Override
        JFreeChart makeChart(CategoryDataset categoryDataset) {
            return ChartFactory.createBarChart("", "", "", categoryDataset);
        }
    },

    LINEAR{
        @Override
        JFreeChart makeChart(CategoryDataset categoryDataset) {
            return ChartFactory.createLineChart("", "", "", categoryDataset);
        }
    };


    abstract JFreeChart makeChart(CategoryDataset categoryDataset);
}

