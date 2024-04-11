package com.example.oros

import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.random.Random

class MyApp : Application() {
    override fun start(primaryStage: Stage) {
        val textField = TextField("")
        textField.isEditable = false

        val buttonM = Button("M")
        val buttonK = Button("K")
        val buttonC = Button("C")
        val buttonClear = Button("Удалить текст")

        buttonM.setOnAction {
            val previousText = textField.text
            val randomTemp = Random.nextDouble(36.7, 36.9)
            textField.text = "%.1f".format(randomTemp)
            // Восстанавливаем " Сметана", если было
            if (previousText.contains("Сметана")) {
                textField.text += " Сметана"
            }
            // Восстанавливаем числа, если были
            val regex = Regex("\\s\\d+/\\d+") // регулярное выражение для поиска чисел
            val matchResult = regex.find(previousText)
            if (matchResult != null) {
                textField.text += matchResult.value // добавляем найденные числа
            }
        }

        buttonK.setOnAction {
            if (!textField.text.contains("/")) { // Проверяем наличие "/" вместо "Кефир"
                val num1 = Random.nextInt(110, 130) // генерируем первое число
                val num2 = Random.nextInt(75, 88)  // генерируем второе число
                textField.text += " $num1/$num2"  // добавляем числа с "/" и пробелом
            }
        }

        buttonC.setOnAction {
            if (!textField.text.contains("Сметана")) {
                textField.text += " Сметана"
            }
        }

        buttonClear.setOnAction {
            textField.text = ""
        }

        val buttonBox = HBox(10.0).apply {
            alignment = Pos.CENTER
            children.addAll(buttonM, buttonK, buttonC, buttonClear)
        }

        val root = VBox(10.0).apply {
            alignment = Pos.CENTER
            children.addAll(buttonBox, textField)
        }

        val scene = Scene(root, 300.0, 200.0)
        primaryStage.title = "My First JavaFX App"
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(MyApp::class.java)
}