scalacOptions := Seq(
  "-deprecation"
)

// For MimeLoader plugin. Dogfooding and hoping it doesn't clash with
// our other sbt plugins.
libraryDependencies ++= List(
  "com.eed3si9n" %% "treehugger" % "0.4.4",
  "io.circe" %% "circe-generic" % "0.14.3",
  "org.http4s" %% "http4s-ember-client" % "0.23.18",
  "org.http4s" %% "http4s-circe" % "0.23.18",
)

// native packager and scoverage are conflicting...
libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
