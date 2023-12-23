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

import java.util.Map;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public interface RecordEdit<R extends Record, K> {

    R edit(R orginal, Map<String, Object> changes);

    default R edit(R orginal, String fieldName0, Object fieldValue0) {
        return edit( orginal, Map.of( fieldName0, fieldValue0 ) );
    }

    default R edit(R orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1
        ) );
    }

    default R edit(R orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2
    ) {
        return edit( orginal
        , Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2
        ) 

    );
    }

    default R edit(R orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2,
            String fieldName3, Object fieldValue3
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2,
                fieldName3, fieldValue3
        ) );
    }

    default R edit(R  orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2,
            String fieldName3, Object fieldValue3,
            String fieldName4, Object fieldValue4
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2,
                fieldName3, fieldValue3,
                fieldName4, fieldValue4
        ) );
    }

    default R edit(R  orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2,
            String fieldName3, Object fieldValue3,
            String fieldName4, Object fieldValue4,
            String fieldName5, Object fieldValue5
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2,
                fieldName3, fieldValue3,
                fieldName4, fieldValue4,
                fieldName5, fieldValue5
        ) );
    }

    default R edit(R  orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2,
            String fieldName3, Object fieldValue3,
            String fieldName4, Object fieldValue4,
            String fieldName5, Object fieldValue5,
            String fieldName6, Object fieldValue6
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2,
                fieldName3, fieldValue3,
                fieldName4, fieldValue4,
                fieldName5, fieldValue5,
                fieldName6, fieldValue6
        ) );
    }

    /**
     * Return an entity with fieldName0 set to fieldvalue0, fieldName1 set to
     * fieldvalue1, fieldName2 set to fieldvalue2, fieldName3 set to
     * fieldvalue3, fieldName4 set to fieldvalue4, fieldName5 set to
     * fieldvalue5, fieldName6 set to fieldvalue6, and fieldName7 set to
     * fieldvalue7.
     *
     * @param orginal
     * @param fieldName0
     * @param fieldValue0
     * @param fieldName1
     * @param fieldValue1
     * @param fieldName2
     * @param fieldValue2
     * @param fieldName3
     * @param fieldValue3
     * @param fieldName4
     * @param fieldValue4
     * @param fieldName5
     * @param fieldValue5
     * @param fieldName6
     * @param fieldValue6
     * @param fieldName7
     * @param fieldValue7
     * @return an updated entity
     */
    default R edit(R  orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2,
            String fieldName3, Object fieldValue3,
            String fieldName4, Object fieldValue4,
            String fieldName5, Object fieldValue5,
            String fieldName6, Object fieldValue6,
            String fieldName7, Object fieldValue7
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2,
                fieldName3, fieldValue3,
                fieldName4, fieldValue4,
                fieldName5, fieldValue5,
                fieldName6, fieldValue6,
                fieldName7, fieldValue7
        ) );
    }

    /**
     * Return an entity with fieldName0 set to fieldvalue0, fieldName1 set to
     * fieldvalue1, fieldName2 set to fieldvalue2, fieldName3 set to
     * fieldvalue3, fieldName4 set to fieldvalue4, fieldName5 set to
     * fieldvalue5, fieldName6 set to fieldvalue6, fieldName7 set to
     * fieldvalue7, and fieldName8 set to fieldvalue8.
     *
     * @param orginal
     * @param fieldName0
     * @param fieldValue0
     * @param fieldName1
     * @param fieldValue1
     * @param fieldName2
     * @param fieldValue2
     * @param fieldName3
     * @param fieldValue3
     * @param fieldName4
     * @param fieldValue4
     * @param fieldName5
     * @param fieldValue5
     * @param fieldName6
     * @param fieldValue6
     * @param fieldName7
     * @param fieldValue7
     * @param fieldName8
     * @param fieldValue8
     * @return an updated entity
     */
    default R edit(R  orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2,
            String fieldName3, Object fieldValue3,
            String fieldName4, Object fieldValue4,
            String fieldName5, Object fieldValue5,
            String fieldName6, Object fieldValue6,
            String fieldName7, Object fieldValue7,
            String fieldName8, Object fieldValue8
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2,
                fieldName3, fieldValue3,
                fieldName4, fieldValue4,
                fieldName5, fieldValue5,
                fieldName6, fieldValue6,
                fieldName7, fieldValue7,
                fieldName8, fieldValue8
        ) );
    }

    /**
     * Return an entity with fieldName0 set to fieldvalue0, fieldName1 set to
     * fieldvalue1, fieldName2 set to fieldvalue2, fieldName3 set to
     * fieldvalue3, fieldName4 set to fieldvalue4, fieldName5 set to
     * fieldvalue5, fieldName6 set to fieldvalue6, fieldName7 set to
     * fieldvalue7, fieldName8 set to fieldvalue8, and fieldName9 set to
     * fieldvalue9.
     *
     * @param orginal
     * @param fieldName0
     * @param fieldValue0
     * @param fieldName1
     * @param fieldValue1
     * @param fieldName2
     * @param fieldValue2
     * @param fieldName3
     * @param fieldValue3
     * @param fieldName4
     * @param fieldValue4
     * @param fieldName5
     * @param fieldValue5
     * @param fieldName6
     * @param fieldValue6
     * @param fieldName7
     * @param fieldValue7
     * @param fieldName8
     * @param fieldValue8
     * @param fieldName9
     * @param fieldValue9
     * @return an updated entity
     */
    default R edit(R  orginal,
            String fieldName0, Object fieldValue0,
            String fieldName1, Object fieldValue1,
            String fieldName2, Object fieldValue2,
            String fieldName3, Object fieldValue3,
            String fieldName4, Object fieldValue4,
            String fieldName5, Object fieldValue5,
            String fieldName6, Object fieldValue6,
            String fieldName7, Object fieldValue7,
            String fieldName8, Object fieldValue8,
            String fieldName9, Object fieldValue9
    ) {
        return edit( orginal, Map.of(
                fieldName0, fieldValue0,
                fieldName1, fieldValue1,
                fieldName2, fieldValue2,
                fieldName3, fieldValue3,
                fieldName4, fieldValue4,
                fieldName5, fieldValue5,
                fieldName6, fieldValue6,
                fieldName7, fieldValue7,
                fieldName8, fieldValue8,
                fieldName9, fieldValue9
        ) );
    }

}
