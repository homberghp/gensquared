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
package io.github.homberghp.jsonconverters;

import io.github.homberghp.recordmappers.RecordMapper;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static java.util.stream.Collectors.joining;
import java.util.stream.IntStream;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class JSONConverter<R extends Record> {

    final RecordMapper<R, ?> mapper;

    private JSONConverter(Class<R> forRecord) {
        this.mapper = RecordMapper.mapperFor( forRecord );
    }

    private static final ConcurrentMap<Class<? extends Record>, JSONConverter> cache = new ConcurrentHashMap<>();

    public static JSONConverter forRecord(Class<? extends Record> forRecord) {
        return cache.computeIfAbsent( forRecord, JSONConverter::new );
    }

    public String toJSON(R r) {
        Object[] params = mapper.deconstruct( r );
        List<RecordMapper.EditHelper> editHelpers = mapper.editHelpers();
        String collect = IntStream.range( 0, editHelpers.size() )
                .mapToObj( i -> jsonQuote( editHelpers.get( i ).fieldName(), params[ i ] ) )
                .collect( joining( ", " ) );
        return "{ " + collect
                + " }";
    }

    static String jsonQuote(String name, Object value) {
        boolean dontQuote = null == value || value instanceof Number || value instanceof Boolean;
        return "\"" + name + "\":" + ( dontQuote ? Objects.toString( value ) : "\"" + Objects.toString( value ) + "\"" );
    }

    public R fromJSON(String json) {
        Map<String, Object> params = Map.of();
        return mapper.construct( params );
    }
}
