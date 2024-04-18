//> using scala 3.3.3
//> using platform jvm

// dependencies
//> using test.dep org.scalatest::scalatest::3.2.18

//> using options -deprecation -unchecked -feature

// these are flags used by Scala native: if you aren't using scala-native, then they do nothing
// lto-thin has decent linking times, and release-fast does not too much optimisation.
//> using nativeLto thin
//> using nativeGc commix
//> using nativeMode release-fast
