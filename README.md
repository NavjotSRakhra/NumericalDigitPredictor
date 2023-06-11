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