package com.benjaminran.practice.concurrency.highlevel;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class ForkBlur extends RecursiveAction {
    private int[][] src;
    private int startRow;
    private int startCol;
    private int width;
    private int height;
    private int[][] dst;

    private int blurWidth; // should be odd
    private static int threshold = 100000;

    public ForkBlur(int[][] src, int startRow, int startCol, int width, int height, int[][] dst) {
        this.src = src;
        this.startRow = startRow;
        this.startCol = startCol;
        this.width = width;
        this.height = height;
        this.dst = dst;
    }

    protected void computeDirectly() {
        int margin = (blurWidth - 1) / 2;
        for(int row = startRow; row < startRow + height; row++) {
            for(int col = startCol; col < startCol + width; col++) { 
                float rt = 0, gt = 0, bt = 0;
                for(int ir = ((row - margin < 0) ? 0 : row - margin);
                    ir <= ((row + margin > src.length - 1) ? src.length - 1 : row + margin);
                    ir++) {
                    for(int ic = ((col - margin < 0) ? 0 : col - margin);
                        ic <= ((col + margin > src[row].length - 1) ? src[row].length - 1 : col + margin);
                        ic++) {
                        int pixel = src[ir][ic];
                        rt += (float)((pixel & 0x00ff0000) >> 16) / blurWidth;
                        gt += (float)((pixel & 0x0000ff00) >> 8) / blurWidth;
                        bt += (float)((pixel & 0x000000ff) >> 0) / blurWidth;
                    }
                }
                dst[row][col] = (0xff000000) | (((int) rt) << 16) | (((int) gt) << 8) | (((int) bt) << 0);
            }
        }
    }

    protected void compute() {
        if(width > threshold) {
            int colSplit = width / 2;
            invokeAll(new ForkBlur(src, startCol, startRow, colSplit, height, dst),
                      new ForkBlur(src, startCol + colSplit, startRow, width - colSplit, height, dst));
            return;
        }
        if(height > threshold){
            int rowSplit = height / 2;
            invokeAll(new ForkBlur(src, startCol, startRow, width, rowSplit, dst),
                      new ForkBlur(src, startCol, startRow + rowSplit, width, height - rowSplit, dst));
            return;
        }
        computeDirectly();
    }

    public static void main(String[] args) {
        if(args.length != 2) {
            usage();
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(args[0]));
        }
        catch (IOException e) {
            usage();
        }
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        System.out.format("w:%d h:%d%n", imgWidth, imgHeight);
        int[][] src = new int[imgHeight][imgWidth];
        int[][] dst = new int[imgHeight][imgWidth];
        for(int row = 0; row < imgHeight; row++){
            for(int col = 0; col < imgWidth; col++) {
                System.out.format("r:%d c:%d%n", row, col);
                src[row][col] = img.getRGB(row, col);
            }
        }
        ForkBlur blur = new ForkBlur(src, 0, 0, src.length, src[0].length , dst);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(blur);
        for(int row = 0; row < dst.length; row++) {
            for(int col = 0; col < dst[0].length; col++) {
                img.setRGB(row, col, dst[row][col]);
            }
        }
        try {
            File outputFile = new File(args[1]);
            ImageIO.write(img, "png", outputFile);
        }
        catch (IOException e) {
            usage();
        }
    }

    private static void usage() {
        System.out.println("Usage: java -jar highlevel path/to/input/image.png path/to/output/image.png");
        System.exit(0);
    }
}
