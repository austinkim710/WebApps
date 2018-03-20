package controllers

import javax.inject.Inject
import javax.inject.Singleton
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.number
import play.api.mvc.MessagesAbstractController
import play.api.mvc.MessagesControllerComponents
import play.api.data._
import play.api.data.Forms._
//import views.html.taskLogin
import scala.collection.mutable.Map
import scala.collection.mutable.Buffer


case class UserData(username: String, password: String)
case class UsersList(users: List[UserData])

@Singleton
class TaskController @Inject() (cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

  var users: Map[String,String] = Map()
  var tasks: Map[String,Buffer[String]] = Map()

  val userForm = Form(
  mapping(
    "username" -> text,
    "password" -> nonEmptyText
    )(UserData.apply)(UserData.unapply)
  )
  


  def login() = Action {implicit request => 
    request.body.asFormUrlEncoded match {
      case Some(formData) =>
        val username = formData("username").head.toString()
        val password = formData("password").head.toString()
        if (users.exists(_ == username -> password))  {
         // Ok(views.html.taskTable(tasks(username),username)).withSession("connection" -> username)
          Redirect(routes.TaskController.createTaskPage).withSession("connection" -> username)
        } else {
          Ok("Invalid username/password")
          //Redirect(routes.TaskController.welcome)
        }
      case None =>
        Ok("One of the fields is blank!")
        //Redirect(routes.TaskController.welcome)
    }
  }
  
  def createUserPage = Action {implicit request =>   
    Ok(views.html.createUser(users, userForm))
  }
  
  def createUser() = Action {implicit request =>
    request.body.asFormUrlEncoded match {
      case Some(formData) =>
        val newUser = formData("username").head.toString
        val newPass = formData("password").head.toString
        if (newUser == "" || newPass == "") {
          Ok("One of the fields is blank!")
          Redirect(routes.TaskController.createUserPage)
        } else if (users.contains("newUser")) {
          Ok("User already exists")
          Redirect(routes.TaskController.createUserPage)
        } else {
          users += newUser -> newPass
          tasks += newUser -> Buffer[String]()
          //Ok(views.html.taskLogin(users,userForm))
          Redirect(routes.TaskController.createTaskPage).withSession("connection" -> newUser)
        }
      case None =>
        Ok("One of the fields is blank!")
        Redirect(routes.TaskController.createUserPage)
    }
  
  }
  
  
  def createTaskPage = Action { implicit request =>
    request.session.get("connection").map { user =>
      Ok(views.html.taskTable(tasks(user), user))
    }.getOrElse(Redirect(routes.TaskController.welcome))
    
  }
  
  
  def createTask = Action {implicit request =>
    request.body.asFormUrlEncoded match {
      case Some(formData) =>
        val newTask = formData("newTask").head.toString()
        request.session.get("connection").map { username =>
          	tasks(username) += newTask
          	}
          	Redirect(routes.TaskController.createTaskPage)       
       case None => 
            Redirect(routes.TaskController.createTaskPage)
    
    }
  }
  
  def deleteTask = Action {implicit request =>
    request.body.asFormUrlEncoded match {
      case Some(formData) =>
        val markedTask = formData("markedTask").head.toString()
        request.session.get("connection").map { username =>
          	tasks(username) -= markedTask
          	}
          	Redirect(routes.TaskController.createTaskPage)       
       case None => 
            Redirect(routes.TaskController.createTaskPage)
    
    }
  }
  
  def logout = Action {implicit request =>
    Ok(views.html.taskLogin(users,userForm)).withNewSession
  }
  
  
  
  def welcome = Action { implicit request =>
    println(request.session)
    Ok(views.html.taskLogin(users,userForm))
  }//2. we need to go to routes from here


  



}
