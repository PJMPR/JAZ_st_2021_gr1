package com.pjwstk.sakila.logic.charts.builder;

import com.pjwstk.sakila.logic.charts.data.Series;
import org.jfree.chart.JFreeChart;

public interface ChartBuilding {
    ChartBuilder forSeries(Series series);

    ChartBuilder forChartType(ChartType chartType);

    ChartBuilder build();

    JFreeChart getChart();
}
