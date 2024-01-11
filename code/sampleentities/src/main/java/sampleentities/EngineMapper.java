/*
 * The code in this class is generated on 2024-01-11T15:46:43.829825889.
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

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "type", String.class, false ),
         new EditHelper( "cilinders", Integer.class, false ),
         new EditHelper( "hp", Double.class, false ),
         new EditHelper( "id", String.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
