package controllers

import javax.inject._
import java.util.concurrent.atomic.AtomicInteger
import play.api.mvc._
import play.api.libs.json.Writes
import play.api.libs.json.Json
import play.api.libs.json.Reads
import play.api.libs.json.JsPath
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import java.util.concurrent.atomic.AtomicReference
import akka.actor.ActorSystem
import akka.stream.Materializer
import javax.inject.Inject
import play.api.libs.streams.ActorFlow
import play.api.mvc.AbstractController
import play.api.mvc.ControllerComponents
import play.api.mvc.WebSocket

import actors.DrawActor
import actors.DrawManager

class Task10Controller @Inject() (cc:ControllerComponents) (implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {
  val DrawingManager = system.actorOf(DrawManager.props)
  
  def index = Action { implicit request =>
    Ok(views.html.task10())
  }
  
  def socket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      DrawActor.props(out, DrawingManager)
    }
  }
}