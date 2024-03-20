package korlibs.korge.datapresentation

import korlibs.image.color.*
import korlibs.korge.input.*
import korlibs.korge.testing.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import org.junit.*

class BarChartTest {
  @Test
  fun test() = korgeScreenshotTest(Size(220, 160)) {
    val barChart = barChart(Size(200, 100)) {
      updateData(
        listOf(
          "low" to 64f,
          "medium" to 111f,
          "high" to 132f,
        )
      )
    }
    assertScreenshot(posterize = 5, psnr = 1.0)

    barChart.apply{
      barSpacing = 15f
      chartSize = Size(230, 120)
      setColors(listOf(Colors.BROWN, Colors.YELLOW, Colors.LAWNGREEN))
      updateData(
        listOf(
          "high" to 132f,
          "low" to 64f,
          "medium" to 111f,
        )
      )
    }
    assertScreenshot(posterize = 5, psnr = 1.0)
  }
}
