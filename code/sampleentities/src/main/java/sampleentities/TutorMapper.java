/*
 * The code in this class is generated on 2024-01-11T15:46:43.827852006.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package sampleentities;

import sampleentities.Tutor;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Tutor class.
 *
 */
public class TutorMapper extends RecordMapper<Tutor, String> {

    private TutorMapper() {
        super(Tutor.class);
    }

    static {
        RecordMapper.register( new TutorMapper() );
    }

    @Override
    public Tutor construct(Object[] fieldValues) {
        return new Tutor( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      String.class.cast( fieldValues[ 2 ] ),
                      String.class.cast( fieldValues[ 3 ] ),
                      String.class.cast( fieldValues[ 4 ] ),
                      java.time.LocalDate.class.cast( fieldValues[ 5 ] ),
                      String.class.cast( fieldValues[ 6 ] ),
                      String.class.cast( fieldValues[ 7 ] ) );
    }

    @Override
    public Object[] deconstruct(Tutor t) {
        return new Object[]{ t.abreviation(),
                             t.firstname(),
                             t.lastname(),
                             t.tussenvoegsel(),
                             t.academicTitle(),
                             t.dob(),
                             t.gender(),
                             t.teaches() };
    }

    @Override
    public Function<? super Tutor, ? extends String> keyExtractor() {
        return t -> t.abreviation();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "abreviation";
    }

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "abreviation", String.class, false ),
         new EditHelper( "firstname", String.class, false ),
         new EditHelper( "lastname", String.class, false ),
         new EditHelper( "tussenvoegsel", String.class, false ),
         new EditHelper( "academicTitle", String.class, false ),
         new EditHelper( "dob", java.time.LocalDate.class, false ),
         new EditHelper( "gender", String.class, false ),
         new EditHelper( "teaches", String.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
