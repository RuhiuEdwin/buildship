package Buildship

import jetbrains.buildServer.configs.kotlin.v2018_1.CheckoutMode
import jetbrains.buildServer.configs.kotlin.v2018_1.Template
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.retryBuild

object EclipseBuildTemplate : Template({
    name = "Tooling-Eclipse-Build"

    artifactRules = """
        org.eclipse.buildship.site/build/repository/** => update-site
        org.eclipse.buildship.core.test/build/eclipseTest/workspace/.metadata/.log => test/org.eclipse.buildship.core.test
        org.eclipse.buildship.ui.test/build/eclipseTest/workspace/.metadata/.log => test/org.eclipse.buildship.ui.test
    """.trimIndent()

    params {
        param("eclipse.release.type", "snapshot")
        param("build.invoker", "ci")
        param("eclipse.test.java.home", "%env.JAVA_HOME%")
        param("gradle.tasks", "clean build")
        param("env.JAVA_HOME", "%linux.java7.oracle.64bit%")
    }

    vcs {
        root(GitHubVcsRoot)

        checkoutMode = CheckoutMode.ON_AGENT
    }

    triggers {
        retryBuild {
            delaySeconds = 0
            attempts = 2
        }
    }

    steps {
        gradle {
            name = "RUNNER_21"
            id = "RUNNER_21"
            tasks = "%gradle.tasks%"
            buildFile = ""
            gradleParams = "-Peclipse.version=%eclipse.version% -Pcompiler.location='%compiler.location%' -Pbuild.invoker=%build.invoker% -Prelease.type=%eclipse.release.type% -Peclipse.test.java.home='%eclipse.test.java.home%' --info --stacktrace -Declipse.p2.mirror=false -Dscan"
            jvmArgs = "-XX:MaxPermSize=256m"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
    }

    failureConditions {
        errorMessage = true
    }

    cleanup {
        all(days = 5)
        history(days = 5)
        artifacts(days = 5)
        preventDependencyCleanup = false
    }
})