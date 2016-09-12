# auto-value-map
AutoValue Extension to add Map generation support. Generates a `Map<String,Object>` where the keys are the field names and the values the related field values.  

## Usage

Include the AutoValue: Map Extension in your project and add `public abstract Map<String,Object> toMap()` to any of your `@AutoValue` annotated classes.

```java
@AutoValue public abstract class Foo {
  public abstract String bar();
  public abstract Integer bar2();
  public abstract Map<String,Object> toMap();
}
```

Now build your project and use the generated `toMap()` method in Foo.

## Download

Add a Gradle dependency:

```groovy
apt 'com.github.cynnyx:auto-value-map:1.0'
```

(Using the [android-apt](https://bitbucket.org/hvisser/android-apt) plugin)

## License

    Copyright 2016 Cynny

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
