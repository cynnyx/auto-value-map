/*
 * Copyright (c) 2016 Cynny SpA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cynnyx.auto.value.map;

import com.google.auto.value.extension.AutoValueExtension;
import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

import static javax.lang.model.element.Modifier.PUBLIC;

class MapMethod {
    private static final String METHOD_NAME = "toMap";

    static MethodSpec generateMethod(List<Property> properties) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME)
                .addModifiers(PUBLIC)
                .returns(Map.class);

        builder.addStatement("$T<$T, $T> map = new $T<>()", Map.class, String.class, Object.class, HashMap.class);
        for (Property p : properties) {
            builder.addStatement("map.put($S,$L())", p.humanName, p.methodName);
        }
        builder.addStatement("return map");

        return builder.build();
    }

    static Set<ExecutableElement> filteredAbstractMethods(AutoValueExtension.Context context) {
        TypeElement type = context.autoValueClass();

        // check that the class contains method: public abstract Map<String, Object> toMap() or Map toMap()
        ParameterizedTypeName targetReturnType = ParameterizedTypeName.get(ClassName.get(Map.class), ClassName.get(String.class), TypeName.OBJECT);
        ImmutableSet.Builder<ExecutableElement> foundMethods = ImmutableSet.builder();

        for (ExecutableElement method : ElementFilter.methodsIn(type.getEnclosedElements())) {
            if (method.getModifiers().contains(Modifier.ABSTRACT) && method.getModifiers().contains(Modifier.PUBLIC)
                    && method.getSimpleName().toString().equals(METHOD_NAME) && method.getParameters().size() == 0) {
                TypeMirror rType = method.getReturnType();
                TypeName returnType = TypeName.get(rType);
                if (returnType.equals(targetReturnType)) {
                    foundMethods.add(method);
                    break;
                }

                if (returnType.equals(targetReturnType.rawType)) {
                    if (returnType instanceof ParameterizedTypeName) {
                        Messager messager = context.processingEnvironment().getMessager();
                        ParameterizedTypeName paramReturnType = (ParameterizedTypeName) returnType;
                        TypeName argument = paramReturnType.typeArguments.get(0);
                        messager.printMessage(Diagnostic.Kind.WARNING,
                                String.format("Found public static method returning Map<%s> instead of Map<String,Object>", argument));
                    } else {
                        foundMethods.add(method);
                    }
                }
            }
        }

        return foundMethods.build();
    }
}
