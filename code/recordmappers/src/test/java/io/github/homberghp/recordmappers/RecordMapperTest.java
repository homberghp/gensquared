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
package io.github.homberghp.recordmappers;

import io.github.homberghp.recordmappers.RecordMapper.FieldPair;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class RecordMapperTest {

    //@Disabled("think TDD")
    @Test
    public void testGeneratedFieldNames() {
        RecordMapper<F, String> fMapper = RecordMapper.mapperFor( F.class );
        Set<String> generatedFieldNames = fMapper.generatedFieldNames();
        assertThat( generatedFieldNames ).containsExactlyInAnyOrder("f_id", "age" );

//        fail( "method testGeneratedFieldNames reached end. You know what to do." );
    }

    //@Disabled("think TDD")
    @Test
    public void testNonGeneratedFieldNames() {
        RecordMapper<F, String> fMapper = RecordMapper.mapperFor( F.class );
        F f = new F( "puk", 16, 55.1234D );
        List<FieldPair> generatedFieldNames = fMapper.nonGeneratedFieldPairs( f );
        assertThat( generatedFieldNames ).containsExactly( new FieldPair("latitude",55.1234D ));
//        fail( "method testNonGeneratedFieldNames reached end. You know what to do." );
    }

}
