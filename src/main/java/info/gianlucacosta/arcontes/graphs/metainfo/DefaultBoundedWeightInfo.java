/*§
  ===========================================================================
  Arcontes - Core
  ===========================================================================
  Copyright (C) 2013-2015 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.arcontes.graphs.metainfo;

import info.gianlucacosta.arcontes.formatting.CommonDecimalFormat;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Implementation of BoundedWeightInfo
 */
public class DefaultBoundedWeightInfo<TWeight extends Comparable<TWeight>> extends DefaultWeightInfo<TWeight> implements BoundedWeightInfo<TWeight> {

    private final TWeight capacity;

    public DefaultBoundedWeightInfo(TWeight weight, TWeight capacity) {
        super(weight);

        if (capacity.compareTo(weight) < 0) {
            throw new IllegalArgumentException("It must be weight <= capacity");
        }

        this.capacity = capacity;
    }

    @Override
    public TWeight getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoundedWeightInfo) {
            BoundedWeightInfo<TWeight> other = (BoundedWeightInfo<TWeight>) obj;
            return super.equals(obj)
                    && Objects.equals(capacity, other.getCapacity());
        } else if (obj instanceof WeightInfo) {
            return super.equals(obj);
        } else if (obj instanceof CapacityInfo) {
            CapacityInfo<TWeight> other = (CapacityInfo<TWeight>) obj;
            return Objects.equals(capacity, other.getCapacity());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = CommonDecimalFormat.getDecimalFormat();
        return String.format("%s / %s", decimalFormat.format(getWeight()), decimalFormat.format(getCapacity()));
    }

}
