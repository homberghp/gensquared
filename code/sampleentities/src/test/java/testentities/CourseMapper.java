/*
 * The code in this class is generated on 2023-12-31T12:29:31.946969053.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package testentities;

import testentities.Course;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Course class.
 *
 */
public class CourseMapper extends RecordMapper<Course, Integer> {

    private CourseMapper() {
        super(Course.class);
    }

    static {
        RecordMapper.register( new CourseMapper() );
    }

    @Override
    public Course construct(Object[] fieldValues) {
        return new Course( Integer.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      Integer.class.cast( fieldValues[ 2 ] ),
                      String.class.cast( fieldValues[ 3 ] ),
                      Short.class.cast( fieldValues[ 4 ] ) );
    }

    @Override
    public Object[] deconstruct(Course c) {
        return new Object[]{ c.courseId(),
                             c.courseName(),
                             c.credits(),
                             c.description(),
                             c.semester() };
    }

    @Override
    public Function<? super Course, ? extends Integer> keyExtractor() {
        return c -> c.courseId();
    }

    @Override
    public Class<?> keyType() {
        return Integer.class;
    }

    @Override
    public String keyName() {
        return "courseId";
    }

    private static List<EditHelper> editHelpers = List.of(
         new EditHelper( "courseId", Integer.class ),
         new EditHelper( "courseName", String.class ),
         new EditHelper( "credits", Integer.class ),
         new EditHelper( "description", String.class ),
         new EditHelper( "semester", Short.class )
      );

    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
