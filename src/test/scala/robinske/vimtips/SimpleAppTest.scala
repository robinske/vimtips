package robinske.vimtips

import org.scalatest._

class SimpleAppTest extends FlatSpec with Matchers {

  "The Answer" should "always be 42" in {
    VimTips.theAnswer should be (42)
  }

}
