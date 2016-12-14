package ghosts

import cats.Monad
import cats.syntax.either._
import cats.syntax.functor._


trait GhostEitherT {
  implicit def convert[F[_] : Monad, A, B](m: F[Either[A, B]]): GhostEitherOps[F, A, B] = new GhostEitherOps(m)
}

class GhostEitherOps[F[_] : Monad, A, B](m: F[Either[A, B]]) {
  def flatMapT[C](f: B => Either[A, C]): F[Either[A, C]] = m.map {
    case Right(b) => f(b)
    case Left(a) => Left(a)
  }

  def mapT[C](f: B => C): F[Either[A, C]] = m.map(_.map(f))

  def leftMapT[C](f: A => C): F[Either[C, B]] = m.map(_.leftMap(f))
}

object either extends GhostEitherT

