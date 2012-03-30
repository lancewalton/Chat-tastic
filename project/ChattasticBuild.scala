import java.io.File
import sbt._
import Keys._
import IO._

object BuildSettings {

  import com.github.siasia.PluginKeys._
  
  System.setProperty("run.mode", "production")

  val cleanLib = TaskKey[Unit]("clean-lib")
  val reallyClean = TaskKey[Unit]("really-clean")
  val cleanOnlyTestOutput = TaskKey[Unit]("clean-only-test-output")

  val buildSettings: Seq[Setting[_]] = Defaults.defaultSettings ++ Seq[Setting[_]](
    organization := "Casual Miracles",
    version := "1.0.0",
    scalaVersion := "2.9.1",
    retrieveManaged := false,

    scalacOptions ++= Seq("-deprecation", "-optimise", "-explaintypes"),
    javaOptions ++= Seq("-Xmx1G","-Xss4m","-server"),
    testOptions in Test ++= Seq(Tests.Argument("junitxml", "html", "console")),
    parallelExecution in Test := true,

    cleanLib <<= (baseDirectory) map { (theBase) =>
        IO.delete(((theBase / "lib_managed") ** "*.*").get.filter(_.isFile).filterNot(_.getPath.contains(".svn")))
    }
  )
}

object Resolvers {
  val jettyRepo = "Jetty Repo" at "http://repo1.maven.org/maven2/"
}

object Dependencies {

  val lift = {
    val liftVersion = "2.4"
    Seq(
      "net.liftweb" %% "lift-webkit" % liftVersion
    )
  }

  val jetty = "org.eclipse.jetty" % "jetty-webapp" % "7.5.3.v20111011" % "container;compile"
}

object ChattasticBuild extends Build {

  import com.github.siasia.WebPlugin._
  import BuildSettings._
  import Dependencies._
  import Resolvers._

  lazy val asynchronousDistributor = Project("Chat-tastic", file("."), settings = buildSettings ++ webSettings ++ Seq(
    resolvers := Seq(jettyRepo, Classpaths.typesafeResolver),
    libraryDependencies ++= lift ++ Seq(jetty))
  )
}