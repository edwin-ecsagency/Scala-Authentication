package models

import scala.slick.driver.PostgresDriver.simple._
/**
 * Created by developer3 on 10/9/2014.
 */
sealed trait Permission
case object Administrator extends Permission
case object NormalUser extends Permission

object Permission {
  implicit val PermissionTimeMapper = MappedColumnType.base[Permission, String](
    d => Permission.stringValueOf(d),
    t => Permission.valueOf(t))

  def valueOf(value: String): Permission = value match {
    case "Administrator" => Administrator
    case "NormalUser" => NormalUser
    case _ => throw new IllegalArgumentException()
  }

  def stringValueOf(value: Permission): String = value match {
    case Administrator => "Administrator"
    case NormalUser => "NormalUser"
    case _ => throw new IllegalArgumentException()
  }
}
