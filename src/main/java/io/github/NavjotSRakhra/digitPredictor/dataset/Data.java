package io.github.NavjotSRakhra.digitPredictor.dataset;

public record Data(int label, byte[] image) {
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Label: ");
        str.append(label);
        str.append('\n');
        for (int i = 0; i < image().length; i++) {
            str.append(String.format("%03d", Byte.toUnsignedInt(image()[i])));
            if (i % 28 == 27)
                str.append('\n');
        }
        return str.toString();
    }
}
