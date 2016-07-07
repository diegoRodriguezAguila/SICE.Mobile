/*
 * Copyright (C) 2006 The Android Open Source Project
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

package com.elfec.sice.view.text.method;


import android.text.method.PasswordTransformationMethod;

import java.lang.reflect.Field;

/**
 * Helper para cambiar el caracter de el PasswordTransformationMethod
 */
public class MetroPasswordTransformationMethod
{
    private static final char DOT = '\u25CF';


    /**
     * Obtiene una instancia del password Transformation method
     * con el caracter por defecto de metro {@link MetroPasswordTransformationMethod#DOT}
     * @return {@link PasswordTransformationMethod}
     */
    public static PasswordTransformationMethod getInstance(){
        return getInstance(DOT);
    }

    /**
     * /**
     * Obtiene una instancia del password Transformation method
     * con el caracter de los parámetros
     * @param dot caracter
     * @return {@link PasswordTransformationMethod}
     */
    public static PasswordTransformationMethod getInstance(char dot){
        try {
            Field field = PasswordTransformationMethod.class.getDeclaredField("DOT");
            field.setAccessible(true);
            field.set(null, dot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PasswordTransformationMethod.getInstance();
    }
}
