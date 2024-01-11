/*
 * The code in this class is generated on 2024-01-11T15:46:44.186729870.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package testentities;

import testentities.Door;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Door class.
 *
 */
public class DoorMapper extends RecordMapper<Door, String> {

    private DoorMapper() {
        super(Door.class);
    }

    static {
        RecordMapper.register( new DoorMapper() );
    }

    @Override
    public Door construct(Object[] fieldValues) {
        return new Door( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ) );
    }

    @Override
    public Object[] deconstruct(Door d) {
        return new Object[]{ d.doorName(),
                             d.color() };
    }

    @Override
    public Function<? super Door, ? extends String> keyExtractor() {
        return d -> d.doorName();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "doorName";
    }

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "doorName", String.class, false ),
         new EditHelper( "color", String.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
