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
package sampleentities;

import org.junit.jupiter.api.*;
import io.github.homberghp.recordmappers.RecordMapper;
import io.github.homberghp.recordmappers.RecordMapper.EditHelper;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class AnnotationTest {

    //@Disabled("think TDD")
    @Test public void snummerHasId() {
        RecordMapper<Student, Integer> rm = RecordMapper.mapperFor( Student.class );
        List<EditHelper> editHelpers = rm.editHelpers();
        boolean allMatch = editHelpers.stream().filter( eh -> eh.fieldName().equals( "snummer" ) ).allMatch( eh -> eh.generated() );
        assertThat( allMatch ).isTrue();
        System.out.println( "editHelpers = " + editHelpers );
        fail( "method snummerHasId reached end. You know what to do." );
    }
}
