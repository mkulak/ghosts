package ghosts

import cats.syntax.either._
import cats.instances.option._
import org.specs2.mutable._
import either._

import scala.math.BigInt

class GhostEitherTSpec extends Specification {

  "Ghost EitherT" should {
    "map inner Either via mapT" in {
      def f(a: Int) = BigInt(a + 1)

      val v1: Option[Either[String, Int]] = Some(Either.right[String, Int](10))
      v1.mapT(f) should_== Some(Either.right[String, BigInt](BigInt(11)))

      val v2: Option[Either[String, Int]] = None
      v2.mapT(f) should_== v2

      val v3: Option[Either[String, Int]] = Some(Either.left[String, Int]("error"))
      v3.mapT(f) should_== v3
    }

    "flat map inner Either via flatMapT" in {
      def f(a: Int) = Either.right[String, Int](a + 1)

      val v1: Option[Either[String, Int]] = Some(Either.right[String, Int](10))
      v1.flatMapT(f) should_== Some(Either.right[String, BigInt](BigInt(11)))

      val v2: Option[Either[String, Int]] = None
      v2.flatMapT(f) should_== v2

      val v3: Option[Either[String, Int]] = Some(Either.left[String, Int]("error"))
      v3.flatMapT(f) should_== Some(Either.left[String, BigInt]("error"))
    }

    "left map inner Either via leftMapT" in {
      def f(e: String) = ("new", e)

      val v1: Option[Either[String, Int]] = Some(Either.right[String, Int](10))
      v1.leftMapT(f) should_== Some(Either.right[(String, String), Int](10))

      val v2: Option[Either[String, Int]] = None
      v2.leftMapT(f) should_== v2

      val v3: Option[Either[String, Int]] = Some(Either.left[String, Int]("error"))
      v3.leftMapT(f) should_== Some(Either.left[(String, String), Int](("new", "error")))
    }
  }
}
