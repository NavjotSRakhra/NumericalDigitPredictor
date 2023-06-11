package io.github.NavjotSRakhra.digitPredictor;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import io.github.NavjotSRakhra.digitPredictor.dataset.Data;
import io.github.NavjotSRakhra.digitPredictor.dataset.TestingDataset;
import io.github.NavjotSRakhra.digitPredictor.dataset.TrainingDataset;
import io.github.NavjotSRakhra.neuralNetwork.NeuralNetwork;
import io.github.NavjotSRakhra.neuralNetwork.activation.TanH;
import io.github.NavjotSRakhra.progressPrinter.ProgressPrinter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TestAndTrain {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacDarkLaf());

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Navjot's Neural Network File", "nnn");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileFilter(filter);
        fileChooser.setDragEnabled(true);

        int result = fileChooser.showOpenDialog(null);

        NeuralNetwork neuralNetwork;
        if (result == JFileChooser.APPROVE_OPTION) {
            System.out.println("Reading from: " + fileChooser.getSelectedFile().getPath());
            neuralNetwork = NeuralNetwork.readFrom(fileChooser.getSelectedFile().getPath());
        } else {
            neuralNetwork = new NeuralNetwork(new TanH(), 784, 10, 800);
            neuralNetwork.isOutputSoftmax(true);
            neuralNetwork.setLearningRate(0.1);
        }

        testNeuralNetwork(neuralNetwork);
        final int epochs = 10;
        UUID uuid = UUID.randomUUID();

        for (int i = 0; i < epochs; i++) {
            System.out.println();
            System.out.println("-".repeat(150));
            System.out.println();
            System.out.println("Training Epoc: " + (i + 1));

            trainNeuralNetwork(neuralNetwork);

            System.out.println("Testing" + " Epoc: " + (i + 1));

            testNeuralNetwork(neuralNetwork);

            neuralNetwork.setLearningRate(Math.pow(neuralNetwork.getLearningRate(), 1.1));

            System.out.println("New learning rate: " + neuralNetwork.getLearningRate());

            neuralNetwork.writeTo(uuid + "-Trained-Neural-Network-" + i + ".nnn");
        }
    }


    private static void trainNeuralNetwork(NeuralNetwork neuralNetwork) {
        List<Integer> arr = new ArrayList<>(TrainingDataset.DATA_SIZE);
        ProgressPrinter progressPrinter = new ProgressPrinter();
        for (int i = 0; i < TrainingDataset.DATA_SIZE; i++) {
            arr.add(i);
        }
        Collections.shuffle(arr);

        System.out.println("Training neural network");
        progressPrinter.update(0);

        float c = 0;
        for (Integer i : arr) {
            Data trainingData = TrainingDataset.getDataAt(i);
            neuralNetwork.train(normalizeAndMapToDoubleArray(trainingData.image()), getLabelAt(trainingData.label()));
            progressPrinter.update((c + 1) / TrainingDataset.DATA_SIZE);
            c += 1;
        }
    }

    private static void testNeuralNetwork(NeuralNetwork neuralNetwork) {
        int correct = 0;
        ProgressPrinter progressPrinter = new ProgressPrinter();
        System.out.println("Testing");
        progressPrinter.update(0);

        for (int i = 0; i < TestingDataset.DATA_SIZE; i++) {
            Data testData = TestingDataset.getDataAt(i);
            double[] output = neuralNetwork.feedForward(normalizeAndMapToDoubleArray(testData.image()));
            int max = 0;
            for (int j = 0; j < output.length; j++) {
                if (output[max] < output[j]) max = j;
            }
            if (testData.label() == max) correct++;

            progressPrinter.update((i + 1d) / TestingDataset.DATA_SIZE);
        }
        System.out.println("Correctly predicted count: " + correct);
        System.out.printf("Accuracy: %3.2f%%%n", (100d * correct / TestingDataset.DATA_SIZE));
    }

    private static double[] getLabelAt(int label) {
        double[] temp = new double[10];
        temp[label] = 1;
        return temp;
    }

    private static short unsignedByte(byte n) {
        return (short) Byte.toUnsignedInt(n);
    }

    private static double[] normalizeAndMapToDoubleArray(byte[] input) {
        double[] doubleInput = new double[input.length];
        for (int i = 0; i < doubleInput.length; i++) {
            doubleInput[i] = unsignedByte(input[i]) / 255d;
        }
        return doubleInput;
    }
}
