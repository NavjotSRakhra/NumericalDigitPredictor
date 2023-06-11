package io.github.NavjotSRakhra.digitPredictor.prediction;

import io.github.NavjotSRakhra.digitPredictor.dataset.Data;
import io.github.NavjotSRakhra.digitPredictor.dataset.TrainingDataset;
import io.github.NavjotSRakhra.neuralNetwork.NeuralNetwork;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Predictor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Predictor::createAndShowGUI);


    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Canvas");

        PaintCanvas paintCanvas = new PaintCanvas();
        Label label = new Label("Guess: ___");
        JPanel panel = new JPanel();

        panel.add(paintCanvas);
        panel.add(label);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Thread thread = new Thread(() -> {

            JFileChooser fileChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Navjot's Neural Network File", "nnn");
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileFilter(filter);
            fileChooser.setDragEnabled(true);

            int result = fileChooser.showOpenDialog(null);

            NeuralNetwork neuralNetwork = null;
            if (result == JFileChooser.APPROVE_OPTION) {
                System.out.println("Reading from: " + fileChooser.getSelectedFile().getPath());
                neuralNetwork = NeuralNetwork.readFrom(fileChooser.getSelectedFile().getPath());
            }
            if (neuralNetwork == null) System.exit(1);
            frame.setTitle(frame.getTitle() + " - " + fileChooser.getSelectedFile().getName());
            while (true) {
                Rectangle rectangle = paintCanvas.getBounds();
                final BufferedImage img = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_ARGB);
                FutureTask<Void> futureTask = new FutureTask<>(() -> {
                    paintCanvas.paintAll(img.createGraphics());
                    return null;
                });
                SwingUtilities.invokeLater(futureTask);
                try {
                    futureTask.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                Image image = img.getScaledInstance(28, 28, Image.SCALE_FAST);
                BufferedImage img2 = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
                img2.createGraphics().drawImage(image, 0, 0, null);

                int[] pixels = new int[28 * 28];
                for (int i = 0; i < 28; i++) {
                    for (int j = 0; j < 28; j++) {
                        int color = (img2.getRGB(i, j));
                        Color c = new Color(color);
                        pixels[j * 28 + i] = 255 - (int) ((c.getBlue() + c.getGreen() + c.getRed()) / 3d);
                    }
                }
//                for (int i = 0; i < pixels.length; i++) {
//                    System.out.printf("%3d", pixels[i]);
//                    if (i % 28 == 27) System.out.println();
//                }
                label.setText("Guess: " + getOutput(neuralNetwork.feedForward(normalizeAndMapToDouble(pixels))));
                System.out.print("\r" + getOutput(neuralNetwork.feedForward(normalizeAndMapToDouble(pixels))));
            }
        });
        thread.start();
    }

    private static void printLetter(int letter) {
        for (int i = 0; i < 10000; i++) {
            Data data = TrainingDataset.getDataAt(i);
            if (data.label() == letter) {
                System.out.println(data);
                break;
            }
        }
    }

    private static String getOutput(double[] doubles) {
        int maxIndex = 0;
        for (int i = 0; i < doubles.length; i++) {
            if (doubles[maxIndex] < doubles[i]) maxIndex = i;
        }
        return String.valueOf(maxIndex);
    }

    private static double[] normalizeAndMapToDouble(int[] pixels) {
        double[] pix = new double[pixels.length];
        for (int i = 0; i < pix.length; i++) {
            pix[i] = pixels[i] / 255d;
        }
        return pix;
    }
}
