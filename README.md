# Steps to reproduce

1. Clone repository
2. Run in the terminal:
   ```bash
   sbt ";clean;compile"
   ```
   Output:
   ```
   Annotations on constructor: List(baz.description("foo"))
   Annotations: List(<notype>), is method? - false
   Annotations: List(<notype>), is method? - true
   ```

3. Comment out line 6 in `Main.scala` and run `sbt ";clean;compile"` again:
   ```
   Annotations on constructor: List(baz.description("foo"))
   Annotations: List(), is method? - false
   Annotations: List(), is method? - true
   ```