import scala.language.experimental.macros
import scala.reflect.macros._

object Gen {
  def derive[T]: Unit = macro gen[T]

  def gen[T: c.WeakTypeTag](c: whitebox.Context): c.Tree = {
    import c.universe._

    val classMembers = weakTypeOf[T].typeSymbol.asType.toType.members

    val annotationsOnConstructorParams =
      classMembers
        .filter(_.isConstructor)
        .flatMap(_.asMethod.paramLists.flatten)
        .flatMap(_.annotations)

    annotationsOnConstructorParams.foreach(_.tree) // touch annotations to evaluate lazy info

    println(
      s"Annotations on constructor: $annotationsOnConstructorParams"
    )

    classMembers
      .filter(_.toString.contains("foo")) // Take only "foo" TermSymbol and MethodSymbol
      .foreach { s =>
        val annotations = s.annotations
        annotations.foreach(_.tree) // touch annotations to evaluate lazy info
        println(s"Annotations: $annotations, is method? - ${s.isMethod}")
      /*
          The output of the program shows the following:

          Annotations on constructor: List(baz.description("foo"))
          Annotations: List(<notype>), is method? - false
          Annotations: List(<notype>), is method? - true

          Moreover, these annotations are the instances of
          scala.reflect.internal.AnnotationInfos.UnmappableAnnotation
       */

      /*
          On the other hand, commenting out line 6 in Main.scala and running ;clean;compile shows

          Annotations on constructor: List(baz.description("foo"))
          Annotations: List(), is method? - false
          Annotations: List(), is method? - true
       */
      }

    q"()"
  }
}
