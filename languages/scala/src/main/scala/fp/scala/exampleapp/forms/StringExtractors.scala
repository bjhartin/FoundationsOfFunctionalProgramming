package fp.scala.exampleapp.forms

object NonBlankString {
  def unapply(s:String) = {s != null && s.trim != ""}
}

object BlankString {
  def unapply(s:String) = {s == null || s.trim == ""}
}
