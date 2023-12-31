package testentities;

import io.github.homberghp.gensquared_annotations.ID;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public record  Car(@ID  String brand, String color, Engine engine ) {}