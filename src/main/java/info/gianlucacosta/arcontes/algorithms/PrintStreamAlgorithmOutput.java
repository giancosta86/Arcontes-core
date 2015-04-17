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

package info.gianlucacosta.arcontes.algorithms;

import java.io.PrintStream;

/**
 * Implementation of AlgorithmOutput that writes to a PrintStream (defaulting to
 * System.out)
 */
public class PrintStreamAlgorithmOutput extends AbstractAlgorithmOutput {

    private final PrintStream printStream;

    public PrintStreamAlgorithmOutput() {
        this(System.out);
    }

    public PrintStreamAlgorithmOutput(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(Object output) {
        printStream.print(output);
    }

    @Override
    public void println() {
        printStream.println();
    }

    @Override
    public void println(Object output) {
        printStream.println(output);
    }

}
