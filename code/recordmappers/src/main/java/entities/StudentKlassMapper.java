/*
 * The code in this class is generated on 2023-12-29T17:41:43.264237327.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package entities;

import entities.StudentKlass;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps StudentKlass class.
 *
 */
public class StudentKlassMapper extends RecordMapper<StudentKlass, String> {

    private StudentKlassMapper() {
        super(StudentKlass.class);
    }

    static {
        RecordMapper.register( new StudentKlassMapper() );
    }

    @Override
    public StudentKlass construct(Object[] fieldValues) {
        return new StudentKlass( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ) );
    }

    @Override
    public Object[] deconstruct(StudentKlass s) {
        return new Object[]{ s.studentKlassId(),
                             s.Description() };
    }

    @Override
    public Function<? super StudentKlass, ? extends String> keyExtractor() {
        return s -> s.studentKlassId();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "studentKlassId";
    }

    private static List<EditHelper> editHelpers = List.of(
         new EditHelper( "studentKlassId", String.class ),
         new EditHelper( "Description", String.class )
      );

    protected List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
