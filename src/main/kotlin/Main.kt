import kotlin.math.abs

data class Point(val x: Double, val y: Double)

class Triangle(val p1: Point, val p2: Point, val p3: Point) {

    private fun area(a: Point, b: Point, c: Point): Double {
        return abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2.0)
    }

    fun contains(point: Point): Boolean {
        val totalArea = area(p1, p2, p3)
        val area1 = area(point, p2, p3)
        val area2 = area(p1, point, p3)
        val area3 = area(p1, p2, point)

        return abs(totalArea - (area1 + area2 + area3)) < 1e-6
    }
}

fun main() {
    try {
        println("Введите координаты вершин треугольника (x y):")
        val trianglePoints = mutableListOf<Point>()
        for (i in 1..3) {
            println("Вершина $i:")
            val (x, y) = readLine()!!.split(" ").map { it.toDoubleOrNull() ?: throw IllegalArgumentException("Некорректный ввод") }
            trianglePoints.add(Point(x, y))
        }

        println("Введите координаты точки (x y):")
        val (px, py) = readLine()!!.split(" ").map { it.toDoubleOrNull() ?: throw IllegalArgumentException("Некорректный ввод") }
        val point = Point(px, py)

        val triangle = Triangle(trianglePoints[0], trianglePoints[1], trianglePoints[2])

        if (triangle.contains(point)) {
            println("Точка находится внутри треугольника.")
        } else {
            println("Точка находится вне треугольника.")
        }
    } catch (e: Exception) {
        println("Ошибка: ${e.message}. Убедитесь, что вводите числа через пробел.")
    }
}
