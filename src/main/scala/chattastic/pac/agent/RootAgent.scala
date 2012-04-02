package chattastic.pac.agent

import net.liftweb.http.js.JsCmds._

class RootAgent {
  def render_↓ = <div id="root"/>
    
  def updateNumberOfSubscribers_↓(newNumber: Int) =
    SetHtml("root",
            if (newNumber == 1) 
              <p>There is 1 subscriber</p>
            else
              <p>There are {newNumber} subscribers</p>)
}