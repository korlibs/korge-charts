package korlibs.korge.datapresentation

import korlibs.image.color.*
import korlibs.image.text.*
import korlibs.io.util.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import korlibs.math.geom.vector.*

inline fun Container.lineChart(
  chartSize: Size, callback: @ViewDslMarker LineChart.() -> Unit = {}
) = LineChart(chartSize).addTo(this, callback)

class LineChart(var chartSize: Size) : Container() {
  private val mainContainer = container {}
  var lineThickness = 4f
  var lineColor = Colors.WHITE
  var axlesColor = Colors.DIMGREY
  var hintsColor = Colors.WHITE

  private class Scale(fromA: Float, fromB: Float, toA: Float, toB: Float) {
    private val scaleFactor = (toB - toA) / (fromB - fromA)
    private val offset = toA - fromA * scaleFactor

    fun get(x: Float): Float {
      return x * scaleFactor + offset
    }
  }

  fun updateData(
    dataPoints: List<Point>,
    xHints: List<Double> = emptyList(),
    yHints: List<Double> = emptyList(),
    showHintLabels: Boolean = false
  ) {
    mainContainer.removeChildren()
    if (dataPoints.isEmpty()) return

    val xVals = dataPoints.map { it.x } + xHints
    val xMin = xVals.min().toFloat()
    val xMax = xVals.max().toFloat()
    val yVals = dataPoints.map { it.y } + yHints
    val yMin = yVals.min().toFloat()
    val yMax = yVals.max().toFloat()
    val xScale = Scale(xMin, xMax, 0f, chartSize.width.toFloat())
    val yScale = Scale(yMin, yMax, chartSize.height.toFloat(), 0f)

    val scaledPoints = dataPoints.map { Point(xScale.get(it.x.toFloat()), yScale.get(it.y.toFloat())) }

    mainContainer.graphics {
      stroke(axlesColor, info = StrokeInfo(thickness = lineThickness * 2)) {
        polyline(Point(0, 0), Point(0, chartSize.height), Point(chartSize.width, chartSize.height))
      }
      stroke(lineColor, info = StrokeInfo(thickness = lineThickness)) {
        polyline(scaledPoints)
      }
    }
    xHints.map { it to Point(xScale.get(it.toFloat()), chartSize.height - 7) }.forEach { (origVal, scaledVal) ->
      solidRect(lineThickness, lineThickness * 4, color = hintsColor) {
        position(scaledVal)
      }
      if (showHintLabels) {
        text(origVal.toStringDecimal(2, true), lineThickness * 4.0, alignment = TextAlignment.CENTER) {
          position(scaledVal + Point(3, 17))
        }
      }
    }
    yHints.map { it to Point(-7, yScale.get(it.toFloat())) }.forEach { (origVal, scaledVal) ->
      solidRect(lineThickness * 4, lineThickness, color = hintsColor) {
        position(scaledVal)
      }
      if (showHintLabels) {
        text(origVal.toStringDecimal(2, true), lineThickness * 4.0, alignment = TextAlignment.RIGHT) {
          position(scaledVal + Point(-2, -6))
        }
      }
    }
  }
}
