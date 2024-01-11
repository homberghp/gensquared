package entities;

import io.github.homberghp.gensquared_annotations.Generated;
import io.github.homberghp.gensquared_annotations.ID;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public record Department(@ID( generated = false )
        String name, String desciption,
        String email, @Generated
        Integer departmentid) {

}
