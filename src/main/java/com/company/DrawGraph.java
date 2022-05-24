package com.company;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.*;



@SuppressWarnings("serial")
public class DrawGraph extends JPanel  {
    private static final int MAX_SCORE = 20;
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 12;
    private static final int Y_HATCH_CNT = 10;
    private List<Integer> kuyruklist;
    private List<Integer> timelist;


    public DrawGraph(List<Integer> scores,List<Integer> timelist) {
        this.kuyruklist = scores;
        this.timelist=timelist;
    }






    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    static void createAndShowGui() {
        List<Integer> kuyrukdizi = new ArrayList<Integer>();
        List<Integer> süredizi = new ArrayList<Integer>();
        Random random = new Random();
        int maxDataPoints = 16;
        int maxScore = 20;


        //---------------------------------------------------
        Random rand = new Random();
        int dizi[] = new int[180];
        dizi[0] = rand.nextInt(24 ) + 1;
        int süre = 0;
        int topsüre=0;
        int kuyruk = 0;
        int süreort=0;
        int topkuy=0;
        int müşsayi=0;
        int sürework=0;
        for (int i = 1; i < 180; i++) {
            dizi[i] = 0;
        }

        for (int i = 0; i<179; i++) {
            if (dizi[i + 1] == 0) {
                dizi[i + 1] = rand.nextInt(24 - 1 + 1) + 1;
            }

            for (int j = 0; j < dizi[i]; j++) {

                if(topsüre ==179){
                    break;
                }

                if ((j+1) % 10==0) {
                    kuyruk++;
                    topkuy++;

                }
                kuyrukdizi.add(kuyruk);

                System.out.println("Bilgisayar : " + (i+1) + "---> Dakika : " + (j+1) + "--->Tahmin Edilen Süre :" + dizi[i] + "--->Kuyruk : " + kuyruk);
                müşsayi=i+1;
                süre++;

                süredizi.add(süre);
                topsüre++;
                if (kuyruk!=0){
                    süreort++;

                }


            }
            if (kuyruk >=1) {
                kuyruk=kuyruk-1;

            }

            if (süre<10){

                for (int j=süre;j<10;j++){
                    süre++;
                    System.out.println("Bilgisayar Yok: " + "---> Dakika : " + (j+1) + "--->Kuyruk : " + kuyruk);
                    if (i != 180) {
                        if (dizi[i + 1] == 0) {
                            dizi[i + 1] = rand.nextInt(24 - 1 + 1) + 1;
                        }
                    }
                    sürework++;

                }
                kuyrukdizi.add(kuyruk);
                süredizi.add(süre);
            }



        }
        if (sürework==0){
            sürework++;
        }
        System.out.println("Toplam Kuyruk : "+topkuy);
        System.out.println("Süre Ort : "+süreort);
        System.out.println("Boş Kalınan Süre : "+sürework);
        System.out.println("Toplam Müşteri : "+müşsayi);
        System.out.println("Kuyruktaki ortalam Müşteri : "+süreort/topkuy);
        System.out.println("Ortalama Çalışma Süresi : "+(topsüre+1)/sürework);
        System.out.println("Ortalama Müşteri Sayısı : "+180/müşsayi);
        //---------------------------------------------------




        //DrawGraph mainPanel = new DrawGraph(kuyrukdizi,süredizi);

        CategoryChart chart = getChart(süredizi.stream().mapToDouble(i -> i).toArray(),kuyrukdizi.stream().mapToDouble(i -> i).toArray());
        System.out.println(Arrays.toString(süredizi.stream().mapToDouble(i -> i).toArray()));

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                new SwingWrapper<CategoryChart>(chart).displayChart();
            }
        });

        t.start();

    }
    public static CategoryChart  getChart(double[] xData, double[] yData) {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Stick);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideN.InsideSW);
        chart.getStyler().setMarkerSize(16);


        chart.addSeries(" ", xData, yData);

        return chart;
    }


}