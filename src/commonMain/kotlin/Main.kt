import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.korge.datapresentation.*
import org.korge.samples.mymodule.*

suspend fun main() = Korge {
    sceneContainer().changeTo({ MainMyModuleScene() })
}

class MainMyModuleScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        val pie = pieChart(70f) {
            position(100, 100)
        }

        pie.setColors(
            listOf(
                Colors["#73ff5f"],
                Colors["#4058ff"],
                Colors["#1efff8"],
                Colors["#fff918"],
            )
        )
        pie.updateData(
            listOf(
                "example" to 10f,
                "another" to 30f,
                "very cool" to 5f,
                "exampleeee" to 15f,
            )
        )
        pie.getParts()[2].apply {
            onOver { colorMul = Colors.BLACK }
            onOut { colorMul = Colors.WHITE }
        }
    }
}