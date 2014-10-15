package controllers

import jp.t2v.lab.play2.auth._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import views.html

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Application extends Controller with LoginLogout with AuthConfigImpl {

  /** Your application's login form.  Alter it to fit your application */
  val loginForm = Form {
    mapping("email" -> email, "password" -> text)(QueryAccounts.authenticate)(_.map(u => (u.email, u.password)))
      .verifying("Invalid email or password", result => result.isDefined)
  }


  def index = Action {
    implicit request =>
    Ok(html.index())
  }

  /** Alter the login page action to suit your application. */
  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  /**
   * Return the `gotoLogoutSucceeded` method's result in the logout action.
   *
   * Since the `gotoLogoutSucceeded` returns `Future[Result]`,
   * you can add a procedure like the following.
   *
   *   gotoLogoutSucceeded.map(_.flashing(
   *     "success" -> "You've been logged out"
   *   ))
   */
  def logout = Action.async { implicit request =>
    // do something...
    gotoLogoutSucceeded
  }

  /**
   * Return the `gotoLoginSucceeded` method's result in the login action.
   *
   * Since the `gotoLoginSucceeded` returns `Future[Result]`,
   * you can add a procedure like the `gotoLogoutSucceeded`.
   */
  def authenticate = Action.async { implicit request =>
    loginForm.bindFromRequest.fold(
      // binding failure, you retrieve the form containing errors
      formWithErrors => Future.successful(BadRequest(html.login(formWithErrors))),
      // binding success, you get the actual value.
      user => gotoLoginSucceeded(user.get.id)
    )
  }
}