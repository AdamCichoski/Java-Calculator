package calculatorGUI;

import java.util.ArrayList;

public class Operator {
	Operator() {
	}
	double readArray(ArrayList<Double> numbers, ArrayList<Character> functions) {
		double answer =numbers.get(0);
		for (int i = 0; numbers.size()>1; numbers.remove(1), i++) {
			
			switch (functions.get(i)) {
			case '+':
				answer+= numbers.get(1);
				break;
			case '-':
				answer-=numbers.get(1);
				break;
			case '*':
				answer*=numbers.get(1);
				numbers.set(0, answer);
				break;
			case '/':
				answer/=numbers.get(1);
				numbers.set(0, answer);
				break;
			case '^':
				answer = Math.pow(answer,numbers.get(1));
				numbers.set(0, answer);
				break;
			}
			numbers.set(0, answer);
		}
		return answer;
	}
	double addition(double x, double y) {
		return x+y;
	}
	double subtraction() {
		return 5;
	}
}
