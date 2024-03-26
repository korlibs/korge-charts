import korlibs.image.color.*
import korlibs.korge.*
import korlibs.korge.datapresentation.*
import korlibs.korge.scene.*
import korlibs.korge.ui.*
import korlibs.korge.view.*
import korlibs.math.geom.*

suspend fun main() = Korge {
  sceneContainer().changeTo({ MainMyModuleScene() })
}

class MainMyModuleScene : Scene() {
  override suspend fun SContainer.sceneMain() {
    uiScrollable(Size(800, 500)) {
      val basicBarChart = barChart(Size(200, 100)) {
        position(77, 77)
        updateData(
          listOf(
            "low" to 1f,
            "medium" to 2f,
            "high" to 3f,
          )
        )
      }

      val customizedBarChart = barChart(Size(200, 100)) {
        position(333, 77)
        barSpacing = 15f
        setColors(listOf(Colors["#23c51b"], Colors["#c51417"], Colors["#c5b606"]))
        updateData(
          listOf(
            "" to 1f,
            "" to 2f,
            "" to 3f,
          )
        )
      }

      spiderGraph(100f, 30f) {
        position(155, 444)
        updateData(listOf(15f, 16f, 11f, 15f, 16f))

        backgroundColor = Colors["#1014c5"]
        foregroundColor = Colors["#16c542"].withA(140)
        updateData(listOf(10f, 13f, 29f, 25f, 11f, 26f, 23f))
        val strings = listOf("Speed", "Agility", "Strength", "Luck", "Intelligence", "Wisdom", "Charm")
        getEdgeContainers().forEachIndexed { idx, container ->
          container.apply {
            text(strings[idx])
          }
        }
        getValueContainers().forEach {
          it.apply {
            circle(3) {
              anchor(.5, .5)
            }
          }
        }
      }

      lineChart(Size(200, 100)) {
        position(77, 833)
        updateData(
          listOf(
            Point(15, 55),
            Point(22, 33),
            Point(111, 222),
            Point(333, 312),
          )
        )
      }

      lineChart(Size(200, 100)) {
        position(333, 833)
        lineColor = Colors.RED
        axlesColor = Colors["#85847f"]
        hintsColor = Colors.WHITE
        updateData(
          listOf(
            Point(15, 55),
            Point(22, 33),
            Point(111, 222),
            Point(433, 312),
          ),
          xHints = listOf(100.0, 200.0, 300.0, 400.0),
          yHints = listOf(300.0, 600.0, 900.0),
          showHintLabels = true,
        )
      }

      pieChart(70f) {
        position(155, 1333)
        updateData(
          listOf(
            "Hello" to 10f,
            "World" to 30f,
            "very cool" to 5f,
            "example" to 15f,
          )
        )
      }

      pieChart(70f) {
        position(455, 1333)
        setColors(
          listOf(
            Colors["#73ff5f"],
            Colors["#4058ff"],
            Colors["#1efff8"],
            Colors["#ff0b00"],
          )
        )

        updateData(
          listOf(
            "example" to 10f,
            "another" to 30f,
            "very cool" to 5f,
            "exampleeee" to 15f,
          )
        )
      }
    }
  }
}