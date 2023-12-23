/*
 * The code in this class is generated on 2023-12-29T17:41:43.267486988.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package entities;

import entities.F;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps F class.
 *
 */
public class FMapper extends RecordMapper<F, String> {

    private FMapper() {
        super(F.class);
    }

    static {
        RecordMapper.register( new FMapper() );
    }

    @Override
    public F construct(Object[] fieldValues) {
        return new F( String.class.cast( fieldValues[ 0 ] ),
                      Integer.class.cast( fieldValues[ 1 ] ),
                      Double.class.cast( fieldValues[ 2 ] ) );
    }

    @Override
    public Object[] deconstruct(F f) {
        return new Object[]{ f.f_id(),
                             f.age(),
                             f.latitude() };
    }

    @Override
    public Function<? super F, ? extends String> keyExtractor() {
        return f -> f.f_id();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "f_id";
    }

    private static List<EditHelper> editHelpers = List.of(
         new EditHelper( "f_id", String.class ),
         new EditHelper( "age", Integer.class ),
         new EditHelper( "latitude", Double.class )
      );

    protected List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
