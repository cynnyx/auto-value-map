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
import org.junit.Test;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class AutoValueMapExtensionTest {

    @Test
    public void testNoMap() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Simple",
                "package test;\n"
                        + "import java.util.Map;\n"
                        + "import com.google.auto.value.AutoValue;\n"
                        + "@AutoValue\n"
                        + "public abstract class Simple {\n"
                        + "    public abstract String name();\n"
                        + "    public abstract String value();\n"
                        + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Simple",
                "package test;\n"
                        + "\n"
                        + "import javax.annotation.Generated;\n"
                        + "\n"
                        + "@Generated(\"com.google.auto.value.processor.AutoValueProcessor\")\n"
                        + " final class AutoValue_Simple extends Simple {\n"
                        + "\n"
                        + "  private final String name;\n"
                        + "  private final String value;\n"
                        + "\n"
                        + "  AutoValue_Simple(\n"
                        + "      String name,\n"
                        + "      String value) {\n"
                        + "    if (name == null) {\n"
                        + "      throw new NullPointerException(\"Null name\");\n"
                        + "    }\n"
                        + "    this.name = name;\n"
                        + "    if (value == null) {\n"
                        + "      throw new NullPointerException(\"Null value\");\n"
                        + "    }\n"
                        + "    this.value = value;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public String name() {\n"
                        + "    return name;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public String value() {\n"
                        + "    return value;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public String toString() {\n"
                        + "    return \"Simple{\"\n"
                        + "        + \"name=\" + name + \", \"\n"
                        + "        + \"value=\" + value\n"
                        + "        + \"}\";\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public boolean equals(Object o) {\n"
                        + "    if (o == this) {\n"
                        + "      return true;\n"
                        + "    }\n"
                        + "    if (o instanceof Simple) {\n"
                        + "      Simple that = (Simple) o;\n"
                        + "      return (this.name.equals(that.name()))\n"
                        + "           && (this.value.equals(that.value()));\n"
                        + "    }\n"
                        + "    return false;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public int hashCode() {\n"
                        + "    int h = 1;\n"
                        + "    h *= 1000003;\n"
                        + "    h ^= this.name.hashCode();\n"
                        + "    h *= 1000003;\n"
                        + "    h ^= this.value.hashCode();\n"
                        + "    return h;\n"
                        + "  }\n"
                        + "\n"
                        + "}");

        Truth.assert_().about(javaSource())
                .that(source)
                .processedWith(new AutoValueProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testMapWrongGenericTypes() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Simple",
                "package test;\n"
                        + "import java.util.Map;\n"
                        + "import com.google.auto.value.AutoValue;\n"
                        + "@AutoValue\n"
                        + "public abstract class Simple {\n"
                        + "    public abstract String name();\n"
                        + "    public abstract String value();\n"
                        + "    public abstract Map<String,String> toMap();\n"
                        + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Simple",
                "package test;\n"
                        + "\n"
                        + "import java.util.Map;\n"
                        + "import javax.annotation.Generated;\n"
                        + "\n"
                        + "@Generated(\"com.google.auto.value.processor.AutoValueProcessor\")\n"
                        + " final class AutoValue_Simple extends Simple {\n"
                        + "\n"
                        + "  private final String name;\n"
                        + "  private final String value;\n"
                        + "  private final Map<String, String> toMap;\n"
                        + "\n"
                        + "  AutoValue_Simple(\n"
                        + "      String name,\n"
                        + "      String value,\n"
                        + "      Map<String, String> toMap) {\n"
                        + "    if (name == null) {\n"
                        + "      throw new NullPointerException(\"Null name\");\n"
                        + "    }\n"
                        + "    this.name = name;\n"
                        + "    if (value == null) {\n"
                        + "      throw new NullPointerException(\"Null value\");\n"
                        + "    }\n"
                        + "    this.value = value;\n"
                        + "    if (toMap == null) {\n"
                        + "      throw new NullPointerException(\"Null toMap\");\n"
                        + "    }\n"
                        + "    this.toMap = toMap;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public String name() {\n"
                        + "    return name;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public String value() {\n"
                        + "    return value;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public Map<String, String> toMap() {\n"
                        + "    return toMap;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public String toString() {\n"
                        + "    return \"Simple{\"\n"
                        + "        + \"name=\" + name + \", \"\n"
                        + "        + \"value=\" + value + \", \"\n"
                        + "        + \"toMap=\" + toMap\n"
                        + "        + \"}\";\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public boolean equals(Object o) {\n"
                        + "    if (o == this) {\n"
                        + "      return true;\n"
                        + "    }\n"
                        + "    if (o instanceof Simple) {\n"
                        + "      Simple that = (Simple) o;\n"
                        + "      return (this.name.equals(that.name()))\n"
                        + "           && (this.value.equals(that.value()))\n"
                        + "           && (this.toMap.equals(that.toMap()));\n"
                        + "    }\n"
                        + "    return false;\n"
                        + "  }\n"
                        + "\n"
                        + "  @Override\n"
                        + "  public int hashCode() {\n"
                        + "    int h = 1;\n"
                        + "    h *= 1000003;\n"
                        + "    h ^= this.name.hashCode();\n"
                        + "    h *= 1000003;\n"
                        + "    h ^= this.value.hashCode();\n"
                        + "    h *= 1000003;\n"
                        + "    h ^= this.toMap.hashCode();\n"
                        + "    return h;\n"
                        + "  }\n"
                        + "\n"
                        + "}");

        Truth.assert_().about(javaSource())
                .that(source)
                .processedWith(new AutoValueProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testMapNoGenericTypes() throws Exception {
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
                        + "\n"
                        + "import java.lang.Object;\n"
                        + "import java.lang.String;\n"
                        + "import java.util.HashMap;\n"
                        + "import java.util.Map;\n"
                        + "\n"
                        + "final class AutoValue_Simple extends $AutoValue_Simple {\n"
                        + "  AutoValue_Simple(String name, String value) {\n"
                        + "    super(name, value);\n"
                        + "  }\n"
                        + "\n"
                        + "  public Map toMap() {\n"
                        + "    Map<String, Object> map = new HashMap<>();\n"
                        + "    map.put(\"name\",name());\n"
                        + "    map.put(\"value\",value());\n"
                        + "    return map;\n"
                        + "  }\n"
                        + "}");

        Truth.assert_().about(javaSource())
                .that(source)
                .processedWith(new AutoValueProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testMapRightGenericTypes() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Simple",
                "package test;\n"
                        + "import java.util.Map;\n"
                        + "import com.google.auto.value.AutoValue;\n"
                        + "@AutoValue\n"
                        + "public abstract class Simple {\n"
                        + "    public abstract String name();\n"
                        + "    public abstract String value();\n"
                        + "    public abstract Map<String,Object> toMap();\n"
                        + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Simple",
                "package test;\n"
                        + "\n"
                        + "import java.lang.Object;\n"
                        + "import java.lang.String;\n"
                        + "import java.util.HashMap;\n"
                        + "import java.util.Map;\n"
                        + "\n"
                        + "final class AutoValue_Simple extends $AutoValue_Simple {\n"
                        + "  AutoValue_Simple(String name, String value) {\n"
                        + "    super(name, value);\n"
                        + "  }\n"
                        + "\n"
                        + "  public Map toMap() {\n"
                        + "    Map<String, Object> map = new HashMap<>();\n"
                        + "    map.put(\"name\",name());\n"
                        + "    map.put(\"value\",value());\n"
                        + "    return map;\n"
                        + "  }\n"
                        + "}");

        Truth.assert_().about(javaSource())
                .that(source)
                .processedWith(new AutoValueProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }
}