package chattastic.comet

import net.liftweb.http._
import net.liftweb.http.js.JsCmds._

class RootPump extends CometActor {
  override def render = {
    initialiseContentAfterRender
    <div id="root"/>
  }

  override def lowPriority = {
    case NumberOfSubscribers(newNumber) ⇒
      partialUpdate(
          SetHtml("root",
                  if (newNumber == 1) 
                    <p>There is 1 subscriber</p>
                  else
                    <p>There are {newNumber} subscribers</p>))
  }

  override def localSetup {
    super.localSetup()
    Distributor ! SubscribePump(this)
  }

  override def localShutdown {
    Distributor ! UnsubscribePump(this)
    super.localShutdown()
  }
  
  private def initialiseContentAfterRender {
    Distributor ! PleaseSendNumberOfSubscribers(this)
  }
}