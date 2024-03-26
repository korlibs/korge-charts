package korlibs.korge.datapresentation

import korlibs.datastructure.*
import korlibs.image.color.*
import korlibs.image.text.*
import korlibs.korge.view.*
import korlibs.korge.view.align.*
import korlibs.logger.Console.warn
import korlibs.math.geom.*

/**
 * example:
 * ```kotlin
 *  barChart(Size(200, 100)) {
 *    updateData(
 *      listOf(
 *        "low" to 1f,
 *        "medium" to 2f,
 *        "high" to 3f,
 *      )
 *    )
 *  }
 * ```
 */
inline fun Container.barChart(chartSize: Size, callback: @ViewDslMarker BarChart.() -> Unit = {}) =
  BarChart(chartSize).addTo(this, callback)

class BarChart(var chartSize: Size) : Container() {
  var barSpacing = 1f
  private var colors = listOf(
    Colors["#ff3d43"],
    Colors["#73ff5f"],
    Colors["#4058ff"],
    Colors["#1efff8"],
    Colors["#fff918"],
  )

  fun setColors(newColors: List<RGBA>) {
    colors = newColors
  }

  fun updateData(datas: List<Pair<String, Float>>) {
    removeChildren()
    ensureColorsAreDefined(datas)

    val barWidth = (chartSize.width / datas.size) - barSpacing
    val maxValue = datas.map { it.second }.max()

    datas.forEachIndexed { index, pair ->
      val (name, barValue) = pair
      val barHeight = (barValue / maxValue) * chartSize.height
      container {
        position((index * (barWidth + barSpacing)), chartSize.height - barHeight)
        var rect = solidRect(Size(barWidth, barHeight), colors[index])
        text(name, barWidth / 3) {
          centerXOn(rect)
          alignTopToBottomOf(rect, 2)
        }
      }
    }
  }

  private fun ensureColorsAreDefined(datas: List<Pair<String, Float>>) {
    if (datas.size > colors.size) {
      warn("Defined colors set is smaller than input data. Please call setColors to set color for each input data.")
      val colorList = Colors.colorsByName.values.toList()
      colors = colors + (0..datas.size - colors.size).map { colorList.getCyclic(it * 15) }
    }
  }
}
