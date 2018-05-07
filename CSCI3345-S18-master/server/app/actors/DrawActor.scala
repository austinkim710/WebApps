package actors

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

class DrawActor(out: ActorRef, manager: ActorRef) extends Actor {
  
  out ! "We are connected!"
  manager ! DrawManager.AddPlayer(self)
  
  import DrawActor._
  
  def receive = {
    case players: String =>
      manager ! DrawManager.ShowPlayersConnected(players)
    case NewCircle(circle) => 
      out ! circle
    //case m => 
     // println("Got unknown player: "+m)
  }
}
  object DrawActor {
  def props(out: ActorRef, manager: ActorRef) = Props(new DrawActor(out, manager))
  
  case class NewCircle(circle: String)
  }


