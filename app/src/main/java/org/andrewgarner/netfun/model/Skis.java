package org.andrewgarner.netfun.model;

/**
 * Describes Skis that a Skier might use.
 * Created by andrewgarner on 4/10/18.
 */
public class Skis {

    public static class Builder {

        private String brand;
        private String model;
        private double lengthCm;

        public void setBrand(final String brand) {
            this.brand = brand;
        }

        public void setModel(final String model) {
            this.model = model;
        }

        public void setLengthCm(final double lengthCm) {
            this.lengthCm = lengthCm;
        }

        public Skis build() {
            return new Skis(this);
        }
    }

    public final String brand;
    public final String model;
    public final double lengthCm;

    public Skis(final Builder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.lengthCm = builder.lengthCm;
    }
}
