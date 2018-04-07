name := "sbt-frege-quickcheck"
organization := "xyz.denshi_no_yamaoku"
version := "0.1"

autoScalaLibrary := false
crossPaths := false

libraryDependencies += "org.scala-sbt" % "test-interface" % "1.0"

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }

description := "An implementation of sbt's test interface for Frege QuickCheck"
startYear := Some(2018)

scriptedLaunchOpts += s"-Dproject.version=${version.value}"
scriptedBufferLog := false
