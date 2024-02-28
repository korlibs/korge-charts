package korlibs.korge.datapresentation

import korlibs.image.color.*
import korlibs.korge.input.*
import korlibs.korge.testing.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import org.junit.*

class SpiderGraphTest {
  @Test
  fun test() = korgeScreenshotTest(Size(280, 280)) {
    val spider = spiderGraph(100f, 30f) {
      position(150, 150)
    }

    spider.updateData(listOf(15f, 16f, 11f, 15f, 16f))
    assertScreenshot(posterize = 5)

    spider.backgroundColor = Colors["#1014c5"]
    spider.foregroundColor = Colors["#16c542"].withA(140)
    spider.updateData(listOf(10f, 13f, 29f, 25f, 11f, 26f, 23f))
    val strings = listOf("big", "brown", "fox", "jumped", "over", "the", "fence")
    spider.getEdgeContainers().forEachIndexed { idx, container ->
      container.apply {
        text(strings[idx])
      }
    }
    spider.getValueContainers().forEach {
      it.apply {
        circle(3) {
          anchor(.5, .5)
        }
      }
    }
    assertScreenshot(posterize = 5)
  }
}
