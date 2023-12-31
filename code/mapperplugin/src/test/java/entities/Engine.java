package entities;

import io.github.homberghp.gensquared_annotations.ID;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public record Engine( @ID String type, int cilinders, double hp ) {}