
/** Eclipse */
resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"    

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.10"))

resolvers += Classpaths.typesafeResolver

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.5.0")

resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"
