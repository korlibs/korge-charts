package korlibs.korge.datapresentation

import korlibs.image.color.*
import korlibs.korge.input.*
import korlibs.korge.testing.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import org.junit.*

class LineChartTest {
  @Test
  fun test() = korgeScreenshotTest(Size(280, 160)) {
    val lineChart = lineChart(Size(200, 100)) {
      position(45, 15)
    }
    lineChart.updateData(
      listOf(
        Point(0, 0),
        Point(1, 30),
        Point(2, 20),
        Point(3, 10),
        Point(4, 15),
        Point(5, 15),
      )
    )
    assertScreenshot(posterize = 5, psnr = 1.0)

    lineChart.lineColor = Colors["#fffa0e"]
    lineChart.axlesColor = Colors["#19a996"]
    lineChart.hintsColor = Colors["#8fffdf"].withA(100)
    lineChart.updateData(
      listOf(
        Point(15, 55),
        Point(22, 33),
        Point(35.37, 788),
        Point(77, 333),
        Point(111, 222),
        Point(333, 312),
      ),
      xHints = listOf(35.37, 100.0, 200.0, 300.0, 400.0),
      yHints = listOf(300.0, 600.0, 900.0),
      showHintLabels = true,
    )
    assertScreenshot(posterize = 5, psnr = 1.0)

  }
}
