java -Djline.terminal=jline.UnixTerminal -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m -Xmx1024M -Xss4M -jar `dirname $0`/tools/sbt-launch-0.11.2.jar "$@"
