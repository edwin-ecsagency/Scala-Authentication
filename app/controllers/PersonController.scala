package controllers

import jp.t2v.lab.play2.auth._
import models.{NormalUser, Administrator}
import play.api.mvc._
import views.html

object PersonController extends Controller with AuthElement with AuthConfigImpl {

  // The `StackAction` method
  //    takes `(AuthorityKey, Authority)` as the first argument and
  //    a function signature `RequestWithAttributes[AnyContent] => Result` as the second argument and
  //    returns an `Action`

  // thw `loggedIn` method
  //     returns current logged in user

  def main = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    val user = loggedIn
    val title = "message main"
    Ok(html.Person.main())
  }

  def read = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    val user = loggedIn
    val title = "read messages"
    Ok(html.Person.read())
  }

  // Only Administrator can execute this action.
  def write = StackAction(AuthorityKey -> Administrator) { implicit request =>
    val user = loggedIn
    val title = "write message"
    Ok(html.Person.write())
  }
}