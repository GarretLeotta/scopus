name := "Scopus"
organization := "za.co.monadic"
version := "0.3.15"
scalaVersion := "2.12.6"
crossScalaVersions := Seq("2.11.8", "2.12.6")
fork in Test := true

organizationName := "David Weber"
startYear := Some(2014)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/davidmweber/scopus"))

libraryDependencies ++= List(
  "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)

publishMavenStyle := true

// sbt publishSigned
// sbt sonatypeRelease
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ =>
  false
}

pomExtra :=
  <scm>
      <url>git@github.com:davidmweber/scopus.git</url>
      <connection>scm:git:git@github.com:davidmweber/scopus.git</connection>
    </scm>
    <developers>
      <developer>
        <id>davidmweber</id>
        <name>David Weber</name>
        <url>https://github.com/davidmweber</url>
      </developer>
    </developers>
