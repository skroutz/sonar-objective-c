/*
 * Objective-C Sonar Plugin - Enables analysis of Objective-C projects into SonarQube.
 * Copyright © 2012 OCTO Technology, Backelite (${email})
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sonar.plugins.objectivec.violations.fauxpas;

import org.junit.Before;
import org.junit.Test;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.plugins.objectivec.core.ObjectiveC;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by gillesgrousset on 12/02/15.
 */
public class FauxPasSensorTest {

    private Settings settings;

    @Before
    public void setUp() {
        settings = mock(Settings.class);
    }

    @Test
    public void shouldExecuteOnProjectShouldBeTrueWhenProjectIsObjc() {
        final Project project = new Project("Test");

        FileSystem fileSystem = mock(FileSystem.class);
        ResourcePerspectives resourcePerspectives = mock(ResourcePerspectives.class);
        SortedSet<String> languages = new TreeSet<String>();
        languages.add(ObjectiveC.KEY);
        when(fileSystem.languages()).thenReturn(languages);

        final FauxPasSensor testedSensor = new FauxPasSensor(fileSystem, settings, resourcePerspectives);

        assertTrue(testedSensor.shouldExecuteOnProject(project));
    }

    @Test
    public void shouldExecuteOnProjectShouldBeFalseWhenProjectIsSomethingElse() {
        final Project project = new Project("Test");

        FileSystem fileSystem = mock(FileSystem.class);
        ResourcePerspectives resourcePerspectives = mock(ResourcePerspectives.class);
        SortedSet<String> languages = new TreeSet<String>();
        languages.add("Test");
        when(fileSystem.languages()).thenReturn(languages);

        final FauxPasSensor testedSensor = new FauxPasSensor(fileSystem, settings, resourcePerspectives);

        assertFalse(testedSensor.shouldExecuteOnProject(project));
    }
}
