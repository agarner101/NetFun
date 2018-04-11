package org.andrewgarner.netfun.io.get_skier;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import org.andrewgarner.netfun.model.Skier;

/**
 * Parser for {@link GetSkierApi}. Intentionally package-private.
 * Created by andrewgarner on 4/10/18.
 */
class GetSkierApiParser {

    private GetSkierApiParser() {
        throw new IllegalAccessError("Class is fully static!");
    }

    /**
     * Parses and returns {@link Skier} if response is valid. Returns null if parsing fails.
     * Example response:
     * <pre>
     *        {
     *          "firstName": "Andy",
     *          "lastName": "Garnier",
     *          "skillLevel": 5,
     *          "skis": [
     *              {
     *                  "brand": "Black Crows",
     *                  "model": "Atris",
     *                  "lengthCm": 189.9
     *              },
     *              {
     *                  "brand": "Armada",
     *                  "model": "JJ",
     *                  "lengthCm": 195
     *              }
     *          ]
     * }
     * </pre>
     */
    @Nullable
    static Skier parse(@Nullable final String response) {
        return new Gson().fromJson(response, Skier.class);
    }
}
