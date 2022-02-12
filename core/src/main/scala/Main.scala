import bar.Bar
import foo.Foo

object Main {

  Bar.bar(null)

  Gen.derive[Foo]
}
