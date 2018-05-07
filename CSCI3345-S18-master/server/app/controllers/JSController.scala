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

/**
 * This controller creates an `Action` that demonstrates how to write
 * simple asynchronous code in a controller. It uses a timer to
 * asynchronously delay sending a response for 1 second.
 *
 * @param cc standard controller components
 * @param actorSystem We need the `ActorSystem`'s `Scheduler` to
 * run code after a delay.
 * @param exec We need an `ExecutionContext` to execute our
 * asynchronous code.  When rendering content, you should use Play's
 * default execution context, which is dependency injected.  If you are
 * using blocking operations, such as database or network access, then you should
 * use a different custom execution context that has a thread pool configured for
 * a blocking API.
 */
@Singleton
class JSController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val cnt = new AtomicInteger(0)
  private val shapes = new AtomicReference(List[MyShape](Rectangle(1, 2, 3, 4), Circle(4, 5, 6)))
  private val player = new AtomicReference(List[MyPlayer](Player(14, 85, 400, false, false)))
  
  
  sealed trait MyShape
  case class Circle(x: Int, y: Int, r: Int) extends MyShape
  case class Rectangle(x: Int, y: Int, width: Int, height: Int) extends MyShape
  
  sealed trait MyPlayer
  case class Player(paddleHeight: Int, paddleWidth: Int, paddleX: Int, rightPressed: Boolean, leftPressed: Boolean) extends MyPlayer
  
  def index = Action { implicit request =>
    Ok(views.html.scalaJSCanvas())
  }
  


}
