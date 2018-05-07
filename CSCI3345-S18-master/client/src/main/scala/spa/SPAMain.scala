package spa

import org.scalajs.dom
import org.scalajs.dom._
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.annotation.JSExport
import org.querki.jquery._
import scala.scalajs.js.JSON
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSGlobal
import org.scalajs.dom.html
import org.scalajs.dom.{WebSocket, MessageEvent, Event, CloseEvent}

object SPAMain {
  val canvas = $("#Task10Canvas")(0).asInstanceOf[html.Canvas]
  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  var color = "black"
  var mousePressed = false
  val socket = new dom.WebSocket("ws://"+window.location.hostname+":"+window.location.port+"/socket")
  var randCircleX = (Math.random() * canvas.width)
  var randCircleY = (Math.random() * canvas.height)
  
  var pixWidth = 50
  var pixHeight = 50
  
  def main(): Unit = {
     socket.onopen = { (e: dom.Event) =>
       socket.send("circle")  
     }
     
     socket.onmessage = { (e: dom.MessageEvent) =>
       val square = JSON.parse(e.data.toString)
       ctx.fillStyle = square.color
       ctx.fillRect(square.x.asInstanceOf[Double], 
                    square.y.asInstanceOf[Double], 
                    square.width.asInstanceOf[Double], 
                    square.height.asInstanceOf[Double])
     }
     draw()
     $("#eraseTask10").click(() => erase())
     $("#red").click(() => color = "red")
     $("#green").click(() => color = "green")
     $("#blue").click(() => color = "blue")
  }
  
  def toggle(): Unit = {
    mousePressed = !mousePressed
  }
  
  def draw(): Unit = {
     dom.window.onmousemove = (evt:dom.MouseEvent) => {
       val rect = canvas.getBoundingClientRect()
       if (mousePressed) {
         val square = {
           js.Dynamic.literal(x = evt.clientX - rect.left, 
                              y = evt.clientY - rect.top, 
                              width = pixWidth, 
                              height = pixHeight,
                              color = color)
         }
         val circletoSend = JSON.stringify(square)
         socket.send(circletoSend)
       }
     }
     dom.window.onmousedown = (evt:dom.MouseEvent) => {
       toggle()
     }
     dom.window.onmouseup = (evt:dom.MouseEvent) => {
       toggle()
     }
  }
  
  def erase(): Unit = {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
  }
}