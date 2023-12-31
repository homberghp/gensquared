/*
 * The code in this class is generated on 2023-12-31T12:29:31.946394565.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package testentities;

import testentities.Student;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Student class.
 *
 */
public class StudentMapper extends RecordMapper<Student, String> {

    private StudentMapper() {
        super(Student.class);
    }

    static {
        RecordMapper.register( new StudentMapper() );
    }

    @Override
    public Student construct(Object[] fieldValues) {
        return new Student( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      String.class.cast( fieldValues[ 2 ] ),
                      java.time.LocalDate.class.cast( fieldValues[ 3 ] ),
                      Integer.class.cast( fieldValues[ 4 ] ),
                      String.class.cast( fieldValues[ 5 ] ),
                      String.class.cast( fieldValues[ 6 ] ),
                      String.class.cast( fieldValues[ 7 ] ),
                      Integer.class.cast( fieldValues[ 8 ] ),
                      Boolean.class.cast( fieldValues[ 9 ] ) );
    }

    @Override
    public Object[] deconstruct(Student s) {
        return new Object[]{ s.lastname(),
                             s.tussenvoegsel(),
                             s.firstname(),
                             s.dob(),
                             s.cohort(),
                             s.email(),
                             s.gender(),
                             s.student_class(),
                             s.snummer(),
                             s.active() };
    }

    @Override
    public Function<? super Student, ? extends String> keyExtractor() {
        return s -> s.lastname();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "lastname";
    }

    private static List<EditHelper> editHelpers = List.of(
         new EditHelper( "lastname", String.class ),
         new EditHelper( "tussenvoegsel", String.class ),
         new EditHelper( "firstname", String.class ),
         new EditHelper( "dob", java.time.LocalDate.class ),
         new EditHelper( "cohort", Integer.class ),
         new EditHelper( "email", String.class ),
         new EditHelper( "gender", String.class ),
         new EditHelper( "student_class", String.class ),
         new EditHelper( "snummer", Integer.class ),
         new EditHelper( "active", Boolean.class )
      );

    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
