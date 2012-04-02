package chattastic.comet

import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import chattastic.pac.agent.RootAgent

class RootPump extends CometActor {
  private val rootAgent = new RootAgent
  
  override def render = {
    initialiseContentAfterRender
    rootAgent.render_↓
  }

  override def lowPriority = {
    case NumberOfSubscribers(newNumber) ⇒
      partialUpdate(rootAgent.updateNumberOfSubscribers_↓(newNumber))
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