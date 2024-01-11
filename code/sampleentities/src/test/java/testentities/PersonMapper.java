/*
 * The code in this class is generated on 2024-01-11T15:46:44.183594551.
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

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "lastname", String.class, false ),
         new EditHelper( "tussenvoegsel", String.class, false ),
         new EditHelper( "firstname", String.class, false ),
         new EditHelper( "dob", java.time.LocalDate.class, false ),
         new EditHelper( "gender", String.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
