/*
 * The code in this class is generated on 2024-01-11T15:11:31.515881654.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package entities;

import entities.Truck;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Truck class.
 *
 */
public class TruckMapper extends RecordMapper<Truck, Integer> {

    private TruckMapper() {
        super(Truck.class);
    }

    static {
        RecordMapper.register( new TruckMapper() );
    }

    @Override
    public Truck construct(Object[] fieldValues) {
        return new Truck( Integer.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ) );
    }

    @Override
    public Object[] deconstruct(Truck t) {
        return new Object[]{ t.truckid(),
                             t.Plate() };
    }

    @Override
    public Function<? super Truck, ? extends Integer> keyExtractor() {
        return t -> t.truckid();
    }

    @Override
    public Class<?> keyType() {
        return Integer.class;
    }

    @Override
    public String keyName() {
        return "truckid";
    }

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "truckid", Integer.class, false ),
         new EditHelper( "Plate", String.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
