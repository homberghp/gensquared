package testentities;

import io.github.homberghp.gensquared_annotations.ID;


/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public record Door( @ID String doorName, String color ) {}