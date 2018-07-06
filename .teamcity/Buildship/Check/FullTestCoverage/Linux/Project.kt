package Buildship.Check.FullTestCoverage.Linux

import Buildship.Check.FullTestCoverage.Linux.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("Full_Test_Coverage_Linux")
    name = "Full Test Coverage - Linux"

    buildType(Eclipse42)
    buildType(Eclipse44)
    buildType(Eclipse47)
    buildType(Eclipse48)
    buildType(Eclipse45)
    buildType(Eclipse46)
    buildType(Eclipse43)
    buildType(Eclipse47Java9)
})
