package models

class User(val username: String, val password: String) {
  
}

object User {
  def apply(username: String, password: String) = new User(username, password)
}