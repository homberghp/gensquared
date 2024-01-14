package sampleentities;

import io.github.homberghp.gensquared_annotations.ID;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public record Car( @ID String id, String brand, String color, Engine engine ) {}
