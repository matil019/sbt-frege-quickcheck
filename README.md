# sbt Test Interface for Frege QuickCheck

This sbt library enables you to run your tests written for Frege's
QuickCheck with `sbt test` command.

sbt-frege-quickcheck is known to work with:

- sbt-1.1.2
- Frege-3.24.100.1-jdk8
- sbt-frege-3.0.0

Great thanks to sbt, Frege and sbt-frege:

- sbt: https://github.com/sbt/sbt
- Frege: https://github.com/Frege/frege
- sbt-frege: https://github.com/earldouglas/sbt-frege

## How to use

This library is published to the author's self-hosted repository.
Please put the following into your `build.sbt`:

```scala
resolvers += "denshi-no-yamaoku.xyz" at "http://maven.denshi-no-yamaoku.xyz/"
libraryDependencies += "xyz.denshi_no_yamaoku" % "sbt-frege-quickcheck" % "0.1" % Test
testFrameworks += new TestFramework("xyz.denshi_no_yamaoku.quickcheck.QuickCheckFramework")
```

You need to give sbt the capability to compile Frege. If not done yet,
please put the following into your `project/plugins.sbt`:

```scala
addSbtPlugin("com.earldouglas" % "sbt-frege" % "3.0.0")
```

See [sbt-frege](https://github.com/earldouglas/sbt-frege) for details.

Put your tests into `src/test/frege`. Test modules should look like:

```fr.hs
-- src/test/frege/example/FooTest.fr
module example.FooTest where

import frege.test.QuickCheck
import example.Foo (succs)

data Properties = pure native xyz.denshi_no_yamaoku.quickcheck.Properties

native module interface Properties where {}

intSuccs = property (\(xs::[Int]) -> succs xs == map succ xs)

knownToFail = expectFailure $ property (\(xs::[Int]) -> succs xs /= map succ xs)
unexpectedFail = property (\(xs::[Int]) -> succs xs == map pred xs)
unexpectedThrow = property (\(xs::[Int]) -> error "sorry!" :: Bool)
```

In this example the tested module is:

```fr.hs
-- src/main/frege/example/Foo.fr
module example.Foo where

succs :: (Enum a) => [a] -> [a]
succs = map succ
```

The important part is `native module interface Properties where {}`.
This declares that the module `example.FooTest` implements the marker
interface `xyz.denshi_no_yamaoku.quickcheck.Properties`. The sbt test
framework picks up the modules (classes, if seen from Java) that
implements the interface as test suites.

See [Frege #20](https://github.com/Frege/frege/issues/20) for the
details of this syntax.

See [Frege's own QuickCheck tests](https://github.com/Frege/frege/tree/master/tests/qc)
for example uses of QuickCheck.

## Parallelism and Output

If your tests are run in parallel (the default), the output of
QuickCheck may get intermixed.
If you wish to disable parallelism for tests, put the following into
your `build.sbt`:

```scala
Global / concurrentRestrictions += Tags.limit(Tags.Test, 1)
```

See the official documentation for the details of parallelism in sbt:
https://www.scala-sbt.org/1.x/docs/Parallel-Execution.html
