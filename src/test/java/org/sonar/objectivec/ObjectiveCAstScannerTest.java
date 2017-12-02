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
package org.sonar.objectivec;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;
import org.sonar.objectivec.api.ObjectiveCMetric;
import org.sonar.squidbridge.api.SourceFile;

public class ObjectiveCAstScannerTest {

    @Test
    public void lines() {
        SourceFile file = ObjectiveCAstScanner.scanSingleFile(new File("src/test/resources/objcSample.h"));
        assertThat(file.getInt(ObjectiveCMetric.LINES), is(18));
    }

    @Test
    public void lines_of_code() {
        SourceFile file = ObjectiveCAstScanner.scanSingleFile(new File("src/test/resources/objcSample.h"));
        assertThat(file.getInt(ObjectiveCMetric.LINES_OF_CODE), is(5));
    }

    @Test
    public void comments() {
        SourceFile file = ObjectiveCAstScanner.scanSingleFile(new File("src/test/resources/objcSample.h"));
        assertThat(file.getInt(ObjectiveCMetric.COMMENT_LINES), is(4));
        assertThat(file.getNoSonarTagLines(), hasItem(10));
        assertThat(file.getNoSonarTagLines().size(), is(1));
    }

}
