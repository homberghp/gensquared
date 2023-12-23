/*
 * Copyright 2023 Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}.
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
package entities;

import io.github.homberghp.recordmappers.RecordMapper;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class StudentMapperTest {

    Student piet = new Student( 1234, "Puk", "von", "Piet", LocalDate.of( 2000, 3, 18 ), 2020, "p.puk@fantys.nl", "M", new StudentKlass( "TIP2A", "Informatics 1. semester Dutch" ), true );

    //@Disabled("think TDD")
    @Test
    public void testEdit() {
        RecordMapper<Student, Integer> sm = RecordMapper.mapperFor( Student.class );

        var paula = sm.edit( piet, Map.of( "gender", "F", "firstname", "Paula" ) );
        assertThat( paula.firstname() ).isEqualTo( "Paula" );
        assertThat( paula.gender() ).isEqualTo( "F" );
//        fail( "method testEdit reached end. You know what to do." );
    }

    @Test
    public void testEdit2() {
        RecordMapper<Student, Integer> sm = RecordMapper.mapperFor( Student.class );

        var paula = sm.edit( piet, "gender", "F", "firstname", "Paula", "email", "paula@fantys.nl" );
        assertThat( paula.firstname() ).isEqualTo( "Paula" );
        assertThat( paula.gender() ).isEqualTo( "F" );
        assertThat( paula.email() ).isEqualTo( "paula@fantys.nl" );
//        fail( "method testEdit2 reached end. You know what to do." );
    }
}
