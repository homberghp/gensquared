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
package io.github.homberghp.recordmappers;

import io.github.homberghp.gensquared_annotations.Generated;
import io.github.homberghp.gensquared_annotations.ID;
import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class RecordMapperGeneratorTest {

    record F(@ID String f_id, @Generated int age, double latitude){
    
    }
    
    //@Disabled("think TDD")
    @Test
    public void testGetFieldNamedId() {
        RecordMapperGenerator rmg= new RecordMapperGenerator(F.class);
        Optional<Field> fieldNamedId = rmg.getFieldNamedId();
        assertThat(fieldNamedId).isNotEmpty();
        assertThat(fieldNamedId.get().getName()).isEqualTo( "f_id");
//        fail( "method testGetFieldNamedId reached end. You know what to do." );
    }
    
    //@Disabled("think TDD")
    @Test
    public void testJavaSource() {
        RecordMapperGenerator rmg= new RecordMapperGenerator(F.class);
        String javaSource = rmg.javaSource();
        assertThat(javaSource).contains( "FMapper extends RecordMapper<F, String>");
//        fail( "method testJavaSource reached end. You know what to do." );
    }
}
