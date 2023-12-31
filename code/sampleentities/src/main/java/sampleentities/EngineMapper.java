/*
 * The code in this class is generated on 2023-12-31T12:29:31.833573555.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package sampleentities;

import sampleentities.Engine;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Engine class.
 *
 */
public class EngineMapper extends RecordMapper<Engine, String> {

    private EngineMapper() {
        super(Engine.class);
    }

    static {
        RecordMapper.register( new EngineMapper() );
    }

    @Override
    public Engine construct(Object[] fieldValues) {
        return new Engine( String.class.cast( fieldValues[ 0 ] ),
                      Integer.class.cast( fieldValues[ 1 ] ),
                      Double.class.cast( fieldValues[ 2 ] ),
                      String.class.cast( fieldValues[ 3 ] ) );
    }

    @Override
    public Object[] deconstruct(Engine e) {
        return new Object[]{ e.type(),
                             e.cilinders(),
                             e.hp(),
                             e.id() };
    }

    @Override
    public Function<? super Engine, ? extends String> keyExtractor() {
        return e -> e.id();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "id";
    }

    private static List<EditHelper> editHelpers = List.of(
         new EditHelper( "type", String.class ),
         new EditHelper( "cilinders", Integer.class ),
         new EditHelper( "hp", Double.class ),
         new EditHelper( "id", String.class )
      );

    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
