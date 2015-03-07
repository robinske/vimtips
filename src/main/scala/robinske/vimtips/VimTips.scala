package robinske.vimtips

import scalaj.http._
import java.net.URL

object VimTips extends App {

  val RANDOM = "http://vim.wikia.com/wiki/Special:Random"
  lazy val randomResponse = get(RANDOM)

  def get(url: String): HttpResponse[String] = Http(url).asString

  def pathSlug(url: String): String = {
    val parsedUrl = new URL(url)
    parsedUrl.getPath.stripPrefix("/wiki/")
  }

  def isValidTip(pathSlug: String): Boolean = {
    val err = """VimTip(\d)+""".r
    err.findFirstIn(pathSlug).isEmpty
  }

  def slugToTitle(pathSlug: String): String =
    pathSlug.split("_").map(_.capitalize).mkString(" ")


  for {
    url <- randomResponse.location
  } yield {
    val path = pathSlug(url)
    if (isValidTip(path)) {
      println(slugToTitle(path))
      // send email
    } else {
      println("Invalid!!!")
      println(path)
      // try again w/o timeout
    }



    // TODO:
    // include link in email
    // div id=WikiaArticle
    // send email daily
  }
}
