package robinske.vimtips

import scalaj.http._

object VimTips extends App {


  def get(url: String): HttpResponse[String] = Http(url).asString

  val random = "http://vim.wikia.com/wiki/Special:Random"
  val randomResponse = get(random)

  for {
    url <- randomResponse.headers.get("Location")
  } yield {
    val response = get(url)

    // TODO:
    // Filter out: "xxx does not exist"
    // parse response body
    // send email daily
  }
}
