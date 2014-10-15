package models

import scala.slick.driver.PostgresDriver.simple._
/**
 * Created by developer3 on 10/9/2014.
 */
case class Account(id:Int, email:String, password:String, name:String, permission:Permission)

class Accounts(tag:Tag) extends Table[Account](tag, "mitabla"){
  def id = column[Int]("ID", O.PrimaryKey)
  def email = column[String]("EMAIL")
  def password = column[String]("PASSWORD")
  def name = column[String]("NAME")
  def permission = column[Permission]("PERMISSION")

  def * = (id, email, password, name, permission) <> (Account.tupled, Account.unapply)
}

object QueryAccounts{

  val database = Database.forURL("jdbc:postgresql://localhost:5432/TestDB", user = "postgres", password = "password", driver = "org.postgresql.Driver")
  val account = TableQuery[Accounts]

  def authenticate(email: String, password: String): Option[Account] = {
    findByEmail(email).filter { a => password.equals(a.password) }
  }

  def findByEmail(email: String): Option[Account] = {
    database withSession { implicit session =>
      val q1 = for (u <- account if u.email === email) yield u
      q1.list.headOption
    }
  }

  def findById(id: Int): Option[Account] = {
    database withSession { implicit session =>
      val q1 = for (u <- account if u.id === id) yield u
      q1.list.headOption
    }
  }

  def findAll: Seq[Account] = {
    database withSession { implicit session =>
      val q1 = for (u <- account) yield u
      q1.list.asInstanceOf[Seq[Account]]
    }
  }
}
