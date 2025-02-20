Calculator Application

Overview

This is a simple calculator application built using Java Swing and AWT. It provides basic arithmetic operations, a clear button, a backspace button, and evaluates expressions using a stack-based approach.

Features

Basic Arithmetic Operations: Addition, Subtraction, Multiplication, Division, and Modulus.

Expression Evaluation: Handles multi-step calculations using operator precedence.

Clear Button (C): Clears the entire expression.

Backspace Button (⌫): Deletes the last entered character.

Decimal Support: Allows floating-point calculations.

Error Handling: Displays "Error" for invalid expressions.

Installation

Ensure you have Java installed on your system.

Clone this repository:

git clone https://github.com/yourusername/calculator.git

Navigate to the project directory:

cd calculator

Compile and run the Java file:

javac eleven.java
java eleven

Usage

Click on the buttons to enter numbers and operators.

Press = to evaluate the expression.

Use C to clear the screen and ⌫ to delete the last character.

The result will be displayed in the text field.

Code Structure

eleven.java - Main class that initializes the calculator UI and handles user input.

evaluateExpression - Parses and evaluates mathematical expressions using stacks.

ButtonClickListener - Handles button clicks and updates the display accordingly.

Contributing

Feel free to contribute by forking the repository and submitting pull requests.

License

This project is open-source and available under the MIT License.

Author: Akshat Gupta

For any issues or feature requests, please open an issue on GitHub.

