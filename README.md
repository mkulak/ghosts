# Ghosts

Nano library that provides syntax niceties on top of [cats](https://github.com/typelevel/cats)


## Rationale

Let's say you have `val v: F[Either[A, B]]` where `F` is some monad and you want to convert `Either` inside `F`. 
You have following options:
* Use nested map calls:
    ```scala
    v.map(_.map(f))
    ```
    Pros:
    * Pretty straightforward
    * Type of outer monad doesn't change - it's still `F`
    
    Cons:
    * Code become clattered with multiple map/flatMap calls

* Use ```EitherT```:
    ```scala
    import cats.data.EitherT
        
    EitherT(v).map(f).value
    ```
    Pros:
    * Allows you to use it in for-comprehension
    
    Cons:
    * Type of outer monad changes - now it's `EitherT` instead of `F` and you can't directly pass your value to 
    function that expects `F'.
    * Increased depth of monad's stack makes code harder to read
    * Code is clattered with `EitherT`'s contructor calls 

* Use this library:
    ```scala
    import ghosts.either._
        
    v.mapT(f)
    ```
    Pros:
    * Type of outer monad doesn't change: all methods of `F` are directly available to you, 
     you can freely pass it to any fuction that expects `F`.
    * Depth of monad stack stays the same
    
    Cons:
    * Not suitable for use inside for-comprehension


## How to use

Add the following to your `build.sbt` file:
`libraryDependencies += "com.github.mkulak" %% "ghosts" % "0.0.1"`


## List of Dependencies

`org.typelevel" %% "cats" % "0.8.1"`
