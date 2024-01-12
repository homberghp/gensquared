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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class AnnTester {

    public static void main(String[] args) {
        Class<?> clz = Student.class;
        Field[] declaredFields = clz.getDeclaredFields();
        for ( Field df : declaredFields ) {
            Annotation[] annotations = df.getAnnotations();
            System.out.println( "annotations = " + Arrays.toString( annotations ) );
            System.out.println( "df = " + df );
        }
        System.out.println( "declaredFields = " + Arrays.toString( declaredFields ) );
    }
}
