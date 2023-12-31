/*
 * The code in this class is generated on 2023-12-31T11:37:54.922782440.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package testentities;

import testentities.Car;
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
                      testentities.Engine.class.cast( fieldValues[ 2 ] ) );
    }

    @Override
    public Object[] deconstruct(Car c) {
        return new Object[]{ c.brand(),
                             c.color(),
                             c.engine() };
    }

    @Override
    public Function<? super Car, ? extends String> keyExtractor() {
        return c -> c.brand();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "brand";
    }

    private static List<EditHelper> editHelpers = List.of(
         new EditHelper( "brand", String.class ),
         new EditHelper( "color", String.class ),
         new EditHelper( "engine", testentities.Engine.class )
      );

    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
