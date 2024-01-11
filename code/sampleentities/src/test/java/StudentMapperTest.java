/*
 * Copyright 2024 Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.github.homberghp.recordmappers.RecordMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import sampleentities.Student;
 
/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class StudentMapperTest {

    //@Disabled("think TDD")
    @Test
    public void idShouldBeGenerated() {
        RecordMapper<Student,Integer> rm = RecordMapper.mapperFor( Student.class);
        List<RecordMapper.EditHelper> editHelpers = rm.editHelpers();
        boolean allMatch = editHelpers.stream().peek(System.out::println ).filter( eh -> eh.fieldName().equals("snummer") ).allMatch( x -> x.generated());
        assertThat(allMatch).isTrue();
        
        fail( "method idShouldBeGenerated reached end. You know what to do." );
    }
}