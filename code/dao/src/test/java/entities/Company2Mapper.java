/*
 * The code in this class is generated on 2024-01-11T15:11:31.512558692.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package entities;

import entities.Company2;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Company2 class.
 *
 */
public class Company2Mapper extends RecordMapper<Company2, String> {

    private Company2Mapper() {
        super(Company2.class);
    }

    static {
        RecordMapper.register( new Company2Mapper() );
    }

    @Override
    public Company2 construct(Object[] fieldValues) {
        return new Company2( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      String.class.cast( fieldValues[ 2 ] ),
                      String.class.cast( fieldValues[ 3 ] ),
                      String.class.cast( fieldValues[ 4 ] ),
                      String.class.cast( fieldValues[ 5 ] ),
                      Integer.class.cast( fieldValues[ 6 ] ),
                      Integer.class.cast( fieldValues[ 7 ] ) );
    }

    @Override
    public Object[] deconstruct(Company2 c) {
        return new Object[]{ c.name(),
                             c.country(),
                             c.city(),
                             c.address(),
                             c.ticker(),
                             c.postcode(),
                             c.i(),
                             c.j() };
    }

    @Override
    public Function<? super Company2, ? extends String> keyExtractor() {
        return c -> c.name();
    }

    @Override
    public Class<?> keyType() {
        return String.class;
    }

    @Override
    public String keyName() {
        return "name";
    }

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "name", String.class, false ),
         new EditHelper( "country", String.class, false ),
         new EditHelper( "city", String.class, false ),
         new EditHelper( "address", String.class, false ),
         new EditHelper( "ticker", String.class, false ),
         new EditHelper( "postcode", String.class, false ),
         new EditHelper( "i", Integer.class, false ),
         new EditHelper( "j", Integer.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
