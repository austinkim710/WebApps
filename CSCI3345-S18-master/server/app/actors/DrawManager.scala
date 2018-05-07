package actors

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

class DrawManager extends Actor {
  import DrawManager._
  
  private var players = List[ActorRef]()
  
  def receive = {
    case AddPlayer(circle) =>
      players ::= circle
    case ShowPlayersConnected(playersConn) =>
      players.foreach(_! DrawActor.NewCircle(playersConn))
  } 
}

object DrawManager {
  def props = Props[DrawManager]
  
  case class AddPlayer(circle: ActorRef)
  case class ShowPlayersConnected(playersConn: String)
}
