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

import com.google.common.collect.Lists;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import java.util.ArrayList;
import java.util.List;

class ConstructorMethod {
    static MethodSpec generateConstructor(List<Property> properties) {
        List<ParameterSpec> params = Lists.newArrayList();
        List<String> keySet = new ArrayList<>();
        for (Property p : properties) {
            params.add(ParameterSpec.builder(p.type, p.humanName).build());
            keySet.add(p.humanName);
        }

        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addParameters(params);

        StringBuilder superFormat = new StringBuilder("super(");
        for (int i = properties.size(); i > 0; i--) {
            superFormat.append("$N");
            if (i > 1) superFormat.append(", ");
        }
        superFormat.append(")");
        builder.addStatement(superFormat.toString(), keySet.toArray());

        return builder.build();
    }
}
