/*
 * The code in this class is generated on 2024-01-11T15:11:31.510825517.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package entities;

import entities.Department;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Department class.
 *
 */
public class DepartmentMapper extends RecordMapper<Department, Integer> {

    private DepartmentMapper() {
        super(Department.class);
    }

    static {
        RecordMapper.register( new DepartmentMapper() );
    }

    @Override
    public Department construct(Object[] fieldValues) {
        return new Department( String.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      String.class.cast( fieldValues[ 2 ] ),
                      Integer.class.cast( fieldValues[ 3 ] ) );
    }

    @Override
    public Object[] deconstruct(Department d) {
        return new Object[]{ d.name(),
                             d.desciption(),
                             d.email(),
                             d.departmentid() };
    }

    @Override
    public Function<? super Department, ? extends Integer> keyExtractor() {
        return d -> d.departmentid();
    }

    @Override
    public Class<?> keyType() {
        return Integer.class;
    }

    @Override
    public String keyName() {
        return "departmentid";
    }

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "name", String.class, false ),
         new EditHelper( "desciption", String.class, false ),
         new EditHelper( "email", String.class, false ),
         new EditHelper( "departmentid", Integer.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
