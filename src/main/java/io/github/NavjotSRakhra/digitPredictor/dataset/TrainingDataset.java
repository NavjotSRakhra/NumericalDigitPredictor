package io.github.NavjotSRakhra.digitPredictor.dataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class TrainingDataset {
    public static final int DATA_SIZE = 60_000;
    private static final HashMap<Integer, Data> cache = new HashMap<>(30_000);
    private static final int dataOffset = 16;
    private static final int labelOffset = 8;
    private static final int imageSize = 28 * 28;
    private static final String pathToTrainingData = "D:\\Java Projects\\MNISTWithPersonalNeuralNetwork\\src\\main\\resources\\train-images-idx3-ubyte", pathToTrainingLabels = "D:\\Java Projects\\MNISTWithPersonalNeuralNetwork\\src\\main\\resources\\train-labels-idx1-ubyte";
    private static final RandomAccessFile imageData, imageLabel;

    static {
        try {
            imageData = new RandomAccessFile(new File(pathToTrainingData), "r");
            imageLabel = new RandomAccessFile(new File(pathToTrainingLabels), "r");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getImageAt(int i) throws IOException {
        if (i >= DATA_SIZE) throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds of " + DATA_SIZE);
        byte[] image = new byte[imageSize];
        imageData.seek(dataOffset + (long) i * imageSize);
        imageData.read(image);
        return image;
    }

    private static int getLabelAt(int i) throws IOException {
        if (i >= DATA_SIZE) throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds of " + DATA_SIZE);
        int label;
        imageLabel.seek(labelOffset + (long) i);
        label = imageLabel.read();
        return label;
    }

    public static Data getDataAt(int i) {
        try {
            if (cache.containsKey(i)) return cache.get(i);
            Data data = new Data(getLabelAt(i), getImageAt(i));
            cache.put(i, data);
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
