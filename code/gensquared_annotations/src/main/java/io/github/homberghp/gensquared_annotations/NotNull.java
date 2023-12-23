package io.github.homberghp.gensquared_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tag a field or that should not be null.
 * 
 * @author Pieter van den Hombergh  {@code pieter.van.den.hombergh@gmail.com}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.RECORD_COMPONENT, ElementType.PARAMETER } )
public @interface NotNull {
}
