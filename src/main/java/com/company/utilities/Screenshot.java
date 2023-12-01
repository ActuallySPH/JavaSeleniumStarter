package com.company.utilities;

import com.company.pages.HomePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screenshot {
    WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void captureScreen(String foldername, String filename) {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshot\\" + foldername + "\\" + filename + ".jpg");
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkMultipleScreenCaptured(String browser, String method) {
        File downloadsDir = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshot\\" + browser + "\\MultipleScreens\\" + method + "\\");
        File[] downloadDirFiles = downloadsDir.listFiles();
        if (downloadDirFiles != null) {
            if (browser.contains("Pane Behavior")) {
                captureScreen(browser + "\\MultipleScreens\\" + method + "\\", "item" + downloadDirFiles.length);
            }
            return true;
        } else {
            return false;
        }
    }

    public void appendMultipleScreenCaptured(String foldername, String filename) throws IOException {
        File downloadsDir = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshot\\" + foldername.split("\\\\")[0] + "\\MultipleScreens\\" + filename + "\\");
        File[] imgFiles = new File[downloadsDir.list().length];
        HomePage homePage = new HomePage(driver);
        homePage.getMultipleScreenCapture(filename, imgFiles.length);
        imgFiles = new File[downloadsDir.list().length];
        for (int i = 0; i < imgFiles.length; i++) {
            imgFiles[i] = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshot\\" + foldername.split("\\\\")[0] + "\\MultipleScreens\\" + filename + "\\item" + i + ".jpg");
        }
        int offset = 2;
        int height = 0, width = 0;
        if (imgFiles != null) {
            BufferedImage[] imgarray = new BufferedImage[imgFiles.length];
            for (int i = 0; i < imgFiles.length; i++) {
                imgarray[i] = ImageIO.read(imgFiles[i]);
                height += imgarray[i].getHeight() + offset;
                width = imgarray[i].getWidth() + offset;
            }
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = newImage.createGraphics();
            Color oldColor = g2.getColor();
            g2.setPaint(Color.BLACK);
            g2.fillRect(0, 0, width, height);
            g2.setColor(oldColor);
            g2.drawImage(imgarray[0], null, 0, 0);
            int hgt = imgarray[0].getHeight();
            for (int i = 1; i < imgFiles.length; i++) {
                g2.drawImage(imgarray[i], null, 0, hgt);
                hgt += imgarray[i].getHeight();
            }
            g2.dispose();
            File destFolder = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshot\\" + foldername);
            if (!destFolder.exists()) {
                destFolder.mkdir();
            }
            ImageIO.write(newImage, "png", new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshot\\" + foldername + "\\" + filename + ".jpg"));
        }
        FileUtils.cleanDirectory(downloadsDir);
    }
}
