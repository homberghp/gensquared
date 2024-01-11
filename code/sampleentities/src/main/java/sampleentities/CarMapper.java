/*
 * The code in this class is generated on 2024-01-11T15:46:43.824955293.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package sampleentities;

import sampleentities.Car;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Car class.
 *
 */
public class CarMapper extends RecordMapper<Car, String> {

    private CarMapper() {
        super(Car.class);
    }

    static {
        RecordMapper.register( new CarMapper() );
    }

    @Override
    public Car construct(Object[] fieldValues) {
        return new Car( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      String.class.cast( fieldValues[ 2 ] ),
                      sampleentities.Engine.class.cast( fieldValues[ 3 ] ) );
    }

    @Override
    public Object[] deconstruct(Car c) {
        return new Object[]{ c.id(),
                             c.brand(),
                             c.color(),
                             c.engine() };
    }

    @Override
    public Function<? super Car, ? extends String> keyExtractor() {
        return c -> c.id();
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
         new EditHelper( "id", String.class, false ),
         new EditHelper( "brand", String.class, false ),
         new EditHelper( "color", String.class, false ),
         new EditHelper( "engine", sampleentities.Engine.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
