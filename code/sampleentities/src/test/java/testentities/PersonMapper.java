/*
 * The code in this class is generated on 2023-12-31T12:29:31.945877817.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package testentities;

import testentities.Person;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Person class.
 *
 */
public class PersonMapper extends RecordMapper<Person, String> {

    private PersonMapper() {
        super(Person.class);
    }

    static {
        RecordMapper.register( new PersonMapper() );
    }

    @Override
    public Person construct(Object[] fieldValues) {
        return new Person( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      String.class.cast( fieldValues[ 2 ] ),
                      java.time.LocalDate.class.cast( fieldValues[ 3 ] ),
                      String.class.cast( fieldValues[ 4 ] ) );
    }

    @Override
    public Object[] deconstruct(Person p) {
        return new Object[]{ p.lastname(),
                             p.tussenvoegsel(),
                             p.firstname(),
                             p.dob(),
                             p.gender() };
    }

    @Override
    public Function<? super Person, ? extends String> keyExtractor() {
        return p -> p.lastname();
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
         new EditHelper( "gender", String.class )
      );

    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
