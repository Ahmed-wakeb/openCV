import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {

//        System.out.println("Hello world!");

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture video = new VideoCapture(0);
        Mat f = new Mat();
        while (true){
            video.read(f);
            showResult(f);
        }
    }
    public static void showResult(Mat img) {
        Imgproc.resize(img,img,new Size(640,480));
        MatOfByte m = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, m);
        byte[] byteArray = m.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
            frame.pack();
            frame.setVisible(true);
            FileWriter fileWriter = new FileWriter("C:\\Users\\User\\IdeaProjects\\openCV\\src\\respons.json");
            fileWriter.write(frame.toString());
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}