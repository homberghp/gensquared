package entities;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public record Course( int courseId, String courseName, int credits,
            String description, short semester ) {}