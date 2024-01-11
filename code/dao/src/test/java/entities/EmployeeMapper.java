/*
 * The code in this class is generated on 2024-01-11T15:11:31.513840369.
 * Do not edit, your changes will be lost on the next build of your project.
 */
package entities;

import entities.Employee;
import java.util.function.Function;
import java.util.List;
import io.github.homberghp.recordmappers.RecordMapper;
import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;

/**
 * Maps Employee class.
 *
 */
public class EmployeeMapper extends RecordMapper<Employee, Integer> {

    private EmployeeMapper() {
        super(Employee.class);
    }

    static {
        RecordMapper.register( new EmployeeMapper() );
    }

    @Override
    public Employee construct(Object[] fieldValues) {
        return new Employee( Integer.class.cast( fieldValues[ 0 ] ),
                      String.class.cast( fieldValues[ 1 ] ),
                      String.class.cast( fieldValues[ 2 ] ),
                      usertypes.Email.class.cast( fieldValues[ 3 ] ),
                      Integer.class.cast( fieldValues[ 4 ] ),
                      Boolean.class.cast( fieldValues[ 5 ] ),
                      java.time.LocalDate.class.cast( fieldValues[ 6 ] ),
                      java.time.LocalDate.class.cast( fieldValues[ 7 ] ) );
    }

    @Override
    public Object[] deconstruct(Employee e) {
        return new Object[]{ e.employeeid(),
                             e.lastname(),
                             e.firstname(),
                             e.email(),
                             e.departmentid(),
                             e.available(),
                             e.dob(),
                             e.hiredate() };
    }

    @Override
    public Function<? super Employee, ? extends Integer> keyExtractor() {
        return e -> e.employeeid();
    }

    @Override
    public Class<?> keyType() {
        return Integer.class;
    }

    @Override
    public String keyName() {
        return "employeeid";
    }

    private static final List<EditHelper> editHelpers = List.of(
         new EditHelper( "employeeid", Integer.class, false ),
         new EditHelper( "lastname", String.class, false ),
         new EditHelper( "firstname", String.class, false ),
         new EditHelper( "email", usertypes.Email.class, false ),
         new EditHelper( "departmentid", Integer.class, false ),
         new EditHelper( "available", Boolean.class, false ),
         new EditHelper( "dob", java.time.LocalDate.class, false ),
         new EditHelper( "hiredate", java.time.LocalDate.class, false )
      );

    @Override
    public List<EditHelper> editHelpers(){
         return editHelpers;
    }

}
