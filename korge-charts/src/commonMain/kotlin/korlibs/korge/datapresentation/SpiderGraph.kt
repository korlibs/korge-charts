package korlibs.korge.datapresentation

import korlibs.image.color.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import korlibs.math.geom.vector.*

/**
 * Example:
 * ```kotlin
 *   spiderGraph(100f, 30f) {
 *     updateData(listOf(15f, 16f, 11f, 15f, 16f))
 *   }
 * ```
 */
inline fun Container.spiderGraph(
  diameter: Float, maxValue: Float, callback: @ViewDslMarker SpiderGraph.() -> Unit = {}
) = SpiderGraph(diameter, maxValue).addTo(this, callback)

class SpiderGraph(var diameter: Float, var maxValue: Float, var webThickness: Float = 4f) : Container() {
  var backgroundColor = Colors.DARKGREY
  var foregroundColor = Colors.WHITE.withA(155)
  var intermediateNets = listOf(.3, .6, .9)
  private val mainContainer = container {}
  private val edgeContainers = mutableListOf<Container>()
  fun getEdgeContainers() = edgeContainers
  private val valueContainers = mutableListOf<Container>()
  fun getValueContainers() = valueContainers

  private fun Angle.getVector(distance: Float) = Point(this.sine, this.cosine) * distance
  private fun getVectorForIdx(idx: Int, dataRows: Int): Point {
    val treeIdx = idx % dataRows
    return ((360.degrees / dataRows) * treeIdx).getVector(1f)
  }

  fun updateData(datas: List<Float>) {
    mainContainer.removeChildren()
    edgeContainers.removeAll { true }
    valueContainers.removeAll { true }
    val normalizedValues = datas.map { it / maxValue }

    mainContainer.graphics {
      stroke(backgroundColor, info = StrokeInfo(thickness = webThickness)) {
        for (idx in datas.indices) {
          val vectorForIdx = getVectorForIdx(idx, datas.size)
          val vectorForNextIdx = getVectorForIdx(idx + 1, datas.size)
          val farOutPosition = vectorForIdx * diameter * 1.1
          edgeContainers.add(mainContainer.container { position(farOutPosition) })
          intermediateNets.forEach {
            val distance = diameter * it
            moveTo(vectorForIdx * distance)
            lineTo(vectorForNextIdx * distance)
            moveTo(0, 0)
            lineTo(farOutPosition)
          }
        }
      }
    }
    mainContainer.graphics {
      fillStroke(foregroundColor, foregroundColor) {
        moveTo(getVectorForIdx(datas.indices.last, datas.size) * datas.last())
        val points = datas.indices.map {
          val entryValue = normalizedValues[it]
          val vectorForIdx = getVectorForIdx(it, datas.size)
          val point = vectorForIdx * (diameter * entryValue)
          valueContainers.add(mainContainer.container { position(point) })
          point
        }
        polyline(points, true)
      }
    }
  }
}
