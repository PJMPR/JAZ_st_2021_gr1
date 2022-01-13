package com.pjwstk.sakila.logic.charts;

import com.pjwstk.sakila.logic.charts.data.Series;

import java.io.IOException;

public interface ChartCreation {
    ChartCreation makePieChart(Series series);

    ChartCreation makeBarChart(Series series);

    ChartCreation makeLinearChart(Series series);

    byte[] toByteArray();

    void toFile(String filePath);
}
