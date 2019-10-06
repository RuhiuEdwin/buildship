/*
 * Copyright (c) 2017 the original author or authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.buildship.core.internal.configuration;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.ILaunchConfiguration;

import org.eclipse.buildship.core.GradleDistribution;

/**
 * Defines how to access a hierarchy of preferences for the Gradle projects in the workspace.
 *
 * @author Donat Csikos
 */
public interface ConfigurationManager {

    WorkspaceConfiguration loadWorkspaceConfiguration();

    void saveWorkspaceConfiguration(WorkspaceConfiguration configuration);

    BuildConfiguration createBuildConfiguration(File rootProjectDirectory, boolean overrideWorkspaceSettings,
                                                GradleDistribution gradleDistribution, File gradleUserHome,
                                                File javaHome, boolean buildScansEnabled,
                                                boolean offlineMode, boolean autoSync,
                                                List<String> arguments, List<String> jvmArguments,
                                                boolean showConsoleView, boolean showExecutionsView);

    BuildConfiguration loadBuildConfiguration(File rootProject);

    void saveBuildConfiguration(BuildConfiguration configuration);

    ProjectConfiguration createProjectConfiguration(BuildConfiguration configuration, File projectDir);

    ProjectConfiguration tryLoadProjectConfiguration(IProject project);

    ProjectConfiguration loadProjectConfiguration(IProject project);

    void saveProjectConfiguration(ProjectConfiguration configuration);

    void deleteProjectConfiguration(IProject project);

    RunConfiguration loadRunConfiguration(ILaunchConfiguration configuration);

    TestRunConfiguration loadTestRunConfiguration(ILaunchConfiguration configuration);

    TestRunConfiguration loadTestRunConfiguration(BaseRunConfiguration configuration);

    RunConfiguration createDefaultRunConfiguration(BuildConfiguration configuration);
}