package de.constantinuous.tessara.browser

/**
 * Created by RichardG on 08.06.2016.
 */
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.Background
import javafx.scene.layout.Border
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.Controller
import tornadofx.FX

class LoginController : Controller() {
    val loginScreen: LoginScreen by inject()
    val workbench: WorkBench by inject()

    val loginName = SimpleStringProperty()

    fun init() {
        val hbox = HBox()
        hbox.border = Border.EMPTY

    }

    fun showLoginScreen(message: String, shake: Boolean = false) {
        if (FX.primaryStage.scene.root != loginScreen.root) {
            FX.primaryStage.scene.root = loginScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = message

        Platform.runLater {
            loginScreen.username.requestFocus()
            if (shake) loginScreen.shakeStage()
        }
    }

    fun showWorkbench() {
        if (FX.primaryStage.scene.root != workbench.root) {
            FX.primaryStage.scene.root = workbench.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }
    }

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            username == "admin" && password == "secret"
        } ui { successfulLogin ->
            print(loginName)

            if (successfulLogin) {
                loginScreen.clear()


                if (remember) {
                    with (config) {
                        set(USERNAME to username)
                        set(PASSWORD to password)
                        save()
                    }
                }

                showWorkbench()
            } else {
                showLoginScreen("Login failed. Please try again.", true)
            }
        }
    }

    fun logout() {
        with (config) {
            remove(USERNAME)
            remove(PASSWORD)
            save()
        }

        showLoginScreen("Log in as another user")
    }

    companion object {
        val USERNAME = "username"
        val PASSWORD = "password"
    }

}