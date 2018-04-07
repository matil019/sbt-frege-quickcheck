resolvers += "denshi-no-yamaoku.xyz" at "http://maven.denshi-no-yamaoku.xyz/"
libraryDependencies += "xyz.denshi_no_yamaoku" % "sbt-frege-quickcheck" % sys.props("project.version") % Test
testFrameworks += new TestFramework("xyz.denshi_no_yamaoku.quickcheck.QuickCheckFramework")

lazy val checkTestDefinitions = taskKey[Unit]("Tests that the test is discovered properly")

checkTestDefinitions := {
  val definitions = (definedTests in Test).value
  locally {
    val expected = Seq("example.FooTest")
    val actual   = definitions.map(_.name)
    assert(expected == actual, s"expected $expected, actual $actual")
  }
}
