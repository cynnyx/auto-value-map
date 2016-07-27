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

import com.google.auto.value.processor.AutoValueProcessor;
import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import javax.tools.JavaFileObject;
import org.junit.Ignore;
import org.junit.Test;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class AutoValueMapExtensionTest {

    @Ignore
    @Test
    public void simple() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Simple",
                "package test;\n"
                        + "import java.util.Map;\n"
                        + "import com.google.auto.value.AutoValue;\n"
                        + "@AutoValue\n"
                        + "public abstract class Simple {\n"
                        + "    public abstract String name();\n"
                        + "    public abstract String value();\n"
                        + "    public abstract Map toMap();\n"
                        + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Simple",
                "package test;\n"
                        + "import com.google.auto.value.AutoValue;\n"
                        + "@AutoValue\n"
                        + "public abstract class AutoValue_Simple {\n"
                        + "    public abstract String name();\n"
                        + "    public abstract String value();\n"
                        + "}");

        Truth.assert_().about(javaSource())
                .that(source)
                .processedWith(new AutoValueProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }
}