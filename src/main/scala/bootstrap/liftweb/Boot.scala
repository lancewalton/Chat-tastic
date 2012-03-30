package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._

class Boot extends Loggable {
  def boot {
    LiftRules.addToPackages("chattastic")

    val entries =
      Menu(Loc("Home", List("index"), "Home")) :: Nil

    LiftRules.setSiteMap(SiteMap(entries: _*))

    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))    

    logger.error("Lift Booted.")
  }
}
