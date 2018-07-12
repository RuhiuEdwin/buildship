package Buildship.Check.Checkpoints.buildTypes

import Buildship.GitHubVcsRoot
import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.CheckoutMode
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.finishBuildTrigger

object FullTestCoverage : BuildType({
    id("Checkpoint_Full_Test_Coverage")
    name = "Full Test Coverage"
    description = "Runs all integration tests"

    vcs {
        root(GitHubVcsRoot)
        checkoutMode = CheckoutMode.ON_AGENT
    }

    triggers {
        finishBuildTrigger {
            buildTypeExtId = "${BasicTestCoverage.id}"
            successfulOnly = true
            branchFilter = "+:*"
        }
    }

    dependencies {
        snapshot(BasicTestCoverage, CheckpointUtils.DefaultFailureCondition)

        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse42, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse43, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse44, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse45, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse46, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse47, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse47Java9, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Linux.buildTypes.Eclipse48, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Windows.buildTypes.Eclipse44, CheckpointUtils.DefaultFailureCondition)
        snapshot(Buildship.Check.FullTestCoverage.Windows.buildTypes.Eclipse47, CheckpointUtils.DefaultFailureCondition)
    }
})