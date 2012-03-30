package chattastic.comet

import net.liftweb.actor.LiftActor
import net.liftweb.http.CometActor

case class SubscribePump(pump: CometActor)
case class UnsubscribePump(pump: CometActor)
case class NumberOfSubscribers(newNumber: Int)
case class PleaseSendNumberOfSubscribers(actor: CometActor)

object Distributor extends LiftActor {
  private var pumps: List[CometActor] = Nil

  override def messageHandler = {
    case SubscribePump(pump) ⇒ { pumps ::= pump; sendNumberOfSubscribers }
    case UnsubscribePump(pump) ⇒ { pumps = pumps filterNot (_ == pump); sendNumberOfSubscribers }
    case PleaseSendNumberOfSubscribers(actor) ⇒ actor ! NumberOfSubscribers(pumps.size)
    case m ⇒ pumps.foreach(_ ! m)
  }

  private def sendNumberOfSubscribers {
    this ! NumberOfSubscribers(pumps.size)
  }
}
