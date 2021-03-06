/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.base.density;

import gwt.material.design.client.base.helper.EnumHelper;

public enum DisplayDensity implements Density {

    /**
     * Standard or Default Density
     */
    DEFAULT(new DensityValue("", 55)),
    /**
     * Comfortable or Less Density
     */
    COMFORTABLE(new DensityValue("comfortable", 40)),
    /**
     * Compact or High Density
     */
    COMPACT(new DensityValue("compact", 20));

    private final DensityValue densityValue;

    DisplayDensity(DensityValue densityValue) {
        this.densityValue = densityValue;
    }

    public static DisplayDensity fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, DisplayDensity.class, DEFAULT);
    }

    @Override
    public int getValue() {
        return densityValue.getValue();
    }

    @Override
    public String getCssName() {
        return densityValue.getCssName();
    }
}
