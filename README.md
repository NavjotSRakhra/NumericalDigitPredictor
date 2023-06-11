# Digit Predictor - A Neural Network based A.I.

<!-- TOC -->

* [Digit Predictor - A Neural Network based A.I.](#digit-predictor---a-neural-network-based-ai)
    * [Introduction](#introduction)
    * [Preview](#preview)
    * [Project setup](#project-setup)
    * [Project usage](#project-usage)
    * [License](#license)

<!-- TOC -->

## Introduction

This project is based on the Neural Network I implemented. The
neural network is trained on *MNIST* dataset. Its dimensions are
*input layer: 784, hidden layer: 800, output layer: 10.* The
accuracy of neural network is above **97%**

## Preview

![](resources/Preview.gif)

## Project setup

The project is built upon OpenJDK 20 and should be compatible with
Java 20+.

## Project usage

To try this yourself, go to the directory that has `pom.xml` in
console and:

- Execute `mvn clean package` to run the code.
- A dialog
  box pops up and asks you to select the trained model, you can
  either provide your own trained model
  (`io.github.NavjotSRakhra.NeuralNetwork`) or use the ones which
  I have trained that are present in the resources folder with the
  extension `.nnn`

- You have to click/drag to paint the canvas/drawing area.
- To clean the canvas press the keyboard key **R.**

## License

MIT License

Copyright (c) 2023 Navjot Singh Rakhra

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
