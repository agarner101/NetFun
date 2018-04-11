package org.andrewgarner.netfun.model;

import java.util.List;

/**
 * Describes a Skier.
 * Created by andrewgarner on 4/10/18.
 */
public class Skier {

    public static class Builder {

        private String firstName;
        private String lastName;
        private int skillLevel;
        private List<Skis> skis;

        public void setFirstName(final String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(final String lastName) {
            this.lastName = lastName;
        }

        public void setSkillLevel(final int skillLevel) {
            this.skillLevel = skillLevel;
        }

        public void setSkis(final List<Skis> skis) {
            this.skis = skis;
        }

        public Skier build() {
            return new Skier(this);
        }

    }

    public final String firstName;
    public final String lastName;
    public final int skillLevel;
    public final List<Skis> skis;

    private Skier(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.skillLevel = builder.skillLevel;
        this.skis = builder.skis;
    }

    @Override
    public String toString() {
        return String.format("Skier %s %s", firstName, lastName);
    }
}
