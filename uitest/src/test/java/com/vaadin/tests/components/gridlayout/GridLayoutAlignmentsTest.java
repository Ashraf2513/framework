/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.components.gridlayout;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Point;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.GridLayoutElement;
import com.vaadin.testbench.elements.NativeButtonElement;
import com.vaadin.tests.tb3.MultiBrowserTest;

public class GridLayoutAlignmentsTest extends MultiBrowserTest {

    private NativeButtonElement targetButton;
    private Point gridLayoutLocation;

    private int middleY = 400 / 2;
    private int middleX = middleY;
    private int bottomX = 400;
    private int bottomY = bottomX;

    private int buttonWidth = 30;
    private int buttonHeight = 30;

    @Override
    protected boolean requireWindowFocusForIE() {
        return true;
    }

    @Test
    public void setAlignment() {
        openTestURL();

        targetButton = $(NativeButtonElement.class).first();
        GridLayoutElement gridLayout = $(GridLayoutElement.class).first();
        gridLayoutLocation = gridLayout.getLocation();
        assertOffset(middleX, middleY);

        $(ButtonElement.class).caption("Align top, left").first().click();
        assertOffset(0, 0);
        $(ButtonElement.class).caption("Align middle, left").first().click();
        assertOffset(0, middleY);
        $(ButtonElement.class).caption("Align bottom, left").first().click();
        assertOffset(0, bottomY);

        $(ButtonElement.class).caption("Align top, center").first().click();
        assertOffset(middleX, 0);
        $(ButtonElement.class).caption("Align middle, center").first().click();
        assertOffset(middleX, middleY);
        $(ButtonElement.class).caption("Align bottom, center").first().click();
        assertOffset(middleX, bottomY);

        $(ButtonElement.class).caption("Align top, right").first().click();
        assertOffset(bottomX, 0);
        $(ButtonElement.class).caption("Align middle, right").first().click();
        assertOffset(bottomX, middleY);
        $(ButtonElement.class).caption("Align bottom, right").first().click();
        assertOffset(bottomX, bottomY);
    }

    private void assertOffset(int x, int y) {
        Point location = targetButton.getLocation();
        int offsetX = location.x - gridLayoutLocation.x + buttonWidth / 2;
        int offsetY = location.y - gridLayoutLocation.y + buttonHeight / 2;

        // Border: 2px
        x += 2;
        y += 2;

        Assert.assertEquals("X offset incorrect", x, offsetX);
        Assert.assertEquals("Y offset incorrect", y, offsetY);

    }
}
