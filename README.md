# korge-charts

Charts
This provides support to display basic charts. The goal is to keep the code simple and provide some customization options.
The functions available are:
- lineChart
- barChart
- pieChart
- spiderGraph

---
```kotlin
lineChart(Size(200, 100)) {
  position(50, 50)
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
```
![Zrzut ekranu 2024-03-25 171128](https://github.com/korlibs/korge-charts/assets/33195725/816eda3a-0824-4716-9ad8-993950b1e7db)

---

Code examples:
```kotlin
barChart(Size(200, 100)) {
  position(50, 50)
  updateData(
    listOf(
      "low" to 1f,
      "medium" to 2f,
      "high" to 3f,
    )
  )
}
```   
![Zrzut ekranu 2024-03-25 170553](https://github.com/korlibs/korge-charts/assets/33195725/4ad8d73d-48c3-418b-8c08-1f63018a411e)

---

```kotlin
pieChart(70f) {
  position(50, 50)
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
```   
![Zrzut ekranu 2024-03-25 171147](https://github.com/korlibs/korge-charts/assets/33195725/b2d3e644-d1dd-4929-a62e-7956b14448e9)

---


```kotlin
spiderGraph(100f, 30f) {
  position(50, 50)
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
```   
![Zrzut ekranu 2024-03-25 171105](https://github.com/korlibs/korge-charts/assets/33195725/278a09af-908f-4a4b-821f-322eba510aa4)

