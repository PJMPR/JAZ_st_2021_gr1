package com.example.demo.Charts;

import com.example.demo.Services.ChartsType;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public abstract class ChartGenerator {
    public abstract byte [] generate(String title, ChartsType type, String xLabel, String yLabel) throws IOException;
    public abstract AbstractDataset getDataSet();
    public byte [] saveChart (String title, JFreeChart chart, ChartsType type) throws IOException{
        File chartFile = new File(type + title + ".jpeg");
        ChartUtilities.saveChartAsJPEG(chartFile, chart, 900, 600);
        BufferedImage bImage = ImageIO.read(new File(type+title+".jpeg"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
    }
}