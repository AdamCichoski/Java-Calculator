package calculatorGUI;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculator implements ActionListener {

	ArrayList<Double> numberValues = new ArrayList<Double>();
	static ArrayList<Double> tempNumberValues = new ArrayList<Double>();
	static ArrayList<Character> tempOperatorValues = new ArrayList<Character>();
	String pi = "\u03c0";
	JFrame frame;
	JTextField textfield; // Declares a JTextField
	JButton[] numberButtons = new JButton[10]; // Declares an array for the numbered buttons
	JButton[] functionButtons = new JButton[18];// Declares an array for the action buttons
	JButton[] trigButtons = new JButton[14];// Declares a JButton array for the trig functions
	JButton addButton, subButton, mulButton, divButton; // Creates names for the action buttons
	JButton decButton, equButton, delButton, clrButton, negButton, ansButton;// Creates more names for the action
																				// buttons
	JButton sinButton, cosButton, tanButton, secButton, cscButton, cotButton;
	JButton arcsin, arccos, arctan, arcsec, arccsc, arccot, degreeButton, radButton;
	JButton piButton, sqrButton, sqrtButton, leftParaButton, rightParaButton;
	JButton backButton, forwardButton, clearAllButton;
	JPanel panel;// Declares a JPanel
	JPanel panel2;

	Font myFont = new Font("Times New Roman", Font.BOLD, 30);
	Font font2 = new Font("Times New Roman", Font.BOLD, 30);
	
	
	// Initializing and declaring a number of different variables
	double num1 = 0, answer = 0;;
	char operator = '!';
	String trigOperator = "";
	String readLine, tempLine;
	static int maxPosition = 0;
	static int clickCount = 0;

	//Creating the calculator object
	public Calculator() {
		frame = new JFrame("Calculator");// Creates the name for the JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// The default way to close the frame and program
		frame.setSize(1300, 550);
		frame.setLayout(null);
		
		//Creating the textfield and setting boundaries
		textfield = new JTextField();
		textfield.setBounds(50, 25, 905, 50);// Parameters are the x (50), the y (25), the width (875), and the height
												// (50)
		textfield.setFont(myFont);
		textfield.setEditable(false);

		// These next lines create what is shown by the function buttons that were names
		// above (addButton, subButton, equButton, etc...)
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Delete");
		clrButton = new JButton("Clear");
		negButton = new JButton("(-)");
		piButton = new JButton(pi);
		sqrButton = new JButton("^");
		sqrtButton = new JButton("sqrt");
		leftParaButton = new JButton("(");
		rightParaButton = new JButton(")");
		degreeButton = new JButton("Deg");
		ansButton = new JButton("ANS");
		radButton = new JButton("Rad");
		backButton = new JButton("Back");
		forwardButton = new JButton("Next");
		clearAllButton = new JButton("CA");

		// These lines create the graphics for the trig buttons
		sinButton = new JButton("sin");
		cosButton = new JButton("cos");
		tanButton = new JButton("tan");
		secButton = new JButton("sec");
		cscButton = new JButton("csc");
		cotButton = new JButton("cot");
		arcsin = new JButton("arcsin");
		arccos = new JButton("arccos");
		arctan = new JButton("arctan");
		arcsec = new JButton("arcsec");
		arccsc = new JButton("arccsc");
		arccot = new JButton("arccot");

		// initalizes the values for each index of the functionButtons array
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		functionButtons[9] = piButton;
		functionButtons[10] = sqrButton;
		functionButtons[11] = sqrtButton;
		functionButtons[12] = leftParaButton;
		functionButtons[13] = rightParaButton;
		functionButtons[14] = ansButton;
		functionButtons[15] = backButton;
		functionButtons[16] = forwardButton;
		functionButtons[17] = clearAllButton;

		// initalizes the values for each index for the trig buttons array
		trigButtons[0] = sinButton;
		trigButtons[1] = cosButton;
		trigButtons[2] = tanButton;
		trigButtons[3] = secButton;
		trigButtons[4] = cscButton;
		trigButtons[5] = cotButton;
		trigButtons[6] = arcsin;
		trigButtons[7] = arccos;
		trigButtons[8] = arctan;
		trigButtons[9] = arcsec;
		trigButtons[10] = arccsc;
		trigButtons[11] = arccot;
		trigButtons[12] = degreeButton;
		trigButtons[13] = radButton;

		for (int i = 0; i < 18; i++) {// this for loop is for the function buttons
			functionButtons[i].addActionListener(this);// makes it so that whenever the user makes an action, the
														// program is alerted about the action
			functionButtons[i].setFont(myFont);// sets the font
			functionButtons[i].setFocusable(false);// makes it so that when you click on a button there is not an
													// outline around it
		}

		for (int i = 0; i < 10; i++) {// this for loop is for the number buttons
			numberButtons[i] = new JButton(String.valueOf(i));// sets the numberButton array index of i to the value of
																// i
			numberButtons[i].addActionListener(this);// makes it so that whenever the user makes an action, the program
														// is alerted about the action
			numberButtons[i].setFont(myFont);// sets the font
			numberButtons[i].setFocusable(false);// makes it so that when you click on a button there is not an outline
													// around it
		}
		for (int i = 0; i < 14; i++) {
			trigButtons[i].addActionListener(this);// makes it so that whenever the user makes an action, the program is
													// alerted about the action
			trigButtons[i].setFont(font2);// sets the font
			trigButtons[i].setFocusable(false);// makes it so that when you click on a button there is not an outline
												// around it
		}

		// Creates the bounds and displacement of the negative, clear, and delete buttons
		delButton.setBounds(50, 430, 150, 50);
		clrButton.setBounds(200, 430, 150, 50);
		leftParaButton.setBounds(360, 430, 70, 50);
		rightParaButton.setBounds(430, 430, 70, 50);
		degreeButton.setBounds(515, 430, 140, 50);
		radButton.setBounds(667, 430, 140, 50);
		ansButton.setBounds(820, 430, 140, 50);
		backButton.setBounds(970, 430, 120, 50);
		forwardButton.setBounds(1090, 430, 120, 50);
		clearAllButton.setBounds(970, 101, 140, 67);

		// Creates the grid area for the number and function buttons, with the exception
		// of the delete, clear, and negative buttons
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		panel2 = new JPanel();
		panel2.setBounds(360, 100, 600, 300);
		panel2.setLayout(new GridLayout(4, 7, 10, 10));

		// This sections fills in the spaces for the gridLayout that holds the numbers and some action buttons. It is the left side panel
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		// This section fills in the spaces for the panel2 grid layout, which holds mostly trig functions and is on the right side
		panel2.add(negButton);
		panel2.add(piButton);
		panel2.add(sqrButton);
		panel2.add(sqrtButton);
		for (int i = 0; i < 12; i++) {
			panel2.add(trigButtons[i]);
		}

		// This is adding everything to the frame, including grid layouts, individual buttons, and the text box
		frame.add(panel);
		frame.add(panel2);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);
		frame.add(leftParaButton);
		frame.add(rightParaButton);
		frame.add(degreeButton);
		frame.add(ansButton);
		frame.add(radButton);
		frame.add(backButton);
		frame.add(forwardButton);
		frame.add(clearAllButton);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		try {
			Calculator calc = new Calculator();
		} catch (Exception e1) {
			
		}
	}

	/**
	 * This class is used to set functions for any actions performed by the user
	 * through button clicking
	 * 
	 * @Override
	 **/
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		if (e.getSource() == decButton) {
			if (textfield.getText().indexOf('.')==-1) {
				textfield.setText(textfield.getText().concat("."));
				tempOperatorValues.add('.');
			}
			
		}
		if (e.getSource() == addButton) {
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			tempOperatorValues.add('+');
			operator = '+';
			textfield.setText("");
		}
		if (e.getSource() == subButton) {
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			tempOperatorValues.add('-');
			operator = '-';
			textfield.setText("");
		}
		if (e.getSource() == mulButton) {
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			tempOperatorValues.add('*');
			operator = '*';
			textfield.setText("");
		}
		if (e.getSource() == divButton) {
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			tempOperatorValues.add('/');
			operator = '/';
			textfield.setText("");
		}

		if (e.getSource() == piButton) {
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			answer =  Double.parseDouble(textfield.getText())* Math.PI;
			textfield.setText(String.valueOf(answer));
			
		}
		if (e.getSource() == sqrButton) {
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			tempOperatorValues.add('^');
			operator = '^';
			textfield.setText("");
		}
		if (e.getSource() == sqrtButton) {
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			answer = Math.sqrt(Double.parseDouble(textfield.getText()));
			textfield.setText(String.valueOf(answer));
			numberValues.add(Double.parseDouble(textfield.getText()));
			tempNumberValues.add(Double.parseDouble(textfield.getText()));
			
		}
		if (e.getSource() == leftParaButton) {
			tempLine = textfield.getText();
			textfield.setText(tempLine + "(");
		}
		if (e.getSource() == rightParaButton) {
			tempLine = textfield.getText();
			textfield.setText(tempLine + ")");
		}
		/*
		 * This large chunk of if statements looks at all of the trigonometric buttons, and sets the textfield to 
		 * that trig function
		 */
		if (e.getSource() == sinButton) {//checks for the sin button
			textfield.setText("sin(");
			trigOperator = "sin";
		}
		if (e.getSource() == cosButton) {//checks for the cos button
			textfield.setText("cos(");
			trigOperator = "cos";
		}
		if (e.getSource() == tanButton) {//checks for the tan button
			textfield.setText("tan(");
			trigOperator = "tan";
		}
		if (e.getSource() == secButton) {//checks for the sec button
			textfield.setText("sec(");
			trigOperator = "sec";
		}
		if (e.getSource() == cscButton) {//checks for the csc button
			textfield.setText("csc(");
			trigOperator = "csc";
		}
		if (e.getSource() == cotButton) {//checks for the cot button
			textfield.setText("cot(");
			trigOperator = "tan";
		}
		if (e.getSource() == arcsin) {//checks for the arcsin button
			textfield.setText("arcsin(");
			trigOperator = "arcsin";
		}
		if (e.getSource() == arccos) {//checks for the arccos button
			textfield.setText("arccos(");
			trigOperator = "arccos";
		}
		if (e.getSource() == arctan) {//checks for the arctan button
			textfield.setText("arctan(");
			trigOperator = "arctan";
		}
		if (e.getSource() == arcsec) {//checks for the arcsec button
			textfield.setText("arcsec(");
			trigOperator = "arcsec";
		}
		if (e.getSource() == arccsc) {//checks for the arccsc button
			textfield.setText("arccsc(");
			trigOperator = "arccsc";
		}
		if (e.getSource() == arccot) {//checks for the arccot button
			textfield.setText("arccot(");
			trigOperator = "arccot";
		}
		if (e.getSource() == degreeButton) {//This if statement takes the input in the textfield, and then parses it to a double, and then sends it to the degreeCalculator method 
			num1 = Double.parseDouble(textfield.getText());
			answer = degreeCalculator(num1);
			textfield.setText(String.valueOf(answer));
		}
		if (e.getSource() == radButton) {//This if statement takes the input in the textfield, and then parses it to a double, and then sends it to the radCalculator method 
			num1 = Double.parseDouble(textfield.getText());
			answer = radCalculator(num1);
			textfield.setText(String.valueOf(answer));
		}
		if (e.getSource() == clrButton) { //This is the basic clear button to just clear the temporary arrayLists, and sets the text field to being blank
			textfield.setText("");
			clearArrayList(tempNumberValues);
			clearOperatorArray(tempOperatorValues);
		}
		if (e.getSource() == delButton) {//This if statement acts for the delete button, giving functionality to the button
			String string = textfield.getText();
			textfield.setText("");
			for (int i = 0; i < string.length() - 1; i++) {
				textfield.setText(textfield.getText() + string.charAt(i));
			}
		}
		if (e.getSource() == ansButton) {//This button will allow the user to display whatever the most recent answer was
			tempLine = textfield.getText();
			textfield.setText(tempLine + String.valueOf(answer));
		}
		if (e.getSource() == negButton) { // This if statement gives functionality to the negative button, making it so that any number in the text box will become a negative
			double temp = Double.parseDouble(textfield.getText());// takes whatever value is in the text field and assigns it to the temp variable
			temp *= -1;
			textfield.setText(String.valueOf(temp));
		}
		if (e.getSource() == backButton) { //This if statement gives functionality to the back button
			maxPosition = numberValues.size() - 1;
			if (maxPosition != 0 && clickCount < maxPosition) {
				clickCount++;
				textfield.setText(String.valueOf(numberValues.get(maxPosition - clickCount)));
			}
		}
		if (e.getSource() == forwardButton) {//This if statement gives functionality to the forward button, which is labeled "Next" in the actual frame
			maxPosition = numberValues.size() - 1;
			if (maxPosition!= 0 && clickCount!=0) {
				textfield.setText(String.valueOf(numberValues.get(maxPosition - clickCount +1)));
				clickCount--;
			}
		}
		if (e.getSource() == clearAllButton) {//This if statement works with the clear all button to clear all of the arrays and set all other variables to their standard settings for this program
			textfield.setText("");
			num1 = 0;
			answer = 0;
			operator='!';
			maxPosition = 0;
			clearArrayList(numberValues);
			clearArrayList(tempNumberValues);
			clearOperatorArray(tempOperatorValues);
		}
		
	/*
	 * This big chunk here is an if and an elif statement that makes the determination for what should be done when the equal sign button is pressed
	 * This first half looks at the trigonometry equations
	 */
	if (e.getSource() == equButton && trigOperator != "" && textfield.getText()!="") {
		readLine = textfield.getText();
		num1 = grabNumber(readLine);
		switch (trigOperator) {
		case "sin":
			answer = Math.sin(num1);
			break;
		case "cos":
			answer = Math.cos(num1);
			break;
		case "tan":
			answer = Math.tan(num1);
			break;
		case "sec":
			answer = 1/Math.cos(num1);
			break;
		case "csc":
			answer = 1/Math.sin(num1);
			break;
		case "cot":
			answer = 1/Math.tan(num1);
			break;
		case "arcsin":
			answer = Math.asin(num1);
			break;
		case "arccos":
			answer = Math.acos(num1);
			break;
		case "arctan": 
			answer = Math.atan(num1);
			break;
		}
		clearArrayList(tempNumberValues);
		textfield.setText(String.valueOf(answer));
		numberValues.add((double)answer);
		trigOperator = "";
	/*
	 * This else-if statement takes a look at all other inputs that are not trigonometric, and uses an object from the 
	 * Operator class that has been built for this program
	 * After each run of the loop, the tempNumberValues array list will get smaller, setting index 0 to the answer of the operation between index 0 and 1
	 */
	} else if (e.getSource() == equButton && operator!='!' && textfield.getText()!="") {
		tempNumberValues.add(Double.parseDouble(textfield.getText()));//This line of code retrieves whatever is currently in the textfield, which in this case is going to be the final inputed number
		numberValues.add(Double.parseDouble(textfield.getText()));// This does the same thing as the line before, but this array will not be emptied out after the equals sign operation is completed
		double tempAnswer= tempNumberValues.get(0); //This sets the double variable tempAnswer to the first number (index 0) of the tempNumberValues array list
		for (int i = 0; tempNumberValues.size()>1; tempNumberValues.remove(1), i++) {
			switch (tempOperatorValues.get(i)) {
			case '+':
				tempAnswer+= tempNumberValues.get(1);
				break;
			case '-':
				tempAnswer-=tempNumberValues.get(1);
				break;
			case '*':
				tempAnswer*=tempNumberValues.get(1);
				break;
			case '/':
				tempAnswer/=tempNumberValues.get(1);
				break;
			case '^':
				tempAnswer = Math.pow(answer,tempNumberValues.get(1));
				break;
			}
			tempNumberValues.set(0, tempAnswer);
		}
		answer = tempNumberValues.get(0);
		textfield.setText(String.valueOf(answer));
		numberValues.add((double)answer);
		clearArrayList(tempNumberValues);
		clearOperatorArray(tempOperatorValues);
		}
	}

	/**
	 * This calculates a number as a degree. It is meant to start with a radian number to get the degree number of that radian
	 * @param num1 is the double radian value
	 * @return returns the degree version of that radian number
	 */
	public static double degreeCalculator(double num1) {
		num1 *= 180 / Math.PI;
		return num1;
	}
	/**
	 * This is a radian calculator to get the radian format of a degree number
	 * @param num1 is the double degree value
	 * @return returns the radian version of that degree number
	 */
	public static double radCalculator(double num1) {
		num1 *= Math.PI / 180;
		return num1;
	}
	/**
	 * This method clears the array lists that hold double values, removing all indexes in the array
	 * @param arr is the double array list variable name
	 */
	public static void clearArrayList(ArrayList<Double> arr) {
		maxPosition = arr.size()-1;
		for (int i = maxPosition; i >= 0; i--) {
				arr.remove(i);
			}
	}
	/**
	 * This method clears array lists that hold the char type by removing all indexes
	 * @param arr is the variable name for the array list
	 */
	public static void clearOperatorArray(ArrayList<Character> arr) {
		maxPosition = arr.size()-1;
		for (int i = maxPosition; i >= 0; i--) {
				arr.remove(i);
			}
	}
	/**
	 * This method retrieves whatever number is inside of the parentheses for an equation
	 * @param trig is the name of the string that is in the text box
	 * @return returns the 
	 */
	public static double grabNumber(String trig) {
		double number = 0;
		int index1 = trig.indexOf('(') + 1;
		int index2 = trig.indexOf(')');
		trig = trig.substring(index1, index2);
		number = Double.parseDouble(trig);
		return number;
	}

}
