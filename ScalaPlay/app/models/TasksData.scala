package models

import scala.collection.mutable.Map
import scala.collection.mutable.Buffer

class Task(val username: String, task: String) {
  var tasks: Map[String,Buffer[String]] = Map()
  
  /*
  def addTask(username: String, task: String) = {
    tasks += (username -> (task :: (tasks get task getOrElse Nil)))
  }
  */
}

object Task {
  def apply(username: String, password: String) = new User(username, password)
  
  
}